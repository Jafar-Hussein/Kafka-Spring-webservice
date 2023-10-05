package com.example.Consumer;


import com.example.interfaces.MovieRepository;
import com.example.payload.MovieInfo;
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

    @KafkaListener(topics = "movieListTopic", groupId = "myGroup") // lyssna på topic "movieListTopic" med groupId "myGroup"
    public void consume(MovieInfo message) {
        // spara meddelandet i databasen
        movieRepository.save(message);
        // logga meddelandet
        LOGGER.info(String.format("Message received %s", message));
    }
}