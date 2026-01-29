package com.konate.music_application.Exceptions;

public class UserFound extends RuntimeException {
    public UserFound(String message) {
        super(message);
    }
}
