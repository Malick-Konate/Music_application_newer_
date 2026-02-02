package com.konate.music_application.AdService.MappingLayer;

import com.konate.music_application.AdService.DataLayer.AdCampaign;
import com.konate.music_application.AdService.DataLayer.AdIdentifier;
import com.konate.music_application.AdService.PresentationLayer.AdRequestModel;
import com.konate.music_application.ArtistService.dataLayer.ArtistIdentifier;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastIdentifier;
import com.konate.music_application.UserService.DataLayer.UserIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AdCampaignRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(adIdentifier)", target = "adIdentifier"),
            @Mapping(expression = "java(userIdentifier)", target = "userIdentifier"),
//            @Mapping(expression = "java(new ArtistIdentifier())", target = "artistIdentifier"),
//            @Mapping(expression = "java(new PodcastIdentifier())", target = "podcastIdentifier"),
            @Mapping(source = "requestModel.name", target = "name"),
            @Mapping(source = "requestModel.budget", target = "budget"),
            @Mapping(source = "requestModel.remainingSpend", target = "remainingSpend"),
            @Mapping(source = "requestModel.status", target = "status"),
            @Mapping(source = "requestModel.creatives", target = "creatives"),
            @Mapping(source = "requestModel.targetingRules", target = "targetingRules"),
            @Mapping(source = "requestModel.adTarget", target = "adTarget")


    })
    AdCampaign toAdCampaign(AdRequestModel requestModel,
                            AdIdentifier adIdentifier,
                            UserIdentifier userIdentifier);
}
