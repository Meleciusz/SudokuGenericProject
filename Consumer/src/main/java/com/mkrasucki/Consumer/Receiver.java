package com.mkrasucki.Consumer;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "spring-boot")
public class Receiver {

    private final int instance;

    public Receiver(int instance){
        this.instance = instance;
    }

    @RabbitHandler
    public void receive(String message) throws InterruptedException{

        System.out.println("instance " + this.instance +
                " [x] Received '" + "'");
    }

    //private CountDownLatch latch = new CountDownLatch(1);


//    public void receiveMessage(String message) {
//        System.out.println("Received <" + message + ">");
//        //latch.countDown();
//    }

//    public CountDownLatch getLatch() {
//        return latch;
//    }

}
