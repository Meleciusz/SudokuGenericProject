package Receiver;


import Repository.Repository;
import Sudoku.SudokuSolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import Model.Status;
import java.util.List;

@Slf4j
public class Receiver {


    private SudokuSolver sudokuSolver;
    private static Repository repository = Repository.getRepository();

    //If message is detected in the queue make receive() method
    @RabbitListener(queues = "${queue.name}")
    public void receive(List<int[][]> message){
        repository.setStateById(repository.getID(), Status.COMPLETED.toString());
        log.info("ID:" + repository.getID() + " [x] Received " + " state: " + repository.getStateById(repository.getID()));

        sudokuSolver = new SudokuSolver(message);
        int[][] bestBoard = sudokuSolver.findBestSudoku();
        repository.setAnswerById(repository.getID(), bestBoard);
    }
}
