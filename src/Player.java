public class Player {
    private Piece currentPiece; //Stores piece that the player is using on given turn
    private String name;    //Player's name

    public Player(String n){
        /*Constructor method for player*/
        currentPiece = null;
        name = n;
    }

    public String getName(){
        /*Method for getting a player's name*/
        return name;
    }

    public char getVal(){
        /*Method for obtaining the value of the player's current piece*/
        return currentPiece.getVal ();
    }

    public void changePiece(Piece p){
        /*Method for changing pieces*/
        currentPiece = p;
    }

    public Piece getCurrentPiece(){
        return currentPiece;
    }

}
