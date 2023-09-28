package Sender;


import Repository.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import org.springframework.amqp.core.Queue;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    private Queue queue;

    @Autowired
    public Sender(@Qualifier("returnQueue") Queue senderQueue) {
        this.queue = senderQueue;
    }


    //If user send to message to queue send() method is called
    public void send(List<Task> message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        for(int i = 0; i < message.size(); i++){
            message.get(i).setState("SENT");
            String json = mapper.writeValueAsString(message.get(i));
            template.convertAndSend(queue.getName(), json);

            System.out.println("Task state:");
            for(Task task : message) {
                System.out.println(task.getState());
            }
        }
    }

    //curl http://localhost:8080/add?message=..41..3.8.1....62...82..4.....3.28.9....7....7.16.8...562..17.3.3.....4.1....5...
    //docker run --name dbTasks -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

}