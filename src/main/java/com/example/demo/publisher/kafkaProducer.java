package com.example.demo.publisher;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducer {

    private static final Logger logger=LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String,String> kafkaTemplate;

    public kafkaProducer(KafkaTemplate<String,String>kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void SendMessage(String message){
        kafkaTemplate.send("demoTopic",message);
        logger.info("@kafkaproducer message sent ");
    }

}
