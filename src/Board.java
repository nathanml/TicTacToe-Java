public class Board {
    private int numRows;    //Number of rows in board
    private int numColumns; //Number of columns in board
    private Tile[][] tiles; //Array of tiles for holding pieces
    private int numTiles;   //Stores number of tiles on board (for checking if board is full)

    public Board(int w, int h){
        /* Method for initializing a board to specified dimensions*/
        numTiles = h*w;
        numRows = h;
        numColumns = w;
        tiles = new Tile[w][h];
    }

    public void printBoard(){
        /* Method for displaying board to user */
        for(int i=0; i < numRows; i++){
            String toPrint = "";
            for(int j=0; j < numColumns; j++){
                if(tiles[i][j] == null) {
                    tiles[i][j] = new Tile(new Piece(' '));
                }
                toPrint += tiles[i][j].getTile();
            }
            System.out.println(toPrint);
        }
    }

    public int getNumTiles()
    {
        /*  Method for obtaining number of rows */
        return numTiles;
    }

    public int getNumRows()
    {
        /*  Method for obtaining number of rows */
        return numRows;
    }

    public int getNumColumns()
    {
        /*  Method for obtaining number of rows */
        return numColumns;
    }

    public Tile tileAt(int row, int col) {
        /*Method for returning specified tile*/
        return tiles[row][col];
    }

    public void setTile(int row, int col, Piece p){
        /* Method for putting piece on tile*/
        tiles[row][col] = new Tile (p);
    }

    public boolean isValid(int row, int col){
        /*Method for checking if move is valid in game*/
        if(row >= numRows || row < 0 || col >= numColumns || col < 0)
            return false;
        if(tileAt(row,col).currentOccupant.getVal() == ' ')
            return true;
        else return false;
    }

    public boolean isWinningBoard(Piece p, int row, int col, int size){
        /*Method for determining if inputted piece has won the board*/
        return(isRowWin (p, row, size) || isColWin (p, col, size) || isDiagWin (p, row, col, size));
    }

    /*  Helper methods loop through the current piece's row, column, and diagonal.
    *   The size parameter is holds the necessary number in a row for a win.
    *   The currentWindow variable is used to check for 5 in a row when the board is 6x6
    *   in Order and Chaos.
    * */

    public boolean isRowWin(Piece p, int row, int size)
    {
        /*Helper method for checking if input piece has row win*/
        int currentWindow=0;
        for(int i = 0; i < numColumns; i++)
        {
            if(tiles[row][i].currentOccupant.getVal() == p.getVal ())
                currentWindow++;
            else currentWindow = 0;
            if(currentWindow == size)
                return true;
        }
        return false;
    }

    public boolean isColWin(Piece p, int col, int size)
    {
        /*Helper method for checking if input piece has column win*/
        int currentWindow = 0;
        for(int i=0; i < numRows; i++)
        {
            if(tiles[i][col].currentOccupant.getVal () == p.getVal ())
            {
                currentWindow++;
            }
            else currentWindow=0;
            if(currentWindow == size)
                return true;
        }
        return false;
    }

    public boolean isDiagWin(Piece p, int row, int col, int size) {
        /*Helper method for checking if input piece has row win*/

        //Case 1: Diagonal left to right
        int currentWindow = 0;
        if (row == col) {
            //Check for diagonal win
            for (int i = 0; i < numRows; i++) {
                if (tiles[i][i].currentOccupant.getVal () == p.getVal ())
                    currentWindow++;
                else currentWindow = 0;
                if(currentWindow == size)
                    return true;
            }
        }

        //Case 2: Diagonal right to left
        else if (row == (numRows-1) - col) {
            for (int i = 0; i < numRows; i++) {
                if (tiles[i][(numRows-1) - i].currentOccupant.getVal () == p.getVal ())
                    currentWindow++;
                else currentWindow=0;

                if(currentWindow == size)
                    return true;
            }
        }

        //Case 3: Diagonal from [0,4] to [4,0]
        else if(row + col == 4){
            for (int i = 0; i < 5; i++)
            {
                if(tiles[i][4-i].currentOccupant.getVal () == p.getVal ())
                    currentWindow++;
                else currentWindow = 0;

                if(currentWindow == size)
                    return true;
            }
        }

        //Case 4: Diagonal from [1,5] to [5,1]
        else if(row + col == 6){
            for (int i = 1; i < 6; i++)
            {
                if(tiles[i][6-i].currentOccupant.getVal () == p.getVal ())
                    currentWindow++;
                else currentWindow = 0;

                if(currentWindow == size)
                    return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
	// write your code here
    }
}
