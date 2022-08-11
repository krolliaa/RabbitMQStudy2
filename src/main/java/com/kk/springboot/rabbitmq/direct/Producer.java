package com.kk.springboot.rabbitmq.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        rabbitTemplate.convertAndSend("key.direct", "Hello Direct RabbitMQ");
    }
}
