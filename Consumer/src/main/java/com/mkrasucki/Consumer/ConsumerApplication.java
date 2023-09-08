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


	static final String fanoutExchangeName = "fanoutExchange";
	//static final String topicExchangeName = "spring-boot-exchange";

	static final String queueName = "spring-boot";


	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}


//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange(fanoutExchangeName);
//	}
	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange(fanoutExchangeName);
	}

	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}


	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	public static void main(String[] args) throws InterruptedException{
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq");
		boolean connected = false;
		while (!connected) {
			try {
				Connection connection = connectionFactory.createConnection();
				connected = true;
				connection.close();
				SpringApplication.run(ConsumerApplication.class, args).close();
			} catch (Exception e) {
				System.out.println("Waiting for RabbitMQ to become available...");
				Thread.sleep(5000);
			}
		}


	}

}
