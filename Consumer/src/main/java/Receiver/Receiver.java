package Receiver;

import Answer.Answer;
import Sudoku.SudokuSolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkrasucki.Consumer.ConsumerController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Task.Task;

import java.util.List;

@Component
public class Receiver {

  private SudokuSolver builder;

  @Autowired
  ConsumerController sender;

  private int howManyElementsShouldSudokuHave = 81;


  //If message is detected in the queue make receive() method
  @RabbitListener(queues = "sender")
  public void receive(String message) throws InterruptedException {
    System.out.println("[x] Received " + message);
    ObjectMapper objectMapper = new ObjectMapper();

    try{
      Task task = objectMapper.readValue(message, Task.class);
      SudokuSolver solver = new SudokuSolver(task.getTask());
      solver.findSolution();
      List<int[][]> bestPopulation = solver.getBestPopulation();


      sender.send(new Answer(bestPopulation, task.getID()));

    } catch (Exception e){
      e.printStackTrace();
    }
    //sender.send(message);
  }
}