package com.konate.music_application.PodcastService.DataLayer.Podcast;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "podcast")
@Data
@NoArgsConstructor
public class Podcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Embedded
    private PodcastIdentifier podcastIdentifier;

    @Column(name = "title")
    private String title;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_model")
    private PodcastPricing pricingModel;

    public Podcast(@NotNull String title, @NotNull String hostname,
                   @NotNull String description, @NotNull PodcastPricing pricing ){
        this.description = description;
        this.hostname = hostname;
        this.title = title;
        this.pricingModel = pricing;
        this.podcastIdentifier = new PodcastIdentifier();
    }
}
