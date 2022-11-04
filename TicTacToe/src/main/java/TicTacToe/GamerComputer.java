package TicTacToe;

import java.util.*;

public class GamerComputer {

    Mechanics mechanics = new Mechanics();
    public List<Integer> emptySquares = new ArrayList<>();

    public void creatingComputerMoves(int boardListSize) {
        for (int i = 0; i < boardListSize; i++) {
            emptySquares.add(i);
        }
    }
    public boolean computerMove(List<Character> board, Gamer gamer, List<Integer> emptySquares ){
        Random rand = new Random();
        boolean squareEmpty = false;

        if (emptySquares.size()>0) {
            int square = rand.nextInt(emptySquares.size());
            board.set(emptySquares.get(square), gamer.getGamerSymbol());
            emptySquares.remove(square);
            mechanics.getUsedSquares();
            squareEmpty = true;
        }
        return  squareEmpty;
    }

    public List<Integer> getEmptySquares() {
        return emptySquares;
    }
}