package com.konate.music_application.ArtistService.dataLayer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Embedded
    private ArtistIdentifier artistIdentifier;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    //    @Embedded
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "artist_genre",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "artist_id")
    )
//    @Column(name = "genre")
    private List<Genre> genres;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "artist_social_media_links",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "artist_id")
    )
    private List<SocialMediaLink> socialMediaLinks;

//    private

    @Embedded
    private ArtistBio biography;


}
