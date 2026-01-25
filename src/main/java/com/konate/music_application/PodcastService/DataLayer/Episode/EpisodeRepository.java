package com.konate.music_application.PodcastService.DataLayer.Episode;
import com.konate.music_application.PodcastService.DataLayer.Podcast.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer>{
    Episode findAllByEpisodeIdentifier_EpisodeId(String episodeId);

    List<Episode> findAllByPodcastIdentifier_PodcastId(String podcastId);

    Episode findAllByEpisodeTitle(String title);
}
