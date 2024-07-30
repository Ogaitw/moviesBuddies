package com.buddies.movies.api.v1.builder;

import com.buddies.movies.entity.MoviesResponseDTO;
import com.buddies.movies.entity.SeriesRequestDTO;
import com.buddies.movies.entity.SeriesResponseDTO;
import com.buddies.movies.model.Movies;
import com.buddies.movies.model.Series;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BuilderUtils {

    public MoviesResponseDTO toMoviesResponseDTO(Movies movies) {
        return MoviesResponseDTO.builder()
                .movies_id(movies.getMovies_id())
                .title(movies.getTitle())
                .genre(movies.getGenre())
                .releaseDate(movies.getReleaseDate())
                .director(movies.getDirector())
                .synopsis(movies.getSynopsis())
                .build();
    }
    public List<MoviesResponseDTO> toMoviesResponseDTOList(List<Movies> moviesList) {
        return moviesList.stream()
                .map(BuilderUtils::toMoviesResponseDTO)
                .collect(Collectors.toList());
    }
    public SeriesResponseDTO toSeriesResponseDTO(Series series) {
        return SeriesResponseDTO.builder()
                .id(series.getId())
                .title(series.getTitle())
                .genre(series.getGenre())
                .seasons(series.getSeasons())
                .episodes(series.getEpisodes())
                .synopsis(series.getSynopsis())
                .creator(series.getCreator())
                .build();
    }
    public List<SeriesResponseDTO> toSeriesResponseDTOList(List<Series> moviesList) {
        return moviesList.stream()
                .map(BuilderUtils::toSeriesResponseDTO)
                .collect(Collectors.toList());
    }


public Series toSeriesEntity( Long id, SeriesRequestDTO seriesRequestDTO) {
        return Series.builder()
                .id(id!=null ? id :null)
                .title(seriesRequestDTO.title())
                .genre(seriesRequestDTO.genre())
                .seasons(seriesRequestDTO.seasons())
                .episodes(seriesRequestDTO.episodes())
                .synopsis(seriesRequestDTO.synopsis())
                .creator(seriesRequestDTO.creator())
                .build();


    }

}
