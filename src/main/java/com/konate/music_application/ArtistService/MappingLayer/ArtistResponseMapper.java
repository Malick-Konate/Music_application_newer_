package com.konate.music_application.ArtistService.MappingLayer;



import com.konate.music_application.ArtistService.Presentation.ArtistController;
import com.konate.music_application.ArtistService.Presentation.ArtistResponseModel;
import com.konate.music_application.ArtistService.dataLayer.Artist;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface ArtistResponseMapper {

    @Mappings({
            @Mapping(source = "artistIdentifier.artistId", target = "artistIdentifier"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "genres", target = "genres"),
            @Mapping(source = "socialMediaLinks", target = "socialMediaLinks"),
            @Mapping(source = "biography.text", target = "biography")
    })
    ArtistResponseModel toRespondModel(Artist artistResponseModel);
    List<ArtistResponseModel> entityListToRespondModel(List<Artist> artistResponseModels);

    @AfterMapping
    default void setLinks(Artist artist, @MappingTarget ArtistResponseModel artistResponseModel) {
        Link selfLink = linkTo(methodOn(ArtistController.class)
                .getArtistById(artistResponseModel.getArtistIdentifier()))
                .withSelfRel();
        artistResponseModel.add(selfLink);

//        Link allArtistsLink = linkTo(methodOn(ArtistController.class)
//                .getAllArtists(new HashMap<>()))
//                .withRel("allArtists");
//        artistResponseModel.add(allArtistsLink);
    }

}
