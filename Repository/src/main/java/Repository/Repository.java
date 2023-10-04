package Repository;

import Model.Answer;
import Model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class Repository {

    private static int lastAssignedID = 0;

    @Getter @Setter
    private int ID = 0;

    @Getter
    private static Repository repository = new Repository();
    @Getter @Setter
    private static Map<Integer, Task> tasksRepository = new HashMap<>();
    @Getter @Setter
    private static Map<Integer, Answer> answersRepository = new HashMap<>();

    public void add(Task task) {
        this.ID = ++lastAssignedID;
        this.tasksRepository.put(this.ID, task);
        this.answersRepository.put(this.ID, new Answer(new ArrayList<>()));
    }

    public void setStateById(int id, String state){
        this.tasksRepository.get(id).setState(state);
    }

    public void setAnswerById(int id, Answer answer){
        this.answersRepository.get(id).setAnswer(answer);
    }
}
