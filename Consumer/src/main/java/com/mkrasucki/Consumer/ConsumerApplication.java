package com.mkrasucki.Consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) throws InterruptedException{


		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq");
		boolean connected = false;
		while (!connected) {
			try {
				Connection connection = connectionFactory.createConnection();
				connected = true;
				connection.close();
				SpringApplication.run(ConsumerApplication.class, args);
			} catch (Exception e) {
				System.out.println("Waiting for RabbitMQ to become available...");
				Thread.sleep(5000);
			}
		}

	}
}