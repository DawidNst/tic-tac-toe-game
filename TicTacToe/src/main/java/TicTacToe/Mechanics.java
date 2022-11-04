package TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class Mechanics extends Board{

    List<Gamer> playersList = new ArrayList<>();
    int usedSquares = 0;
    public int winningNumber ;
    boolean ifWin = false;
    int whichPlayer = 1;

    public int getUsedSquares() {
        return usedSquares;
    }

    public void setUsedSquares() {
        this.usedSquares++;
    }

    public void resetUsedSquares() {
        this.usedSquares = 0;
    }

    public boolean checkIfWinRows(List<Character> board, Gamer gamer, int boardSize) {
        ifWin = false;
        int j=0;
        int counter;
        while ((j <= board.size()-1) && (!ifWin)) {
            int i=j;
            while ((i <= (boardSize + j - winningNumber)) && (!ifWin)) {
                if (board.get(i) == gamer.getGamerSymbol()) {
                    int ii = i;
                    ifWin = true;
                    counter = 1;
                    while (counter<=winningNumber) {
                        if (board.get(ii) != gamer.getGamerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        ii++;
                        counter++;
                    }
                }
                i++;
            }
            j += boardSize;
        }
        return ifWin ;
    }
    public boolean checkIfWinColumns(List<Character> board, Gamer gamer, int boardSize) {
        ifWin = false;
        int j=0;
        int counter;
        while ((j <= (board.size() - (boardSize * winningNumber))) && (!ifWin)) {
            int i=j;
            while ((i < (boardSize + j)) && (!ifWin)) {
                if (board.get(i) == gamer.getGamerSymbol()) {
                    int ii = i;
                    ifWin = true;
                    counter = 1;
                    while (counter<=winningNumber) {
                        if (board.get(ii) != gamer.getGamerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        ii += boardSize;
                        counter++;
                    }

                }
                i++;
            }
            j += boardSize;
        }
        return ifWin ;
    }
    public boolean checkIfWinDiagonallyPositive(List<Character> board, Gamer gamer, int boardSize){
        ifWin = false;
        int j=0;
        int counter;
        while ((j <= board.size()- (boardSize * winningNumber)) && (!ifWin)) {
            int i=j;
            while ((i <= (boardSize + j - winningNumber)) && (!ifWin)) {
                if (board.get(i) == gamer.getGamerSymbol()) {
                    int ii = i;
                    ifWin = true;
                    counter = 1;
                    while (counter<=winningNumber) {
                        if (board.get(ii) != gamer.getGamerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        ii +=boardSize + 1 ;
                        counter++;
                    }
                }
                i++;
            }
            j += boardSize;
        }
        return ifWin ;
    }
    public boolean checkIfWinDiagonallyNegative(List<Character> board, Gamer gamer, int boardSize){
        ifWin = false;
        int j=0;
        int counter=0;
        while ((j <= (board.size()) - (boardSize * winningNumber)) && (!ifWin)) {
            int i=j+winningNumber-1;
            while ((i <= (boardSize + j-1)) && (!ifWin)) {
                if (board.get(i) == gamer.getGamerSymbol()) {
                    int ii = i+boardSize-1;
                    ifWin = true;
                    counter = 2;
                    while (counter<=winningNumber) {
                        if (board.get(ii) != gamer.getGamerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        ii +=boardSize - 1 ;
                        counter++;
                    }
                }
                i++;
            }
            j += boardSize;
        }
        return ifWin ;
    }

    public boolean gamerMove(List<Character> board, Gamer gamer, List<Integer> squareSelection, int boardSize, List<Integer> emptySquares ){
        boolean squareEmpty = false;
        int row = 0;
        int column = 0;
        int square=0;

        row = squareSelection.get(0)-1;
        column = squareSelection.get(1)-1;
        if (row == 0){
            square = column;
        }else {
            square = (row*boardSize)+column;
        }
        if ((board.get(square) != 'O') && (board.get(square) !='X')) {
            board.set(square, gamer.getGamerSymbol());
            squareEmpty = true;
            for (int i=0; i<=emptySquares.size();i++){
                if(emptySquares.get(i) == square){
                    emptySquares.remove(i);
                    break;
                }
            }
            usedSquares++;
        } else {
            System.out.println("Selected square is ready.Select another square:");
        }
        return  squareEmpty;
    }

    public void currentGamer(){
        if (whichPlayer == 0) {
            whichPlayer = 1;
        } else {
            whichPlayer = 0;
        }
    }

    public void setPlayers(){
        Entry entry = new Entry();
        String name;
        boolean isHuman;

        playersList.add(new Gamer("Player#1",true,'O'));
        playersList.add(new Gamer("Player#2",true,'X'));


        System.out.println("Enter name #1: ");
        name = entry.getStringEntry();
        System.out.println("Is player#1 human? [y]-yes, [n]-no:");
        isHuman = entry.selectYesOrNot();
        playersList.set(0,new Gamer(name,isHuman,'O'));

        System.out.println("Enter name #2: ");
        name = entry.getStringEntry();
        System.out.println("Is player#2 human? [y]-yes, [n]-no:");
        isHuman = entry.selectYesOrNot();
        playersList.set(1,new Gamer(name,isHuman,'X'));
    }
    public void setWinningNumber(int boardSize){
        Entry entry = new Entry();
        boolean numberValidation=false;
        while (!numberValidation) {
            System.out.println("Please enter number 3-" + boardSize + ":");
            try {
                winningNumber = entry.getIntegerEntry();
                if((winningNumber<=boardSize) &&(winningNumber>2))
                    numberValidation=true;
            } catch (WrongValueEnteredException ignored) {
            }
        }
    }
}