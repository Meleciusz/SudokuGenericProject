package Model.Consumer;

import Receiver.Receiver;
import Sender.Sender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Consumer config
@Configuration
public class ConsumerConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    //Create receiver profile
    @Bean
    public Receiver receiver() {
        return new Receiver();
    }


    //    Sender profile ↓

    //Create queue
    @Bean
    public Queue returnQueue() {
        return new Queue("returnQueue");
    }

    //Create sender profile
    @Bean
    public Sender sender() {
        return new Sender(returnQueue());
    }
}