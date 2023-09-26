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

        int minCoordinate = 2;
        int maxCoordinate = 7;

        Random random = new Random();

        for(int i=0; i<howManyChildren; i++){
            int y = random.nextInt(maxCoordinate - minCoordinate + 1) + minCoordinate;

            int[][] newBoard = createNewBoard(ParentBoardFirst, ParentBoardSecond, y);
            double fitness = individualSolver.fitFunction(newBoard);
            //new Population is Pair that contains board and fitness
            newPopulation.add(new Pair(newBoard, fitness));
        }
    }

    //Create new board from parents (y - which block in sudoku board)
    public int[][] createNewBoard(int[][] parentBoardFirst, int[][] parentBoardSecond, int y) {
        int[][] newBoard = new int[sudokuSize][sudokuSize];

        Random random = new Random();
        //Randomly choose blocks and copy them to new board
        for(int i=0; i<y; i++){
            int row = random.nextInt(3) * 3;
            int col = random.nextInt(3) * 3;
            copySquare(parentBoardFirst, newBoard, row, col);
        }

        //Copy remaining blocks to new board
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (newBoard[row][col] == 0) {
                    copySquare(parentBoardSecond, newBoard, row, col);
                }
            }
        }

        //Sudoku have 1% to mutate
        boolean mutation = false;
        double chance = 0.01;
        double ifMutation = random.nextDouble();
        if(ifMutation < chance){
            mutation=true;
        }

        if(mutation){
            for(int i=0; i<10; i++){
                newBoard = mutate(newBoard);
            }
        }

        return newBoard;
    }

    //Copy square to new board
    private void copySquare(int[][] parent, int[][] newBoard, int row, int col){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newBoard[row + i][col + j] = parent[row + i][col + j];
            }
        }
    }

    //Mutate
    public int[][] mutate(int[][] board){

        Random random = new Random();
        int[] randomCoordinatesX = new int[3];
        randomCoordinatesX[0] = random.nextInt(2);
        randomCoordinatesX[1] = random.nextInt(5 - 3 + 1) + 3;
        randomCoordinatesX[2] = random.nextInt(8 - 6 + 1) + 6;
        int[] randomCoordinatesY = new int[3];
        randomCoordinatesY[0] = random.nextInt(2);
        randomCoordinatesY[1] = random.nextInt(5 - 3 + 1) + 3;
        randomCoordinatesY[2] = random.nextInt(8 - 6 + 1) + 6;

        int[] changeCoordinatesX = new int[3];
        changeCoordinatesX[0] = random.nextInt(2);
        changeCoordinatesX[1] = random.nextInt(5 - 3 + 1) + 3;
        changeCoordinatesX[2] = random.nextInt(8 - 6 + 1) + 6;
        int[] changeCoordinatesY = new int[3];
        changeCoordinatesY[0] = random.nextInt(2);
        changeCoordinatesY[1] = random.nextInt(5 - 3 + 1) + 3;
        changeCoordinatesY[2] = random.nextInt(8 - 6 + 1) + 6;

        for(int i=0; i<3; i++){
            int tmp = board[randomCoordinatesX[i]][randomCoordinatesY[i]];
            board[randomCoordinatesX[i]][randomCoordinatesY[i]] = board[changeCoordinatesX[i]][changeCoordinatesY[i]];
            board[changeCoordinatesX[i]][changeCoordinatesY[i]] = tmp;
        }

        return board;
    }

    //Find best half
    public List<Pair> findBestHalf(List<Pair> sudokuBoards) {
        Collections.sort(sudokuBoards, Comparator.comparingDouble(pair -> -pair.getFitness()));

        int childrenPopulationSize = populationSize / 2;

        return sudokuBoards.subList(0, childrenPopulationSize);
    }
}
