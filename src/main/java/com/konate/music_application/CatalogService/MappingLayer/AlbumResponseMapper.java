package com.konate.music_application.CatalogService.MappingLayer;

import com.konate.music_application.CatalogService.DataLayer.Album;
import com.konate.music_application.CatalogService.PresentationLayer.AlbumController;
import com.konate.music_application.CatalogService.PresentationLayer.AlbumResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Mapper(componentModel = "spring")
public interface AlbumResponseMapper {
    @Mappings({
            @Mapping(source = "albumIdentifier.albumId", target = "albumId"),
//            @Mapping( source = "artist.artistId",target = "artistId"),
//            @Mapping(source = "artist.artistName", target = "artistName"),
            @Mapping(source = "albumType", target = "albumType"),
            @Mapping(source = "releaseDate", target = "releaseDate"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "recordLabel", target = "recordLabel"),
//            @Mapping(source = "song.title", target = "songTitle"),
//            @Mapping(source = "song.duration", target = "songDuration"),
//            @Mapping(source = "song.lyrics", target = "songLyrics")
            @Mapping(source = "song", target = "song")

    })
    AlbumResponseModel toResponseModel(Album album);
    List<AlbumResponseModel> entityListToResponseModelList(List<Album> albumList);

    @AfterMapping
    default void addLinks(AlbumResponseModel albumResponseModel, @MappingTarget Album album) {
        Link selfLink = linkTo(methodOn(AlbumController.class)
                .getAlbumById(album.getAlbumIdentifier()
                        .getAlbumId())).withSelfRel();

        albumResponseModel.add(selfLink);
    }

}
