package com.konate.music_application.AdService.MappingLayer;
import com.konate.music_application.AdService.DataLayer.AdCampaign;
import com.konate.music_application.AdService.PresentationLayer.AdCampaignController;
import com.konate.music_application.AdService.PresentationLayer.AdResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface AdCampaignResponseMapper {

    @Mappings({
            @Mapping(source = "adIdentifier.adId", target = "ad_id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "budget", target = "budget"),
            @Mapping(source = "remainingSpend", target = "remainingSpend"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "creatives", target = "creatives"),
            @Mapping(source = "targetingRules", target = "targetingRules")
//            @Mapping(source = "userIdentifier.userEmail", target = "userEmail"),
//            @Mapping(source = "artistIdentifier.artistName", target = "artistName"),
//            @Mapping(source = "podcastIdentifier.podcastTitle", target = "podcastTitle")

    })
    AdResponseModel toAdResponseModel(AdCampaign adCampaign);
    List<AdResponseModel> toAdResponseModelList(List<AdCampaign> adCampaigns);

    public default void addLinks(@MappingTarget AdResponseModel adResponseModel, AdCampaign adCampaign) {
        Link selfLink = linkTo(methodOn(AdCampaignController.class)
                .getAdCampaign(adCampaign.getAdIdentifier().getAdId()))
                .withSelfRel();
        adResponseModel.add(selfLink);
    }
}
