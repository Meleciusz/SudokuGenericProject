package Receiver;

import Sudoku.SudokuSolver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.ArrayList;
import java.util.List;

public class Receiver {

    private int messageSize = 60;
    private int instanceSize = 3;
    private static List<int[][]> messages = new ArrayList<>();

    private SudokuSolver sudokuSolver;

    //If message is detected in the queue make receive() method
    @RabbitListener(queues = "returnQueue")
    public void receive(List<int[][]> message) throws InterruptedException {
        System.out.println(" [x] Received ");

        for(int i = 0; i < message.size(); i++){
            messages.add(message.get(i));
        }

        //If we have enough messages(From every instance) find best board
        if(messages.size() == messageSize * instanceSize){
            System.out.println();
            System.out.println();
            System.out.println();
            sudokuSolver = new SudokuSolver(messages);
            sudokuSolver.findBestSudoku();
        }
    }
}
