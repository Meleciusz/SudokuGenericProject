package Sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuPopulationSolverTest {

    private int boardSize = 81;
    private  SudokuPopulationSolver populationSolver = new SudokuPopulationSolver();


    @Test
    @DisplayName("Test if new board is generated correctly")
    void testCreateNewBoard() {
        int[][] first = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        int[][] second = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        int[][] third = {
                {8, 7, 6, 5, 4, 3, 2, 1, 9},
                {5, 4, 3, 2, 1, 9, 8, 7, 6},
                {2, 1, 9, 8, 7, 6, 5, 4, 3},
                {7, 6, 5, 4, 3, 2, 1, 9, 8},
                {4, 3, 2, 1, 9, 8, 7, 6, 5},
                {1, 9, 8, 7, 6, 5, 4, 3, 2},
                {6, 5, 4, 3, 2, 1, 9, 8, 7},
                {3, 2, 1, 9, 8, 7, 6, 5, 4},
                {9, 8, 7, 6, 5, 4, 3, 2, 1}
        };

        int[][] newBoard = populationSolver.createNewBoard(first, second, third, 3, 6, 9);

        assertEquals(boardSize, newBoard.length*newBoard.length);

        for(int i=0; i<3; i++){
            assertEquals(first[0][i], newBoard[0][i]);
        }
        for(int i=3; i<6; i++){
            assertEquals(second[0][i], newBoard[0][i]);
        }
        for(int i=6; i<9; i++){
            assertEquals(third[0][i], newBoard[0][i]);
        }
    }
}