package com.buddies.movies.entity;

import com.buddies.movies.model.Movies;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MoviesResponseDTO(Long movies_id, String title, String genre, LocalDate releaseDate, String director, String synopsis) {

    public MoviesResponseDTO(Movies movies) {
        this(movies.getMovies_id(), movies.getTitle(), movies.getGenre(), movies.getReleaseDate(), movies.getDirector(), movies.getSynopsis());
    }
}

