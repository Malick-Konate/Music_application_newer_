package com.konate.music_application.PodcastService.BusinessLayer.Episode;

import com.konate.music_application.Exceptions.InconsistentPodcastException;
import com.konate.music_application.PodcastService.DataLayer.Episode.Episode;
import com.konate.music_application.PodcastService.DataLayer.Episode.EpisodeIdentifier;
import com.konate.music_application.PodcastService.DataLayer.Episode.EpisodeRepository;
import com.konate.music_application.PodcastService.DataLayer.Episode.EpisodeStatus;
import com.konate.music_application.PodcastService.DataLayer.Podcast.Podcast;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastRepository;
import com.konate.music_application.Exceptions.InvalidInputException;
import com.konate.music_application.Exceptions.NotFoundException;
import com.konate.music_application.PodcastService.MappingLayer.Episode.EpisodeRequestMapper;
import com.konate.music_application.PodcastService.MappingLayer.Episode.EpisodeResponseMapper;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeRequestModel;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EpisodeServiceImpl implements EpisodeService{

    private final EpisodeResponseMapper responseMapper;
    private final EpisodeRequestMapper requestMapper;
    private final EpisodeRepository episodeRepository;
    private final PodcastRepository podcastRepository;

    public EpisodeServiceImpl(EpisodeResponseMapper responseMapper, EpisodeRequestMapper requestMapper,
                              EpisodeRepository episodeRepository, PodcastRepository podcastRepository) {
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
        this.episodeRepository = episodeRepository;
        this.podcastRepository = podcastRepository;
    }

    @Override
    public List<EpisodeResponseModel> getEpisodesByPodcastId(String podcastId) {
        if (podcastId.isBlank() || podcastId == null )
            throw new InvalidInputException("Sorry, cannot be null.");

        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        List<Episode> episodes = episodeRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        return responseMapper.entityListToResponseModelList(episodes);
    }

    @Override
    public EpisodeResponseModel createEpisode(String podcastId, EpisodeRequestModel requestModel) {
        if (podcastId.isBlank() || podcastId == null)
            throw new InvalidInputException("Sorry, cannot be null.");

        validateEpisodeInvariants(requestModel); // Run check


        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);

        // Check for existing episode title to avoid duplicates
        Episode byTitle = episodeRepository.findAllByEpisodeTitle(requestModel.getEpisodeTitle());
        if(byTitle != null) {
            throw new InvalidInputException("Episode with title '" + requestModel.getEpisodeTitle() + "' already exists.");
        }

        try {
            Episode episode = requestMapper.toEntity(requestModel,
                    new EpisodeIdentifier(), podcast.getPodcastIdentifier());
            Episode savedEpisode = episodeRepository.save(episode);
            return responseMapper.toResponseModel(savedEpisode);
        } catch (Exception e) {
            // Log the error and throw a custom exception instead of a generic 500
            throw new InvalidInputException("Could not create episode: " + e.getMessage());
        }
    }

    @Override
    public EpisodeResponseModel getEpisodeById(String podcastId, String episodeId) {
        if (podcastId.isBlank() || podcastId == null || episodeId.isBlank() || episodeId == null)
            throw new InvalidInputException("Sorry, cannot be null.");

        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        Episode episode = episodeRepository.findAllByEpisodeIdentifier_EpisodeId(episodeId);
        if (episode == null)
            throw new NotFoundException("Episode not found at: " + episodeId);
        if(!Objects.equals(podcast.getPodcastIdentifier().getPodcastId(), episode.getPodcastIdentifier().getPodcastId()))
            throw new NotFoundException("Episode: " + episodeId + " not found in the podcast: " + podcastId);
        return responseMapper.toResponseModel(episode);
    }

    @Override
    public EpisodeResponseModel updateEpisode(String podcastId, String episodeId, EpisodeRequestModel requestModel) {
        if (podcastId.isBlank() || podcastId == null || episodeId.isBlank() || episodeId == null)
            throw new InvalidInputException("Sorry, cannot be null.");
        validateEpisodeInvariants(requestModel); // Run check
        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        Episode episode = episodeRepository.findAllByEpisodeIdentifier_EpisodeId(episodeId);
        if (episode == null)
            throw new NotFoundException("Episode not found at: " + episodeId);
        if(!Objects.equals(podcast.getPodcastIdentifier().getPodcastId(), episode.getPodcastIdentifier().getPodcastId()))
            throw new NotFoundException("Episode: " + episodeId + " not found in the podcast: " + podcastId);

        episode.setEpisodeTitle(requestModel.getEpisodeTitle());
        episode.setDuration(requestModel.getDuration());
        episode.setPublishDate(requestModel.getPublishDate());
        episode.setStatus(requestModel.getStatus());

        Episode updatedEpisode = episodeRepository.save(episode);
        return responseMapper.toResponseModel(updatedEpisode);
    }

    @Override
    public void deleteEpisode(String episodeId, String podcastId) {
        if (podcastId.isBlank() || podcastId == null || episodeId.isBlank() || episodeId == null)
            throw new InvalidInputException("Sorry, cannot be null.");

        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        Episode episode = episodeRepository.findAllByEpisodeIdentifier_EpisodeId(episodeId);
        if (episode == null)
            throw new NotFoundException("Episode not found at: " + episodeId);
        if(!Objects.equals(podcast.getPodcastIdentifier().getPodcastId(), episode.getPodcastIdentifier().getPodcastId()))
            throw new NotFoundException("Episode: " + episodeId + " not found in the podcast: " + podcastId);

        episodeRepository.delete(episode);

    }

    private void validateEpisodeInvariants(EpisodeRequestModel request) {
        // 1. Duration Invariant
        if (request.getDuration() == null || request.getDuration().getTime() <= 0) {
            throw new InconsistentPodcastException("Episode duration must be greater than 0.");
        }

        // 2. Status vs Date Invariant
        if (request.getStatus() == EpisodeStatus.PUBLISHED && request.getPublishDate() == null) {
            throw new InconsistentPodcastException("A published episode must have a publish date.");
        }

        // 3. Prevent future dates for Published status
        if (request.getStatus() == EpisodeStatus.PUBLISHED && request.getPublishDate().after(new java.util.Date())) {
            throw new InconsistentPodcastException("Cannot set status to PUBLISHED for a future date.");
        }
    }
}
