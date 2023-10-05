package Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Task {

    @Getter @Setter
    private String task;

    @Getter @Setter
    private String state;

    public Task(String task, String state) {
        this.task = task;
        this.state = state;
    }
}