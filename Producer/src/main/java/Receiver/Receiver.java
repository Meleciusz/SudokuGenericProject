package Receiver;


import Repository.Repository;
import Sudoku.SudokuSolver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import java.util.List;

public class Receiver {

    private final String rabbitQueueName = "returnQueue";
    private SudokuSolver sudokuSolver;
    private static Repository repository = Repository.getRepository();

    //If message is detected in the queue make receive() method
    @RabbitListener(queues = rabbitQueueName)
    public void receive(List<int[][]> message){

        System.out.println();
        System.out.println();
        System.out.println();
        repository.setStateById(repository.getID(), "COMPLETED");
        System.out.println("ID:" + repository.getID() + " [x] Received "  + " state: " + repository.getStateById(repository.getID()));


        sudokuSolver = new SudokuSolver(message);
        sudokuSolver.findBestSudoku();

    }
}
