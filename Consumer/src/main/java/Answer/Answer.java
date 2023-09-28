package Answer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Answer {
    @Getter @Setter
    private List<int[][]> lastPopulation;
    @Getter @Setter
    private int ID;

    public Answer(List<int[][]> lastPopulation, int ID) {
        this.lastPopulation = lastPopulation;
        this.ID = ID;
    }

    public Answer() {
    }
}
