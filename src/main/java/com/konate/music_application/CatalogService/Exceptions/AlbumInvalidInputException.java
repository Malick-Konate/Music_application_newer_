package com.konate.music_application.CatalogService.Exceptions;

public class AlbumInvalidInputException extends RuntimeException {
    public AlbumInvalidInputException(String message) {
        super(message);
    }
}
