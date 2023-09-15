package com.mkrasucki.Producer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Receiver {

    @RabbitListener(queues = "returnQueue")
    public void receive(String message) throws InterruptedException {
        System.out.println(" [x] Received '" + message + "'");
    }
}
