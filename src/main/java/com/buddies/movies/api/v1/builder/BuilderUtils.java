package com.buddies.movies.api.v1.builder;

import com.buddies.movies.entity.MoviesResponseDTO;
import com.buddies.movies.model.Movies;
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
}
