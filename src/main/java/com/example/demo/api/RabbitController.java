package com.example.demo.api;

import com.example.demo.domain.User;
import com.example.demo.publisher.RabitmqJsonProducer;
import com.example.demo.publisher.RabitmqProducer;
import com.example.demo.subscriber.RabitmqConsumer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RabbitController {

    private RabitmqProducer rabitmqProducer;
    @Autowired
    private RabitmqJsonProducer rabitmqJsonProducer;

    @Autowired
    public RabbitController(RabitmqProducer rabitmqProducer) {
        this.rabitmqProducer = rabitmqProducer;
    }

    @Autowired
    RabitmqConsumer rabitmqConsumer;

    @GetMapping("/publish/message")
    public ResponseEntity<String> sendMessage(@RequestParam(name = "message")String message){

        rabitmqProducer.sendMessage(message);
        return ResponseEntity.ok("message sent to rabbitmq...");
    }
    @PostMapping("/publish/message")
    public ResponseEntity<String> sendJson(@RequestBody User user){

       rabitmqJsonProducer.sendJsonMessage(user);
       return ResponseEntity.ok("Rabitmq json sent :"+ user.toString() );
    }

}
