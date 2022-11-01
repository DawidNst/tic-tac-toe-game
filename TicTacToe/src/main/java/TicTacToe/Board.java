package TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<Character> board = new ArrayList<>();
    public int boardSize;

    public Board(){}

    public void preparingBoard(int size){
        for(int i=0; i < size * size; i++){
            board.add('-');
        }
    }

    public void resetBoard(){
        for(int i=0; i <getBoardSize()*getBoardSize(); i++){
            board.set(i, '-');
        }
    }
    public int getBoardSize() {
        return boardSize;
    }
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

}

