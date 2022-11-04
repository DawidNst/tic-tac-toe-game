package TicTacToe;

public class Gamer {
    private final String gamerName;
    private boolean gamerIsHuman;
    private final char gamerSymbol;


    public Gamer(String gamerName, boolean gamerIsHuman, char gamerSymbol) {
        this.gamerName = gamerName;
        this.gamerIsHuman = gamerIsHuman;
        this.gamerSymbol = gamerSymbol;
    }

    public String getGamerName() {
        return gamerName;
    }

    public char getGamerSymbol() {
        return gamerSymbol;
    }

    public boolean isGamerIsHuman() {
        return gamerIsHuman;
    }
}