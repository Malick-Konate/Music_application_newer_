package com.konate.music_application.PodcastService.BusinessLayer.Episode;

import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeRequestModel;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeResponseModel;
import java.util.List;

public interface EpisodeService {
//    List<EpisodeResponseModel> getAllEpisodes();
//    EpisodeResponseModel getEpisodeById(String episodeId);
//    List<EpisodeResponseModel> getEpisodeByPodcastId(String podcastId);
//    EpisodeResponseModel createEpisode(String podcastId, EpisodeRequestModel requestModel);
//    EpisodeResponseModel updateEpisode(String episodeId, EpisodeRequestModel requestModel);
//    void deleteEpisode(String episodeId);

    // 1. Matches GET /podcasts/{podcastId}/episodes
    List<EpisodeResponseModel> getEpisodesByPodcastId(String podcastId);

    // 2. Matches POST /podcasts/{podcastId}/episodes
    // Passing podcastId here ensures the episode is linked to the parent aggregate
    EpisodeResponseModel createEpisode(String podcastId, EpisodeRequestModel requestModel);

    // 3. Matches GET /podcasts/{podcastId}/episodes/{episodeId} (or flat GET /episodes/{id})
    EpisodeResponseModel getEpisodeById(String podcastId, String episodeId);

    // 4. Matches PUT /podcasts/{podcastId}/episodes/{episodeId}
    EpisodeResponseModel updateEpisode(String podcastId, String episodeId, EpisodeRequestModel requestModel);

    // 5. Matches DELETE /podcasts/{podcastId}/episodes/{episodeId}
    void deleteEpisode(String episodeId, String podcastId);
}
