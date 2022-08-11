package com.kk.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("exchange.type.topic", BuiltinExchangeType.TOPIC, true, false, null);
        channel.queueDeclare("queue.type.topic1", true, false, false, null);
        channel.queueDeclare("queue.type.topic2", true, false, false, null);
        channel.queueDeclare("queue.type.topic3", true, false, false, null);
        channel.queueBind("queue.type.topic1", "exchange.type.topic", "key.typ.topic");
        channel.queueBind("queue.type.topic2", "exchange.type.topic", "key.*.topic");
        channel.queueBind("queue.type.topic3", "exchange.type.topic", "key.tyq.topic");
        channel.basicPublish("exchange.type.topic", "key.tyq.topic", null, "Hello Topic RabbitMQ!".getBytes());
    }
}
