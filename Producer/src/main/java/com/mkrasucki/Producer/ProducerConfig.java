package com.mkrasucki.Producer;

import Receiver.Receiver;
import Sender.Sender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProducerConfig {

    // Define which exchange is using + binding name
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    //Create sender profile
    @Bean
    public Sender sender() {
        return new Sender();
    }

    //Create receiver profile
    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}