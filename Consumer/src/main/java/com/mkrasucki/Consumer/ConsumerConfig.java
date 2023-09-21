package com.mkrasucki.Consumer;

import Receiver.Receiver;
import Sender.Sender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Consumer config
@Configuration
public class ConsumerConfig {

    //    Receiver profile ↓

    // Define which exchange is using + binding name
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    //Create queue
    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    //Bind queue to exchange
    @Bean
    public Binding binding1(FanoutExchange fanout,
                            Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
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