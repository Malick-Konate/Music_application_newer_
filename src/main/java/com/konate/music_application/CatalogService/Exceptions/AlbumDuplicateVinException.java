package com.konate.music_application.CatalogService.Exceptions;


public class AlbumDuplicateVinException extends RuntimeException {

    public AlbumDuplicateVinException() {
    }

    public AlbumDuplicateVinException(String message) {
        super(message);
    }

    public AlbumDuplicateVinException(Throwable cause) {
        super(cause);
    }

    public AlbumDuplicateVinException(String message, Throwable cause) {
        super(message, cause);
    }
}
