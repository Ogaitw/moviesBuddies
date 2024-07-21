package com.buddies.movies.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movies {

   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long movies_id;

    private String title;
    private String genre;
    private LocalDate releaseDate;
    private String director;
    private String synopsis;


}
