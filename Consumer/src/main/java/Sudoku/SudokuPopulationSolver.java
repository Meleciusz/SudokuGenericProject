package Sudoku;

import java.util.*;

//Class that makes methods on a whole population
public class SudokuPopulationSolver {

    private int sudokuSize = 9;
    private int populationSize = 60;
    private List<Pair> newPopulation = new ArrayList<>();
    private int howManyChildren = 4;
    private SudokuIndividualSolver individualSolver = new SudokuIndividualSolver();

    //Create new population (children) from parent population
    public List<Pair> findNewGeneration(List<Pair> oldGeneration){
        newPopulation.clear();
        //Find best half(in this project worse half of population is cutting off and children are created from the best half)
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

    //Create new population (children) from parent population
    private void generateChildren(int[][] ParentBoardFirst, int[][] ParentBoardSecond) {

        int minCoordinate = 3;
        int maxCoordinate = 6;

        Random random = new Random();

        for(int i=0; i<howManyChildren; i++){
            int y = random.nextInt(maxCoordinate - minCoordinate + 1) + minCoordinate;

            int[][] newBoard = createNewBoard(ParentBoardFirst, ParentBoardSecond, y);
            double fitness = individualSolver.fitFunction(newBoard);
            //new Population is Pair that contains board and fitness
            newPopulation.add(new Pair(newBoard, fitness));
        }
    }

    //Create new board from parents
    public int[][] createNewBoard(int[][] ParentBoardFirst, int[][] ParentBoardSecond, int y) {
        int[][] newBoard = new int[sudokuSize][sudokuSize];

        for(int i = 0; i< sudokuSize; i++){
            for(int j=0; j<y; j++){
                newBoard[i][j] = ParentBoardFirst[i][j];
            }
        }

        for(int i = 0; i< sudokuSize; i++){
            for(int j = y; j< sudokuSize; j++){
                newBoard[i][j] = ParentBoardSecond[i][j];
            }
        }

        //Sudoku have 1% to mutate
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

    //Mutate
    public int[][] mutate(int[][] board){

        Random random = new Random();
        int[] randomCoordinatesX = new int[2];
        randomCoordinatesX[0] = random.nextInt(9);
        randomCoordinatesX[1] = random.nextInt(9);
        int[] randomCoordinatesY = new int[2];
        randomCoordinatesY[0] = random.nextInt(9);
        randomCoordinatesY[1] = random.nextInt(9);

        int tmp = board[randomCoordinatesX[0]][randomCoordinatesY[0]];
        board[randomCoordinatesX[0]][randomCoordinatesY[0]] = board[randomCoordinatesX[1]][randomCoordinatesY[1]];
        board[randomCoordinatesX[1]][randomCoordinatesY[1]] = tmp;

        return board;
    }

    //Find best half
    public List<Pair> findBestHalf(List<Pair> sudokuBoards) {
        Collections.sort(sudokuBoards, Comparator.comparingDouble(pair -> -pair.getFitness()));

        int childrenPopulationSize = populationSize / 2;

        return sudokuBoards.subList(0, childrenPopulationSize);
    }
}
