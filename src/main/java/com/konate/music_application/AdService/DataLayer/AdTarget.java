package com.konate.music_application.AdService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class AdTarget {
    @Enumerated(EnumType.STRING)
    @Column(name = "target_type")
    private AdTargetType targetType;

    @Column(name = "target_id")
    private String targetId;

    public AdTarget(AdTargetType targetType, String targetId) {
        this.targetType = targetType;
        this.targetId = targetId;
    }
    public AdTarget(){

    }
}
