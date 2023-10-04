package Receiver;

import Model.Answer;
import Repository.Repository;
import Sender.Sender;
import Sudoku.SudokuSolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.ArrayList;
import java.util.List;

public class Receiver {

    private SudokuSolver sudokuSolver;
    private Repository repository = Repository.getRepository();

    //If message is detected in the queue make receive() method
    @RabbitListener(queues = "returnQueue")
    public void receive(String message){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            Answer answer = objectMapper.readValue(message, Answer.class);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(" [x] Received Sudoku "+repository.getID());
            repository.setStateById(repository.getID(), "COMPLETED");

            sudokuSolver = new SudokuSolver(answer.getLastPopulation());
            sudokuSolver.findBestSudoku();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
