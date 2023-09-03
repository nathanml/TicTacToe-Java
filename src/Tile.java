public class Tile {

    public Piece currentOccupant;   //Stores piece currently on tile

    public Tile(Piece p)
    {
        /* Constructor method for tile*/
        currentOccupant = p;
    }

    public String getTile()
    {
        /* Obtains string representation of tile
        *  Useful for printing board
        */
        return("[" + currentOccupant.getVal () + "]");
    }



    public static void main(String[] args) {
        // write your code here
    }
}
