package com.mkrasucki.Consumer;

import Receiver.Receiver;
import Sender.Sender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Consumer config
@Configuration
public class ConsumerConfig {

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