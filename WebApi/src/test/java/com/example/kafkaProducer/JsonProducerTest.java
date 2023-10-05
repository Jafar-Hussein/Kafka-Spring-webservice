package com.example.kafkaProducer;



import com.example.kafkaproducer.JsonProducer;
import com.example.payload.MovieInfo;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@SpringBootTest
public class JsonProducerTest {
    @Mock
    private KafkaTemplate<String, MovieInfo> kafkaTemplate;
    @Autowired
    private JsonProducer jsonProducer;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize the mocks
        jsonProducer = new JsonProducer(kafkaTemplate);
    }

    @Test
    void sendMessage_Success() {
        // Arrange
        MovieInfo expectedMovieInfo = new MovieInfo();
        // Initialize with specific values
        expectedMovieInfo.setId(0L);
        expectedMovieInfo.setMovieTitle("The Matrix");
        expectedMovieInfo.setMovieGenre("Action");
        expectedMovieInfo.setMovieReleaseDate("1999");

        // Act
        boolean result = jsonProducer.sendMessage(expectedMovieInfo);

        // Assert
        assertTrue(result);
    }

    @Test
    void sendMessage_Failure() {
        // Arrange
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setId(0L);
        movieInfo.setMovieTitle(null); // Simulate null data
        movieInfo.setMovieGenre(null); // Simulate null data
        movieInfo.setMovieReleaseDate(null); // Simulate null data

        // Create an instance of JsonProducer with the mocked KafkaTemplate
        JsonProducer jsonProducer = new JsonProducer(kafkaTemplate);

        // Act
        boolean result = jsonProducer.sendMessage(movieInfo);

        // Assert
        assertFalse(result); // Verify that the method returns false for null data
        // Replace verifyZeroInteractions with verify
        verify(kafkaTemplate, times(0)).send(any(Message.class));

    }
    }


