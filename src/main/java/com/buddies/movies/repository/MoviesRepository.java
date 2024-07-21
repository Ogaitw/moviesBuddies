package com.buddies.movies.repository;

import com.buddies.movies.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {
    boolean existsByTitle(String title);
}
