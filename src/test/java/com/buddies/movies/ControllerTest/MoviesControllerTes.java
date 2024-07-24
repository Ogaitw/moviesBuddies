package com.buddies.movies.ControllerTest;

import com.buddies.movies.controller.MovieController;
import com.buddies.movies.entity.MoviesRequestDTO;
import com.buddies.movies.model.Movies;
import com.buddies.movies.service.MoviesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import static org.hamcrest.Matchers.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class MoviesControllerTes {
    @Mock
    private MockMvc mockMvc;

    @Mock
    MoviesRequestDTO  moviesRequestDTO;
    @Mock
    private MoviesService moviesService;
    @InjectMocks
    private MovieController movieController;
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }



    @Test
    void getAllMoviesSucess() throws Exception{
        when(moviesService.getAllMovies()).thenReturn(Arrays.asList(Movies.builder().build(),Movies.builder().build()));
        ResponseEntity<List<Movies>> responseEntity = movieController.getAllMovies();
        assertThat(responseEntity.getBody(), hasSize(2));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }
    @Test
    void getMoviesIdSuccess() throws Exception {

        Movies movie = Movies.builder().movies_id(1L).build();
        when(moviesService.getMovie(1L)).thenReturn(Optional.of(movie));
        ResponseEntity<Movies> responseEntity = movieController.getMovieID(1L);
        assertThat(responseEntity.getBody(), is(movie));
        assertThat(responseEntity.getBody(), notNullValue());
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }


    @Test
    void deleteMoviesSuccess() throws Exception {

        when(moviesService.deleteMovies(1L)).thenReturn(ResponseEntity.noContent().build());
        ResponseEntity<Void> response = movieController.deleteMovie(1L);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCodeValue(), is(204));
    }


    @Test
    void testSave() throws Exception {
        moviesRequestDTO = MoviesRequestDTO.builder()
                .title("fuga das depressivas")
                .genre("Sci-Fi")
                .releaseDat(LocalDate.of(2010, 7, 16))
                .director("Sharpey sancleire")
                .synopsis("Uma garota do interior resolve fugir de sua cidade")
                .build();

        ResponseEntity<MoviesRequestDTO> responseEntity = new ResponseEntity<>(moviesRequestDTO, HttpStatus.OK);
        when(moviesService.salvar(any(MoviesRequestDTO.class))).thenReturn(responseEntity.getBody());

        String jsonContent = objectMapper.writeValueAsString(movieController);

        mockMvc.perform((RequestBuilder) post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(jsonContent)))
                .andExpect(status().isOk());
    }

}
