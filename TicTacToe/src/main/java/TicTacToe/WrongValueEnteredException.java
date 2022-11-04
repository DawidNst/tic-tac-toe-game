package TicTacToe;

public class WrongValueEnteredException extends Exception{
    public WrongValueEnteredException(String message){
        super(message);
    }
}