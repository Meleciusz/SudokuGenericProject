package Sudoku;

public class SudokuPopulationSolver {

    //Create children from parents
    public int[][] createNewBoard(int[][] ParentBoardFirst, int[][] ParentBoardSecond, int[][] ParentBoardThird, int y1, int y2, int sudokuSize) {
        int[][] newBoard = new int[sudokuSize][sudokuSize];

        for(int i=0; i<sudokuSize ;i++){
            for(int j=0; j<y1; j++){
                newBoard[i][j] = ParentBoardFirst[i][j];
            }
        }

        for(int i=0; i<sudokuSize; i++){
            for(int j=y1; j<y2; j++){
                newBoard[i][j] = ParentBoardSecond[i][j];
            }
        }

        for(int i=0; i<sudokuSize; i++){
            for(int j=y2; j<sudokuSize; j++){
                newBoard[i][j] = ParentBoardThird[i][j];
            }
        }


        return newBoard;
    }
}
