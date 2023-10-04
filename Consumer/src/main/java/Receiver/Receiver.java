package Receiver;

import Model.Answer;
import Sudoku.SudokuSolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Consumer.ConsumerController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Model.Task;

import java.util.List;

@Component
public class  Receiver {

  private ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  ConsumerController sender;


  //If message is detected in the queue make receive() method
  @RabbitListener(queues = "sender")
  public void receive(String message) throws InterruptedException {
    System.out.println("[x] Received " + message);

    try{
      Task task = objectMapper.readValue(message, Task.class);
      SudokuSolver solver = new SudokuSolver(task.getTask());
      solver.findSolution();
      List<int[][]> bestPopulation = solver.getBestPopulation();


      sender.send(new Answer(bestPopulation));

    } catch (Exception e){
      e.printStackTrace();
    }
    //sender.send(message);
  }
}