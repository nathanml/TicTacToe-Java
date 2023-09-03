import java.util.Scanner;

public class Team{
    private String name; //Name of the team
    private Player[] players;   //List of all players on team
    private int playerIndex;    //Index to keep track of current player
    private int numWins;        //Stores team's wins
    private Player currentPlayer;   //Team's current player
    private static Scanner input = new Scanner(System.in);

    public Team(String teamName, int n)
    {
        /* Constructor method for team
        *  Individually initializes each player on team
        * */
        name = teamName;
        players = new Player[n];
        for(int i=0; i<n; i++)
        {
            System.out.println("Player " + i + ", enter your name: ");
            String name = input.next ();
            players[i] = new Player (name);
        }
        playerIndex = 0;
        numWins = 0;
        currentPlayer = players[playerIndex];
    }

    public void changePlayer()
    {
        /* Method for updating the current player for a team*/
        if(playerIndex == players.length -1) {
            playerIndex = 0;
        }
        else {
            playerIndex++;
        }
        currentPlayer = players[playerIndex];
    }

    public Player getCurrentPlayer(){
        /* Method for obtaining current player*/
        return currentPlayer;
    }

    public void addWin(){
        /* Method to increase team's win count*/
        numWins++;
    }

    public int getWins(){
        /*Method for getting team's number of wins*/
        return numWins;
    }

    public String getName(){
        /* Method for obtaining team name*/
        return name;
    }

    public void setPiece(Piece p){
        /* Method for updating the team's current piece
        *  Updates every player on the team's current piece
        * */
        for(Player player: players)
            player.changePiece (p);
    }

    public static void main(String[] args){

    }

}
