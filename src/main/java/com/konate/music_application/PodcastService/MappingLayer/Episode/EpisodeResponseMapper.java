package com.konate.music_application.PodcastService.MappingLayer.Episode;

import com.konate.music_application.PodcastService.DataLayer.Episode.Episode;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeResponseModel;
//import org.aspectj.lang.annotation.After;
import com.konate.music_application.PodcastService.PresentationLayer.PodcastController;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface EpisodeResponseMapper {
    @Mappings({
            @Mapping(source = "episodeIdentifier.episodeId", target = "episodeId"),
            @Mapping(source = "episodeTitle", target = "episodeTitle"),
            @Mapping(source = "duration", target = "duration"),
            @Mapping(source = "publishDate", target = "publishDate"),
            @Mapping(source = "status", target = "status")
    })
    EpisodeResponseModel toResponseModel(Episode episode);

    List<EpisodeResponseModel> entityListToResponseModelList (List<Episode> episodes);

    @AfterMapping
    default void addLinks (@MappingTarget EpisodeResponseModel responseModel,  Episode episode){
        Link selfLink = linkTo(methodOn(PodcastController.class)
                .getEpisodeById(episode.getPodcastIdentifier().getPodcastId(),episode.getEpisodeIdentifier().getEpisodeId()))
                .withSelfRel();
        Link allLinkPodcast = linkTo(methodOn(PodcastController.class).getAllPodcast()).withRel("all podcasts");
        Link allEpisode = linkTo(methodOn(PodcastController.class)
                .getEpisodeById(episode.getPodcastIdentifier().getPodcastId(), episode.getEpisodeIdentifier().getEpisodeId())).withRel("all episodes fot this podcast");
        Link delete = linkTo(methodOn(PodcastController.class)
                .deleteEpisode(episode.getPodcastIdentifier().getPodcastId(), episode.getEpisodeIdentifier().getEpisodeId())).withRel("delete");
        responseModel.add(selfLink, allLinkPodcast,allEpisode, delete);
    }
}
