/**
 * Main
 *
 *
 * REMARKS: To implement a chess game
 */

public class Main
{
    public static void main(String[] args)
    {
        ChessGameDisplay display = new ChessGameDisplay();
        GameLogic game = new GameLogic(display);
        game.playGame();

        System.out.println("End of processing!");
    }
}
