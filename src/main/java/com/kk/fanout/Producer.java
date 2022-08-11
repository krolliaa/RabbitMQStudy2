package com.kk.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("exchange.type.fanout", BuiltinExchangeType.FANOUT, true, false, null);
            channel.queueDeclare("queue.type.fanout1", true, false, false, null);
            channel.queueDeclare("queue.type.fanout2", true, false, false, null);
            channel.queueDeclare("queue.type.fanout3", true, false, false, null);
            String messageToSend = "Hello Rabbit!";
            channel.queueBind("queue.type.fanout1", "exchange.type.fanout", "key.fanout");
            channel.queueBind("queue.type.fanout2", "exchange.type.fanout", "key.fanout");
            channel.queueBind("queue.type.fanout3", "exchange.type.fanout", "key.fanout");
            channel.basicPublish("exchange.type.fanout", "key.fanout", null, messageToSend.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
