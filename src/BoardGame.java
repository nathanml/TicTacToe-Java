import java.util.Scanner;

public abstract class BoardGame implements Playable{
    /*Abstract class for all board games. All games will have a name and a playGame method */
    String name;    //Name
    Team[] teams;   //List of all teams playing game
    Team currentTeam;   //Team who's turn it is
    Player currentPlayer;   //Player who's turn it is
    Player winner;      //Stores winner
    Piece[] gamePieces; //List of all pieces used for game
    Board board;        //Game board
    int numTurns;       //Counts the how many turns there have been in a game
    boolean isOver;     //Keeps track of when game is over
    int numGames;       //Tracks how many games have been played

    public BoardGame(){
        /*Constructor for board game*/
    }

    public Board initializeBoard(int h, int w) {
        /*Method for initializing empty board*/
        Board board = new Board(h, w);
        return board;
    }

    public boolean isWinner(Player p){
        /* Method implemented from Playable interface for determining
        *  if player is winner of the board game. Used for checking winner of other potential board games
        */
        return (winner==p);
    }

    public abstract void changeTeam();  //Abstract method for changing the team when a turn is over

    public abstract void initializePieces();    //Abstract method for initializing pieces

    //Abstract method for determining if player is winner after specified move (row, col)
    public abstract boolean isWinner(Player p, int row, int col);

    public static void main(String[] args) {
        // write your code here
    }
}
