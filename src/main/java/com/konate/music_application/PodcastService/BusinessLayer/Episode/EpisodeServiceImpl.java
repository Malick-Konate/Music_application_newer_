package com.konate.music_application.PodcastService.BusinessLayer.Episode;

import com.konate.music_application.PodcastService.DataLayer.Episode.Episode;
import com.konate.music_application.PodcastService.DataLayer.Episode.EpisodeIdentifier;
import com.konate.music_application.PodcastService.DataLayer.Episode.EpisodeRepository;
import com.konate.music_application.PodcastService.DataLayer.Podcast.Podcast;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastRepository;
import com.konate.music_application.PodcastService.Exceptions.NotFoundException;
import com.konate.music_application.PodcastService.MappingLayer.Episode.EpisodeRequestMapper;
import com.konate.music_application.PodcastService.MappingLayer.Episode.EpisodeResponseMapper;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeRequestModel;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        List<Episode> episodes = episodeRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        return responseMapper.entityListToResponseModelList(episodes);
    }

    @Override
    public EpisodeResponseModel createEpisode(String podcastId, EpisodeRequestModel requestModel) {
        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        Episode episode = requestMapper.toEntity(requestModel,
                new EpisodeIdentifier(), podcast.getPodcastIdentifier());
        Episode savedEpisode = episodeRepository.save(episode);

        return responseMapper.toResponseModel(savedEpisode);
    }

    @Override
    public EpisodeResponseModel getEpisodeById(String podcastId, String episodeId) {
        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        Episode episode = episodeRepository.findAllByEpisodeIdentifier_EpisodeId(episodeId);
        if (episode == null)
            throw new NotFoundException("Episode not found at: " + episodeId);
        return responseMapper.toResponseModel(episode);
    }

    @Override
    public EpisodeResponseModel updateEpisode(String podcastId, String episodeId, EpisodeRequestModel requestModel) {
        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        Episode episode = episodeRepository.findAllByEpisodeIdentifier_EpisodeId(episodeId);
        if (episode == null)
            throw new NotFoundException("Episode not found at: " + episodeId);
        episode.setEpisodeTitle(requestModel.getEpisodeTitle());
        episode.setDuration(requestModel.getDuration());
        episode.setPublishDate(requestModel.getPublishDate());
        episode.setStatus(requestModel.getStatus());

        Episode updatedEpisode = episodeRepository.save(episode);
        return responseMapper.toResponseModel(updatedEpisode);
    }

    @Override
    public void deleteEpisode(String episodeId, String podcastId) {
        Podcast podcast = podcastRepository.findAllByPodcastIdentifier_PodcastId(podcastId);
        if(podcast == null)
            throw new NotFoundException("Podcast not found with Id: " + podcastId);
        Episode episode = episodeRepository.findAllByEpisodeIdentifier_EpisodeId(episodeId);
        if (episode == null)
            throw new NotFoundException("Episode not found at: " + episodeId);

        episodeRepository.delete(episode);

    }
}
