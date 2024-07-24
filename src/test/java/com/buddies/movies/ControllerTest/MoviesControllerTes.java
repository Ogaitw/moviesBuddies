package com.buddies.movies.ControllerTest;

import com.buddies.movies.controller.MovieController;
import com.buddies.movies.entity.MoviesRequestiDTO;
import com.buddies.movies.model.Movies;
import com.buddies.movies.repository.MoviesRepository;
import com.buddies.movies.service.MoviesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@ExtendWith(MockitoExtension.class)
public class MoviesControllerTes {
    private MockMvc mockMvc;
    @Mock
    private MoviesService moviesService;
    @Mock
    private MoviesRepository moviesRepository;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp(){}


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

//    @Test
//    public void saveMovieSuccess() throws Exception {
//        // Arrange
//        MoviesRequestiDTO movieRequest = new MoviesRequestiDTO();
//        when(moviesService.salvar((MoviesRequestiDTO.class).newInstance())).thenReturn(ResponseEntity.noContent().build());
//
//        mockMvc.perform((RequestBuilder) post("/movies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.valueOf(new ObjectMapper().writeValueAsString(movieRequest))))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.movies_id", is(1)));
//
//    }
//
//    @Test
//    public void saveMovieFailure() throws Exception {
//        // Arrange
//        MoviesRequestiDTO movieRequest = new MoviesRequestiDTO();
//
//        when(moviesService.salvar(any(MoviesRequestiDTO.class))).thenThrow(new RuntimeException("Error saving movie"));
//
//        // Act & Assert
//        mockMvc.perform((RequestBuilder) post("/movies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.valueOf(new ObjectMapper().writeValueAsString(movieRequest))))
//                .andExpect(status().isBadRequest())
//                .andExpect(contentType().toString());
//
//        verify(moviesService, times(1)).salvar(any(MoviesRequestiDTO.class));
//    }
//


}
