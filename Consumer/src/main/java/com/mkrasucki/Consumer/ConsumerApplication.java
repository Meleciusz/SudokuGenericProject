package com.mkrasucki.Consumer;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {


	//main function, that starts the Spring after securing that RabbitMQ becomes available
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