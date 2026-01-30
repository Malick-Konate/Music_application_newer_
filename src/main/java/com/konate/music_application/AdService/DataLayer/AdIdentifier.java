package com.konate.music_application.AdService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class AdIdentifier {

    @Column(name = "ad_id")
    private String adId;

    public AdIdentifier(String adId) {
        this.adId = adId;
    }

    public AdIdentifier() {
        this.adId = "ad_" + UUID.randomUUID().toString();
    }
}
