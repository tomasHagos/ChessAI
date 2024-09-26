/**
 * Pawn
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is a subclass of Peice- represents the Pawn
 */

public class Pawn extends Piece
{


    public Pawn(int player)
    {

       setPlayer(player);
    }

    public boolean validMove(Move currentMove, Piece[][] gameBoard)
    {
        boolean valid = false;

        int fromRow = currentMove.getFromRow();
        int fromCol = currentMove.getFromCol();
        int toRow = currentMove.getToRow();
        int toCol = currentMove.getToCol();

        //if it's the AI
        int moved = -1;

        //if it's the human.
        if(getPlayer() == 1)
        {
           moved = 1;
        }

        //depending on if we are going Down or UP(depends on which player).
        if(fromRow == (toRow +moved))
        {
            //if we are just moving forward
            if(fromCol == toCol && gameBoard[toRow][toCol] == null)
            {
                valid = true;
            }
            //if we are possibly capturing we must ensure it's the other player's piece
            else if(gameBoard[toRow][toCol] != null && gameBoard[toRow][toCol].getPlayer() != getPlayer())
            {
                //ensure it is a diagonal move
                if(fromCol == toCol-1 || fromCol == toCol+1)
                {
                    valid = true;
                }
            }
        }

        return valid;
    }


    //used by the AI.
    public boolean kingCapturePossible(int fromRow, int fromCol,Piece[][] gameboard)
    {
        boolean capturePossible = false;

        fromRow--;

        //check if a capture is possible diagonally to the right.
        if(fromCol+1 <= 7)
        {
            if(kingCapture(fromRow,fromCol+1,gameboard))
            {
                capturePossible = true;
            }
        }

        //check if a capture is possible diagonally to the left
        if(fromCol-1>=0)
        {
            if(kingCapture(fromRow,fromCol-1,gameboard))
            {
                capturePossible = true;
            }
        }

        return capturePossible;
    }


    public String getType()
    {

        return "Pawn";
    }




    public String getPiece()
    {
       String piece = "p";

       if(getPlayer() == 1)
       {
           piece = "P";
       }

       return piece;

    }
}
