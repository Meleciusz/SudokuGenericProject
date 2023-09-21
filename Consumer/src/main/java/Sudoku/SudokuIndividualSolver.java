package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.HashMap;

public class SudokuIndividualSolver {

    private final int size = 9;
    private final int squareSize = 3;

    public int[][] initialize(String input) {

        char[][] board = new char[size][size];
        int[][] boardIntValue = new int[size][size];
        StringBuilder sb = new StringBuilder();
        List<int[]> squares = new ArrayList<>();

       for(int i = 0; i < size; i++) {
           for(int j = 0; j < size; j++) {
               board[i][j] = input.charAt(i * size + j);
           }
       }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                fillSquare(board, i, j);
            }
        }


        IntStream.range(0, board.length)
                .forEach(i ->
                        IntStream.range(0, board[i].length)
                                .forEach(j -> boardIntValue[i][j] = Character.getNumericValue(board[i][j]))
                );


        return boardIntValue;
    }

    public static void fillSquare(char[][] sudoku, int row, int col) {
        List<Integer> numbers = new ArrayList<>();
        for (int num = 1; num <= 9; num++) {
            numbers.add(num);
        }
        Collections.shuffle(numbers);

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                char cellValue = sudoku[i][j];
                if (cellValue != '.') {
                    int num = Character.getNumericValue(cellValue);
                    numbers.remove(Integer.valueOf(num));
                }
            }
        }

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

    public static void printSudoku(char[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

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
