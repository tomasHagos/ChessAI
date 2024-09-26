/**
 * HardAI
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is a much harder AI. It is very defensive. If a king is going to be captured by a piece
 * it attemps to capture that piece.
 */
public class HardAi extends AI implements ChessPlayer{

    public HardAi(int player)
    {
        super(player);
    }

    public AiMove makeMove(Move humanMove, Piece[][] gameboard)
    {
        AiMove move = null;

        //if the king is going to be captured
        if(defenseNeeded(humanMove,gameboard))
        {
            //find a move that captures the piece that's attempting to capture the king.
            move = findDefendingMove(humanMove.getToRow(),humanMove.getToCol(),gameboard);
            //if a move was found
            if(move != null)
            {
                completeMove(move,gameboard);
            }
        }

        //Defending move not found or defending is not needed. We generate random move.
        if(move == null)
        {
            move = super.makeRandomMove(humanMove,gameboard);
        }

        return move;
    }

    /**
     * This methods finds a move that captures the piece that might capture the king.
     *
     * @param toRow  the row where the attacking piece is located
     * @param toCol the column where the attacking piece is located
     * @return  either the move or if a move could not be found return null.
     */
    private AiMove findDefendingMove(int toRow, int toCol, Piece[][] gameboard)
    {

        //initialize variables.
        AiMove defendingMove = null;
        Move currMove = new Move();

        for(int i=0; i < gameboard.length; i++)
        {
            for(int j=0; j < gameboard[i].length; j++)
            {
                //if it's the AI's piece
                if(gameboard[i][j] != null && gameboard[i][j].getPlayer() == getPlayer())
                {
                    //set the piece locations.
                    currMove.setFromRow(i);
                    currMove.setToRow(toRow);
                    currMove.setToCol(toCol);
                    currMove.setFromCol(j);

                    //if the current piece can capture the attacking piece
                    if(gameboard[i][j].validMove(currMove,gameboard))
                    {
                        //set up the defending AI move
                        defendingMove = new AiMove(gameboard[toRow][toCol],gameboard[i][j]);
                        defendingMove.setToCol(toCol);
                        defendingMove.setToRow(toRow);
                        defendingMove.setFromRow(i);
                        defendingMove.setFromCol(j);

                        //return it here.
                        //it is early return because if a suitable move is found,
                        // there is no point in looking for other move
                        return defendingMove;
                    }
                }
            }
        }

        return defendingMove;
    }

    /**
     * This method determines if the King is in a position where it could be captured.
     * It uses the methods from the Pieces.
     *
     * @return  returns true if it's a position where it could be captured, False otherwise.
     */
    public boolean defenseNeeded(Move humanMove, Piece[][] gameboard)
    {
        //this kingCapturePossible method is located in the piece's class.
        //getMoved() returns the piece that was recently moved by the human.
        //so king Capture method determines if that piece can capture the king on the next play
        return (humanMove.getMoved().kingCapturePossible(humanMove.getToRow(), humanMove.getToCol(),gameboard));
    }



}
