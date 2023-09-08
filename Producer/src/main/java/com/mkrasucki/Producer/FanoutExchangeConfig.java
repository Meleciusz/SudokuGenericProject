package com.mkrasucki.Producer;

import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {
    @Bean
    public AbstractExchange fanoutExchange() {
        return new FanoutExchange("fanout.exchange");
    }
}