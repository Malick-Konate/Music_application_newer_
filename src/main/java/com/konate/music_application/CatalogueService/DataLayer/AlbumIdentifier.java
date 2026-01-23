package com.konate.music_application.CatalogueService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class AlbumIdentifier {
    @Column(name = "album_id")
    private String albumId;

    public AlbumIdentifier() {
        this.albumId = java.util.UUID.randomUUID().toString();
    }

    public AlbumIdentifier(String albumId) {
        this.albumId = albumId;
    }
}
