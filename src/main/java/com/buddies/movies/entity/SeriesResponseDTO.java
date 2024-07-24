package com.buddies.movies.entity;

import com.buddies.movies.model.Series;


public record SeriesResponseDTO(Long id, String title, String genre, int seasons, int episodes, String creator) {

    public SeriesResponseDTO(Series series) {
        this(series.getId(), series.getTitle(), series.getGenre(), series.getSeasons(), series.getEpisodes(), series.getSynopsis());
    }

}
