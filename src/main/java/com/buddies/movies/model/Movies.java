package com.buddies.movies.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
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
