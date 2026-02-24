package com.konate.music_application.ArtistService.BusinessLayer;


import com.konate.music_application.ArtistService.Presentation.ArtistRequestModel;
import com.konate.music_application.ArtistService.Presentation.ArtistResponseModel;

import java.util.List;

public interface ArtistService {
    List<ArtistResponseModel> getAllArtists();

    ArtistResponseModel getArtistById(String id);

    ArtistResponseModel createArtist(ArtistRequestModel artist);

    ArtistResponseModel updateArtist(String id, ArtistRequestModel artist);

    void deleteArtist(String id);

    ArtistResponseModel getArtistByLastName(String lastName);
}
