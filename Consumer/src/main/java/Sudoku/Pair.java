package Sudoku;

public class Pair {
    private int[][] board;
    private double fitness;

    public Pair(int[][] board, double fitness) {
        this.board = board;
        this.fitness = fitness;
    }

    public int[][] getBoard() {
        return board;
    }

    public double getFitness() {
        return fitness;
    }
}
