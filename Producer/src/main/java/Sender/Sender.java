package Sender;


import Model.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.amqp.core.Queue;
import Repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    private Queue queue;

    @Autowired
    public Sender(@Qualifier("returnQueue") Queue senderQueue) {
        this.queue = senderQueue;
    }

//    private ObjectMapper mapper = new ObjectMapper();


    private static Repository repository = Repository.getRepository();
    private ObjectMapper objectMapper = new ObjectMapper();

    //If user send to message to queue send() method is called
    public void send(Task message) throws JsonProcessingException {
        repository.add(message);
        repository.setStateById(repository.getID(), "ADDED");

        System.out.println("ID:" + repository.getID() + " [x] Sent " + message + " state: " + repository.getStateById(repository.getID()));

        String json = objectMapper.writeValueAsString(message);
        template.convertAndSend(queue.getName(), json);
    }

    //curl http://localhost:8080/send?message=..41..3.8.1....62...82..4.....3.28.9....7....7.16.8...562..17.3.3.....4.1....5...
    //docker run --name dbTasks -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

}