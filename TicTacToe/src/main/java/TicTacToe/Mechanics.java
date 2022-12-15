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
        int row=0;
        int counter;
        while ((row <= board.size()-1) && (!ifWin)) {
            int square=row;
            while ((square <= (boardSize + row - winningNumber)) && (!ifWin)) {
                if (board.get(square) == gamer.getGamerSymbol()) {
                    int symbol = square;
                    ifWin = true;
                    counter = 1;
                    while (counter<=winningNumber) {
                        if (board.get(symbol) != gamer.getGamerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        symbol++;
                        counter++;
                    }
                }
                square++;
            }
            row += boardSize;
        }
        return ifWin ;
    }
    public boolean checkIfWinColumns(List<Character> board, Gamer gamer, int boardSize) {
        ifWin = false;
        int row=0;
        int counter;
        while ((row <= (board.size() - (boardSize * winningNumber))) && (!ifWin)) {
            int square=row;
            while ((square < (boardSize + row)) && (!ifWin)) {
                if (board.get(square) == gamer.getGamerSymbol()) {
                    int column = square;
                    ifWin = true;
                    counter = 1;
                    while (counter<=winningNumber) {
                        if (board.get(column) != gamer.getGamerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        column += boardSize;
                        counter++;
                    }

                }
                square++;
            }
            row += boardSize;
        }
        return ifWin ;
    }
    public boolean checkIfWinDiagonallyPositive(List<Character> board, Gamer gamer, int boardSize){
        ifWin = false;
        int row=0;
        int counter;
        while ((row <= board.size()- (boardSize * winningNumber)) && (!ifWin)) {
            int square=row;
            while ((square <= (boardSize + row - winningNumber)) && (!ifWin)) {
                if (board.get(square) == gamer.getGamerSymbol()) {
                    int column = square;
                    ifWin = true;
                    counter=1;
                while (counter<=winningNumber) {
                        if (board.get(column) != gamer.getGamerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        column +=boardSize + 1 ;
                        counter++;
                    }
                }
                square++;
            }
            row += boardSize;
        }
        return ifWin ;
    }
    public boolean checkIfWinDiagonallyNegative(List<Character> board, Gamer gamer, int boardSize){
        ifWin = false;
        int row=0;
        int counter=0;
        while ((row <= (board.size()) - (boardSize * winningNumber)) && (!ifWin)) {
            int square=row+winningNumber-1;
            while ((square <= (boardSize + row-1)) && (!ifWin)) {
                if (board.get(square) == gamer.getGamerSymbol()) {
                    int column = square+boardSize-1;
                    ifWin = true;
                    counter = 2;
                    while (counter<=winningNumber) {
                        if (board.get(column) != gamer.getGamerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        column +=boardSize - 1 ;
                        counter++;
                    }
                }
                square++;
            }
            row += boardSize;
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
                if(emptySquares.get(square) == square){
                    emptySquares.remove(square);
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

    public void setPlayers() {

        playersList.add(new Gamer("Player#1", false, 'O'));
        playersList.add(new Gamer("Player#2", false, 'X'));

        initPlayer(1,'0');
        initPlayer(2,'X');
    }
        private void initPlayer( int playerNumber, char playerSymbol){
            String name;
            boolean isHuman;
            Entry entry = new Entry();
            System.out.println("Enter name #" + playerNumber + " : " );
            name = entry.getStringEntry();
            System.out.println("Is player#" + playerSymbol + " human? [y]-yes, [n]-no:");
            isHuman = entry.selectYesOrNot();
            playersList.set(1,new Gamer(name,isHuman,playerSymbol));
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