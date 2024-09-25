public interface ChessPlayer {

    /**
     * This method makes a move for the AI, and returns the move infomration.
     *
     * @param human the last move by the human
     */
    public AiMove makeMove(Move humanMove, Piece[][] gameboard);
}
