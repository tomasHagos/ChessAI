/**
 * Piece
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is an abstract class Piece.
 */

public abstract class Piece
{

    //initialized to -1 as a placeholder
    // the subclasses initialize it appropriately
    private int player = -1;

    public int getPlayer()
    {
        return player;
    }


    /**
     * kingCapturePossible: this method determines if the human Player can capture the AI's king.
     * I included it in the piece class not the AI, as a way to use polymorphism- this property is evident in the
     * hardAI class.
     * It uses the last Human Move made as a reference
     *
     * @param fromRow  last Human Move row
     * @param fromCol  last Human Move column

     * @return   returns true if the king can be captured by the Human.
     */
    public abstract boolean kingCapturePossible(int fromRow, int fromCol, Piece[][] gameBoard);


    public void setPlayer(int player)
    {
        this.player = player;
    }

    /**
     * This method determines if a move is valid for the specific piece
     *
     * @param currentMove the attempted move
     * @return   true if the move is valid.
     */
    public abstract boolean validMove(Move currentMove, Piece[][] gameBoard);
    public abstract String getPiece();

    public abstract String getType();


    /**
     * This method determines if a king Capture is possible.
     *
     * @param fromRow the current Row
     * @param fromCol the current column
     * @return  true if a king can be captured.
     */
    public boolean kingCapture(int fromRow, int fromCol,Piece[][] gameBoard)
    {
        Piece currentPiece = gameBoard[fromRow][fromCol];
        //check if the king is opponent's king.
        return currentPiece != null && currentPiece.getPlayer() != getPlayer() &&
                currentPiece.getType().equalsIgnoreCase("king");
    }

}
