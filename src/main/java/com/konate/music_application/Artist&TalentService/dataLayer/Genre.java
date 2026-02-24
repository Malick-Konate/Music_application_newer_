package com.konate.music_application.ArtistService.dataLayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
@Embeddable
@Getter
@NoArgsConstructor
public class Genre {
    private String genre;


    public Genre(@NotNull String genre) {
        this.genre = genre;
    }
}
