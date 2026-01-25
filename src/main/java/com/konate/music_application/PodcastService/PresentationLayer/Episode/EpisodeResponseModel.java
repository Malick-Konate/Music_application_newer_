package com.konate.music_application.PodcastService.PresentationLayer.Episode;

import com.konate.music_application.CatalogueService.PresentationLayer.AlbumResponseModel;
import com.konate.music_application.PodcastService.DataLayer.Episode.EpisodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeResponseModel extends RepresentationModel<AlbumResponseModel> {
    String episodeId;
    String episodeTitle;
    Time duration;
    Date publishDate;
    EpisodeStatus status;
}
