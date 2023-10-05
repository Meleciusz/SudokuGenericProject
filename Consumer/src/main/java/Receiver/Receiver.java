package Receiver;

import Sudoku.SudokuSolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Consumer.ConsumerController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Model.Task;
import Repository.Repository;

import java.util.List;

@Component
public class  Receiver {

   private final String rabbitQueueName = "sender";
  @Autowired
  ConsumerController sender;

  private ObjectMapper objectMapper = new ObjectMapper();

  //If message is detected in the queue make receive() method
  @RabbitListener(queues = rabbitQueueName)
  public void receive(String message) throws JsonProcessingException {
    System.out.println(" [x] Received " + message);

    Task task = objectMapper.readValue(message, Task.class);
    SudokuSolver solver = new SudokuSolver(task.getTask());
    solver.findSolution();
    List<int[][]> bestPopulation = solver.getBestPopulation();

    sender.send(bestPopulation);

  }
}