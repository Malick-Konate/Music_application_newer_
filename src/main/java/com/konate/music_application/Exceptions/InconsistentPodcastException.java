package com.konate.music_application.Exceptions;

public class InconsistentPodcastException extends RuntimeException {
    public InconsistentPodcastException(String message) {
        super(message);
    }
}
