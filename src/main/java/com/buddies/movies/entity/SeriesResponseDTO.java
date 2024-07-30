package com.buddies.movies.entity;


import lombok.Builder;

@Builder
public record SeriesResponseDTO(Long id, String title, String genre, int seasons, int episodes, String creator,String synopsis) {



}
