package com.konate.music_application.AdService.BusinessLayer;

import com.konate.music_application.AdService.DataLayer.*;
import com.konate.music_application.AdService.MappingLayer.AdCampaignRequestMapper;
import com.konate.music_application.AdService.MappingLayer.AdCampaignResponseMapper;
import com.konate.music_application.AdService.PresentationLayer.AdCampaignController;
import com.konate.music_application.AdService.PresentationLayer.AdRequestModel;
import com.konate.music_application.AdService.PresentationLayer.AdResponseModel;
import com.konate.music_application.ArtistService.BusinessLayer.ArtistService;
import com.konate.music_application.ArtistService.Presentation.ArtistResponseModel;
import com.konate.music_application.Exceptions.InvalidAdTargetException;
import com.konate.music_application.Exceptions.InvalidInputException;
import com.konate.music_application.Exceptions.NotFoundException;
import com.konate.music_application.PodcastService.BusinessLayer.Podcast.PodcastService;
import com.konate.music_application.PodcastService.PresentationLayer.Podcast.PodcastResponseModel;
import com.konate.music_application.UserService.BusinessLayer.UserService;
import com.konate.music_application.UserService.DataLayer.UserIdentifier;
import com.konate.music_application.UserService.PresentationLayer.UserResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AdCampaignServiceImpl implements AdCampaignService {
    private final AdRepository repository;

    private final AdCampaignRequestMapper requestMapper;
    private final AdCampaignResponseMapper responseMapper;

    private final PodcastService podcastService;
    private final ArtistService artistService;
    private final UserService userService;

    public AdCampaignServiceImpl(AdRepository repository, AdCampaignRequestMapper requestMapper, AdCampaignResponseMapper responseMapper, PodcastService podcastService, ArtistService artistService, UserService userService) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.podcastService = podcastService;
        this.artistService = artistService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public AdResponseModel createAdCampaign(AdRequestModel adRequestModel) {
        if (adRequestModel == null)
            throw new InvalidInputException("the ad cannot be null");
        if (adRequestModel.getAdTarget() == null) {
            throw new InvalidAdTargetException("Ad must target either an artist or a podcast");
        }

        if (adRequestModel.getAdTarget().getTargetType() == AdTargetType.ARTIST && adRequestModel.getAdTarget().getTargetId() == null) {
            throw new InvalidAdTargetException("Artist ad must have artistId");
        }

        if (adRequestModel.getAdTarget().getTargetType() == AdTargetType.PODCAST && adRequestModel.getAdTarget().getTargetId() == null) {
            throw new InvalidAdTargetException("Podcast ad must have podcastId");
        }
        UserResponseModel user = userService.getUserByEmail(adRequestModel.getUserEmail());

        if (adRequestModel.getTargetingRules() == null)
            throw new InvalidInputException("the target rule cannot be null.");

        List<TargetingRules> targetingRules = new ArrayList<>();
        for (TargetingRules target : adRequestModel.getTargetingRules()) {

            targetingRules.add(new TargetingRules(
                    target.getTargetGenre(), target.getTargetRegion(), target.getMinAge()
            ));
        }
        if (adRequestModel.getCreatives() == null)
            throw new InvalidInputException("the ad creative cannot be null.");

        List<AdCreative> creatives = new ArrayList<>();
        for (AdCreative adCreative : adRequestModel.getCreatives()) {
            creatives.add(new AdCreative(
                    adCreative.getMediaUrl(), adCreative.getCreativeType()
            ));
        }
        if (adRequestModel.getBudget().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Campaign budget must be greater than zero.");
        }
//        if (adRequestModel.getBudget().compareTo( adRequestModel.getRemainingSpend()))
//            throw new InvalidInputException("Sorry but the budget must be greater than the remaining budget.");

        AdCampaign newAd = requestMapper.toAdCampaign(
                adRequestModel,
                new AdIdentifier(),
                new UserIdentifier(user.getUserId())
        );

        newAd.setTargetingRules(targetingRules);
        newAd.setCreatives(creatives);
        newAd.setStatus(CampaignStatus.ACTIVE);
//        if (adRequestModel.getBudget().compareTo(BigDecimal.ZERO) <= 0) {
//            throw new InvalidInputException("Campaign budget must be greater than zero.");
//        }

//        AdCampaign newAd = requestMapper.toAdCampaign(
//                adRequestModel,
//                new AdIdentifier(),
//                new UserIdentifier(user.getUserId())
//        );

        newAd.setRemainingSpend(adRequestModel.getBudget());

        AdCampaign savedCampaign = repository.save(newAd);
        AdResponseModel response = responseMapper.toAdResponseModel(savedCampaign);
        response.setUserName(user.getFullname());

        enrichResponseModel(response, newAd);

        response.add(linkTo(methodOn(AdCampaignController.class)
                .getAdCampaign(savedCampaign.getAdIdentifier().getAdId())).withSelfRel());
        return response;
    }

    @Override
    @Transactional
    public AdResponseModel updateAdCampaign(String adId, AdRequestModel adRequestModel) {
        AdCampaign campaign = repository.findAllByAdIdentifier_AdId(adId);
        if (campaign == null)
            throw new NotFoundException("the ad campaign is not found at this id: " + adId);
        if (campaign.getAdTarget() == null) {
            throw new InvalidAdTargetException("Ad must target either an artist or a podcast");
        }
        if (campaign.getAdTarget().getTargetType() == AdTargetType.ARTIST && campaign.getAdTarget().getTargetId() == null) {
            throw new InvalidAdTargetException("Artist ad must have artistId");
        }
        if (campaign.getAdTarget().getTargetType() == AdTargetType.PODCAST && campaign.getAdTarget().getTargetId() == null) {
            throw new InvalidAdTargetException("Podcast ad must have podcastId");
        }

        campaign.setStatus(adRequestModel.getStatus());
        campaign.setName(adRequestModel.getName());
        campaign.setCreatives(adRequestModel.getCreatives());
        campaign.setAdTarget(adRequestModel.getAdTarget());
//        for (AdCreative creative: adRequestModel.getCreatives()){
//            campaign.setCreatives(creative);
//        }
//        campaign.setBudget(adRequestModel.getBudget());
//        campaign.spend(adRequestModel.getBudget());
        if (adRequestModel.getBudget() != null) {
            BigDecimal oldBudget = campaign.getBudget();
            BigDecimal newBudget = adRequestModel.getBudget();

            BigDecimal newRemaining = getBigDecimal(newBudget, oldBudget, campaign);

            campaign.setBudget(newBudget);
            campaign.setRemainingSpend(newRemaining);
        }

        campaign.setRemainingSpend(adRequestModel.getRemainingSpend());
        campaign.setTargetingRules(adRequestModel.getTargetingRules());

        AdCampaign updated = repository.save(campaign);


        UserResponseModel user = userService.getUserById(updated.getUserIdentifier().getUserId());
        AdResponseModel response = responseMapper.toAdResponseModel(updated);
        response.setUserName(user.getUsername());

//        if (campaign.getAdTarget().getTargetType() == AdTargetType.ARTIST) {
//            ArtistResponseModel artist = artistService.getArtistById(campaign.getAdTarget().getTargetId());
//            response.setTargetHostnameOrArtist(artist.getFirstName() + " " + artist.getLastName());
//            response.setTargetName("Artist");
//        } else if (campaign.getAdTarget().getTargetType() == AdTargetType.PODCAST) {
//            PodcastResponseModel podcast = podcastService.getPodcastById(campaign.getAdTarget().getTargetId());
//            response.setTargetName(podcast.getTitle());
//            response.setTargetHostnameOrArtist(podcast.getHostname());
//        }

        enrichResponseModel(response, campaign);

        response.add(linkTo(methodOn(AdCampaignController.class)
                .getAdCampaign(updated.getAdIdentifier().getAdId())).withSelfRel());
        return response;
    }

    private static BigDecimal getBigDecimal(BigDecimal newBudget, BigDecimal oldBudget, AdCampaign campaign) {
        if (newBudget.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Budget must be positive");
        }

        // Calculate the difference and add it to remaining spend
        BigDecimal budgetDifference = newBudget.subtract(oldBudget);
        BigDecimal newRemaining = campaign.getRemainingSpend().add(budgetDifference);

        // Safety check: Don't allow remaining to be negative if budget was decreased
        if (newRemaining.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidInputException("New budget is too low; current spend already exceeds this amount.");
        }
        return newRemaining;
    }

    @Override
    public void deleteAdCampaign(String adId) {
        AdCampaign campaign = repository.findAllByAdIdentifier_AdId(adId);
        if (campaign == null)
            throw new NotFoundException("the ad campaign is not found at this id: " + adId);

        repository.delete(campaign);
    }

    @Override
    public AdResponseModel getAdCampaign(String adId) {
        AdCampaign campaign = repository.findAllByAdIdentifier_AdId(adId);
        if (campaign == null)
            throw new NotFoundException("the ad campaign is not found at this id: " + adId);

        UserResponseModel user = userService.getUserById(campaign.getUserIdentifier().getUserId());
        AdResponseModel responseModel = responseMapper.toAdResponseModel(campaign);

//        if (campaign.getAdTarget().getTargetType() == AdTargetType.ARTIST) {
//            ArtistResponseModel artist = artistService.getArtistById(campaign.getAdTarget().getTargetId());
//            responseModel.setTargetHostnameOrArtist(artist.getFirstName() + " " + artist.getLastName());
//            responseModel.setTargetName("Artist");
//        } else if (campaign.getAdTarget().getTargetType() == AdTargetType.PODCAST) {
//            PodcastResponseModel podcast = podcastService.getPodcastById(campaign.getAdTarget().getTargetId());
//            responseModel.setTargetName(podcast.getTitle());
//            responseModel.setTargetHostnameOrArtist(podcast.getHostname());
//
//        }

        enrichResponseModel(responseModel, campaign);
        responseModel.setUserName(user.getUsername());

        return responseModel;
    }

    @Override
    public List<AdResponseModel> getAllAdCampaigns() {
        List<AdCampaign> adCampaigns = repository.findAll();
        List<AdResponseModel> responseModels = new ArrayList<>();

        for (AdCampaign adCampaign : adCampaigns) {
            UserResponseModel user = userService.getUserById(adCampaign.getUserIdentifier().getUserId());

            AdResponseModel adResponseModel = responseMapper.toAdResponseModel(adCampaign);

//            if (adCampaign.getAdTarget().getTargetType() == AdTargetType.ARTIST) {
//                ArtistResponseModel artist = artistService.getArtistById(adCampaign.getAdTarget().getTargetId());
//                adResponseModel.setTargetHostnameOrArtist(artist.getFirstName() + " " + artist.getLastName());
//                adResponseModel.setTargetName("Artist");
//            } else if (adCampaign.getAdTarget().getTargetType() == AdTargetType.PODCAST) {
//                PodcastResponseModel podcast = podcastService.getPodcastById(adCampaign.getAdTarget().getTargetId());
//                adResponseModel.setTargetName(podcast.getTitle());
//                adResponseModel.setTargetHostnameOrArtist(podcast.getHostname());
//
//            }
            enrichResponseModel(adResponseModel,adCampaign);

            adResponseModel.setUserName(user.getUsername());

            adResponseModel.add(linkTo(methodOn(AdCampaignController.class)
                    .getAdCampaign(adCampaign.getAdIdentifier().getAdId())).withSelfRel());
            responseModels.add(adResponseModel);

        }


        return responseModels;
    }

    @Override
    public AdResponseModel getAdCampaignsByUser(String userEmail) {
        UserResponseModel user = userService.getUserByEmail(userEmail);
        AdCampaign ad = repository.findAllByAdIdentifier_AdId(user.getUserId());
        if (ad == null)
            throw new NotFoundException("the user " + user.getUsername() + " has no ad at this account.");

        AdResponseModel responseModel = responseMapper.toAdResponseModel(ad);

//        if (ad.getAdTarget().getTargetType() == AdTargetType.ARTIST) {
//            ArtistResponseModel artist = artistService.getArtistById(ad.getAdTarget().getTargetId());
//            responseModel.setTargetHostnameOrArtist(artist.getFirstName() + " " + artist.getLastName());
//            responseModel.setTargetName("Artist");
//        } else if (ad.getAdTarget().getTargetType() == AdTargetType.PODCAST) {
//            PodcastResponseModel podcast = podcastService.getPodcastById(ad.getAdTarget().getTargetId());
//            responseModel.setTargetName(podcast.getTitle());
//            responseModel.setTargetHostnameOrArtist(podcast.getHostname());
//        }
        enrichResponseModel(responseModel, ad);
        responseModel.setUserName(user.getUsername());
        return responseModel;
    }

//    @Override
//    public List<AdResponseModel> getAdCampaignsByArtist(String artistId) {
//        return List.of();
//    }
//
//    @Override
//    public List<AdResponseModel> getAdCampaignsByPodcast(String podcastId) {
//        return List.of();
//    }

    @Override
    @Transactional
    public void activateAdCampaign(String adId) {
        AdCampaign campaign = repository.findAllByAdIdentifier_AdId(adId);
        if (campaign == null)
            throw new NotFoundException("the ad campaign is not found at this id: " + adId);
        campaign.activate();
        repository.save(campaign);

    }

    @Override
    @Transactional
    public void pauseAdCampaign(String adId) {
        AdCampaign campaign = repository.findAllByAdIdentifier_AdId(adId);
        if (campaign == null)
            throw new NotFoundException("the ad campaign is not found at this id: " + adId);

        campaign.pause();
        repository.save(campaign);
    }

    @Override
    public void resumeAdCampaign(String adId) {
        AdCampaign campaign = repository.findAllByAdIdentifier_AdId(adId);
        if (campaign == null)
            throw new NotFoundException("the ad campaign is not found at this id: " + adId);

        campaign.resume();
        repository.save(campaign);
    }

    private void enrichResponseModel(AdResponseModel response, AdCampaign campaign) {
        UserResponseModel user = userService.getUserById(campaign.getUserIdentifier().getUserId());
        response.setUserName(user.getUsername());

        if (campaign.getAdTarget().getTargetType() == AdTargetType.ARTIST) {
            ArtistResponseModel artist = artistService.getArtistById(campaign.getAdTarget().getTargetId());
            response.setTargetHostnameOrArtist(artist.getFirstName() + " " + artist.getLastName());
            response.setTargetName("Artist");
        } else if (campaign.getAdTarget().getTargetType() == AdTargetType.PODCAST) {
            PodcastResponseModel podcast = podcastService.getPodcastById(campaign.getAdTarget().getTargetId());
            response.setTargetName(podcast.getTitle());
            response.setTargetHostnameOrArtist(podcast.getHostname());
        }
    }
}
