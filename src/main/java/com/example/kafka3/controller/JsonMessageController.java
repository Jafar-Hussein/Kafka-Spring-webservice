package com.example.kafka3.controller;


import com.example.kafka3.kafkaproducer.JsonProducer;
import com.example.kafka3.payload.MovieInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka/json")
public class JsonMessageController {
    private final JsonProducer kafkaProducer;

    public JsonMessageController(JsonProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // Endpoint for sending JSON messages
    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody MovieInfo movieInfo) {
        kafkaProducer.sendMessage(movieInfo);
        return ResponseEntity.ok("JSON message sent to Kafka Topic");
    }
}