package com.example.kafka3.interfaces;


import com.example.kafka3.payload.MovieInfo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    static MovieInfo movieInfo;
    @BeforeEach
    void setUp() {
        System.out.println("setUp before each test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown after each test");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("after all tests");
    }
    @Test
    @Order(1)
    void createMovie() {
        //skapa en film
        MovieInfo movie = new MovieInfo();
        movie.setMovieTitle("The Matrix");
        movie.setMovieGenre("Action");
        movie.setMovieReleaseDate("1999");

        //spara filmen i databasen
        movieInfo = movieRepository.save(movie);

        assertNotNull(movieRepository.findById(movieInfo.getId()).get().getMovieTitle());
    }
    @Test
    @Order(2)
    void updateMovie(){
        //hämta filmen från databasen;
        MovieInfo movie = movieRepository.findById(movieInfo.getId()).get();
        assertNotNull(movie);
        //uppdatera filmen
        movie.setMovieTitle("The Matrix Reloaded");
        //spara filmen i databasen
        movieRepository.save(movie);

        assertEquals("The Matrix Reloaded", movieRepository.findById(movieInfo.getId()).get().getMovieTitle());
    }
    @Test
    @Order(3)
    void deleteMovie(){
        assertNotNull(movieRepository.findById(movieInfo.getId()).get());

        //ta bort filmen från databasen
        movieRepository.deleteById(movieInfo.getId());
    }

}