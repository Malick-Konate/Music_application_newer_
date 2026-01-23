package com.konate.music_application.CatalogueService.PresentationLayer;

import com.konate.music_application.CatalogueService.BusinessLayer.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseModel>> getAlbum() {
        return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK);
    }
    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponseModel> getAlbumById(@PathVariable String albumId) {
        return new ResponseEntity<>(albumService.getAlbumById(albumId), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{albumId}")
    public ResponseEntity<String> deleteAlbum(@PathVariable String albumId) {
        albumService.deleteAlbum(albumId);
        return new ResponseEntity<>("Album deleted successfully", HttpStatus.NO_CONTENT);
    }
    @PostMapping("/create")
    public ResponseEntity<AlbumResponseModel> createAlbum(@RequestBody AlbumRequestModel album) {
        AlbumResponseModel createdAlbum = albumService.createAlbum(album);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }
    @PutMapping("/update/{albumId}")
    public ResponseEntity<AlbumResponseModel> updateAlbum(@PathVariable String albumId, @RequestBody AlbumRequestModel album) {
        AlbumResponseModel updatedAlbum = albumService.updateAlbum(albumId, album);
        return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
    }

}
