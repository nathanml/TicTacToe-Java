import java.util.Scanner;

public class TicTacToe extends BoardGame implements Playable {

    private static Scanner input = new Scanner (System.in);

    //Method for initializing brand new game of Tic Tac Toe
    public TicTacToe()
    {
        name = "Tic Tac Toe";
        initializePieces ();
        generateTeams ();
        currentTeam = teams[0];
        currentPlayer = currentTeam.getCurrentPlayer ();
        int size = getSize ();
        board = initializeBoard (size, size);
        numTurns = 0;
        numGames = 0;
        winner = null;
    }


    //Method for initializing game of Tic Tac Toe with existing players
    public TicTacToe(int gameNo, Team[] t, Team first){
        //Constructor for TicTacToe game. Takes 2 inputs for height and width of board
        name = "Tic Tac Toe";
        initializePieces ();
        teams = t;
        teams[0].setPiece (gamePieces[0]);
        teams[1].setPiece (gamePieces[1]);
        currentTeam = first;
        currentPlayer = currentTeam.getCurrentPlayer ();
        int size = getSize ();
        board = initializeBoard (size, size);
        numGames = gameNo;
        numTurns = 0;
        winner = null;
    }

    @Override
    public void changeTeam() {
        if(currentTeam.getName () == teams[0].getName ())
            currentTeam = teams[1];
        else currentTeam = teams[0];
        currentPlayer = currentTeam.getCurrentPlayer ();
    }

    public int getSize(){
        System.out.println("Please enter the size of the board you would like to play (3, 4, 5, or 6): ");
        while (!input.hasNextInt ()){
            System.out.println("Please enter an integer");
            input.next();
        }
        int dim = input.nextInt ();

        //Checks that dimension is valid for TicTacToe
        while(dim < 3 || dim > 6)
        {
            System.out.println("Not a valid board size. Please enter the size of the board you would like to play (3, 4, 5, or 6): ");
            dim = input.nextInt ();
        }
        return dim;
    }

    //Overridden method for generating players
    @Override
    public void generateTeams() {
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
        teams[1].setPiece (gamePieces[1]);
    }

    //Overridden method for initializing game pieces
    @Override
    public void initializePieces() {
        gamePieces = new Piece[] {new Piece('X'), new Piece ('O')};
    }


    //Overridden method for checking if player has won the game
    @Override
    public boolean isWinner(Player p, int row, int col) {
        if(board.isWinningBoard (p.getCurrentPiece (), row, col, board.getNumRows ()))
        {
            winner = p;
            return true;
        }
        else return false;
    }

    //Overridden method for checking if game is tied
    @Override
    public boolean isTie() {
        return (numTurns == board.getNumTiles ());
    }

    @Override
    public void playGame() {
        //Method for playing a Tic Tac Toe Game
        isOver = false;

        //While game is not over
        do{
            board.printBoard ();       //Print board
            System.out.println (currentTeam.getCurrentPlayer ().getName () + ", please enter the tile you'd like to play [row,column]: ");
            String in = input.next ();
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

                //Checks if current player has won game after move
                if(isWinner (currentPlayer, row, col) || isTie ())
                {
                    isOver = true;      //If game is over, update isOver and break from loop
                }
                else{
                    currentTeam.changePlayer ();
                    changeTeam ();    //Update current player if game not over
                }
            }

            //If input is not valid
            else{
                System.out.println("Not a valid input");    //Inform user and do not update the player until valid move entered
            }
        }

        //Will break from above loop when game is over
        while(isOver==false);


        if(isTie ())
        //Checks if game is tied
        {
            System.out.println("Tie Game!");
            currentTeam.changePlayer ();
            changeTeam ();    //Change player for future games
            numGames++;
        }

        else
        //When current player is winner
        {
            board.printBoard ();
            System.out.print ("Congratulations Team " + currentTeam.getName () + "! You won Tic Tac Toe!");
            currentTeam.addWin ();    //Increment player's wins
            currentTeam.changePlayer ();    //Change player on team
            changeTeam ();    //Change team
            numGames++;
        }
    }


    public static void main(String[] args){

    }

}
