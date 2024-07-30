package com.buddies.movies.service;

import com.buddies.movies.api.v1.builder.BuilderUtils;

import com.buddies.movies.entity.SeriesRequestDTO;
import com.buddies.movies.entity.SeriesResponseDTO;

import com.buddies.movies.exception.SeriesException;


import com.buddies.movies.model.Series;
import com.buddies.movies.repository.SeriesRepository;
import com.buddies.movies.util.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Optional;

import static com.buddies.movies.util.ErrorMessages.*;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    public Page<SeriesResponseDTO> getAllSeries(Pageable pageable) {
        Page<Series> seriesPage = seriesRepository.findAll(pageable);
        return seriesPage.map(BuilderUtils::toSeriesResponseDTO);
    }

    public SeriesResponseDTO getSeriesID(Long id) {
        try {
            return seriesRepository.findById(id)
                    .map(BuilderUtils::toSeriesResponseDTO)
                    .orElse(null);

        } catch (Exception e) {
            throw new SeriesException(ErrorMessages.SERIE_NOT_FOUND,e);
        }

    }

    public void saveSeries(SeriesRequestDTO seriesRequestDTO) {
        try {
            var series = BuilderUtils.toSeriesEntity(null, seriesRequestDTO);
            seriesRepository.save(series);
        } catch (Exception e) {
            throw new SeriesException(SERIE_NOT_SAVED, e);
        }
    }
    public void deleteSeries(Long id) {

        try {
            boolean exists = seriesRepository.existsById(id);
            if (exists) {
                seriesRepository.deleteById(id);
            }

        } catch (Exception e
        ) {
            if (e instanceof NoSuchElementException) {
                throw new SeriesException(SERIE_NOT_FOUND, e);
            }else {
                throw new SeriesException(ERROR_DELETE_SERIE, e);
            }
        }
    }

    public void  updateSeries(Long id, SeriesRequestDTO seriesRequestDTO) {
    try {
        Optional<Series> optionalSeries = seriesRepository.findById(id);
    if(optionalSeries.isPresent()) {
       var series = BuilderUtils.toSeriesEntity(optionalSeries.get().getId(), seriesRequestDTO);

        seriesRepository.save(series);

    }

    } catch (Exception e) {

        if (ErrorMessages.SERIE_NOT_FOUND.equals(e.getMessage()) ) {
            throw new SeriesException(SERIE_NOT_FOUND, e);
            } else {
        throw new SeriesException(ERROR_UPDATE_SERIE, e);}

        }
    }
}