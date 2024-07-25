package com.buddies.movies.repository;

import com.buddies.movies.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository <Series,Long> {
}
