package com.example.kafkaconsumer;

import com.example.payload.MovieInfo;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.simple.JSONObject;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

@Service
public class ClientConsumer {
    private final Properties prop = new Properties();

    public static String sendToWebAPI(JSONObject myObj) { // Skicka JSON till WebAPI
        // Skapar en variabel för att lagra svaret från WebAPI
        String returnResp = "";
        // Skapar en HTTP-klient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/kafka/json/publish"); // Skapa en HTTP POST-förfrågan till WebAPI

            // Skapa en JSON-förfrågningskropp
            String jsonPayload = myObj.toJSONString();
            // Skapa en HTTP-förfrågningskropp med JSON-förfrågningskroppen
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            // Lägg till HTTP-förfrågningskroppen till HTTP POST-förfrågan
            httpPost.setEntity(entity);

            // Skicka förfrågan och hantera svaret
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Hämta svaret från WebAPI
                HttpEntity responseEntity = response.getEntity();
                // Om svaret inte är null, konvertera svaret till en sträng och lagra i returnResp
                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity);
                    System.out.println("Svar från server: " + responseString);
                    returnResp = responseString;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return returnResp;
    }

    public static ArrayList<MovieInfo> getDataFromKafka(String topicName) {
        // Skapar properties för consumer
        Properties props = new Properties();
        // Lägger till konfiguration för consumer
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");

        // Skapar consumer med properties för att kunna hämta data från topic
        Consumer<String, MovieInfo> consumer = new KafkaConsumer<>(props);
        // Assignar topic till consumer
        consumer.assign(Collections.singletonList(new TopicPartition(topicName, 0)));

        // Gå till början av topic
        consumer.seekToBeginning(consumer.assignment());

        // Skapar en lista för att lagra meddelanden
        ArrayList<MovieInfo> movies = new ArrayList<MovieInfo>();

        // While-loop som hämtar i JSON-format
        while (true) {
            ConsumerRecords<String, MovieInfo> records = consumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) continue;
            for (ConsumerRecord<String, MovieInfo> record : records) {
                movies.add(record.value());
            }
            break;
        }
        // En for-loop som skriver ut alla filmer i listan med film titel
        for (MovieInfo movie : movies) {
            System.out.println(movie.getMovieTitle());
        }
        return movies;
    }
}
