package com.buddies.movies.service;

import com.buddies.movies.api.v1.builder.BuilderUtils;
import com.buddies.movies.entity.MoviesRequestDTO;
import com.buddies.movies.entity.MoviesResponseDTO;
import com.buddies.movies.exception.MoviesException;
import com.buddies.movies.model.Movies;
import com.buddies.movies.repository.MoviesRepository;
import com.buddies.movies.util.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.buddies.movies.util.ErrorMessages.*;


@Service
public class MoviesService {

     @Autowired
     private MoviesRepository moviesRepository;

     public Page<MoviesResponseDTO> getAllMovies(Pageable pageable) {
          Page<Movies> moviesPage = moviesRepository.findAll(pageable);
          return moviesPage.map(BuilderUtils::toMoviesResponseDTO);
     }
     public MoviesResponseDTO getMovie(Long movies_id) {
          try {
               return moviesRepository.findById(movies_id)
                       .map(BuilderUtils::toMoviesResponseDTO)
                       .orElse(null);
          } catch (Exception e) {
               throw new MoviesException(ErrorMessages.MOVIE_NOT_FOUND, e);
          }
     }

     public void saveMovie(MoviesRequestDTO moviesRequestDTO) {
          try {
               Movies movies = Movies.builder()
                       .title(moviesRequestDTO.title())
                       .genre(moviesRequestDTO.genre())
                       .releaseDate(moviesRequestDTO.releaseDate())
                       .director(moviesRequestDTO.director())
                       .synopsis(moviesRequestDTO.synopsis())
                       .build();
               moviesRepository.save(movies);
          } catch (Exception e) {
               throw new MoviesException(MOVIE_NOT_SAVED, e);
          }
     }

     public void deleteMovies(Long movies_id) {
          try {
               boolean exists = moviesRepository.existsById(movies_id);
               if (exists) {
                    moviesRepository.deleteById(movies_id);
               }
          } catch (Exception e) {
               if (e instanceof NoSuchElementException) {
                    throw new MoviesException(ErrorMessages.MOVIE_NOT_FOUND, e);
               } else {
                    throw new MoviesException(ERROR_DELETE_MOVIE, e);
               }
          }
     }

     public void updateMovies(Long movies_id, MoviesRequestDTO data) {
          try {
               Optional<Movies> optionalMovies = moviesRepository.findById(movies_id);
               if (optionalMovies.isPresent()) {
                    Movies movies = optionalMovies.get();

                    movies = Movies.builder()
                            .movies_id(movies.getMovies_id())
                            .title(data.title())
                            .director(data.director())
                            .genre(data.genre())
                            .releaseDate(data.releaseDate())
                            .synopsis(data.synopsis())
                            .build();

                    moviesRepository.save(movies);
               }
          } catch (Exception e) {
               if (ErrorMessages.MOVIE_NOT_FOUND.equals(e.getMessage())) {
                    throw new MoviesException(ErrorMessages.MOVIE_NOT_FOUND, e);
               } else {
                    throw new MoviesException(ERROR_UPDATE_MOVIE, e);
               }
          }
     }

}
