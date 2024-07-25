package com.buddies.movies.api.v1;

import com.buddies.movies.entity.SeriesRequestDTO;
import com.buddies.movies.model.Series;
import com.buddies.movies.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/series")
@RestController
public class SeriesRest {
    @Autowired
    private SeriesService seriesService;



    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping
    public ResponseEntity<List<Series>> getAllSeries() {
        List<Series> bookRatingsList = seriesService.getAllSeries();
        return ResponseEntity.ok(bookRatingsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Series> getSeriesID(@PathVariable Long id) {
        Optional<Series> bookRatingOptional = seriesService.getMovie(id);
        return bookRatingOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SeriesRequestDTO seriesRequestDTO) {
        try {
            ResponseEntity<?> savedSeries = seriesService.salvarSeries(seriesRequestDTO);
            return ResponseEntity.ok().body(savedSeries);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        return (ResponseEntity<Void>) seriesService.deleteSeries(id);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Series> updateSeries(@PathVariable Long id, @RequestBody SeriesRequestDTO data) {
        return seriesService.updateSeries(id, data);
    }
}
