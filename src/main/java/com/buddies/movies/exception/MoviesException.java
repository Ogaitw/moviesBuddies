package com.buddies.movies.exception;

public class MoviesException extends RuntimeException {

    public MoviesException(String message) {
        super(message);
    }

    public MoviesException(String message, Throwable cause) {
        super(message, cause);
    }
}
