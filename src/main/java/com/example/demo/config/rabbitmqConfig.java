package com.example.demo.config;


import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class rabbitmqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;
    @Value("${rabbitmq.routing.key.json}")
    private String jsonRoutingKey;

    //springboot for rabbitmq queue
    //it uses amqp protocol
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //spring bean for queue to store json
    @Bean
    public Queue jsonQue(){
        return new Queue(jsonQueue);
    }

    //binding between exchange and queue using routing key
    @Bean
    public Binding binding(){
        return  BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding bindingJson(){
        return  BindingBuilder.bind(jsonQue())
                .to(exchange())
                .with(jsonRoutingKey);
    }


    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate amqpTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate( connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;

    }


//   springboot automatically configure these beans otherwise we have to create it explicitly
//    connectionFactory
//    RabbitTemplate
//    rabbitAdmin
}
