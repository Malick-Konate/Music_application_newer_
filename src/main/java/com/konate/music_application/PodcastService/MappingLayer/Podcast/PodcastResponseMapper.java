package com.konate.music_application.PodcastService.MappingLayer.Podcast;

import com.konate.music_application.PodcastService.DataLayer.Podcast.Podcast;
import com.konate.music_application.PodcastService.PresentationLayer.Podcast.PodcastResponseModel;
import com.konate.music_application.PodcastService.PresentationLayer.PodcastController;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface PodcastResponseMapper {
    @Mappings({
            @Mapping(source = "podcastIdentifier.podcastId", target = "podcastId"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "hostname", target = "hostname"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "pricingModel", target = "pricingModel")
    })
    PodcastResponseModel toResponseModel(Podcast podcast);

    List<PodcastResponseModel> entityListToResponseModelList(List<Podcast> podcasts);

    @AfterMapping
    default void addLinks(@MappingTarget PodcastResponseModel responseModel, Podcast podcast){
        Link selfLink = linkTo(methodOn(PodcastController.class)
                .getPodcastById(podcast.getPodcastIdentifier().getPodcastId()))
                .withSelfRel();
        Link allLink = linkTo(methodOn(PodcastController.class).getAllPodcast()).withRel("all podcasts");
        Link delete = linkTo(methodOn(PodcastController.class).deletePodcast(podcast.getPodcastIdentifier().getPodcastId())).withRel("delete");
        responseModel.add(selfLink, allLink, delete);
    }

}
