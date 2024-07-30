package com.buddies.movies.model;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private  String title;
    private String genre;
    private  int seasons;
    private  int episodes;
    private  String creator;
    private  String synopsis;
}
