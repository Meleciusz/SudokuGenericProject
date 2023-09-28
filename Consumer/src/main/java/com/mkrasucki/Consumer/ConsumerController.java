package com.mkrasucki.Consumer;

import Sender.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RestController;
import Answer.Answer;

import java.util.List;

@RestController
public class ConsumerController {
    private Sender sender;

    public ConsumerController(Sender sender) {
        this.sender = sender;
    }

    public void send(Answer message) throws JsonProcessingException {
        sender.send(message);
    }
}
