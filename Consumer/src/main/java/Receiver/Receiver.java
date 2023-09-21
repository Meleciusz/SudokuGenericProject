package Receiver;

import Sudoku.SudokuSolver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Receiver {

  public String message;
  private SudokuSolver builder;


  public String getMessage() {
    return message;
  }

  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive(String message) throws InterruptedException {
    this.message = message;
    System.out.println("[x] Received " + message);

    builder = new SudokuSolver(message);
    builder.findSolution();
  }



}