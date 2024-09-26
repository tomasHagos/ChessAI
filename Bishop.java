/**
 * Bishop
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is a subclass of Peice- represents the Bishop
 */

public class Bishop extends Piece{

    public Bishop(int player)
    {

        setPlayer(player);
    }


    public boolean validMove(Move currentMove, Piece[][] gameBoard)
    {

        //get the move data from the current move
        int fromRow = currentMove.getFromRow();
        int fromCol = currentMove.getFromCol();
        int toRow = currentMove.getToRow();
        int toCol = currentMove.getToCol();

        boolean valid = false;

        //if the space we want to move is not empty, we must make sure it lands on the other player's piece
        if(!(gameBoard[toRow][toCol] != null && gameBoard[toRow][toCol].getPlayer() == getPlayer()))
        {
            //if its a valid Move
            if(Math.abs(fromRow-toRow) == Math.abs(fromCol-toCol))
            {
                int rowDirection = 1;
                int colDirection = 1;
                //this represents which direction we are going on the board.
                if(fromCol > toCol)
                {
                    colDirection = -1;
                }
                if(fromRow > toRow)
                {
                    rowDirection = -1;
                }

                boolean pieceDetected = false;
                boolean done = false;

                //while piece is not detected and we didn't reach the destination
                while(!pieceDetected && !done)
                {
                    fromRow+=rowDirection;
                    fromCol+=colDirection;

                    if(fromRow == toRow && fromCol == toCol)
                    {
                        done = true;
                    }
                    //if a piece detected, and it is in between the moves(i.e not the landing one).
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

    //this Method is only used by the AI.
    // it checks if the last move made by the human can capture the king on the next play
    public  boolean kingCapturePossible(int fromRow, int fromCol, Piece[][] gameBoard)
    {
        //set capture possible to false at first.
        boolean capturePossible = false;
        //make another variable to retain the values of the paramters
        int tempRow = fromRow;
        int tempCol = fromCol;

        //check all possible ways it could potentially capture it.
        //ALL possible diagonal ways we could move, also make sure we don't go out of bounds.
        while(!capturePossible && tempRow <= 7 && tempCol <=7)
        {
            if(kingCapture(tempRow,tempCol,gameBoard))
            {
                capturePossible = true;
            }
            else
            {
                tempRow++;
                tempCol++;
            }
        }

         tempRow = fromRow;
         tempCol = fromCol;

        while(!capturePossible && tempRow <= 7 && tempCol >= 0)
        {
            if(kingCapture(tempRow,tempCol,gameBoard))
            {
                capturePossible = true;
            }
            else
            {
                tempRow++;
                tempCol--;
            }
        }

        tempRow = fromRow;
        tempCol = fromCol;

        while(!capturePossible && tempRow >= 0 && tempCol <= 7)
        {
            if(kingCapture(tempRow,tempCol,gameBoard))
            {
                capturePossible = true;
            }
            else
            {
                tempRow--;
                tempCol++;
            }
        }

        tempRow = fromRow;
        tempCol = fromCol;

        while(!capturePossible && tempRow >= 0 && tempCol >=0)
        {
            if(kingCapture(tempRow,tempCol,gameBoard))
            {
                capturePossible = true;
            }
            else
            {
                tempRow--;
                tempCol--;
            }
        }

        return capturePossible;
    }

    //used for the summarizer
    public String getType()
    {

        return "Bishop";
    }

    //used for the board
    public String getPiece()
    {
        String piece = "b";

        //if it's a human player
        if(getPlayer() == 1)
        {
            piece = "B";
        }

        return piece;
    }
}
