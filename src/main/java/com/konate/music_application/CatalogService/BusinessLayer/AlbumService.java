package com.konate.music_application.CatalogService.BusinessLayer;

import com.konate.music_application.CatalogService.PresentationLayer.AlbumRequestModel;
import com.konate.music_application.CatalogService.PresentationLayer.AlbumResponseModel;

import java.util.List;

public interface AlbumService {
    AlbumResponseModel getAlbumById(String albumId);

     List<AlbumResponseModel> getAllAlbums();

    AlbumResponseModel createAlbum(AlbumRequestModel album);

    AlbumResponseModel updateAlbum(String albumId, AlbumRequestModel album);

     void deleteAlbum(String albumId);

     AlbumResponseModel getAlbumByTitle(String title);
}
