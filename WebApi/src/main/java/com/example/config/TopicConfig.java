package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic myTopic() { // skapa en topic med namnet "movieListTopic"
        return TopicBuilder.name("movieListTopic")
                .build();
    }
}
