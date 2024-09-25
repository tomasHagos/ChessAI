

public interface GameDisplay
{
    public void displayBoard(Piece board[][]);

    public Move promptForMove();

    public void summarizeMove(Piece movedPiece, Move currentMove, Piece captured);

    public void gameOver(int winner);

    public boolean promptPlayAgain();

    public int promptForOpponentDifficulty(int maxDifficulty);

}
