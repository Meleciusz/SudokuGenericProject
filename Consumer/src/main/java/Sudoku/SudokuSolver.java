package Sudoku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SudokuSolver {

    private final int chromosomeCount = 60;
    private final int populationSize = 10;
    private static String input;
    private SudokuIndividualSolver individualSolver = new SudokuIndividualSolver();
    private SudokuPopulationSolver populationSolver = new SudokuPopulationSolver();
    private List<Pair> sudokuBoards = new ArrayList<>();
    private List<Pair> newPopulation = new ArrayList<>();
    private int bestOfHowMany = chromosomeCount;
    private static List<int[][]> lastPopulation = new ArrayList<>();

    public static List<int[][]> getBestPopulation() {
        return lastPopulation;
    }

    public SudokuSolver(String message)
    {
        input = message;
    }

    //Solve the sudoku
    public void findSolution() {
        lastPopulation.clear();

        for(int i = 0; i < chromosomeCount; i++)
        {
            int[][] boardIntValue = individualSolver.initialize(input);
            double fitness = individualSolver.fitFunction(boardIntValue);
            sudokuBoards.add(new Pair(boardIntValue, fitness));
        }

        for(int i = 0; i < populationSize; i++)
        {
            newPopulation = populationSolver.findNewGeneration(sudokuBoards);
        }

        for(int i = 0; i < bestOfHowMany; i++)
        {
            lastPopulation.add(newPopulation.get(i).getBoard());
        }

    }

}
