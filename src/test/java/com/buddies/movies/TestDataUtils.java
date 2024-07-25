package com.buddies.movies;


import com.buddies.movies.entity.MoviesRequestDTO;
import com.buddies.movies.entity.MoviesResponseDTO;
import com.buddies.movies.model.Movies;

import java.time.LocalDate;
import java.util.List;

public class TestDataUtils {

    public static Movies createMoviesEntity(){
        return Movies.builder()
                .movies_id(1L)
                .title("Movie 1")
                .genre("Action")
                .releaseDate(LocalDate.now())
                .director("Director 1")
                .synopsis("Synopsis 1")
                .build();
    }

    public static MoviesResponseDTO createMovieResponseDTO1() {
        return MoviesResponseDTO.builder()
                .movies_id(1L)
                .title("Movie 1")
                .genre("Action")
                .releaseDate(LocalDate.now())
                .director("Director 1")
                .synopsis("Synopsis 1")
                .build();
    }

    public static MoviesResponseDTO createMovieResponseDTO2() {
        return MoviesResponseDTO.builder()
                .movies_id(2L)
                .title("Movie 2")
                .genre("Comedy")
                .releaseDate(LocalDate.now())
                .director("Director 2")
                .synopsis("Synopsis 2")
                .build();
    }

    public static List<MoviesResponseDTO> createMoviesResponseDTOList() {
        return List.of(createMovieResponseDTO1(), createMovieResponseDTO2());
    }

    public static MoviesRequestDTO createMovieRequestDTO(){
        return MoviesRequestDTO.builder()
                .title("Title")
                .director("")
                .genre("")
                .releaseDate(LocalDate.now())
                .synopsis("")
                .build();
    }
}
