package com.example.demo.config;

import com.example.demo.model.CustomerVisitEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
@ConfigurationProperties(prefix = "demo.kafka")
public class kafkaTopicConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public ApplicationRunner runner(final KafkaTemplate<String,String> kafkaTemplate, final kafkaTopicConfig kafkaTopicConfig) throws JsonProcessingException {

        final CustomerVisitEvent event= CustomerVisitEvent.builder().
                customerId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now())
                .build();
        final String payload= objectMapper.writeValueAsString(event);

        return args -> {
            kafkaTemplate.send(kafkaTopicConfig.getTopic(),payload);
        };
    }

    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public kafkaTopicConfig() {
    }

}
