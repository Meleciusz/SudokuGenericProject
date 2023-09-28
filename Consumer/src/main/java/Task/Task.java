package Task;


import lombok.Getter;
import lombok.Setter;

public class Task {

    @Getter @Setter
    private int ID;

    @Getter @Setter
    private String task;

    @Getter @Setter
    private String state;

    public Task(String task, String state, String id) {
        this.ID = Integer.parseInt(id);
        this.task = task;
        this.state = state;
    }

    public Task() {
    }
}
