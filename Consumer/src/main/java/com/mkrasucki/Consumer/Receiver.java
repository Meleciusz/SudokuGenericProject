package com.mkrasucki.Consumer;

import Sudoku.Builder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class Receiver {

  public String message;
  private Builder builder;


  public String getMessage() {
    return message;
  }

  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive(String message) throws InterruptedException {
    this.message = message;
    System.out.println("[x] Received " + message);

    builder = new Builder(message);
  }



}