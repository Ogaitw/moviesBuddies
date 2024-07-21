package com.buddies.movies.controller;

import com.buddies.movies.entity.MoviesRequestiDTO;
import com.buddies.movies.model.Movies;
import com.buddies.movies.repository.MoviesRepository;
import com.buddies.movies.service.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/movies")
@AllArgsConstructor
@RestController
public class movieControllers {
    private final MoviesRepository moviesRepository;
    private MoviesService moviesService;
    @GetMapping
    public ResponseEntity<List<Movies>> getAllMovies() {
        List<Movies> movies = moviesService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MoviesRequestiDTO movies) {
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
}
