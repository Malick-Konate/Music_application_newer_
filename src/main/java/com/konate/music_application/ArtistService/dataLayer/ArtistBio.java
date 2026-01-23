package com.konate.music_application.ArtistService.dataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Embeddable
@Getter
public class ArtistBio {
    @Column(name = "biography_text")
    private String text;

    public ArtistBio() {
//        this.Text = "";
    }
    public ArtistBio(@NotNull String text) {
        this.text = text;
    }
}
