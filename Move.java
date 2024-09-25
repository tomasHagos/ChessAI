
/**
 * Move
 *
 * @author Tomas Hagos, 7969147
 *
 * REMARKS: This stores general information about an attempted Move. This is used by a human.
 * A subclass of this class is used for the AI.
 */
public class Move {

    private int fromRow;
    private int fromCol;

    private int toCol;
    private int toRow;

    private Piece pieceMoved = null;

    public Move()
    {
         fromRow = -1;
         fromCol = -1;
         toCol = -1;
         toRow = -1;
    }

    public int getFromCol()
    {
        return fromCol;
    }

    public int getToCol()
    {
        return toCol;
    }

    public int getToRow()
    {
        return toRow;
    }

    public int getFromRow()
    {
        return fromRow;
    }

    public void setFromCol(int fromCol)
    {
        this.fromCol = fromCol;
    }

    public void setFromRow(int fromRow)
    {
        this.fromRow = fromRow;
    }

    public void setToCol(int toCol)
    {
        this.toCol = toCol;
    }

    public void setToRow(int toRow)
    {
        this.toRow = toRow;
    }

    public void setMoved(Piece piece)
    {
        pieceMoved = piece;
    }

    public  Piece getMoved()
    {
        return pieceMoved;
    }


}

