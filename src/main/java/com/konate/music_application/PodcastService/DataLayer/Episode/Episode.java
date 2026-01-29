package com.konate.music_application.PodcastService.DataLayer.Episode;

import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "episode")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Embedded
    private EpisodeIdentifier episodeIdentifier;

    @Embedded
    private PodcastIdentifier podcastIdentifier;

    @Column(name = "title")
    private String episodeTitle;

    @Column(name = "duration")
    private Time duration;

    @Column (name = "publish_date")
    private Date publishDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EpisodeStatus status;
}
