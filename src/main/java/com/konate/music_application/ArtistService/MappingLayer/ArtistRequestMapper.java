package com.konate.music_application.ArtistService.MappingLayer;
//import com.konate.country_platform_hub.ArtistService.Presentation.ArtistRequestModel;
//import com.konate.country_platform_hub.ArtistService.dataLayer.Artist;
//import com.konate.country_platform_hub.ArtistService.dataLayer.ArtistBio;
//import com.konate.country_platform_hub.ArtistService.dataLayer.ArtistIdentifier;
//import org.mapstruct.*;

import com.konate.music_application.ArtistService.Presentation.ArtistRequestModel;
import com.konate.music_application.ArtistService.dataLayer.Artist;
import com.konate.music_application.ArtistService.dataLayer.ArtistBio;
import com.konate.music_application.ArtistService.dataLayer.ArtistIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface ArtistRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(artistIdentifier)", target = "artistIdentifier"),
            @Mapping(source = "request.firstName", target = "firstName"),
            @Mapping(source = "request.lastName", target = "lastName"),
            @Mapping(source = "request.genres", target = "genres"),
            @Mapping(source = "request.socialMediaLinks", target = "socialMediaLinks"),
            @Mapping(expression = "java(artistBio)", target = "biography")
    })
    Artist toEntity(ArtistRequestModel request, ArtistIdentifier artistIdentifier, ArtistBio artistBio);
}
