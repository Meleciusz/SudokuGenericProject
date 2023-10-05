package Sender;

import Repository.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.List;

public class Sender {

    @Autowired
    private RabbitTemplate template;
    private Queue queue;

    @Autowired
    public Sender(@Qualifier("returnQueue") Queue returnQueue) {
        this.queue = returnQueue;
    }

    //If user send to message to queue send() method is called
    public void send(List<int[][]> message){
        System.out.println(" [x] Sent "  );
        this.template.convertAndSend(queue.getName(), message);
    }
}