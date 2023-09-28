package Sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import Sudoku.SudokuSolver;
import Answer.Answer;

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
    public void send(Answer message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(message);

        this.template.convertAndSend(queue.getName(), json);
    }
}