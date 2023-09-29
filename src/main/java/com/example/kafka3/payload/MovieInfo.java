package com.example.kafka3.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class MovieInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String movieTitle;
    @Lob
    private String movieGenre;
    @Lob
    private String movieReleaseDate;

    @Override
    public String toString() {
        return "MovieInfo{" +
                "id=" + id +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieGenre='" + movieGenre + '\'' +
                ", movieReleaseDate='" + movieReleaseDate + '\'' +
                '}';
    }
}