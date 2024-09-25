/**
 * Rook
 *
 * @author Tomas Hagos, 7969147
 *
 * REMARKS: This is a subclass of Peice- represents the Rook
 */

public class Rook extends Piece{

    public Rook(int player)
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

        //if a capture is possible, it must be the opponents piece.
        if(!(gameBoard[toRow][toCol] != null && gameBoard[toRow][toCol].getPlayer() == getPlayer()))
        {
            if(fromRow == toRow || fromCol == toCol)
            {
                //this is a flag that tells us if we are moving through a row or column
                boolean colMove = true;

                if(fromCol == toCol)
                {
                    colMove = false;
                }

                //also direction we are moving. (Up or Down) or (left or right)
                int direction = 1;

                if(fromCol > toCol || fromRow > toRow)
                {
                    direction = -1;
                }

                boolean pieceDetected = false;
                boolean done = false;

                while(!pieceDetected && !done)
                {
                    //if it's a column move increment the column. Else the row.
                    if(colMove)
                    {
                        fromCol += direction;
                    }
                    else
                    {
                        fromRow +=direction;
                    }

                    //if we reached the destination
                    if(fromRow == toRow && fromCol == toCol)
                    {
                        done = true;
                    }
                    //if we detect a piece without reaching the destination
                    if(gameBoard[fromRow][fromCol] != null && !done)
                    {
                        pieceDetected = true;
                    }
                }

                valid = !pieceDetected;
            }
        }

        return valid;
    }


    public  boolean kingCapturePossible(int fromRow, int fromCol, Piece[][] gameBoard)
    {
        boolean capturePossible = false;

        int tempRow = fromRow;
        int tempCol = fromCol;
        //check going up the rows
        while(!capturePossible && tempRow >=0)
        {
            if(kingCapture(tempRow,fromCol,gameBoard))
            {
                capturePossible = true;
            }
            else
            {
                tempRow--;
            }
        }

        tempRow = fromRow;

        //check going down the rows
        while(!capturePossible && tempRow <= 7)
        {
            if(kingCapture(tempRow,fromCol,gameBoard))
            {
                capturePossible = true;
            }
            else
            {
                tempRow++;
            }
        }

        //check going to the right
        while(!capturePossible && tempCol <=7)
        {
            if(kingCapture(fromRow,tempCol,gameBoard))
            {
                capturePossible = true;
            }
            else
            {
                tempCol++;
            }
        }

        tempCol = fromCol;

        //check going to the left
        while(!capturePossible && tempCol >= 0)
        {
            if(kingCapture(fromRow,tempCol,gameBoard))
            {
                capturePossible = true;
            }
            else
            {
                tempCol--;
            }
        }

        return capturePossible;
    }


    public String getType()
    {
        return "Rook";
    }

    public String getPiece()
    {
        String piece = "r";

        if(getPlayer() == 1)
        {
            piece = "R";
        }

        return piece;
    }
}
