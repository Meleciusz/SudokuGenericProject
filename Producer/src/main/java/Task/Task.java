package Task;
import lombok.Getter;
import lombok.Setter;

public class Task {
    private static int lastAssignedID = 0;

    @Getter @Setter
    private int ID = 0;

    @Getter @Setter
    private String task;

    @Getter @Setter
    private String state;

    public Task(String task, String state) {
        this.ID = ++lastAssignedID;
        this.task = task;
        this.state = state;
    }

}