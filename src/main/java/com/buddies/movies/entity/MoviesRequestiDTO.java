package com.buddies.movies.entity;

import java.time.LocalDate;

public record MoviesRequestiDTO(String title, String genre, LocalDate releaseDat, String director, String synopsis) {
}
