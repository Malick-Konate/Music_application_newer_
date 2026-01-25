package com.konate.music_application.PodcastService.DataLayer.Podcast;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class PodcastIdentifier {
    @Column(name = "podcast_id")
    private String podcastId;

    public PodcastIdentifier (){
        this.podcastId = "podcast - " + java.util.UUID.randomUUID().toString();
    }

    public PodcastIdentifier(String podcastId){
        this.podcastId = podcastId;
    }
}
