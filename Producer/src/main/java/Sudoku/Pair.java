package Sudoku;

//Pair class to store a sudoku board and its fitness
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