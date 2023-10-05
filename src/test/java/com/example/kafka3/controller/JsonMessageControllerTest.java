package com.example.kafka3.controller;

import com.example.kafka3.kafkaproducer.JsonProducer;

import com.example.kafka3.payload.MovieInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(JsonMessageController.class)
public class JsonMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JsonProducer kafkaProducer;

    @Autowired
    private JsonMessageController jsonMessageController;

    @BeforeEach
    public void setup() {
        // Mock the behavior of the kafkaProducer.sendMessage() method
        when(kafkaProducer.sendMessage(any(MovieInfo.class))).thenReturn(true);
    }


    @Test
    public void testPublish() throws Exception {
        // Create a sample MovieInfo object
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setId(1L);
        movieInfo.setMovieTitle("Test Movie");
        movieInfo.setMovieGenre("Test Genre");
        movieInfo.setMovieReleaseDate("Test Release Date");
        movieInfo.setMovieReleaseDate("Test Release Date");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/kafka/json/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Movie\",\"director\":\"Test Director\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("JSON message sent to Kafka Topic"));
    }
}