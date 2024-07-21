package com.buddies.movies.service;

import com.buddies.movies.entity.MoviesRequestiDTO;
import com.buddies.movies.model.Movies;
import com.buddies.movies.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service

public class MoviesService {

     @Autowired
     private MoviesRepository moviesRepository;

     public List<Movies> getAllMovies(){return moviesRepository.findAll();

     }
     public Optional<Movies> getMovie(Long movies_id) {
          return moviesRepository.findById(movies_id);
     }

     public ResponseEntity<?> deleteMovies(Long movies_id) {
          return moviesRepository.findById(movies_id)
                  .map(movies -> {
                       moviesRepository.delete(movies);
                       return ResponseEntity.noContent().build();
                  })
                  .orElseGet(() -> ResponseEntity.notFound().build());
     }

     public ResponseEntity<?> salvar(@RequestBody MoviesRequestiDTO moviesRequestDTO) {
          Movies movies = new Movies();
          movies.setTitle(moviesRequestDTO.title());
          movies.setGenre(moviesRequestDTO.genre());
          movies.setReleaseDate(moviesRequestDTO.releaseDat());
          movies.setDirector(moviesRequestDTO.director());
          movies.setSynopsis(moviesRequestDTO.synopsis());
          moviesRepository.save(movies);
          return ResponseEntity.ok().build();
     }


}
