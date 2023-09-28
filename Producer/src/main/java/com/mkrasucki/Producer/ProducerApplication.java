package com.mkrasucki.Producer;



import Repository.Task;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mkrasucki.Producer.ProducerController.allMessages;


@SpringBootApplication
public class ProducerApplication{

	//main function, that starts the Spring after securing that RabbitMQ becomes available


	public static void main(String[] args) throws InterruptedException {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq");
		boolean connected = false;
		while (! connected) {
			try {
				Connection connection = connectionFactory.createConnection();
				connected = true;
				connection.close();

				SpringApplication.run(ProducerApplication.class, args);

//				ExecutorService executorService = Executors.newSingleThreadExecutor();
//				executorService.execute(() -> {
//					while(true){
//						showProgress();
//						try{
//							Thread.sleep(1000);
//						}catch (InterruptedException e) {
//							Thread.currentThread().interrupt();
//						}
//					}
//				});

			} catch (Exception e) {
				System.out.println("Waiting for RabbitMQ to become available...");
				Thread.sleep(5000);
			}
		}
	}


	public static void showProgress(){
		for(Task task : allMessages){
			System.out.println(task.getState());
		}
	}
}