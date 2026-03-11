package com.konate.music_application.CatalogueService.BusinessLayer;

import com.konate.music_application.ArtistService.BusinessLayer.ArtistService;
//import com.konate.music_application.ArtistService.Exceptions.NotFoundException;
import com.konate.music_application.ArtistService.MappingLayer.ArtistRequestMapper;
import com.konate.music_application.ArtistService.Presentation.ArtistResponseModel;
import com.konate.music_application.ArtistService.dataLayer.ArtistIdentifier;
import com.konate.music_application.CatalogueService.DataLayer.*;
import com.konate.music_application.Exceptions.InconsistentAlbumException;
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
        validateAlbumInvariants(album);


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
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null");
        }
        // 2. Validate Domain Invariants (Now enforced on Update too!)
        validateAlbumInvariants(album);


        existingAlbum.setTitle(album.getTitle());
        existingAlbum.setAlbumType(album.getAlbumType());
        existingAlbum.setReleaseDate(album.getReleaseDate());
        existingAlbum.setRecordLabel(album.getRecordLabel());
        existingAlbum.setSong(album.getSong());
        existingAlbum.setArtistIdentifier(new ArtistIdentifier(album.getArtistId()));

        Album savedAlbum = albumRepository.save(existingAlbum);

        return mapToResponse(savedAlbum, artist);
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




    /**
     * Invariant Validation
     */
    private void validateAlbumInvariants(AlbumRequestModel request) {
        // Invariant: Title cannot be empty
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new InconsistentAlbumException("Album title is required.");
        }

        // Invariant: Album must have at least one song
        if (request.getSong() == null || request.getSong().isEmpty()) {
            throw new InconsistentAlbumException("Album must have at least one song.");
        }

        // Invariant: Logical Song Count based on Album Type
        int songCount = request.getSong().size();
        if (request.getAlbumType() == AlbumType.SINGLE && songCount > 3) {
            throw new InconsistentAlbumException("A SINGLE cannot have more than 3 songs.");
        }
        if (request.getAlbumType() == AlbumType.EP && (songCount < 4 || songCount > 6)) {
            throw new InconsistentAlbumException("An EP must have between 4 and 6 songs.");
        }
        if (request.getAlbumType() == AlbumType.LP && songCount < 7) {
            throw new InconsistentAlbumException("An LP must have at least 7 songs.");
        }
    }

    private AlbumResponseModel mapToResponse(Album album, ArtistResponseModel artist) {
        AlbumResponseModel model = albumResponseMapper.toResponseModel(album);
        model.setArtistFirstName(artist.getFirstName());
        model.setArtistLastName(artist.getLastName());
        return model;
    }
}
