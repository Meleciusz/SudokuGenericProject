package com.mkrasucki.Producer;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ProducerApplication{

	//static final String topicExchangeName = "spring-boot-exchange";

	static final String fanoutExchangeName = "fanoutExchange";
	static final String queueName = "spring-boot";


	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}



//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange(fanoutExchangeName);
//	}

	FanoutExchange fanoutExchange() {
		return new FanoutExchange(fanoutExchangeName);
	}

	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	public static void main(String[] args) throws InterruptedException{

		SpringApplication.run(ProducerApplication.class, args).close();
	}

}
