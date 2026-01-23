package com.konate.music_application.CatalogService.DataLayer;

import com.konate.music_application.ArtistService.dataLayer.ArtistIdentifier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Album")
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Embedded
    private AlbumIdentifier albumIdentifier;

    @Embedded
    private ArtistIdentifier artistIdentifier;

    @Column(name = "title")
    private String title;

    @Column(name = "releaseDate")
    private Date releaseDate;

    @Column(name = "recordLabel")
    private String recordLabel;

    @Enumerated(EnumType.STRING)
    @Column(name = "albumType")
    private AlbumType albumType;

    //    @Embedded
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "song",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "album_id")
    )
    private List<Song> song;

    public Album(@NotNull String title, @NotNull Date releaseDate,
                 @NotNull String recordLabel, @NotNull AlbumType albumType) {
        this.albumIdentifier = new AlbumIdentifier();
        this.title = title;
        this.releaseDate = releaseDate;
        this.recordLabel = recordLabel;
        this.albumType = albumType;
    }

}
