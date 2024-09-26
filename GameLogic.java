/**
 * Game Logic
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is the game logic class. It is the process all the information
 */
import java.util.Scanner;
public class GameLogic implements ChessController{

    private GameDisplay gameDisplay;
    private Piece[][] gameBoard;

    private Scanner input;

    private ChessPlayer computer;

    //flag to tell us if a human Wins.
    private boolean humanWins;

    public GameLogic(ChessGameDisplay display)
    {
        this.gameDisplay = display;

        gameBoard = new Piece[8][8];

        initializeGameBoard(gameBoard);

        input = new Scanner(System.in);

        humanWins = false;


    }
    public void playGame()
    {
        boolean gameOn = true;

        //while game is on.
        while(gameOn)
        {
            System.out.println("Starting Game...");
            game();

            gameOn = gameDisplay.promptPlayAgain();

            if(gameOn)
            {
                reset();
            }
        }

        //close input.
        input.close();
    }

    /**
     * This method runs a game.
     */
    public void game()
    {
        //set the AI type used for the current game.
        setAI();

        Move currentMove = null;
        AiMove currentAiMove = null;

        boolean forfeit = false;
        boolean humanMove = true;

        boolean aiWins = false;


        while(!humanWins && !aiWins && !forfeit)
        {
            gameDisplay.displayBoard(gameBoard);

            //alternate turns here.
            if(humanMove)
            {
                currentMove = gameDisplay.promptForMove();

                forfeit = humanMove(currentMove);

                humanMove = false;
            }
            else
            {
                System.out.println("Computer's turn");
                currentAiMove = computer.makeMove(currentMove,gameBoard);

                aiWins = currentAiMove.getKingCaptured();

                gameDisplay.summarizeMove(currentAiMove.getMoved(),currentAiMove,currentAiMove.getCaptured());
                humanMove = true;
            }

        }

        int winner;

        if(humanWins)
        {
            winner = 1;
        }
        else if(aiWins)
        {
            winner = 2;
        }
        else
        {
            winner = 0;
        }

        gameDisplay.gameOver(winner);
    }

    /**
     * This method prompts and set's the AI difficulty.
     */
    private void setAI()
    {
        int difficulty = gameDisplay.promptForOpponentDifficulty(1);
        if(difficulty == 0)
        {
            computer = new EasyAi(2);
        }
        else
        {
            computer = new HardAi(2);
        }
    }

    /**
     * This method checks if the current attempted move is valid. If not loop unit we get a valid move from the human.
     *
     * @param currentMove the attempted move
     * @return   true if the move is valid.
     */
    private boolean humanMove(Move currentMove)
    {
        boolean forfeit = false;
        //we need currentMove data's update eachTime, for the AI
        //we use a temporary variable, and copy each time.
        Move tempMove;

        //while the move is invalid
        while(currentMove.getFromRow() != -1 && !movePiece(currentMove))
        {
            System.out.println("Invalid Move. Please try again.");
            tempMove = gameDisplay.promptForMove();

            currentMove.setFromRow(tempMove.getFromRow());
            currentMove.setFromCol(tempMove.getFromCol());
            currentMove.setToRow(tempMove.getToRow());
            currentMove.setToCol(tempMove.getToCol());
            currentMove.setMoved(tempMove.getMoved());
        }

        //if the user enters 0. Game is forfeited.
        if(currentMove.getFromRow() == -1)
        {
            forfeit = true;
        }

        return forfeit;

    }

    public void reset()
    {
        humanWins = false;
        initializeGameBoard(gameBoard);
    }


    /**
     * This method determines if a move is valid and moves the piece.
     *
     * @param currentMove the attempted move
     */
    public boolean movePiece(Move currentMove)
    {
        Boolean validMove = false;

        Piece currentPiece = gameBoard[currentMove.getFromRow()][currentMove.getFromCol()];

        //if the player chose a piece, and it's the player's piece
        if(currentPiece != null && currentPiece.getPlayer() == 1)
        {

            //if it's a valid move, for the specific type of piece
            if(currentPiece.validMove(currentMove,gameBoard))
            {
                //if a capture is on, the variable capture is not null.
                Piece captured = gameBoard[currentMove.getToRow()][currentMove.getToCol()];

                //if the king was captured. Human Wins.
                if(captured != null && captured.getType().equalsIgnoreCase("King"))
                {
                    humanWins = true;
                }

                currentMove.setMoved(currentPiece);

                //make the move.
                gameBoard[currentMove.getFromRow()][currentMove.getFromCol()] = null;
                gameBoard[currentMove.getToRow()][currentMove.getToCol()] = currentPiece;

                //promotion is on.
                if(currentMove.getToRow() == 0 && currentPiece.getType().equalsIgnoreCase("Pawn"))
                {
                    promptForPromotion(currentMove.getToCol());
                }

                //call the summary from the game display
                gameDisplay.summarizeMove(currentPiece,currentMove,captured);
                validMove = true;
            }
        }

        return validMove;
    }

    private void promptForPromotion(int column)
    {
        System.out.println("Your pawn is ready to promote. Enter 0 for Queen, 1 for Bishop, 2 for Rook, 3 for Knight");
        int promotionPiece = input.nextInt();

        while(promotionPiece < 0 || promotionPiece > 3)
        {
            System.out.println("Invalid input. Enter 0 for Queen, 1 for Bishop, 2 for Rook, 3 for Knight");
            promotionPiece = input.nextInt();
        }

        Piece piece = null;
        if(promotionPiece == 0)
        {
            piece = new Queen(1);
        }
        else if(promotionPiece == 1)
        {
            piece = new Bishop(1);
        }
        else if(promotionPiece == 2)
        {
            piece = new Rook(1);
        }
        else
        {
             piece = new Knight(1);
        }

        gameBoard[0][column] = piece;

    }
    private void initializeGameBoard(Piece gameBoard[][])
    {
        Piece currentPiece = null;

        for(int i=0; i < gameBoard.length; i++)
        {

            for(int j=0; j < gameBoard[i].length; j++)
            {
                if(i == 0)
                {
                    if(j == 1 || j==6)
                    {
                        currentPiece = new Knight(2);
                    }
                    else if(j == 0 || j==7)
                    {
                        currentPiece = new Rook(2);
                    }
                    else if(j==2 || j==5)
                    {
                        currentPiece = new Bishop(2);
                    }
                    else if(j==3)
                    {
                        currentPiece = new King(2);
                    }
                    else
                    {
                        currentPiece = new Queen(2);
                    }
                }
                else if(i == 1)
                {
                    currentPiece = new Pawn(2);
                }
                else if(i == 6)
                {
                    currentPiece = new Pawn(1);
                }
                else if(i==7)
                {
                     if(j== 1 || j==6)
                     {
                         currentPiece = new Knight(1);
                     }
                     else if(j==0|| j==7)
                     {
                         currentPiece = new Rook(1);
                     }
                     else if(j==2 || j==5)
                     {
                           currentPiece = new Bishop(1);
                     }
                     else if(j==3)
                     {
                          currentPiece = new King(1);
                     }
                     else
                     {
                         currentPiece = new Queen(1);
                     }
                }

                gameBoard[i][j] = currentPiece;
                currentPiece = null;
            }
        }
    }




}
