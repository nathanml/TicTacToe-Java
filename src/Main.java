import java.awt.*;
import java.util.Scanner;

public class Main{
    static Scanner input = new Scanner (System.in);
    static BoardGame game;

    public static void main(String[] args){
        //Welcome Menu
        System.out.println ("Welcome! Please enter the number of the game you want to play or 0 to exit");
        System.out.println ("1. Tic Tac Toe");
        System.out.println ("2. Order and Chaos");
        System.out.println ("0. Exit");
        while (!input.hasNextInt ()){
            System.out.println("Invalid Entry. Please select the number of the game you would like to play or press 0 to exit: ");
            input.nextLine();
        }
        int in = input.nextInt ();
        do{

            /*Initiate Tic Tac Toe Game */
            if(in == 1)
            {
                if(game == null)
                    game = new TicTacToe ();
                else game = new TicTacToe (game.numGames, game.teams , game.currentTeam);
                game.playGame();    //Initiates game of Tic Tac Toe
                System.out.println("Please enter 1 to play again, 2 to play Order and Chaos, or enter 0 to exit.");
                in = input.nextInt ();
            }

            /*Initiate Order and Chaos Game*/
            else if(in == 2)
            {
                //If this is the first game
                if(game== null)
                    game = new OrderAndChaos ();
                //If this is not the first game, keep existing players and start a new game
                else
                    game = new OrderAndChaos (game.numGames, game.teams, game.currentTeam);
                game.playGame();    //Initiates game of order and chaos
                System.out.println("Please enter the number of the board game you'd like to play or enter 0 to exit.");
                in = input.nextInt ();
            }

            //Invalid Entry
            else{
                System.out.println ("Invalid Entry. Please select the number of the game you would like to play or press 0 to exit: ");
                in = input.nextInt ();
            }
        }

        while(in != 0); //When to break from loop

        if(game == null)
        {
            System.out.println("Goodbye!");
            System.exit(0);
        }
        System.out.println("You played " + game.numGames + " games");
        if(game != null){
            for(Team t: game.teams)
            {
                System.out.println("Team " + t.getName () + ", you won " + t.getWins () + " games");
            }
        }
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
