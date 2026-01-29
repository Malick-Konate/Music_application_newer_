package com.konate.music_application.CatalogueService.BusinessLayer;

import com.konate.music_application.ArtistService.BusinessLayer.ArtistService;
//import com.konate.music_application.ArtistService.Exceptions.NotFoundException;
import com.konate.music_application.ArtistService.MappingLayer.ArtistRequestMapper;
import com.konate.music_application.ArtistService.Presentation.ArtistResponseModel;
import com.konate.music_application.ArtistService.dataLayer.ArtistIdentifier;
import com.konate.music_application.CatalogueService.DataLayer.Album;
import com.konate.music_application.CatalogueService.DataLayer.AlbumIdentifier;
import com.konate.music_application.CatalogueService.DataLayer.AlbumRepository;
import com.konate.music_application.CatalogueService.DataLayer.Song;
import com.konate.music_application.Exceptions.NotFoundException;
import com.konate.music_application.CatalogueService.MappingLayer.AlbumRequestMapper;
import com.konate.music_application.CatalogueService.MappingLayer.AlbumResponseMapper;
import com.konate.music_application.CatalogueService.PresentationLayer.AlbumController;
import com.konate.music_application.CatalogueService.PresentationLayer.AlbumRequestModel;
import com.konate.music_application.CatalogueService.PresentationLayer.AlbumResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumResponseMapper albumResponseMapper;
    private final AlbumRequestMapper albumRequestMapper;
    private final ArtistRequestMapper artistRequestMapper;
    private final ArtistService artistService;

    public AlbumServiceImpl(AlbumRepository albumRepository, AlbumResponseMapper albumResponseMapper, AlbumRequestMapper albumRequestMapper, ArtistRequestMapper artistRequestMapper, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.albumResponseMapper = albumResponseMapper;
        this.albumRequestMapper = albumRequestMapper;
        this.artistRequestMapper = artistRequestMapper;
        this.artistService = artistService;
    }


    @Override
    public AlbumResponseModel getAlbumById(String albumId) {

        Album album = albumRepository.findAllByAlbumIdentifier_AlbumId(albumId);
        if (album == null) {
            throw new NotFoundException("Album not found with id: " + albumId);
        }
        ArtistResponseModel artist = artistService.getArtistById(album.getArtistIdentifier().getArtistId());

        AlbumResponseModel albumResponseModel = albumResponseMapper.toResponseModel(album);
        albumResponseModel.setArtistFirstName(artist.getFirstName());
        albumResponseModel.setArtistLastName(artist.getLastName());

        albumResponseModel.add(linkTo(methodOn(AlbumController.class)
                .getAlbumById(album.getAlbumIdentifier().getAlbumId())).withSelfRel());

        return albumResponseModel;
    }

    @Override
    public List<AlbumResponseModel> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        List<AlbumResponseModel> respondModel = new ArrayList<>();
        for (Album album : albums) {
            ArtistResponseModel artist = artistService.getArtistById(album.getArtistIdentifier().getArtistId());

            AlbumResponseModel albumResponseModel = albumResponseMapper.toResponseModel(album);
            albumResponseModel.setArtistFirstName(artist.getFirstName());
            albumResponseModel.setArtistLastName(artist.getLastName());

            albumResponseModel.add(linkTo(methodOn(AlbumController.class)
                    .getAlbumById(album.getAlbumIdentifier().getAlbumId())).withSelfRel());
            respondModel.add(albumResponseModel);
        }


        return respondModel;
    }

    @Override
    public AlbumResponseModel createAlbum(AlbumRequestModel album) {
        ArtistResponseModel artist = artistService.getArtistById(album.getArtistId());
        if (artist == null) {
            throw new NotFoundException("Artist not found with id: " + album.getArtistId());
        }

        List<Song> songs = album.getSong();
        if (songs == null || songs.isEmpty()) {
            throw new IllegalArgumentException("Album must have at least one song");
        }

        Album albumEntity = albumRequestMapper.toAlbum(
                album,
                new AlbumIdentifier(),
                new ArtistIdentifier(album.getArtistId())

        );


        Album savedAlbum = albumRepository.save(albumEntity);
        AlbumResponseModel albumResponseModel = albumResponseMapper.toResponseModel(savedAlbum);
        albumResponseModel.setArtistLastName(artist.getLastName());
        albumResponseModel.setArtistFirstName(artist.getFirstName());


        return albumResponseModel;
    }

    @Override
    public AlbumResponseModel updateAlbum(String albumId, AlbumRequestModel album) {
        Album existingAlbum = albumRepository.findAllByAlbumIdentifier_AlbumId(albumId);
        ArtistResponseModel artist = artistService.getArtistById(album.getArtistId());

        if (existingAlbum == null) {
            throw new NotFoundException("Album not found with id: " + albumId);
        }
        if (album == null) {
            throw new IllegalArgumentException("Album cannot be null");
        }
        existingAlbum.setTitle(album.getTitle());
        existingAlbum.setAlbumType(album.getAlbumType());
        existingAlbum.setReleaseDate(album.getReleaseDate());
        existingAlbum.setRecordLabel(album.getRecordLabel());
        existingAlbum.setArtistIdentifier(new ArtistIdentifier(album.getArtistId()));

        Album savedAlbum = albumRepository.save(existingAlbum);

        AlbumResponseModel albumResponseModel = albumResponseMapper.toResponseModel(savedAlbum);
        albumResponseModel.setArtistLastName(artist.getLastName());
        albumResponseModel.setArtistFirstName(artist.getFirstName());
        return albumResponseModel;
    }

    @Override
    public void deleteAlbum(String albumId) {
        Album album = albumRepository.findAllByAlbumIdentifier_AlbumId(albumId);
        if (album == null) {
            throw new NotFoundException("Album not found with id: " + albumId);
        }
        albumRepository.delete(album);
    }

    @Override
    public AlbumResponseModel getAlbumByTitle(String title) {
        Album album = albumRepository.findAllByTitle(title);
        if (album == null) {
            throw new NotFoundException("Album not found with title: " + title);
        }
        ArtistResponseModel artist = artistService.getArtistById(album.getArtistIdentifier().getArtistId());

        AlbumResponseModel albumResponseModel = albumResponseMapper.toResponseModel(album);
        albumResponseModel.setArtistFirstName(artist.getFirstName());
        albumResponseModel.setArtistLastName(artist.getLastName());

        albumResponseModel.add(linkTo(methodOn(AlbumController.class)
                .getAlbumById(album.getAlbumIdentifier().getAlbumId())).withSelfRel());

        return albumResponseModel;
    }

}
