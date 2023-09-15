package com.mkrasucki.Producer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    private final String message = "SUDOKU";

    public void send() {

        template.convertAndSend(fanout.getName(), "", "SUDOKU");
        System.out.println(" [x] Sent '" + message + "'");
    }

    //curl http://localhost:8080/send

}