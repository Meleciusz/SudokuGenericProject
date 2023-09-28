package Receiver;

import Answer.Answer;
import Sudoku.SudokuSolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkrasucki.Producer.ProducerController;
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
    public void receive(String message){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            Answer answer = objectMapper.readValue(message, Answer.class);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(" [x] Received Sudoku "+answer.getID());
            ProducerController.allMessages.get(answer.getID() - 1).setState("COMPLETED");


            sudokuSolver = new SudokuSolver(answer.getLastPopulation());
            sudokuSolver.findBestSudoku();

        }catch(Exception e){
            e.printStackTrace();
        }

        //If we have enough messages(From every instance) find best board
            

        messages.clear();

    }
}
