package com.konate.music_application.UserService.Exceptions;

public class UserFound extends RuntimeException {
    public UserFound(String message) {
        super(message);
    }
}
