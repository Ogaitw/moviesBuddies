package com.buddies.movies.ControllerTest;

import com.buddies.movies.TestDataUtils;
import com.buddies.movies.api.v1.MovieRest;
import com.buddies.movies.entity.MoviesRequestDTO;
import com.buddies.movies.entity.MoviesResponseDTO;
import com.buddies.movies.repository.MoviesRepository;
import com.buddies.movies.service.MoviesService;
import com.buddies.movies.util.ErrorMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.server.ResponseStatusException;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MoviesRestTest {

    @InjectMocks
    private MovieRest movieController;

    @Mock
    private MoviesService moviesService;

    @Mock
    private MoviesRepository moviesRepository;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestCaptor;

    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());



        mockMvc = MockMvcBuilders.standaloneSetup(movieController)
                .setValidator(validator)
                .build();
    }

    @Test
    void sucessGetAllMovies() throws Exception {
        mockMvc.perform(get("/api/movies/v1"))
                .andExpect(status().isOk());
        verify(moviesService).getAllMovies(PageRequest.of(0,10));
        verifyNoInteractions(moviesRepository);
    }

    @Test
    void sucessGetMovieById() throws Exception {
        MoviesResponseDTO movieResponseDTO = TestDataUtils.createMovieResponseDTO1();
        when(moviesService.getMovie(1L)).thenReturn(movieResponseDTO);

        mockMvc.perform(get("/api/movies/v1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(moviesService).getMovie(1L);
        verifyNoMoreInteractions(moviesService);
    }

    @Test
    void movieNotFound() throws Exception {
        when(moviesService.getMovie(1L)).thenAnswer(invocation -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessages.MOVIE_NOT_FOUND);
        });

        mockMvc.perform(get("/api/movies/v1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveMovieSuccess() throws Exception {

        final var movieDTO =  objectMapper.writeValueAsString(TestDataUtils.createMovieRequestDTO());
        doNothing().when(moviesService).saveMovie(any(MoviesRequestDTO.class));

        mockMvc.perform(post("/api/movies/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieDTO))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMovieSuccess() throws Exception {
        doNothing().when(moviesService).deleteMovies(anyLong());

        mockMvc.perform(delete("/api/movies/v1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateMovieSuccess() throws Exception {
        doNothing().when(moviesService).updateMovies(anyLong(), any(MoviesRequestDTO.class));

        final var moviesRequestDTO = TestDataUtils.createMovieResponseDTO2();
        String movieJson = objectMapper.writeValueAsString(moviesRequestDTO);

        mockMvc.perform(patch("/api/movies/v1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson))
                .andExpect(status().isOk());
    }
}
