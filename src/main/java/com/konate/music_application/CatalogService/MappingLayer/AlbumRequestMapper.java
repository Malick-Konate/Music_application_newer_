package com.konate.music_application.CatalogService.MappingLayer;

import com.konate.music_application.ArtistService.dataLayer.ArtistIdentifier;
import com.konate.music_application.CatalogService.DataLayer.Album;
import com.konate.music_application.CatalogService.DataLayer.AlbumIdentifier;
import com.konate.music_application.CatalogService.PresentationLayer.AlbumRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AlbumRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(artistIdentifier)", target = "artistIdentifier"),
            @Mapping(expression = "java(albumIdentifier)", target = "albumIdentifier"),
            @Mapping(source = "albumRequestModel.title", target = "title"),
            @Mapping(source = "albumRequestModel.releaseDate", target = "releaseDate"),
            @Mapping(source = "albumRequestModel.recordLabel", target = "recordLabel"),
            @Mapping(source = "albumRequestModel.albumType", target = "albumType"),
//            @Mapping(source = "albumRequestModel.songTitle", target = "song.title"),
//            @Mapping(source = "albumRequestModel.songDuration", target = "song.duration"),
//            @Mapping(source = "albumRequestModel.songLyrics", target = "song.lyrics")
//            @Mapping(expression = "java(song)", target = "song"),
            @Mapping(source = "albumRequestModel.song", target = "song")

    })
    Album toAlbum(AlbumRequestModel albumRequestModel,
                  AlbumIdentifier albumIdentifier,
                  ArtistIdentifier artistIdentifier
                  );
}
