package Model.Consumer;

import Receiver.Receiver;
import Sender.Sender;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Consumer config
@Configuration
public class ConsumerConfig {

    @Value("${returnQueue.name}")
    private String returnQueueName;
    //Create receiver profile
    @Bean
    public Receiver receiver() {
        return new Receiver();
    }


    //    Sender profile ↓

    //Create queue
    @Bean
    public Queue returnQueue() {
        return new Queue(returnQueueName);
    }

    //Create sender profile
    @Bean
    public Sender sender() {
        return new Sender(returnQueue());
    }
}