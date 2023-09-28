package com.mkrasucki.Producer;

//import Repository.Task;
//import Repository.TasksRepository;
import Sender.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Repository.Task;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProducerController {

//    @Autowired
//    private TasksRepository tasksRepository;

    private Sender sender;

    @Autowired
    public ProducerController(Sender sender) {
        this.sender = sender;
    }

    public static List<Task> allMessages = new ArrayList<>();

    @GetMapping("/send")
    public void send() throws JsonProcessingException {
        sender.send(allMessages);
    }

    @GetMapping("/add")
    public void add(@RequestParam(value = "message") String message){
        String firstState = "ADDED";
        allMessages.add(new Task(message, firstState));
       // tasksRepository.save(new Task(message, firstState));
    }
}
