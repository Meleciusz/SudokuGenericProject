package Model.Producer;

import Sender.Sender;
import Receiver.Receiver;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProducerConfig {

    @Value("${returnQueue.name}")
    private String returnQueueName;

    @Bean
    public Queue senderQueue(){
        return new Queue(returnQueueName);
    }

    //Create sender profile
    @Bean
    public Sender sender() {
        return new Sender(senderQueue());
    }

    //Create receiver profile
    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}