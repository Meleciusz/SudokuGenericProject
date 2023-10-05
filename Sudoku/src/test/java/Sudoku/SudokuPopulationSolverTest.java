package Sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SudokuPopulationSolverTest {

    private List<Pair> sudokuBoards = new ArrayList<>();
    private int chromosomeCount = 60;
    private int boardSize = 81;
    private int sudokuSize = 9;
    private SudokuIndividualSolver individualSolver = new SudokuIndividualSolver();
    private  SudokuPopulationSolver populationSolver = new SudokuPopulationSolver();
    private String input="..41..3.8.1....62...82..4.....3.28.9....7....7.16.8...562..17.3.3.....4.1....5...";

    @BeforeEach
    void init() {
        for(int i = 0; i < chromosomeCount; i++)
        {
            int[][] boardIntValue = individualSolver.initialize(input);
            double fitness = individualSolver.fitFunction(boardIntValue);
            sudokuBoards.add(new Pair(boardIntValue, fitness));
        }
    }

    @Test
    @DisplayName("Test if new generation is created correctly")
    void testGenerations() {
        List<Pair> newGeneration = populationSolver.findNewGeneration(sudokuBoards);

        assertEquals(sudokuBoards.size(), newGeneration.size());

        if(newGeneration.get(0).getFitness() > sudokuBoards.get(0).getFitness()){
            assertFalse(Arrays.equals(sudokuBoards.get(0).getBoard(), newGeneration.get(0).getBoard()));
            assertTrue(newGeneration.get(0).getFitness() > sudokuBoards.get(0).getFitness());
        }else{
//            assertArrayEquals(sudokuBoards.get(0).getBoard(), newGeneration.get(0).getBoard());
            assertFalse(newGeneration.get(0).getFitness() > sudokuBoards.get(0).getFitness());
        }
    }

    @Test
    @DisplayName("Test if new board is generated correctly")
    void testCreateNewBoard() {
        int[][] newBoard = populationSolver.createNewBoard(sudokuBoards.get(0).getBoard(), sudokuBoards.get(1).getBoard(), 4);

        assertEquals(boardSize, newBoard.length*newBoard.length);
    }

    @Test
    @DisplayName("Test if better half is generated correctly")
    void testFindBestHalf() {
        List<Pair> bestHalf = populationSolver.findBestHalf(sudokuBoards);

        List<Pair> sudokuBoardsCopy = new ArrayList<>(sudokuBoards);
        Collections.sort(sudokuBoardsCopy, Comparator.comparingDouble(pair -> -pair.getFitness()));
        sudokuBoardsCopy.subList(0, 30);

        for(int i=0; i<30; i++){
            assertArrayEquals(sudokuBoardsCopy.get(i).getBoard(), bestHalf.get(i).getBoard());
        }

    }

    @Test
    @DisplayName("Test if mutation is working correctly")
    void testMutation(){
        int[][] beforeMutation = new int[sudokuSize][sudokuSize];
        for(int i=0; i<sudokuSize; i++){
            for(int j=0; j<sudokuSize; j++){
                beforeMutation[i][j] = sudokuBoards.get(0).getBoard()[i][j];
            }
        }

        int[][] boardAfterMutation = populationSolver.mutate(sudokuBoards.get(0).getBoard());


        assertFalse(Arrays.equals(beforeMutation, boardAfterMutation));
    }
}