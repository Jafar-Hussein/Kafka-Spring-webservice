package com.example.controller;



import com.example.kafkaproducer.JsonProducer;
import com.example.payload.MovieInfo;
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

    // Endpoint för att skicka JSON till Kafka
    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody MovieInfo movieInfo) {
        kafkaProducer.sendMessage(movieInfo);
        return ResponseEntity.ok("JSON message sent to Kafka Topic");
    }
}