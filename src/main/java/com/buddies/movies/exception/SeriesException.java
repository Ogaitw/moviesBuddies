package com.buddies.movies.exception;

public class SeriesException extends RuntimeException {
    public SeriesException(String message) {super(message);


    }
    public SeriesException(String message, Throwable cause) {
        super(message, cause);
    }
}

