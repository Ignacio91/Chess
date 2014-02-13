package Movement;
import java.util.ArrayList;
import Basic_Objects.Piece;
import Basic_Objects.Position;

/**
 * MovementPawn , control the Movements of the Pawn
 * 
 * @author Ignacio Ferrero
 * 
 */

public class MovementPawn extends Movement
{
	Piece [][] pieces = new Piece[8][8];
	int x, y;
	Piece piece;
	
	public MovementPawn(Piece[][] _pieces, Piece _piece, int _x, int _y)
	{
		super.piece=_piece;
		super.pieces= _pieces;
		super.x = _x;
		super.y = _y;
		piece = _piece;
		x=_x;
		y=_y;
		pieces =_pieces;
	}
	
	/**
	 * Gives back every available Movement that the Pawn has
	 * in an Array of Positions
	 */	
	public ArrayList<Position> checkPawn() 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>() ;

		
		if(piece.getColor())//Checks if the Pawn is White
		{
			if(lookMovementPawn1(x-1,y))
			{
				possible_move.add(new Position(x-1,y));
					if(lookMovementPawn1(x-2,y))
						possible_move.add(new Position(x-2,y));
					
			}
				if(lookMovementPawn2(x-1,y-1))
					possible_move.add(new Position(x-1,y-1));
				if(lookMovementPawn2(x-1,y+1))
					possible_move.add(new Position(x-1,y+1));
		}
		else//Pawn is Black
		{
			if(lookMovementPawn1(x+1,y))
			{
				possible_move.add(new Position(x+1,y));
					if(lookMovementPawn1(x+2,y))//only if position 1 is free checks position 2
						possible_move.add(new Position(x+2,y));
					
			}
				if(lookMovementPawn2(x+1,y-1))
					possible_move.add(new Position(x+1,y-1));
				if(lookMovementPawn2(x+1,y+1))
					possible_move.add(new Position(x+1,y+1));
		}
		return possible_move;
	}
	/**
	 * Looks if the position is free
	 */	
	private boolean lookMovementPawn1(int i, int j) {
		if(!checkoutofbounds(i, j) && pieces[i][j] == null)
			return true;
		else 
			return false;
	}
	/**
	 * Looks if the position is  not free and has a piece of a different color
	 */	
	private boolean lookMovementPawn2(int i, int j) 
	{	
		if(!checkoutofbounds(i, j) && pieces[i][j] != null)
		{
			if(pieces[i][j].getColor() != piece.getColor())
				return true;
			else 
				return false;
		}
		else return false;
	}
}
