# TicTacToe-Java
TicTacToe game designed using object-oriented principles in Java

To compile and run this project, run the following command

javac Main.java
java Main

This project contains the following class hierarchy:

The Playable Interface: This holds methods shared by all games that can be played

BoardGame: An abstract class that implements the Playable Interface. This holds data and methods shared by all board games, including a board, and various competing teams.

Board: This is a class for a board object. Holds an array of Tiles.

Tile: This class is for tiles on the board. Tiles can either be empty or have a piece on them

Piece: This class is for the individual pieces that a board game can have. These pieces can be placed on tiles.

Player: This class is for the users of the game. Each player is part of a team and can access the game pieces and game board when it is their turn, and they can use those pieces to make valid game moves.

Team: The team class stores a collection of players who rotate turns. Teams also have a win count so they can see how they've played when the exit the game.

TicTacToe: This class extends the BoardGame class for a Tic Tac Toe game. The board size can be from 3x3 to 6x6 based on the user specification and each team gets assigned 1 piece, X or O.

OrderAndChaos: This class extends the BoardGame class for an Order and Chaos game. The board size is fixed to a 6x6 board and each team has access to both game pieces, X and O. For each move they get prompted which piece they'd like to play and which tile they'd like to play.

Main: The main class for the program creates the user interface where user can choose to play either Order and Chaos or Tic Tac Toe. Once the user chooses this class initializes the user specified game.