/**
 * Main
 *
 * COMP 2150 SECTION A01
 * INSTRUCTOR    Dr. Heather Matheson
 * ASSIGNMENT    Assignment 3, question 1
 * @author       Tomas Hagos, 7969147
 * @version      Saturday March 23, 2024
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