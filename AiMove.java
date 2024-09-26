/**
 * AiMove
 *
 * @author Tomas Hagos
 *
 * REMARKS: This is a subclass of Move. Since the AI returns its move, we need to store a lot more information here.
 */
public class AiMove extends Move {

    private Piece captured;




    private boolean kingCaptured;
    public AiMove(Piece captured, Piece moved)
    {
        this.captured = captured;
        super.setMoved(moved);
        kingCaptured = false;
    }

    public Piece getCaptured()
    {
        return captured;
    }


    //this stores the piece the AI captured.
    public void setCaptured(Piece captured)
    {
        this.captured = captured;
    }

    //this is a flag that tells us if the AI captures the king.
    public void setKingCaptured(boolean kingCaptured)
    {

        this.kingCaptured = kingCaptured;
    }

    public boolean getKingCaptured()
    {
        return kingCaptured;
    }

}
