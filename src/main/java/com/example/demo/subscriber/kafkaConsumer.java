package com.example.demo.subscriber;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {
    @KafkaListener(topics = "customer.visit")
    public String listens(String in){
        System.out.println(in);
        return in;

    }
}
