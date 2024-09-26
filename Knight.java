/**
 * Knight
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is a subclass of Peice- represents the Knight
 */

public class Knight extends Piece {


    public Knight(int player)
    {

        setPlayer(player);
    }



    public boolean validMove(Move currentMove, Piece[][] gameBoard)
    {
        boolean valid = false;

        //get the data's from the move Object.
        int fromRow = currentMove.getFromRow();
        int fromCol = currentMove.getFromCol();
        int toRow = currentMove.getToRow();
        int toCol = currentMove.getToCol();

        //if there is a possibility of capturing a piece make sure we are capturing the opponents piece
        if(!(gameBoard[toRow][toCol] != null && gameBoard[toRow][toCol].getPlayer() == getPlayer()))
        {
            //if its L shaped. Perpendicular
            if(Math.abs(fromRow-toRow) == 2 && Math.abs(fromCol-toCol) ==1)
            {
                valid = true;
            }
            //L shaped Horizontal
            else if(Math.abs(fromRow-toRow) == 1 && Math.abs(fromCol-toCol) ==2)
            {
                    valid = true;
            }
        }


        return valid;
    }


    public  boolean kingCapturePossible(int fromRow, int fromCol, Piece[][] gameBoard)
    {
        boolean capturePossible = false;

        //check every possible way the last moved knight could capture the King's AI.
        //L shaped perpendicular Moves.
        if(fromRow + 1 <=7)
        {
            if(fromCol+2 <=7)
            {
                if(kingCapture(fromRow+1,fromCol+2,gameBoard))
                {
                    capturePossible = true;
                }
            }
            if(fromCol-2 >=0)
            {
                if(kingCapture(fromRow+1,fromCol-2,gameBoard))
                {
                    capturePossible = true;
                }
            }
        }
        //L shaped perpendicular, upwards
        if(fromRow-1>=0)
        {
            if(fromCol+2 <=7)
            {
                if(kingCapture(fromRow-1,fromCol+2,gameBoard))
                {
                    capturePossible = true;
                }
            }
            if(fromCol-2 >=0)
            {
                if(kingCapture(fromRow-1,fromCol-2,gameBoard))
                {
                    capturePossible = true;
                }
            }
        }
        //L shaped horizontal Moves, upwards
        if(fromRow+2 <= 7)
        {
            if(fromCol+1 <=7)
            {
                if(kingCapture(fromRow+2,fromCol+1,gameBoard))
                {
                    capturePossible = true;
                }
            }
            if(fromCol-1 >=0)
            {
                if(kingCapture(fromRow+2,fromCol-1,gameBoard))
                {
                    capturePossible = true;
                }
            }
        }
        //L shaped horizontal moves, downwards
        if(fromRow-2 >= 0)
        {
            if(fromCol+1 <=7)
            {
                if(kingCapture(fromRow-2,fromCol+1,gameBoard))
                {
                    capturePossible = true;
                }
            }
            if(fromCol-1 >=0)
            {
                if(kingCapture(fromRow-2,fromCol-1,gameBoard))
                {
                    capturePossible = true;
                }
            }
        }

        return  capturePossible;
    }



    public String getType()
    {
        return "Knight";
    }

    public String getPiece()
    {
        String piece = "n";

        if(getPlayer()== 1)
        {
            piece = "N";
        }

        return piece;
    }
}
