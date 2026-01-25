package com.konate.music_application.PodcastService.PresentationLayer.Podcast;

import com.konate.music_application.CatalogueService.PresentationLayer.AlbumResponseModel;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastPricing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PodcastResponseModel extends RepresentationModel<AlbumResponseModel> {
    String podcastId;
    String title;
    String hostname;
    String description;
    PodcastPricing pricingModel;
}
