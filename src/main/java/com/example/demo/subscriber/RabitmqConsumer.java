package com.example.demo.subscriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabitmqConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public String consumer(String message){
        System.out.println("consumed rabitmq message is " + message);
        return message;

    }
}
