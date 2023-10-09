package com.example.interfaces;


import com.example.payload.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieInfo, Long> { // JpaRepository är en interface som innehåller metoder för att hämta data från databasen
    // JpaRepository<MovieInfo, Long> säger att vi vill hämta data från databasen av typen MovieInfo och att id:n är av typen Long

}
