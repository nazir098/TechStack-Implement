package com.example.demo.publisher;

import com.example.demo.domain.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabitmqJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.json}")
    private String jsonRoutingKey;

    private RabbitTemplate rabbitTemplate;


    public RabitmqJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
    }
}
