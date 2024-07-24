package com.buddies.movies.entity;

import lombok.Builder;

@Builder
public record SeriesRequestDTO(String title, String genre, int seasons, int episodes, String creator, String synopsis) {
}
