package Receiver;

import Sudoku.SudokuSolver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Receiver {

  public String message;
  private SudokuSolver builder;

  private int howManyElementsShouldSudokuHave = 81;

  public String getMessage() {
    return message;
  }

  //If message is detected in the queue make receive() method
  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive(String message) throws InterruptedException {
    this.message = message;
    System.out.println("[x] Received " + message);

    if(message.length() == howManyElementsShouldSudokuHave) {
      builder = new SudokuSolver(message);
      builder.findSolution();
    } else {
      System.out.println("Wrong message");
    }
  }
}