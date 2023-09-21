package Sudoku;

import java.util.*;
public class SudokuSolver {

    private int sudokuSize = 9;
    private int[][] bestBoard = new int[sudokuSize][sudokuSize];
    private List<int[][]> messages;
    private int howManyChildren = 3;
    private List<Pair> newPopulation = new ArrayList<>();
    public SudokuSolver(List<int[][]> messages){
        this.messages = messages;
    }

    private void showBestBoard(double fitness){
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

    public void findBestSudoku(){
        List<Integer> randomNumbers = new ArrayList<>();
        for(int i = 0; i < sudokuSize; i++){
            randomNumbers.add(i);
        }
        Collections.shuffle(randomNumbers);

        int minCoordinateFirst = 2;
        int maxCoordinateFirst = 4;
        int minCoordinateSecond = 5;
        int maxCoordinateSecond = 7;

        Random random = new Random();

        for(int i=0; i<messages.size(); i+=3){
            for(int j=0; j < howManyChildren; j++) {
                int y1 = random.nextInt(maxCoordinateFirst - minCoordinateFirst + 1) + minCoordinateFirst;
                int y2 = random.nextInt(maxCoordinateSecond - minCoordinateSecond + 1) + minCoordinateSecond;

                int[][] newBoard = createNewBoard(messages.get(i), messages.get(i+1), messages.get(i+2), y1, y2);
                double fitness = fitFunction(newBoard);
                newPopulation.add(new Pair(newBoard, fitness));
            }
        }

        Collections.sort(newPopulation, Comparator.comparingDouble(pair -> -pair.getFitness()));

        bestBoard = newPopulation.get(0).getBoard();
        showBestBoard(newPopulation.get(0).getFitness());
    }
    private int[][] createNewBoard(int[][] ParentBoardFirst, int[][] ParentBoardSecond, int[][] ParentBoardThird, int y1, int y2) {
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

    private double fitFunction(int[][] board) {
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
