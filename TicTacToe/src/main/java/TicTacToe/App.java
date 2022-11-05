package TicTacToe;

public class App {

    public static void main(String[] args) {

        Mechanics mechanics = new Mechanics();
        Board board = new Board();
        Entry entry = new Entry();
        View view = new View();
        GamerComputer gamerComputer = new GamerComputer();
        boolean theEnd;
        boolean vileMove;
        boolean deadHeat;
        boolean winner;

        initialization(mechanics, board, entry);
        gamerComputer.creatingComputerMoves(board.board.size());
        do {
            winner = false;
            deadHeat = false;
            vileMove = false;
            while((!winner) && (!deadHeat)) {
                mechanics.currentGamer();
                mechanics(mechanics, board, entry, view, gamerComputer, vileMove);

                winner = isWinner(mechanics, board);
                vileMove = false;
                if (((mechanics.getUsedSquares() >= board.board.size()) || (gamerComputer.emptySquares.size()==0)) && (!winner)) {
                    System.out.println("dead-heat");
                    deadHeat = true;
                }
            }
            if (winner) {
                view.viewBoard(board.board, board.getBoardSize());
                System.out.println("\n*** " + mechanics.playersList.get(mechanics.whichPlayer).getGamerName() + " You win! ***");
            }
            System.out.println("\nI will play again?");
            theEnd = entry.selectYesOrNot();
            if ((theEnd) || (mechanics.getUsedSquares() >= board.board.size())) {
                board.resetBoard();
                mechanics.resetUsedSquares();
                gamerComputer.emptySquares.removeAll(gamerComputer.emptySquares);
                gamerComputer.creatingComputerMoves(board.board.size());
            }
        }
        while (theEnd);
        System.out.println("\nSee you!");
    }

    private static void mechanics(Mechanics mechanics, Board board, Entry entry, View view, GamerComputer gamerComputer, boolean vileMove) {
        while (!vileMove){
            view.viewBoard(board.board, board.getBoardSize());
            if (mechanics.playersList.get(mechanics.whichPlayer).isGamerIsHuman()){
                System.out.println("\n" + mechanics.playersList.get(mechanics.whichPlayer).getGamerName() + ":");
                vileMove = mechanics.gamerMove(board.board, mechanics.playersList.get(mechanics.whichPlayer)
                        , entry.squareSelection(board.getBoardSize()), board.getBoardSize(), gamerComputer.getEmptySquares());
            }else{
                if(!mechanics.playersList.get(mechanics.whichPlayer).isGamerIsHuman()){
                    vileMove = gamerComputer.computerMove(board.board, mechanics.playersList.get(mechanics.whichPlayer)
                            , gamerComputer.getEmptySquares());
                }
            }
        }
    }

    private static boolean isWinner(Mechanics mechanics, Board board) {
        boolean winner;
        winner = mechanics.checkIfWinRows(board.board, mechanics.playersList.get(mechanics.whichPlayer)
                , board.getBoardSize());
        if (!winner) {
            winner = mechanics.checkIfWinColumns(board.board, mechanics.playersList.get(mechanics.whichPlayer)
                    , board.getBoardSize());
            if (!winner) {
                winner = mechanics.checkIfWinDiagonallyPositive(board.board, mechanics.playersList.get(mechanics.whichPlayer)
                        , board.getBoardSize());
                if (!winner) {
                    winner = mechanics.checkIfWinDiagonallyNegative(board.board, mechanics.playersList.get(mechanics.whichPlayer)
                            , board.getBoardSize());
                }
            }
        }
        return winner;
    }

    private static void initialization(Mechanics mechanics, Board board, Entry entry) {
        System.out.println("""
                Welcome,
                Will you play Tic Tac Toe.
                """);
        board.setBoardSize(entry.setBoardSize());
        System.out.println("""
                Focus! Now select "O" or "X", it will be you :):
                """);
        mechanics.setWinningNumber(board.getBoardSize());
        System.out.println("\nGood! Let's choose the gamers.\n");
        mechanics.setPlayers();
        board.preparingBoard(board.getBoardSize());
        System.out.println("""              
                 It's nice to see you! Are you ready for the first move? Choose the square you want to select. 
                 Go ahead, you do it this way [6-16]. 
                 (If the board size is greater than or equal to 34) The first digit is the second row column.
                """);
    }
}