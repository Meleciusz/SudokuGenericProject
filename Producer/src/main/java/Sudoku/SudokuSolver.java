package Sudoku;

import java.util.*;
public class SudokuSolver {

    private int sudokuSize = 9;
    private int howManyInstances = 3;
    private int[][] bestBoard = new int[sudokuSize][sudokuSize];
    private List<int[][]> messages;
    private int howManyChildren = 3;
    private List<Pair> newPopulation = new ArrayList<>();
    public SudokuSolver(List<int[][]> messages){
        this.messages = messages;
    }
    private SudokuPopulationSolver population = new SudokuPopulationSolver();
    private SudokuIndividualSolver individual = new SudokuIndividualSolver();

    //Find best board
    public void findBestSudoku(){
        //Define min and max coordinates
        int minCoordinateFirst = 2;
        int maxCoordinateFirst = 4;
        int minCoordinateSecond = 5;
        int maxCoordinateSecond = 7;

        Random random = new Random();

        //Find best board by creating children and sorting
        for(int i=0; i<messages.size(); i+=howManyInstances){
            for(int j=0; j < howManyChildren; j++) {
                int y1 = random.nextInt(maxCoordinateFirst - minCoordinateFirst + 1) + minCoordinateFirst;
                int y2 = random.nextInt(maxCoordinateSecond - minCoordinateSecond + 1) + minCoordinateSecond;

                //Create new board(By creating children from the best boards from instances)
                int[][] newBoard = population.createNewBoard(messages.get(i), messages.get(i+1), messages.get(i+2), y1, y2, sudokuSize);
                double fitness = individual.fitFunction(newBoard);
                newPopulation.add(new Pair(newBoard, fitness));
            }
        }

        //Sort new population by fitness
        Collections.sort(newPopulation, Comparator.comparingDouble(pair -> -pair.getFitness()));

        //Determine best board
        bestBoard = newPopulation.get(0).getBoard();
        individual.showBestBoard(newPopulation.get(0).getFitness(), sudokuSize, bestBoard);
    }



}
