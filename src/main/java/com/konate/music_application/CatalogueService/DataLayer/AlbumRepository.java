package com.konate.music_application.CatalogueService.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Album findAllByAlbumIdentifier_AlbumId(String albumId);
    Album findAllByTitle(String title);

}
