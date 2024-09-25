public interface ChessController {


    //resets the board
    public void reset();

    //plays a game
    public void playGame();

    //moves a piece. It is used by the human.
    public boolean movePiece(Move currentMove);

}
