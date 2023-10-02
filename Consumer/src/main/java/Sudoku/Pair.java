package Sudoku;

import lombok.Getter;
import lombok.Setter;

//Pair class to store a sudoku board and its fitness
public class Pair {
    @Getter @Setter
    private int[][] board;

    @Getter @Setter
    private double fitness;

    public Pair(int[][] board, double fitness) {
        this.board = board;
        this.fitness = fitness;
    }
}
