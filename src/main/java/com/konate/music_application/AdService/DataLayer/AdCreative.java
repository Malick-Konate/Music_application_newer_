package com.konate.music_application.AdService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class AdCreative {
    @Column(name = "media_Url")
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "creative_type")
    private CreativeType creativeType;

    public AdCreative(String mediaUrl, CreativeType creativeType) {
        this.mediaUrl = mediaUrl;
        this.creativeType = creativeType;
    }

    public AdCreative() {
        this.mediaUrl = "";
        this.creativeType = CreativeType.AUDIO_CLIP;
    }
}
