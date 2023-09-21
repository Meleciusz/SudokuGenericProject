package com.mkrasucki.Producer;

import Receiver.Receiver;
import Sender.Sender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProducerConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}