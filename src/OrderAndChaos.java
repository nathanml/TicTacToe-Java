import java.util.Scanner;

public class OrderAndChaos extends BoardGame implements Playable{

    private static Scanner input = new Scanner (System.in);

    public OrderAndChaos()
    {
        /*Method for initializing brand new game of Order and Chaos*/
        name = "Order and Chaos";
        initializePieces ();
        generateTeams ();
        currentTeam = teams[0];
        currentPlayer = currentTeam.getCurrentPlayer ();
        board = initializeBoard (6, 6);
        numTurns = 0;
        numGames = 0;
        winner = null;
    }

    public OrderAndChaos(int gameNo, Team[] t, Team first)
    {
        /*Method for initializing game of Order and Chaos with existing teams and players*/
        name = "Order and Chaos";
        initializePieces ();
        teams = t;
        teams[0].setPiece (gamePieces[0]);
        teams[1].setPiece (gamePieces[0]);
        currentTeam = first;
        currentPlayer = currentTeam.getCurrentPlayer ();
        board = initializeBoard (6,6);
        numTurns = 0;
        numGames = gameNo;
        winner = null;
    }

    @Override
    public void changeTeam() {
        /* Overridden method to change current team*/
        if(currentTeam.getName () == teams[0].getName ())
            currentTeam = teams[1];
        else currentTeam = teams[0];
    }

    @Override
    public void initializePieces() {
        /*Overridden method for initializing game pieces*/
        gamePieces = new Piece[]{new Piece ('X'), new Piece ('O')};
    }

    @Override
    public boolean isWinner(Player p, int row, int col) {
        /*Overridden method for determining if player has won after a move*/
        if(currentTeam == teams[1])
            return (numTurns == board.getNumTiles ());
        if(board.isWinningBoard (p.getCurrentPiece (), row, col, 5))
        {
            winner = p;
            return true;
        }
        else return false;
    }

    @Override
    public void generateTeams() {
        /*Overridden method for generating teams*/
        teams = new Team[2];
        System.out.println("Please enter first team name");
        String name = input.next();
        System.out.println("Team " + name + ", how many players are on your team?");
        while (!input.hasNextInt ()){
            System.out.println("Please enter an integer");
            input.next();
        }
        int n = input.nextInt ();
        teams[0] = new Team(name, n);
        System.out.println("Please enter second team name");
        name = input.next();
        System.out.println("Team " + name + ", how many players are on your team?");
        while (!input.hasNextInt ()){
            System.out.println("Please enter an integer");
            input.next();
        }
        n = input.nextInt ();
        teams[1] = new Team(name, n);
        teams[0].setPiece (gamePieces[0]);
        teams[1].setPiece (gamePieces[0]);
    }

    @Override
    public boolean isTie() {
        /*Overridden method for determining if there is a tie
        * Will always be false for Order and Chaos
        * */
        return false;
    }

    @Override
    public void playGame() {
        /*Method for playing Order and Chaos game*/
        isOver = false;

        do{
            //Order goes first
            System.out.println(currentPlayer.getName () + ", would you like to play X or O?");
            String in = input.next();

            //If player plays X
            if(in.equals ("X")|| in.equals ("x"))
            {
                currentTeam.setPiece (gamePieces[0]);  //Set player's piece to X
            }

            //If player plays O
            else if(in.equals ("O") || in.equals ("o"))
            {
                currentTeam.setPiece (gamePieces[1]);  //Set player's piece to O
            }

            //Invalid input
            else
            {
                System.out.println("Not a valid piece. Would you like to play X or O?");
                in = input.next();
                System.out.println(in);
            }

            //Print the board, prompt user for move and parse user input
            board.printBoard ();
            System.out.println("Which tile would you like to play " + currentPlayer.getCurrentPiece ().getVal () + "?");
            in = input.next();
            String[] move = in.split (",");
            while(move.length != 2)
            {
                System.out.println("Please enter the tile you'd like to play [row,column]");
                in = input.next ();
                move = in.split (",");
            }

            //Obtain and parse desired tile from player
            int row = Integer.parseInt (move[0]);
            int col = Integer.parseInt (move[1]);

            //If valid input entered
            if(board.isValid (row, col))
            {
                board.setTile (row, col, currentPlayer.getCurrentPiece ());   //Put player's piece on specified tile
                numTurns++;     //Increment number of turns in game
                board.printBoard ();

                //Checks if current player has won game after move
                if(isWinner (currentPlayer, row, col) || isTie ())
                {
                    isOver = true;      //If game is over, update isOver and break from loop
                }
                else{
                    currentTeam.changePlayer ();
                    changeTeam();    //Update current player if game not over
                    currentPlayer = currentTeam.getCurrentPlayer ();
                }

            }

            //If input is not valid
            else{
                System.out.println("Not a valid input");    //Inform user and do not update the player until valid move entered
            }

        }

        //breaks from loop when game is over
        while(isOver == false);

        //When current player is winner
            board.printBoard ();
            System.out.print ("Congratulations team " + currentTeam.getName () + "! You won Order and Chaos!");
            currentTeam.addWin ();    //Increment team's wins
            currentTeam.changePlayer ();    //Change player on current team
            changeTeam ();    //Change player (for future games)
            numGames++;

    }

    public static void main(String[] args){

    }
}
