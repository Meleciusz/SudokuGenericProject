package com.mkrasucki.Producer;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Sender implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void run(String... args) throws Exception {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq");
        boolean connected = false;
        while (!connected) {
            try {
                Connection connection = connectionFactory.createConnection();
                connected = true;
                connection.close();
                System.out.println("Sending message...");

                rabbitTemplate.convertAndSend(ProducerApplication.fanoutExchangeName, "foo.bar.baz", "SUDOKU");
            } catch (Exception e) {
                System.out.println("Waiting for RabbitMQ to become available...");
                Thread.sleep(5000);
            }
        }
    }
}
