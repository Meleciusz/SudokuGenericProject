package Repository;

import Model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

//Class representing a Repository
@NoArgsConstructor
public class Repository {

    //synchronized ID to avoid race conditions
    @Getter @Setter
    private static int ID = 0;

    @Getter
    private static Repository repository = new Repository();
    @Getter @Setter
    private static Map<Integer, Task> tasksRepository = new HashMap<>();
    @Getter @Setter
    private static Map<Integer, int[][]> answersRepository = new HashMap<>();

    //synchronized ID and repository objects  to avoid race conditions
    //add new record to repository. Give it an ID and sort of foreign key from tasksRepository to answersRepository
    public synchronized void add(Task task) {
        ID++;
        this.tasksRepository.put(this.ID, task);
        this.answersRepository.put(this.ID, null);
    }

    public void setStateById(int id, String state){
        this.tasksRepository.get(id).setState(state);
    }

    public void setAnswerById(int id, int[][] answer){
        this.answersRepository.put(id, answer);
    }

    public Task getTaskById(int id){
        return this.tasksRepository.get(id);
    }

    public String getStateById(int id){
        return this.tasksRepository.get(id).getState();
    }
}
