package com.buddies.movies.controller;

import com.buddies.movies.entity.MoviesRequestDTO;
import com.buddies.movies.model.Movies;

import com.buddies.movies.service.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/movies")
@AllArgsConstructor
@RestController
public class MovieController {
    private MoviesService moviesService;
    @GetMapping
    public ResponseEntity<List<Movies>> getAllMovies() {
        List<Movies> movies = moviesService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movies> getMovieID(@PathVariable Long id) {
        Optional<Movies> bookRatingOptional;
        bookRatingOptional = moviesService.getMovie(id);
        return bookRatingOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MoviesRequestDTO movies) {
        try {
            ResponseEntity<?> savedNovies = moviesService.salvar(movies);
            return ResponseEntity.ok().body(savedNovies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        return (ResponseEntity<Void>) moviesService.deleteMovies(id);
    }
    @PatchMapping("/{movies_id}")
    public ResponseEntity<Movies> updateBook(@PathVariable Long movies_id, @RequestBody MoviesRequestDTO data) {
        return moviesService.updateMovies(movies_id, data);
    }

}
