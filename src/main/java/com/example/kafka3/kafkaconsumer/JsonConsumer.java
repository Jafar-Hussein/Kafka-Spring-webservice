package com.example.kafka3.kafkaconsumer;

import com.example.kafka3.interfaces.MovieRepository;
import com.example.kafka3.payload.MovieInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumer.class);

    private final MovieRepository movieRepository;
    public JsonConsumer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "movieListTopic", groupId = "myGroup")
    public void consume(MovieInfo message) {
        movieRepository.save(message);
        LOGGER.info(String.format("Message received %s", message));
    }
}