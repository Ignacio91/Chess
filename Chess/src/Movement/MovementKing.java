package Movement;
import java.util.ArrayList;
import Basic_Objects.Piece;
import Basic_Objects.Position;
/**
 * MovementRock , control the Movements of the King
 * @author Ignacio Ferrero
 */	
public class MovementKing extends Movement
{
	Piece [][] pieces = new Piece[8][8];
	int x, y;
	Piece piece;
	
	public MovementKing(Piece[][] _pieces, Piece _piece, int _x, int _y)
	{
		super.piece=_piece;
		super.pieces= _pieces;
		super.x = _x;
		super.y = _y;
		x=_x;
		y=_y;
		
	}
	/**
	 * Gives back every available Movement that the King has
	 * checks if the 8 available option the King has
	 */	
	public ArrayList<Position> checkKing() 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>() ;
		//missing king side castling
		if( lookMovementKing(x+1, y))
			possible_move.add(new Position(x+1,y));
		if( lookMovementKing(x+1, y+1))
			possible_move.add(new Position(x+1,y+1));
		if( lookMovementKing(x+1, y-1))
			possible_move.add(new Position(x+1,y-1));
		if( lookMovementKing(x-1, y))
			possible_move.add(new Position(x-1,y));
		if( lookMovementKing(x-1, y-1))
			possible_move.add(new Position(x-1,y-1));
		if( lookMovementKing(x-1, y+1))
			possible_move.add(new Position(x-1,y+1));
		if( lookMovementKing(x, y+1))
			possible_move.add(new Position(x,y+1));
		if( lookMovementKing(x, y-1))
			possible_move.add(new Position(x,y-1));
		//missing returning back
		return possible_move;
	}
	/**
	 * Look if there is a piece , and when there is it has to be from the different color to be
	 * valid
	 */	
	private boolean lookMovementKing(int i, int j) 
	{
			if(!checkoutofbounds(i, j) && (pieces[i][j] == null ||  checkColorempty(i,j)))
				return true;
			else 
				return false;
		}

	
}
