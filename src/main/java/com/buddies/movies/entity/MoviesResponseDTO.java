package com.buddies.movies.entity;


import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MoviesResponseDTO(Long movies_id, String title, String genre, LocalDate releaseDate, String director, String synopsis) {
}

