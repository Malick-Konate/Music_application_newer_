package com.konate.music_application.PodcastService.DataLayer.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PodcastRepository extends JpaRepository<Podcast, Integer>{
    List<Podcast> findAllByHostname(String hostname);
    
    Podcast findAllByTitle(String title);

    Podcast findAllByPodcastIdentifier_PodcastId(String podcastIdentifierPodcastId);
}
