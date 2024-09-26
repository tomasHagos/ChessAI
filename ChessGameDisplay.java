/**
 * Game Display
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is the game display class. It is task is to display the board and all other informations.
 */
import java.util.Scanner;
public class ChessGameDisplay implements GameDisplay
{
    private Scanner kb;

    public ChessGameDisplay()
    {

        kb = new Scanner(System.in);
    }
    public void displayBoard(Piece[][] board)
    {
        System.out.println("   1 2 3 4 5 6 7 8");
        System.out.println("--------------------");

        for(int i=0; i < board.length; i++)
        {
            System.out.print((i+1));
            System.out.print(" |");

            for(int j=0; j < board[i].length; j++)
            {
                //if its not empty space
                if(board[i][j] != null)
                {
                    System.out.print(board[i][j].getPiece());
                }
                else
                {
                    System.out.print(" ");
                }

                System.out.print("|");
            }

            System.out.println();
            System.out.println("------------------");
        }
    }


    public Move promptForMove()
    {
        //we subtract one from each entry, since array index starts at 0.
        Move currentMove = new Move();

        System.out.println("Please enter the row of the piece you would like to move. Enter 0 to forfeit the game:");
        currentMove.setFromRow(validateInput(kb.nextLine(),kb,0)-1);

        //if the game is not forfeited

        if(currentMove.getFromRow() != -1)
        {
            System.out.println("Please enter the column of the piece you would like to move:");
            currentMove.setFromCol(validateInput(kb.nextLine(),kb,1)-1);

            System.out.println("Please enter the row of the destination.");
            currentMove.setToRow(validateInput(kb.nextLine(),kb,1)-1);

            System.out.println("Please enter the column of the destination");
            currentMove.setToCol(validateInput(kb.nextLine(),kb,1)-1);
        }

        return currentMove;
    }

    public void summarizeMove(Piece movedPiece, Move currentMove, Piece captured)
    {
        System.out.print(movedPiece.getType()+" moved from ");
        System.out.printf("( %d, %d) ",currentMove.getFromRow()+1,currentMove.getFromCol()+1);
        System.out.printf("to (%d,%d). ",currentMove.getToRow()+1,currentMove.getToCol()+1);

        if(captured != null)
        {
            System.out.println(captured.getType() + " captured.");
        }
        else
        {
            System.out.println("No capture was made.");
        }
    }

    public boolean promptPlayAgain()
    {
        boolean returned = false;

        System.out.println("Do you want to play again? Please Enter Yes or No.");
        String response = kb.nextLine();

        while(!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no")))
        {
            System.out.println("Invalid input! Please Enter Yes or No:");
             response = kb.nextLine();
        }

        if(response.equalsIgnoreCase("yes"))
        {
            returned = true;
        }

        return returned;

    }

    public int promptForOpponentDifficulty(int maxDifficulty)
    {
        int num = -1;
        while(num < 0 || num > maxDifficulty)
        {
            System.out.printf("Please enter the desired opponent difficulty, between 0 and %d, where 0 is the easiest opponent" +
                    " and 1 is the hardest opponent\n",maxDifficulty);
            num = kb.nextInt();
        }

        kb.nextLine();

        return num;
    }
    public void gameOver(int winner)
    {

        if(winner == 0)
        {
            System.out.println("Game forfeited!");
        }
        else if(winner == 1)
        {
            System.out.println("Human Won!");
        }
        else
        {
            System.out.println("Computer Won!");
        }

        System.out.println("Game Over!");
    }

    /**
     * This method makes sure the input is valid.
     */

    private int validateInput(String input,Scanner kb, int startPosition)
    {
        int number = 0;
        boolean valid = false;


        while(!valid)
        {
            //uses try catch block to detect number format exception.
            try
            {
                number = Integer.parseInt(input);

                if(number >= startPosition && number <= 8)
                {
                    valid = true;
                }
                else
                {
                    System.out.printf("Invalid Input! Please Enter a number between %d " +
                            "and %d \n",startPosition,8);
                    input = kb.nextLine();
                }
            }
            catch(NumberFormatException ie)
            {
                System.out.println("Invalid Input! Please Enter a number:");
                input = kb.nextLine();
            }
        }

        return number;
    }




}
