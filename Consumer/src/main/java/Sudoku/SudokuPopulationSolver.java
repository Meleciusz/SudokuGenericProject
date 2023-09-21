package Sudoku;

import java.util.*;
import java.util.stream.Collectors;

public class SudokuPopulationSolver {

    private int size = 9;
    private int populationSize = 60;
    private List<Pair> newPopulation = new ArrayList<>();
    private int howManyChildren = 4;

    private SudokuIndividualSolver individualSolver = new SudokuIndividualSolver();
    public List<Pair> fineNewGeneration(List<Pair> oldGeneration){
        newPopulation.clear();
        List<Pair> bestHalf = findBestHalf(oldGeneration);

        //If population size is odd remove the last element
        if(bestHalf.size() % 2 != 0){
            bestHalf.remove(bestHalf.size()-1);
        }


        for(int i=0; i<bestHalf.size(); i+=2){
            generateChildren(bestHalf.get(i).getBoard(), bestHalf.get(i+1).getBoard());
        }

        Collections.sort(newPopulation, Comparator.comparingDouble(pair -> -pair.getFitness()));

        //if new population in weaker than old population return old population instead
        if(newPopulation.get(newPopulation.size()-1).getFitness() > oldGeneration.get(oldGeneration.size()-1).getFitness()){
            return newPopulation;
        }
        else {
            return oldGeneration;
        }

    }

    private void generateChildren(int[][] ParentBoardFirst, int[][] ParentBoardSecond) {

        int minCoordinate = 3;
        int maxCoordinate = 6;

        Random random = new Random();

        for(int i=0; i<howManyChildren; i++){
            int y = random.nextInt(maxCoordinate - minCoordinate + 1) + minCoordinate;


            int[][] newBoard = createNewBoard(ParentBoardFirst, ParentBoardSecond, y);
            double fitness = individualSolver.fitFunction(newBoard);
            newPopulation.add(new Pair(newBoard, fitness));
        }
    }

    private int[][] createNewBoard(int[][] ParentBoardFirst, int[][] ParentBoardSecond, int y) {
        int[][] newBoard = new int[size][size];

        for(int i=0; i<size ;i++){
            for(int j=0; j<y; j++){
                newBoard[i][j] = ParentBoardFirst[i][j];
            }
        }

        for(int i=0; i<size; i++){
            for(int j=y; j<size; j++){
                newBoard[i][j] = ParentBoardSecond[i][j];
            }
        }

        boolean mutation = false;
        double chance = 0.01;
        Random random = new Random();
        double ifMutation = random.nextDouble();
        if(ifMutation < chance){
            mutation=true;
        }

        if(mutation){
            newBoard = mutate(newBoard);
        }

        return newBoard;
    }
    private int[][] mutate(int[][] board){
        Random random = new Random();
        int[] randomCoordinates = new int[2];
        randomCoordinates[0] = random.nextInt(9);
        randomCoordinates[1] = random.nextInt(9);

        return board;
    }
    private List<Pair> findBestHalf(List<Pair> sudokuBoards) {
        Collections.sort(sudokuBoards, Comparator.comparingDouble(pair -> -pair.getFitness()));

        int childrenPopulationSize = populationSize / 2;

        return sudokuBoards.subList(0, childrenPopulationSize);
    }
}
