package com.konate.music_application.ArtistService.dataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Embeddable
@Getter
@NoArgsConstructor
public class SocialMediaLink {
    @Column(name = "platform")
    private String platform;
    @Column(name = "uri")
    private String uri;

    public SocialMediaLink(@NotNull String platform, @NotNull String URI) {
        this.platform = platform;
        this.uri = URI;
    }

}
