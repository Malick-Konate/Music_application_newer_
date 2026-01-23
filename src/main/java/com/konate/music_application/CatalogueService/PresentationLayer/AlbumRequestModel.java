package com.konate.music_application.CatalogueService.PresentationLayer;

import com.konate.music_application.CatalogueService.DataLayer.AlbumType;
import com.konate.music_application.CatalogueService.DataLayer.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumRequestModel {
//    private String albumId;
    private String title;
    private String artistId;
    //    private String artistFirstName;
//    private String artistLastName;
    private Date releaseDate;
    private AlbumType albumType;
    private String recordLabel;
//    private String songTitle;
//    private Time songDuration;
//    private String songLyrics;
    private List<Song> song;
}
