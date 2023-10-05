package Sudoku;

import java.util.*;

import Repository.Repository;

public class SudokuSolver {

    private final int chromosomeCount = 60;
    private final int populationSize = 10;
    private static String input;
    private SudokuIndividualSolver individualSolver = new SudokuIndividualSolver();
    private SudokuPopulationSolver populationSolver = new SudokuPopulationSolver();
    private List<Pair> sudokuBoards = new ArrayList<>();
    private int sudokuSize = 9;
    private List<Pair> newPopulation = new ArrayList<>();
    private int bestOfHowMany = chromosomeCount;
    private static List<int[][]> lastPopulation = new ArrayList<>();
    private List<int[][]> messages;
    public SudokuSolver(List<int[][]> messages){
        this.messages = messages;
    }
    private int howManyInstances = 3;
    private int[][] bestBoard = new int[sudokuSize][sudokuSize];

    public static List<int[][]> getBestPopulation() {
        return lastPopulation;
    }

    public SudokuSolver(String message)
    {
        input = message;
    }
    private Repository repository = Repository.getRepository();

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

    public void findBestSudoku(){
        //Define min and max coordinates
        int minCoordinateFirst = 2;
        int maxCoordinateFirst = 4;
        int minCoordinateSecond = 5;
        int maxCoordinateSecond = 7;

        Random random = new Random();

        //Find best board by creating children and sorting
        for(int i=0; i<messages.size(); i+=howManyInstances){
            for(int j=0; j < chromosomeCount; j++) {
                int y1 = random.nextInt(maxCoordinateFirst - minCoordinateFirst + 1) + minCoordinateFirst;
                int y2 = random.nextInt(maxCoordinateSecond - minCoordinateSecond + 1) + minCoordinateSecond;

                //Create new board(By creating children from the best boards from instances)
                int[][] newBoard = populationSolver.createNewBoard(messages.get(i), messages.get(i+1), messages.get(i+2), y1, y2, sudokuSize);
                double fitness = individualSolver.fitFunction(newBoard);
                newPopulation.add(new Pair(newBoard, fitness));
            }
        }

        //Sort new population by fitness
        Collections.sort(newPopulation, Comparator.comparingDouble(pair -> -pair.getFitness()));

        //Determine best board
        bestBoard = newPopulation.get(0).getBoard();
        repository.setAnswerById(repository.getID(), bestBoard);
        individualSolver.showBestBoard(newPopulation.get(0).getFitness(), sudokuSize, bestBoard);
    }


}
