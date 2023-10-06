package Receiver;

import Sender.Sender;
import Sudoku.SudokuSolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Model.Task;


import java.util.List;

@Slf4j
@Component
public class  Receiver {
  @Autowired
  Sender sender;

  private ObjectMapper objectMapper = new ObjectMapper();

  //If message is detected in the queue make receive() method
  @RabbitListener(queues = "${queue.name}")
  public void receive(String message) throws JsonProcessingException {
    Task task = objectMapper.readValue(message, Task.class);
    log.info(" [x] Received " + message);
;


    SudokuSolver solver = new SudokuSolver(task.getTask());
    solver.findSolution();
    List<int[][]> bestPopulation = solver.getBestPopulation();

    sender.send(bestPopulation);

  }
}