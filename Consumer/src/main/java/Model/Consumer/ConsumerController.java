package Model.Consumer;

import Sender.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ConsumerController {
    private Sender sender;

    public ConsumerController(Sender sender) {
        this.sender = sender;
    }

    public void send(List<int[][]> message){
        sender.send(message);
    }
}