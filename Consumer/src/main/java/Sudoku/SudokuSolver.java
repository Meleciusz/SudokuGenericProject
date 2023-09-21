package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
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

    public static List<int[][]> getBestOfFive() {
        return lastPopulation;
    }

    public SudokuSolver(String message)
    {
        input = message;
    }

    public void findSolution() {
        lastPopulation.clear();

        for(int i = 0; i < chromosomeCount; i++)
        {
            int[][] boardIntValue = individualSolver.initialize(input);
            double fitness = individualSolver.fitFunction(boardIntValue);
            sudokuBoards.add(new Pair(boardIntValue, fitness));
        }

        Comparator<Pair> comparator = Comparator.comparingDouble(pair -> pair.getFitness());
        Pair maxPair = Collections.max(sudokuBoards, comparator);
        double maxFitness = maxPair.getFitness();

        for(int i = 0; i < populationSize; i++)
        {
            newPopulation = populationSolver.fineNewGeneration(sudokuBoards);
        }

        for(int i = 0; i < bestOfHowMany; i++)
        {
            lastPopulation.add(newPopulation.get(i).getBoard());
        }

    }

}
