/**
 * King
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is a subclass of Peice- represents the King
 */

public class King extends Piece{



    public King(int player)
    {

        setPlayer(player);
    }

    public  boolean validMove(Move currentMove, Piece[][] gameBoard)
    {
        int fromRow = currentMove.getFromRow();
        int fromCol = currentMove.getFromCol();
        int toRow = currentMove.getToRow();
        int toCol = currentMove.getToCol();

        boolean valid = false;
        //if there is a possibility of capturing a piece make sure we are capturing the opponent's piece
        if(!(gameBoard[toRow][toCol] != null && gameBoard[toRow][toCol].getPlayer() == getPlayer()))
        {
            //check all the possible moves the king could make.
            if(Math.abs(fromRow - toRow) == 1 && fromCol == toCol)
            {
                valid = true;
            }
            else if(Math.abs(fromCol - toCol) == 1 && fromRow == toRow)
            {
                valid = true;
            }
            else if(Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1)
            {
                 valid = true;
            }
        }

        return valid;
    }


    public  boolean kingCapturePossible(int fromRow, int fromCol, Piece[][] gameBoard)
    {
        //here we are checking if the human last moved King could capture the king.
        boolean capturePossible = false;

        //check all the possible way the King could capture the Ai's King

        //horizontal move to the right check
        if(fromCol +1 <=7)
        {
            capturePossible = kingCapture(fromRow,fromCol+1,gameBoard);
        }

        //horizontal move to the left check
        if(fromCol-1 >=0)
        {
            capturePossible = kingCapture(fromRow,fromCol-1,gameBoard);
        }

        //vertical move upward check
        if(fromRow+1 <=7)
        {
            capturePossible = kingCapture(fromRow+1,fromCol,gameBoard);
        }

        //vertical move downwards check
        if(fromRow-1>=0)
        {
            capturePossible = kingCapture(fromRow-1,fromCol,gameBoard);
        }

        //all the if statements from here are diagonal check.
        if(fromRow+1 <=7 && fromCol+1 <=7)
        {
            capturePossible = kingCapture(fromRow+1,fromCol+1,gameBoard);
        }

        if(fromRow+1 <=7 && fromCol-1 >= 0)
        {
            capturePossible = kingCapture(fromRow+1,fromCol-1,gameBoard);
        }

        if(fromRow-1 >= 0 && fromCol+1 <= 7)
        {
            capturePossible = kingCapture(fromRow-1,fromCol+1,gameBoard);
        }

        if(fromRow-1 >= 0 && fromCol-1 >=0)
        {
            capturePossible = kingCapture(fromRow-1,fromCol-1,gameBoard);
        }

        return capturePossible;

    }

    public String getType()
    {
        return "King";
    }

    public String getPiece()
    {
        String piece = "k";

        if(getPlayer() == 1)
        {
            piece = "K";
        }

        return piece;
    }
}
