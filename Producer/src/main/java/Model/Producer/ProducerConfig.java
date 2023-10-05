package Model.Producer;

import Sender.Sender;
import Receiver.Receiver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProducerConfig {

    @Bean
    public Queue senderQueue(){
        return new Queue("sender");
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