package com.kk.springboot.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class Consumer1 {

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "queue.fanout1"), exchange = @Exchange(value = "exchange.fanout", type = "fanout"))})
    public void receive(String message) {
        System.out.println("广播模式1：" + message);
    }

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "queue.fanout2"), exchange = @Exchange(value = "exchange.fanout", type = "fanout"))})
    public void receive1(String message) {
        System.out.println("广播模式2：" + message);
    }

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "queue.direct1"), exchange = @Exchange(value = "exchange.direct", type = "direct"), key = {"coffee", "tea", "milk"})})
    public void receive2(String message) {
        System.out.println("直连模式1：" + message);
    }

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "queue.direct1"), exchange = @Exchange(value = "exchange.direct", type = "direct"), key = {"hotCoffee", "hotTea", "hotMilk"})})
    public void receive3(String message) {
        System.out.println("直连模式2：" + message);
    }

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "queue.topic1"), exchange = @Exchange(value = "exchange.topic", type = "topic"), key = {"hot.coffee"})})
    public void receive4(String message) {
        System.out.println("话题模式1：" + message);
    }

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "queue.topic2"), exchange = @Exchange(value = "exchange.topic", type = "topic"), key = {"cold.coffee"})})
    public void receive5(String message) {
        System.out.println("话题模式2：" + message);
    }

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "queue.topic3"), exchange = @Exchange(value = "exchange.topic", type = "topic"), key = {"*.coffee"})})
    public void receive6(String message) {
        System.out.println("话题模式3：" + message);
    }
}
