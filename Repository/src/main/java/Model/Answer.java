package Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//class that represents the answer sudoku board
@NoArgsConstructor
public class Answer {
    @Getter @Setter
    private List<int[][]> lastPopulation;

    public Answer(List<int[][]> lastPopulation) {
        this.lastPopulation = lastPopulation;
    }


    public void setAnswer(Answer answer) {
        this.lastPopulation = answer.getLastPopulation();
    }
}