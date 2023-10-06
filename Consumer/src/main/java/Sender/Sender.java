package Sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.List;

@Slf4j
public class Sender {

    @Autowired
    private RabbitTemplate template;
    private Queue queue;

    @Autowired
    public Sender(@Qualifier Queue returnQueue) {
        this.queue = returnQueue;
    }

    //If user send to message to queue send() method is called
    public void send(List<int[][]> message){
        log.info(" [x] Sent "  );
        this.template.convertAndSend(queue.getName(), message);
    }
}