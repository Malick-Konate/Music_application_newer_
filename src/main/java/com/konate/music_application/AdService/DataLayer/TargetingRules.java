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
    @Column(name = "target_genres")
    private List<GenreEnum> targetGenres;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_regions")
    private List<Region> targetRegions;

    @Column(name = "min_age")
    private Integer minAge;

    public TargetingRules(List<GenreEnum> targetGenres, List<Region> targetRegions, Integer minAge) {
        this.targetGenres = targetGenres;
        this.targetRegions = targetRegions;
        this.minAge = minAge;
    }

    public TargetingRules() {
        this.targetGenres = null;
        this.targetRegions = null;
        this.minAge = null;
    }

}
