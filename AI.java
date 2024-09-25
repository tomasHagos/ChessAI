/**
 * AI
 *
 * @author Tomas Hagos, 7969147
 *
 * REMARKS: It is an abstract class that has common properties for the two AI's.
 */
public  abstract class AI {

    private int player;

    public AI(int player)
    {
        this.player = player;
    }

    public int getPlayer()
    {
        return player;
    }


    public void promotePawn(int column,Piece[][] gameBoard)
    {
        //choose a random from 0-3. So each Promoted piece is different.
        int max = 3;
        int min = 0;
        int range = max - min + 1;
        int piece = (int)(Math.random() * range) + min;

        Piece promoted;
        if(piece == 0)
        {
            promoted = new Queen(getPlayer());
        }
        else if(piece == 1)
        {
            promoted = new Bishop(getPlayer());
        }
        else if(piece == 2)
            {
                promoted = new Rook(getPlayer());
            }
            else
            {
                promoted = new Knight(getPlayer());
            }
        //promote the piece, by changing the gameboard
        gameBoard[7][column] = promoted;

        System.out.println("Computer promoted its pawn to: "+ promoted.getType());
    }



    public AiMove makeRandomMove(Move humanMove, Piece[][] gameboard)
    {
        //make a random move. Set up the boundaries.
        int max = 7;
        int min = 0;
        int range = max - min + 1;


        Piece currentPiece = null;
        int fromRow, fromCol, toRow, toCol;
        AiMove possibleMove = new AiMove(null,null);


        //while the move is not legal, try finding a legal move again and again
        while(!(currentPiece != null && currentPiece.getPlayer() == 2 && currentPiece.validMove(possibleMove,gameboard)))
        {
            fromRow = (int)(Math.random() * range) + min;
            fromCol = (int)(Math.random() * range) + min;
            toCol = (int)(Math.random() * range) + min;
            toRow =  (int)(Math.random() * range) + min;

            currentPiece = gameboard[fromRow][fromCol];
            //add the data's here. They will be returned to the summarizer.
            possibleMove.setMoved(currentPiece);
            possibleMove.setCaptured(gameboard[toRow][toCol]);
            possibleMove.setFromRow(fromRow);
            possibleMove.setToRow(toRow);
            possibleMove.setToCol(toCol);
            possibleMove.setFromCol(fromCol);
        }

        //complete the move, since if we exit from the loop it implies that a legal Move has been found.
        completeMove(possibleMove,gameboard);

        return possibleMove;
    }
    public void completeMove(AiMove possibleMove, Piece[][] gameboard)
    {
        //if the other player's king is captured set the flag to true.
        if(possibleMove.getCaptured() != null && possibleMove.getCaptured().getType().equalsIgnoreCase("King"))
        {
            possibleMove.setKingCaptured(true);
        }

        //make the move
        gameboard[possibleMove.getFromRow()][possibleMove.getFromCol()] = null;
        gameboard[possibleMove.getToRow()][possibleMove.getToCol()] = possibleMove.getMoved();

        //if the pawn reaches the other side, promote it.
        if(possibleMove.getToRow() == 7 && possibleMove.getMoved().getType().equalsIgnoreCase("Pawn"))
        {
            promotePawn(possibleMove.getToCol(),gameboard);
        }
    }


}
