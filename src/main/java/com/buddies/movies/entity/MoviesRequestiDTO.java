package com.buddies.movies.entity;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MoviesRequestiDTO(String title, String genre, LocalDate releaseDat, String director, String synopsis) {
}
