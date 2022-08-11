package com.kk;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitMqStudy2ApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void producer1() {
        rabbitTemplate.convertAndSend("exchange.fanout", "key.direct", "Hello Direct RabbitMQ");
    }

    @Test
    void producer2() {
        rabbitTemplate.convertAndSend("exchange.direct", "hotCoffee", "Hello Coffee");
    }
}
