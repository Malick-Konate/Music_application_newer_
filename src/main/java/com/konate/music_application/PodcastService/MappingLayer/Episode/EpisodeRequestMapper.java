package com.konate.music_application.PodcastService.MappingLayer.Episode;

//import org.mapstruct.Mapper;
import com.konate.music_application.PodcastService.DataLayer.Episode.Episode;
import com.konate.music_application.PodcastService.DataLayer.Episode.EpisodeIdentifier;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastIdentifier;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeRequestModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EpisodeRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(podcastIdentifier)", target = "podcastIdentifier"),
            @Mapping(expression = "java(episodeIdentifier)", target = "episodeIdentifier"),
            @Mapping(source = "requestModel.episodeTitle", target = "episodeTitle"),
            @Mapping(source = "requestModel.duration", target = "duration"),
            @Mapping(source = "requestModel.publishDate", target = "publishDate"),
            @Mapping(source = "requestModel.status", target = "status")
    })
    Episode toEntity(EpisodeRequestModel requestModel,
                     EpisodeIdentifier episodeIdentifier,
                     PodcastIdentifier podcastIdentifier);
}
