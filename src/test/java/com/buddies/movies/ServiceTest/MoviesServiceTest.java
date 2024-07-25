package com.buddies.movies.ServiceTest;

import com.buddies.movies.TestDataUtils;
import com.buddies.movies.api.v1.builder.BuilderUtils;
import com.buddies.movies.entity.MoviesRequestDTO;
import com.buddies.movies.entity.MoviesResponseDTO;
import com.buddies.movies.exception.MoviesException;
import com.buddies.movies.model.Movies;
import com.buddies.movies.repository.MoviesRepository;
import com.buddies.movies.service.MoviesService;
import com.buddies.movies.util.ErrorMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.buddies.movies.util.ErrorMessages.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoviesServiceTest {

    @InjectMocks
    private MoviesService moviesService;

    @Mock
    private MoviesRepository moviesRepository;

    private Movies movie;
    private MoviesRequestDTO movieRequestDTO;
    private MoviesResponseDTO movieResponseDTO;
    private Page<Movies> moviePage;

    @BeforeEach
    void setUp() {
        movie = TestDataUtils.createMoviesEntity();

        movieRequestDTO = TestDataUtils.createMovieRequestDTO();

        movieResponseDTO = BuilderUtils.toMoviesResponseDTO(movie);

        moviePage = new PageImpl<>(List.of(movie));
    }

    @Test
    void testGetAllMovies() {
        Pageable pageable = PageRequest.of(0, 10);
        when(moviesRepository.findAll(pageable)).thenReturn(moviePage);

        Page<MoviesResponseDTO> responsePage = moviesService.getAllMovies(pageable);

        assertEquals(1, responsePage.getTotalElements());
        verify(moviesRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetMovieSuccess() {
        when(moviesRepository.findById(1L)).thenReturn(Optional.of(movie));

        MoviesResponseDTO response = moviesService.getMovie(1L);

        assertNotNull(response);
        assertEquals(movie.getTitle(), response.title());
        verify(moviesRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveMovieException() {
        doThrow(new RuntimeException()).when(moviesRepository).save(any(Movies.class));

        MoviesException exception = assertThrows(MoviesException.class, () -> {
            moviesService.saveMovie(movieRequestDTO);
        });

        assertEquals(MOVIE_NOT_SAVED, exception.getMessage());
        verify(moviesRepository, times(1)).save(any(Movies.class));
    }

    @Test
    void testDeleteMovieSuccess() {
        when(moviesRepository.existsById(1L)).thenReturn(true);
        doNothing().when(moviesRepository).deleteById(1L);

        moviesService.deleteMovies(1L);

        verify(moviesRepository, times(1)).existsById(1L);
        verify(moviesRepository, times(1)).deleteById(1L);
    }





}
