package com.konate.music_application.ArtistService.BusinessLayer;

import com.konate.music_application.Exceptions.NotFoundException;
import com.konate.music_application.ArtistService.MappingLayer.ArtistRequestMapper;
import com.konate.music_application.ArtistService.MappingLayer.ArtistResponseMapper;
import com.konate.music_application.ArtistService.Presentation.ArtistRequestModel;
import com.konate.music_application.ArtistService.Presentation.ArtistResponseModel;
import com.konate.music_application.ArtistService.dataLayer.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistResponseMapper artistResponseMapper;
    private final ArtistRequestMapper artistRequestMapper;

    public ArtistServiceImpl(ArtistRepository artistRepository, ArtistResponseMapper artistResponseMapper, ArtistResponseMapper artistResponseMapper1, ArtistRequestMapper artistRequestMapper) {
        this.artistRepository = artistRepository;
        this.artistResponseMapper = artistResponseMapper1;
        this.artistRequestMapper = artistRequestMapper;
    }

    @Override
    public List<ArtistResponseModel> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        return artistResponseMapper.entityListToRespondModel(artists);
    }

    @Override
    public ArtistResponseModel getArtistById(String id) {
//        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NotFoundException("Artist not found with id: " + id));
        Artist artist = artistRepository.findAllByArtistIdentifier_ArtistId(id);
        if (artist == null) {
            throw new NotFoundException("Artist not found with id: " + id);
        }
        return artistResponseMapper.toRespondModel(artist);
    }

    @Override
    public ArtistResponseModel createArtist(ArtistRequestModel artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null");
        }
//        List<SocialMediaLink> socialMediaLinks = artist.getSocialMediaLinks();
//        if (socialMediaLinks != null && !socialMediaLinks.isEmpty()) {
//            artist.setSocialMediaLinks(socialMediaLinks);
//        }
//        List<Genre> genres = artist.getGenres();
//        if (genres != null && !genres.isEmpty()) {
//            artist.setGenres(genres);
//        }

        Artist artistEntity = artistRequestMapper.toEntity(
                artist,
                new ArtistIdentifier(),
                new ArtistBio(artist.getBiography())
        );

        Artist savedArtist = artistRepository.save(artistEntity);
        return artistResponseMapper.toRespondModel(savedArtist);
    }

    @Override
    public ArtistResponseModel updateArtist(String id, ArtistRequestModel artist) {
        Artist artistExisting = artistRepository.findAllByArtistIdentifier_ArtistId(id);

        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null");
        }
        artistExisting.setFirstName(artist.getFirstName());
        artistExisting.setLastName(artist.getLastName());
        List<SocialMediaLink> socialMediaLinks = artist.getSocialMediaLinks();
        if (socialMediaLinks != null && !socialMediaLinks.isEmpty()) {
            List<SocialMediaLink> existingLinks = artistExisting.getSocialMediaLinks();

            artistExisting.setSocialMediaLinks(existingLinks);

        }
        List<Genre> genres = artist.getGenres();
        if (genres != null && !genres.isEmpty()) {
            artistExisting.setGenres(genres);
        }

        artistExisting.setBiography(new ArtistBio(artist.getBiography()));
//        artistExisting.getSocialMediaLinks().add(new SocialMediaLink(artist.getSocialMediaLinks().get(0).getURI()));
        Artist updatedArtist = artistRepository.save(artistExisting);
        return artistResponseMapper.toRespondModel(updatedArtist);
    }

    @Override
    public void deleteArtist(String id) {
        Artist artist = artistRepository.findAllByArtistIdentifier_ArtistId(id);
        if (artist == null) {
            throw new NotFoundException("Artist not found with id: " + id);
        }
        artistRepository.delete(artist);
    }

    @Override
    public ArtistResponseModel getArtistByLastName(String lastName) {
        Artist artist = artistRepository.findAllByLastName(lastName);
        if (artist == null) {
            throw new NotFoundException("Artist not found with id: " + lastName);
        }
        return artistResponseMapper.toRespondModel(artist);
    }
}
