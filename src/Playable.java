public interface Playable {
    /*Interface used to store methods shared by ALL games that can be played*/

    public void generateTeams();    //Method for creating teams

    public boolean isWinner(Player p);  //All games must be able to validate if player is winner

    public boolean isTie();     //Method for determining if game is a tie

    public void playGame();     //Method for playing the game
}
