package com.konate.music_application.PodcastService.DataLayer.Episode;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class EpisodeIdentifier {
    @Column(name = "episode_id")
    private String episodeId;

    public EpisodeIdentifier(){
        this.episodeId = "episode - " + java.util.UUID.randomUUID().toString();
    }

    public EpisodeIdentifier(String episodeId){
        this.episodeId = episodeId;
    }
}
