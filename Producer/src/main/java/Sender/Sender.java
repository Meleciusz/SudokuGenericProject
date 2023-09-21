package Sender;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;


    //If user send to message to queue send() method is called
    public void send(String message) {

        template.convertAndSend(fanout.getName(), "", message);
        System.out.println(" [x] Sent '" + message + "'");
    }

    //curl http://localhost:8080/send?message=..41..3.8.1....62...82..4.....3.28.9....7....7.16.8...562..17.3.3.....4.1....5...

}