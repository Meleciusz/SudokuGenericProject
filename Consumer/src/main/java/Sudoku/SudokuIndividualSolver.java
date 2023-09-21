package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

//Class that makes methods on a single sudoku board
public class SudokuIndividualSolver {

    private final int sudokuSize = 9;

    public int[][] initialize(String input) {
        char[][] board = convert(input);  // input String converted to char 2D array
        int[][] boardIntValue = new int[sudokuSize][sudokuSize]; // sudoku board that was filled with numbers

        //Fill up the board
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                fillSquare(board, i, j);
            }
        }

        //Convert char 2D array to int 2D array
        IntStream.range(0, board.length)
                .forEach(i ->
                        IntStream.range(0, board[i].length)
                                .forEach(j -> boardIntValue[i][j] = Character.getNumericValue(board[i][j]))
                );


        return boardIntValue;
    }

    //Convert input String to char 2D array
    private char[][] convert(String input){
        char[][] board = new char[sudokuSize][sudokuSize];

        for(int i = 0; i < sudokuSize; i++) {
            for(int j = 0; j < sudokuSize; j++) {
                board[i][j] = input.charAt(i * sudokuSize + j);
            }
        }

        return board;
    }

    //Fill up the sudoku board
    public static void fillSquare(char[][] sudoku, int row, int col) {
        //Create a list of numbers
        List<Integer> numbers = new ArrayList<>();
        for (int num = 1; num <= 9; num++) {
            numbers.add(num);
        }
        //Shuffle the numbers
        Collections.shuffle(numbers);

        //Remove the numbers from random numbers list that was already filled
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                char cellValue = sudoku[i][j];
                if (cellValue != '.') {
                    int num = Character.getNumericValue(cellValue);
                    numbers.remove(Integer.valueOf(num));
                }
            }
        }

        //Fill up the sudoku
        int index = 0;
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if(sudoku[i][j] == '.') {
                    sudoku[i][j] = numbers.get(index).toString().charAt(0);
                    index++;
                }
            }
        }
    }

    //Print the sudoku
    public static void printSudoku(char[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Fitness function that gives a percentage accuracy
    public static double fitFunction(int[][] sudoku) {
        int totalNumbers = 9 * 9;
        int totalRepeats = 0;

        for (int num = 1; num <= 9; num++) {
            for (int row = 0; row < 9; row++) {
                int rowRepeats = 0;
                int colRepeats = 0;

                for (int col = 0; col < 9; col++) {
                    if (sudoku[row][col] == num) {
                        rowRepeats++;
                    }
                    if (sudoku[col][row] == num) {
                        colRepeats++;
                    }
                }

                if (rowRepeats > 1) {
                    totalRepeats += rowRepeats - 1;
                }
                if (colRepeats > 1) {
                    totalRepeats += colRepeats - 1;
                }
            }
        }

        double fitnessPercentage = 100 - (double) (totalRepeats * 100) / totalNumbers;
        return fitnessPercentage;
    }
}
