package com.konate.music_application.AdService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.List;

@Embeddable
@Getter
public class TargetingRules {

    @Enumerated(EnumType.STRING)
    @Column(name = "target_genre")
    private GenreEnum targetGenre;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_region")
    private Region targetRegion;

    @Column(name = "min_age")
    private Integer minAge;

    public TargetingRules(GenreEnum targetGenres, Region targetRegions, Integer minAge) {
        this.targetGenre = targetGenres;
        this.targetRegion = targetRegions;
        this.minAge = minAge;
    }

    public TargetingRules() {
        this.targetGenre = null;
        this.targetRegion = null;
        this.minAge = null;
    }

}
