package com.konate.music_application.AdService.DataLayer;

import com.konate.music_application.ArtistService.dataLayer.ArtistIdentifier;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastIdentifier;
import com.konate.music_application.UserService.DataLayer.UserIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ad_campaigns")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdCamplaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Embedded
    private AdIdentifier adIdentifier;

    @Embedded
    private UserIdentifier userIdentifier;

    @Embedded
    private ArtistIdentifier artistIdentifier;

    @Embedded
    private PodcastIdentifier podcastIdentifier;

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private BigDecimal budget;

    @Column(name = "remaining_spend")
    private BigDecimal remainingSpend;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CampaignStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "ad_creative",
            joinColumns = @JoinColumn(name = "ad_id", referencedColumnName = "ad_id")
    )
    private List<AdCreative> creatives;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "targeting_rules",
            joinColumns = @JoinColumn(name = "ad_id", referencedColumnName = "ad_id")
    )
    private List<TargetingRules> targetingRules;
}
