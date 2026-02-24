package com.konate.music_application.AdService.DataLayer;

import com.konate.music_application.Exceptions.InvalidCampaignStateException;
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
public class AdCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Embedded
    private AdIdentifier adIdentifier;

    @Embedded
    private UserIdentifier userIdentifier;
//
//    @Embedded
//    private ArtistIdentifier artistIdentifier;
//
//    @Embedded
//    private PodcastIdentifier podcastIdentifier;

    @Embedded
    private AdTarget adTarget;

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
            name = "ad_creatives",
            joinColumns = @JoinColumn(name = "ad_id", referencedColumnName = "ad_id")
    )
    private List<AdCreative> creatives;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "targeting_rules",
            joinColumns = @JoinColumn(name = "ad_id", referencedColumnName = "ad_id")
    )
    private List<TargetingRules> targetingRules;

    public void activate() {
        if (this.status != CampaignStatus.DRAFT && this.status != CampaignStatus.PAUSED) {
            throw new InvalidCampaignStateException("Campaign cannot be activated from " + this.status);
        }

        if (this.remainingSpend.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCampaignStateException("Cannot activate campaign with no remaining budget");
        }

        this.status = CampaignStatus.ACTIVE;
    }

    public void pause() {
        if (this.status != CampaignStatus.ACTIVE) {
            throw new InvalidCampaignStateException("Only ACTIVE campaigns can be paused. Current status: " + this.status);
        }
        this.status = CampaignStatus.PAUSED;
    }


    public void resume() {
        if (this.status != CampaignStatus.PAUSED) {
            throw new InvalidCampaignStateException("Only PAUSED campaigns can be resumed.");
        }


        if (this.remainingSpend.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCampaignStateException("Cannot resume a campaign with no remaining budget.");
        }

        this.status = CampaignStatus.ACTIVE;
    }


    public void spend(BigDecimal amount) {
        if (this.status != CampaignStatus.ACTIVE) {
            throw new InvalidCampaignStateException("Cannot deduct spend from a non-active campaign.");
        }

        this.remainingSpend = this.remainingSpend.subtract(amount);

        if (this.remainingSpend.compareTo(BigDecimal.ZERO) <= 0) {
            this.remainingSpend = BigDecimal.ZERO;
            this.status = CampaignStatus.EXHAUSTED;
        }
    }
}
