package Model.Producer;

import Sender.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Model.Task;

@RestController
public class ProducerController {


    private Sender sender;

    @Autowired
    public ProducerController(Sender sender) {
        this.sender = sender;
    }



    @GetMapping("/send")
    public void send(@RequestParam(value = "message") String message) throws JsonProcessingException {
        String firstState = "ADDED";
        sender.send(new Task(message, firstState));
    }
}
