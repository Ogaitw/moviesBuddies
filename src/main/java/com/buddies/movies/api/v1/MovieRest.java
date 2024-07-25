package com.buddies.movies.api.v1;

import com.buddies.movies.entity.MoviesRequestDTO;
import com.buddies.movies.entity.MoviesResponseDTO;
import com.buddies.movies.model.Movies;

import com.buddies.movies.service.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/movies/v1")
@AllArgsConstructor
@RestController
public class MovieRest {
    private MoviesService moviesService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Page<MoviesResponseDTO> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @Max(50) int size) {
        return moviesService.getAllMovies(PageRequest.of(page, size));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MoviesResponseDTO getMovieID(@PathVariable Long id) {
        return moviesService.getMovie(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void saveMovie(@RequestBody MoviesRequestDTO movies) {
        moviesService.saveMovie(movies);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        moviesService.deleteMovies(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public void updateMovie(@PathVariable Long id, @RequestBody MoviesRequestDTO data) {
        moviesService.updateMovies(id, data);
    }

}
