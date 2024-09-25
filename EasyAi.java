
/**
 * Easy AI
 *
 * @author Tomas Hagos, 7969147
 *
 * REMARKS: It is an easy AI. It just makes random Move.
 */
public class EasyAi extends AI implements ChessPlayer
{


    public EasyAi(int player)
    {

        super(player);
    }



    public AiMove makeMove(Move humanMove, Piece[][] gameboard)
    {
        //since this is an easy AI. Just make a random Move.
        return super.makeRandomMove(humanMove,gameboard);
    }


}







