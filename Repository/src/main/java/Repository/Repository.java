package Repository;

import Model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class Repository {

//    private static int lastAssignedID = 0;

    public synchronized void synchronizedLastAssignedID() {
        setID(getID() + 1);
    }
    @Getter @Setter
    private static int ID = 0;

    @Getter
    private static Repository repository = new Repository();
    @Getter @Setter
    private static Map<Integer, Task> tasksRepository = new HashMap<>();
    @Getter @Setter
    private static Map<Integer, int[][]> answersRepository = new HashMap<>();

    public void add(Task task) {
        synchronizedLastAssignedID();
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
