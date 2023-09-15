package com.mkrasucki.Producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private Sender sender;

    public ProducerController(Sender sender) {
        this.sender = sender;
    }

    @GetMapping("/send")
    public void send() {
        sender.send();
    }
}
