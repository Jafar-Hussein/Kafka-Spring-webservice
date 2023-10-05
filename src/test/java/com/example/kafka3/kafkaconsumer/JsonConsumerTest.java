package com.example.kafka3.kafkaconsumer;

import com.example.kafka3.interfaces.MovieRepository;
import com.example.kafka3.payload.MovieInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JsonConsumerTest {

    private JsonConsumer jsonConsumer;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jsonConsumer = new JsonConsumer(movieRepository);
    }

    @Test
    public void consumeTest(){
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setId(1L);
        movieInfo.setMovieTitle("Test Movie");
        movieInfo.setMovieGenre("Test Genre");
        movieInfo.setMovieReleaseDate("Test Release Date");
        movieInfo.setMovieReleaseDate("Test Release Date");
        jsonConsumer.consume(movieInfo);
        verify(movieRepository, times(1)).save(movieInfo);
    }
}