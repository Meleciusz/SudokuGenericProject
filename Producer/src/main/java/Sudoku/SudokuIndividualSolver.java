package Sudoku;

public class SudokuIndividualSolver {

    public void showBestBoard(double fitness, int sudokuSize, int[][] bestBoard) {
        System.out.println("Accuracy: " + fitness);
        System.out.println();
        System.out.println("Best Board: ");

        for(int i=0; i<sudokuSize; i++){
            for(int j=0; j<sudokuSize; j++){
                System.out.print(bestBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Method that find accuracy of the board
    public double fitFunction(int[][] board) {
        int totalNumbers = 9 * 9;
        int totalRepeats = 0;

        for(int num = 1; num <= 9; num++) {
            for(int row = 0; row < 9; row++) {
                int rowRepeats = 0;
                int colRepeats = 0;

                for(int col = 0; col < 9; col++) {
                    if(board[row][col] == num) {
                        rowRepeats++;
                    }
                    if(board[col][row] == num) {
                        colRepeats++;
                    }
                }

                if(rowRepeats > 1) {
                    totalRepeats += rowRepeats - 1;
                }
                if(colRepeats > 1) {
                    totalRepeats += colRepeats - 1;
                }
            }
        }
        double fitnessPercentage = 100 - (double) (totalRepeats * 100) / totalNumbers;
        return fitnessPercentage;
    }
}
