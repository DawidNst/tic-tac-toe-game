package TicTacToe;

import java.util.List;

public class View extends Board {

    public void viewBoard(List<Character> board, int boardSize) {

        int columnNumber = 1;
        int rowNumber = 1;
        int listNumber = 0;

        System.out.print(" ");
        for (int i = 1; i <= boardSize; i++) {
            if (i < 10) {
                System.out.print("   " + columnNumber);
            }else
            {
                System.out.print("  " + columnNumber);
            }
            columnNumber++;
        }
        for (int i = 1; i <= boardSize; i++) {
            if (rowNumber < 10){
                System.out.print("\n " + rowNumber + "| ");
            }else {
                System.out.print("\n" + rowNumber + "| ");
            }
            for (int ii = 1; ii <= boardSize; ii++) {
                System.out.print(board.get(listNumber) + " | ");
                listNumber++;
            }
            rowNumber++;
        }
        System.out.println();
    }
}