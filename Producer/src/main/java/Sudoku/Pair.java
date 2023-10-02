package Sudoku;

import lombok.Getter;
import lombok.Setter;

//Pair class to store a sudoku board and its fitness
class Pair {
    @Getter @Setter
    int[][] board;

    @Getter @Setter
    double fitness;
    Pair(int[][] board, double fitness) {
        this.board = board;
        this.fitness = fitness;
    }
}