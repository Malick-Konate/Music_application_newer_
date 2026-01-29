package com.konate.music_application.PodcastService.BusinessLayer.Podcast;

import com.konate.music_application.PodcastService.DataLayer.Podcast.Podcast;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastIdentifier;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastRepository;
import com.konate.music_application.Exceptions.NotFoundException;
import com.konate.music_application.PodcastService.MappingLayer.Podcast.PodcastRequestMapper;
import com.konate.music_application.PodcastService.MappingLayer.Podcast.PodcastResponseMapper;
import com.konate.music_application.PodcastService.PresentationLayer.Podcast.PodcastRequestModel;
import com.konate.music_application.PodcastService.PresentationLayer.Podcast.PodcastResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodcastServiceImpl implements PodcastService{

    private final PodcastResponseMapper responseMapper;
    private final PodcastRequestMapper requestMapper;
    private final PodcastRepository podcastRepository;

    public PodcastServiceImpl(PodcastResponseMapper responseMapper, PodcastRequestMapper requestMapper, PodcastRepository podcastRepository) {
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.podcastRepository = podcastRepository;
    }

    @Override
    public List<PodcastResponseModel> getAllPodcast() {
        List<Podcast> podcasts = podcastRepository.findAll();
        return responseMapper.entityListToResponseModelList(podcasts);
    }

    @Override
    public PodcastResponseModel getPodcastById(String podcastId) {
        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);

        if (podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);

        return responseMapper.toResponseModel(podcast);
    }

    @Override
    public List<PodcastResponseModel> getPodcastByHosname(String hostname) {
        List<Podcast> podcast = podcastRepository.findAllByHostname(hostname);
        if (podcast == null)
            throw new NotFoundException("Podcast not found with hostname: " + hostname);

        return responseMapper.entityListToResponseModelList(podcast);
    }

    @Override
    public PodcastResponseModel getPodcastByTitle(String title) {
        Podcast podcast = podcastRepository.findAllByTitle(title);
        if (podcast == null)
            throw new NotFoundException("Podcast not found with title: " + title);

        return responseMapper.toResponseModel(podcast);
    }

    @Override
    public PodcastResponseModel createPodcast(PodcastRequestModel requestModel) {
        if(requestModel == null)
            throw new IllegalArgumentException("the request cannot be empty or null");

        Podcast podcastEntity = requestMapper.toPodcast(
                requestModel,
                new PodcastIdentifier()
        );
        Podcast savedPodcast = podcastRepository.save(podcastEntity);
        return responseMapper.toResponseModel(savedPodcast);
    }

    @Override
    public PodcastResponseModel updatePodcast(String podcastId, PodcastRequestModel requestModel) {
        Podcast podcastExisting = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(requestModel ==null)
            throw new NotFoundException("Podcast request cannot be null");

        podcastExisting.setDescription(requestModel.getDescription());
        podcastExisting.setTitle(requestModel.getTitle());
        podcastExisting.setHostname(requestModel.getHostname());
        podcastExisting.setPricingModel(requestModel.getPricingModel());

        Podcast updatedPodcast = podcastRepository.save(podcastExisting);
        return responseMapper.toResponseModel(updatedPodcast);
    }

    @Override
    public void deletePodcast(String podcastID) {
        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastID);

        if(podcast ==null)
            throw new NotFoundException("Podcast not found with Id: " + podcastID);

        podcastRepository.delete(podcast);
    }
}
