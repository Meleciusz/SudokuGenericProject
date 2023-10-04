package Model.Producer;



import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



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
//						if(!allMessages.stream().allMatch(task -> "COMPLETED".equals(task.getState()))
//								&& !allMessages.stream().allMatch(task -> "ADDED".equals(task.getState()))){
//						showProgress();
//							try{
//								Thread.sleep(1000);
//							}catch (InterruptedException e) {
//								Thread.currentThread().interrupt();
//								}
//						}
//					}
//				});

			} catch (Exception e) {
				System.out.println("Waiting for RabbitMQ to become available...");
				Thread.sleep(5000);
			}
		}
	}


//	public static void showProgress(){
//		System.out.println("");
//		System.out.println("Task status:         ");
//		for(Task task : allMessages){
//			System.out.println(task.getID()+ " " +task.getState());
//		}
//	}
}