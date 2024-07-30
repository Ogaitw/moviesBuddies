package com.buddies.movies.api.v1;

import com.buddies.movies.entity.MoviesRequestDTO;
import com.buddies.movies.entity.MoviesResponseDTO;
import com.buddies.movies.entity.SeriesRequestDTO;
import com.buddies.movies.entity.SeriesResponseDTO;
import com.buddies.movies.model.Series;
import com.buddies.movies.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/series")
@RestController
public class SeriesRest {
    @Autowired
    private SeriesService seriesService;



    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Page<SeriesResponseDTO> getAllSeries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @Max(50) int size) {
        return seriesService.getAllSeries(PageRequest.of(page, size));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public SeriesResponseDTO getSerieId(@PathVariable Long id) {
        return seriesService.getSeriesID(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void SaveSerie(@RequestBody SeriesRequestDTO seriesRequestDTO) {
        seriesService.saveSeries(seriesRequestDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public void updateSeries(@PathVariable Long id, @RequestBody SeriesRequestDTO seriesRequestDTO) {
        seriesService.updateSeries(id, seriesRequestDTO);
    }












}
