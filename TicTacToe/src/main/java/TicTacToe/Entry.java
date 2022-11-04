package TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entry {

    public int getIntegerEntry() throws WrongValueEnteredException{
        int chosenInteger;
        Scanner numberSelected = new Scanner(System.in);
        if (numberSelected.hasNextInt()) {
            chosenInteger = numberSelected.nextInt();
        }else {
            throw new WrongValueEnteredException("Entered value must be number");
        }
        numberSelected.reset();
        return chosenInteger;
    }

    public String getStringEntry(){
        String chosenString=null;
        Scanner StringSelected = new Scanner(System.in);
        if (StringSelected.hasNext()) {
            chosenString = StringSelected.next();
        }
        StringSelected.reset();
        return chosenString;
    }

    public boolean selectYesOrNot() {
        int tryCounter = 0;
        String entered;
        boolean enteredReturns = false;
        System.out.println("Please enter [y]-Yes or [n]-No");
        entered = getStringEntry().toUpperCase();
        while ((!entered.equalsIgnoreCase("Y")) && (!entered.equalsIgnoreCase( "N")) && (tryCounter < 5)){
            System.out.println("Please enter [y]-Yes or [n]-No");
            entered = getStringEntry().toUpperCase();
            tryCounter++;
        }
        if (entered.equalsIgnoreCase("Y")) {
            enteredReturns = true;
        }
        if (tryCounter>4) {
            System.out.println("You have entered invalid answer 5 times so we will answered for you. The answer is \"No\"");
            enteredReturns = false;
        }
        return enteredReturns;
    }

    public int setBoardSize(){
        boolean correctNumber = false;
        int numberSet=3;
        while (!correctNumber) {
            System.out.println("Select board size [3-99]");
            try {
                numberSet = getIntegerEntry();
                if ((numberSet>2) && (numberSet<100)){
                    correctNumber = true;
                }
            } catch (WrongValueEnteredException err) {
                System.out.println("You mast enter number from 3 to 99");
                correctNumber = false;
            }
        }
        return numberSet;
    }

    public List<Integer> squareSelection(int boardSize)  {
        List<Integer> listWithSelection = new ArrayList<>();
        boolean isNumber = false;
        int row;
        int column;
        listWithSelection.removeAll(listWithSelection);
        while(!isNumber) {
            Scanner enteredValue = new Scanner(System.in);
            Scanner numbersSelected = new Scanner(enteredValue.nextLine()).useDelimiter("\\s*-\\s*");
            if (numbersSelected.hasNextInt()) {
                row = numbersSelected.nextInt();
                if (numbersSelected.hasNextInt())  {
                    column = numbersSelected.nextInt();
                    if ((row > 0) && (row <= boardSize)) {
                        if ((column > 0) && (column <= boardSize)) {
                            listWithSelection.add(row);
                            listWithSelection.add(column);
                            isNumber = true;
                        } else {
                            System.out.println("Entered numbers must be grater then 0 and smaller then " + (boardSize + 1) + ":");
                        }
                    }
                }else{
                    System.out.println("You must enter number for rows and columns!");
                }
            }else {
                System.out.println("""
                You must select square. For example [12-23]. First number is for rows, second for columns"+
                "Entered numbers must be grater then 0 and smaller then """ + (boardSize + 1) + ":");
            }
            enteredValue.reset();
            numbersSelected.reset();
        }
        return listWithSelection;
    }
}