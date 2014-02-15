import Basic_Objects.Piece;
import Basic_Objects.Position;

/**
 * UNDO class
 * Logic : You can only do an undo when your movement didn't eat any opponent piece
 * Drawback : doing the undo will have consequences you loose you movement !!
 */
public class Undo 
{
	Position[] undo= new Position[2];
	boolean turn = true;
	Piece p;
	
	Undo(Position init, Position end, Piece _p)
	{
		undo[0] = init;
		undo[1] = end;
		p =_p;
	}
	public Position getInit(){
		return undo[0];
	}
	public Position getFinal(){
		return undo[1];
	}
	public Boolean getTurn()
	{
		return turn;
	}
	public void SetTurn(Boolean _turn)
	{
		turn = _turn;
	}
	public Piece getPiece() 
	{
		
		return p;
	}
}
