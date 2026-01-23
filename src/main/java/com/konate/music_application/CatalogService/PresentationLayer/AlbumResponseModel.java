package com.konate.music_application.CatalogService.PresentationLayer;

import com.konate.music_application.CatalogService.DataLayer.AlbumType;
import com.konate.music_application.CatalogService.DataLayer.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponseModel extends RepresentationModel<AlbumResponseModel> {
    private String albumId;
    private String title;
//    private String artistId;
    private String artistFirstName;
    private String artistLastName;
    private String releaseDate;
    private AlbumType albumType;
//    private String songTitle;
//    private Time songDuration;
//    private String songLyrics;
    private String recordLabel;

    private List<Song> song;

    // Add any other fields you need for the response model
}
