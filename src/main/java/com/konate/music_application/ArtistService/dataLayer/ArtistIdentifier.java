package com.konate.music_application.ArtistService.dataLayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class ArtistIdentifier {
    @Column(name = "artist_id")
    private String artistId;

    public ArtistIdentifier() {
        this.artistId = UUID.randomUUID().toString();
    }

    public ArtistIdentifier(String artistId) {
        this.artistId = artistId;
    }

}
