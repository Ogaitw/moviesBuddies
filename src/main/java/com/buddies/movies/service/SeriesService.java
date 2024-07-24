package com.buddies.movies.service;

import com.buddies.movies.entity.SeriesRequestDTO;
import com.buddies.movies.model.Series;
import com.buddies.movies.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }
    public Optional<Series> getMovie(Long movies_id) {
        return seriesRepository.findById(movies_id);
    }

    public ResponseEntity<?> deleteSeries(Long id) {
        return seriesRepository.findById(id)
                .map(movies -> {
                    seriesRepository.delete(movies);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    public ResponseEntity<?> salvarSeries(@RequestBody SeriesRequestDTO seriesRequestDTO) {
        Series series = new Series();
        series.setTitle(seriesRequestDTO.title());
        series.setGenre(seriesRequestDTO.genre());
        series.setSeasons(seriesRequestDTO.seasons());
        series.setEpisodes(seriesRequestDTO.episodes());
        series.setSynopsis(seriesRequestDTO.synopsis());
        series.setCreator(seriesRequestDTO.creator());
        seriesRepository.save(series);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<Series> updateSeries(@PathVariable Long id, @RequestBody SeriesRequestDTO data) {
        Optional<Series> optionalMovies = seriesRepository.findById(id);

        if (optionalMovies.isPresent()) {
            Series series = optionalMovies.get();

            series.setTitle(data.title());
            series.setGenre(data.genre());
            series.setSeasons(data.seasons());
            series.setSynopsis(data.synopsis());
            series.setEpisodes(data.episodes());
            series.setCreator(data.creator());
            seriesRepository.save(series);

            return ResponseEntity.ok().body(series);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
