package com.example.kafkaproducer;


import com.example.payload.MovieInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger((JsonProducer.class));
    private KafkaTemplate<String, MovieInfo> kafkaTemplate;

    public JsonProducer(KafkaTemplate<String, MovieInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean sendMessage(MovieInfo data) {
        // kolla om data är null eller om någon av dess fält är null
        if (data == null || data.getMovieTitle() == null || data.getMovieGenre() == null || data.getMovieReleaseDate() == null) {
            LOGGER.error("Error: MovieInfo data contains null values.");
            return false;
        }

        Message<MovieInfo>message = MessageBuilder // skapa ett meddelande med hjälp av MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "movieListTopic")
                .build();
        // skicka meddelandet till Kafka
        kafkaTemplate.send( message);
        // logga meddelandet
        LOGGER.info(String.format("Message sent -> %s", data.toString()));

        return true;
    }

}