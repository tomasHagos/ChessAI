/**
 * Queen
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is a subclass of Peice- represents the Queen.
 * Queen has both properties of bishop and rook, and since multiple inheritance is not allowed in Java,
 * I made both the rook and bishop instances of the queen, so we can use the methods.
 */

public class Queen extends Piece{


    //queen contains both bishop and rook.
    private Bishop bishop;
    private Rook rook;

    public Queen(int player)
    {
       setPlayer(player);
       bishop = new Bishop(player);
       rook = new Rook(player);
    }


    public boolean validMove(Move currentMove, Piece[][] gameBoard)
    {
        //return the value of either the bishop or rook check.
        return bishop.validMove(currentMove,gameBoard) || rook.validMove(currentMove,gameBoard);
    }

    public String getType()
    {
        return "Queen";
    }

    public  boolean kingCapturePossible(int fromRow, int fromCol, Piece[][] gameBoard)
    {
        //return the value of either the bishop or rook check.
        return bishop.kingCapturePossible(fromRow,fromCol,gameBoard) ||
                rook.kingCapturePossible(fromRow,fromCol,gameBoard);
    }

    public String getPiece()
    {
        String piece = "q";

        if(getPlayer() == 1)
        {
            piece = "Q";
        }

        return piece;
    }


}
