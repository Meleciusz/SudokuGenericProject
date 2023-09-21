package com.mkrasucki.Consumer;

import Receiver.Receiver;
import Sender.Sender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

//    Receiver

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(FanoutExchange fanout,
                            Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
    }


    @Bean
    public Receiver receiver() {
        return new Receiver();
    }


//    Sender

    @Bean
    public Queue returnQueue() {
        return new Queue("returnQueue");
    }

    @Bean
    public Sender sender() {
        return new Sender(returnQueue());
    }
}