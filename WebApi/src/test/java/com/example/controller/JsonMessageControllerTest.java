package com.example.controller;


import com.example.kafkaproducer.JsonProducer;
import com.example.payload.MovieInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;



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