package Sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import Sudoku.SudokuSolver;

import java.util.List;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    private Queue queue;


    @Autowired
    public Sender(@Qualifier("returnQueue") Queue returnQueue) {
        this.queue = returnQueue;
    }

    public void send() {
        List<int[][]> message = SudokuSolver.getBestOfFive();
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}