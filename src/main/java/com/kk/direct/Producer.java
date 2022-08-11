package com.kk.direct;

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
        channel.exchangeDeclare("exchange.type.direct", BuiltinExchangeType.DIRECT, true, false, null);
        channel.queueDeclare("queue.type.direct1", true, false, false, null);
        channel.queueDeclare("queue.type.direct2", true, false, false, null);
        channel.queueDeclare("queue.type.direct3", true, false, false, null);
        channel.queueBind("queue.type.direct1", "exchange.type.direct", "key.typ.direct");
        channel.queueBind("queue.type.direct2", "exchange.type.direct", "key.*.direct");
        channel.queueBind("queue.type.direct3", "exchange.type.direct", "key.tyq.direct");
        channel.basicPublish("exchange.type.direct", "key.typ.direct", null, "Hello RabbitMQ!".getBytes());
    }
}
