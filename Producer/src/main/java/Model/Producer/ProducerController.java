package Model.Producer;

import Model.Status;
import Repository.Repository;
import Sender.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Model.Task;
import Model.Status;

@RestController
public class ProducerController {


    private Sender sender;

    @Autowired
    public ProducerController(Sender sender) {
        this.sender = sender;
    }

    private static Repository repository = Repository.getRepository();

    @GetMapping("/send")
    public void send(@RequestParam(value = "message") String message) throws JsonProcessingException {
        Task task = new Task(message, Status.ADDED.toString());
        repository.add(task);
        repository.setStateById(repository.getID(), Status.ADDED.toString());
        sender.send(task);
    }
}
