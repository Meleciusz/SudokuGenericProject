package Receiver;

import Sudoku.SudokuSolver;
import com.mkrasucki.Consumer.ConsumerController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Receiver {

  public String message;
  private SudokuSolver builder;

  @Autowired
  ConsumerController sender;

  private int howManyElementsShouldSudokuHave = 81;


  //If message is detected in the queue make receive() method
  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive(String message) throws InterruptedException {
    this.message = message;
    System.out.println("[x] Received " + message);

    if(message.length() == howManyElementsShouldSudokuHave) {
      builder = new SudokuSolver(message);
      builder.findSolution();

      List<int[][]> output = SudokuSolver.getBestPopulation();
      sender.send(output);
    } else {
      System.out.println("Wrong message");
    }
  }
}