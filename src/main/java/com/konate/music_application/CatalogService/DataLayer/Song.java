package com.konate.music_application.CatalogService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Time;

@Embeddable
@Getter
public class Song {
    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private Time duration;

    @Column(name = "lyrics")
    private String lyrics;

    public Song() {
        this.title = "Unknown Title"; // default value
//        this.duration = 0.0.0; // default value
        this.lyrics = "No lyrics available"; // default value
    }
    public Song(@NotNull String title, @NotNull Time duration, @NotNull String lyrics) {
        this.title = title;
        this.duration = duration;
        this.lyrics = lyrics;
    }
}
