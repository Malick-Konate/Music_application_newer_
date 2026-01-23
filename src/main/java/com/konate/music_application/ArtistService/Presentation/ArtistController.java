package com.konate.music_application.ArtistService.Presentation;

//import com.konate.country_platform_hub.ArtistService.BusinessLayer.ArtistService;
import com.konate.music_application.ArtistService.BusinessLayer.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseModel>> getAllArtists() {
        return new ResponseEntity<>(artistService.getAllArtists(), HttpStatus.OK);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<ArtistResponseModel> getArtistById(@PathVariable String artistId) {
        return new ResponseEntity<>(artistService.getArtistById(artistId), HttpStatus.OK);
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable String artistId) {
        artistService.deleteArtist(artistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<ArtistResponseModel> createArtist(@RequestBody ArtistRequestModel artist) {
        ArtistResponseModel createdArtist = artistService.createArtist(artist);
        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }
    @PutMapping("/{artistId}")
    public ResponseEntity<ArtistResponseModel> updateArtist(@PathVariable String artistId, @RequestBody ArtistRequestModel artist) {
        ArtistResponseModel updatedArtist = artistService.updateArtist(artistId, artist);
        return new ResponseEntity<>(updatedArtist, HttpStatus.OK);
    }
}
