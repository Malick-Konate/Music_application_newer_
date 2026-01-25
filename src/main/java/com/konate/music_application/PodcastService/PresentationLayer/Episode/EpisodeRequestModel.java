package com.konate.music_application.PodcastService.PresentationLayer.Episode;

import com.konate.music_application.PodcastService.DataLayer.Episode.EpisodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeRequestModel {
    String episodeTitle;
    Time duration;
    Date publishDate;
    EpisodeStatus status;
}
