package Sudoku;

class Pair {
    int[][] board;
    double fitness;
    Pair(int[][] board, double fitness) {
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