package com.mkrasucki.Producer;

import Sender.Sender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private Sender sender;

    public ProducerController(Sender sender) {
        this.sender = sender;
    }

    @GetMapping("/send")
    public void send(@RequestParam(value = "message") String message) {
        sender.send(message);
    }
}
