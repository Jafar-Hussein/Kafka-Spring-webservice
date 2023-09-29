package com.example.kafka3.kafkaconsumer;

import com.example.kafka3.payload.MovieInfo;
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

    public static String sendToWebAPI(JSONObject myObj) {
        String returnResp = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/kafka/json/publish");

            // Skapa en JSON-förfrågningskropp
            String jsonPayload = myObj.toJSONString();
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            // Skicka förfrågan och hantera svaret
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
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
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
        //props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonDeserializer");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");

        Consumer<String, MovieInfo> consumer = new KafkaConsumer<>(props);
        //consumer.subscribe(Collections.singletonList(topicName));
        consumer.assign(Collections.singletonList(new TopicPartition(topicName, 0)));

        //Gå till början av Topic
        consumer.seekToBeginning(consumer.assignment());

        //Create User list
        ArrayList<MovieInfo> movies = new ArrayList<MovieInfo>();

        //WhileLoop osm hämtar i JSON format
        while (true) {
            ConsumerRecords<String, MovieInfo> records = consumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) continue;
            for (ConsumerRecord<String, MovieInfo> record : records) {
                movies.add(record.value());
                //System.out.println(record.value());
                //System.out.println(record.value().getClass().toString());
/*
                //Spara datan tillbaka till ett JSONObject
                JSONObject fetchData = (JSONObject) new JSONParser().parse(record.value());

                //Skriva ut data
                System.out.println(fetchData.get("id"));
                System.out.println(fetchData.get("firstName"));
                System.out.println(fetchData.get("lastName"));

*/
            }
            break;
        }

        for (MovieInfo movie : movies) {
            System.out.println(movie.getMovieTitle());
        }
/*
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());

                //Spara datan tillbaka till ett JSONObject
                JSONObject fetchData = (JSONObject) new JSONParser().parse(record.value());

                //Skriva ut data
                System.out.println(fetchData.get("id"));
                System.out.println(fetchData.get("firstName"));
                System.out.println(fetchData.get("lastName"));


            }
        }
*/

        return movies;
    }
}