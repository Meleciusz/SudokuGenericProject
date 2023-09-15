package com.mkrasucki.Consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    private Sender sender;

    public ConsumerController(Sender sender) {
        this.sender = sender;
    }

    @GetMapping("/send")
    public void send() {
        sender.send();
    }
}
