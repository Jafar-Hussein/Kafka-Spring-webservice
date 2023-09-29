package com.example.kafka3.kafkaproducer;

import com.example.kafka3.payload.MovieInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger((JsonProducer.class));
    private KafkaTemplate<String, MovieInfo> kafkaTemplate;

    public JsonProducer(KafkaTemplate<String, MovieInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //        @KafkaListener(topics = "myTopic3", groupId = "myGroup")
//    public void consume(MovieInfo message) {
//        LOGGER.info(String.format("Message received %s", message));
//    }
    public boolean sendMessage(MovieInfo data) {
        // Check if any of the fields in the MovieInfo object is null
        if (data == null || data.getMovieTitle() == null || data.getMovieGenre() == null || data.getMovieReleaseDate() == null) {
            LOGGER.error("Error: MovieInfo data contains null values.");
            return false;
        }

        // Send the Kafka message
        kafkaTemplate.send("movieListTopic", data);
        LOGGER.info(String.format("Message sent -> %s", data.toString()));

        return true;
    }

}