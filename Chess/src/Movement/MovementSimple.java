package Movement;

import java.util.ArrayList;

import Basic_Objects.Piece;
import Basic_Objects.Position;
/**
 * New class of Piece he can jump up and down 2 rows only when empty but only 1 
 * if he can eat an opponen piece
 * 
 * @author Ignacio Ferrero
 * 
 */
public class MovementSimple extends Movement
{
	Piece [][] pieces = new Piece[8][8];
	int x, y;
	Piece piece;
	
	public MovementSimple(Piece[][] _pieces, Piece _piece, int _x, int _y)
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
	

	public ArrayList<Position> checkSimple() 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>() ;

		
		if(!checkoutofbounds(x+2, y) && pieces[x+2][y] == null)// 2 up
		{//only if empty
			possible_move.add(new Position(x+2,y));
		}
		if(!checkoutofbounds(x+1, y) && pieces[x+1][y] != null ) // 1 up
		{//only if there is an opponent piece
			if(piece.getColor()!= pieces[x+1][y].getColor())
				possible_move.add(new Position(x+1,y));
		}
		if(!checkoutofbounds(x-1, y) && pieces[x-1][y] != null )
		{
			if(piece.getColor()!= pieces[x-1][y].getColor())
				possible_move.add(new Position(x-1,y));
		}
		if(!checkoutofbounds(x-2, y) && pieces[x-2][y] == null)
		{
			possible_move.add(new Position(x-2,y));
		}
		
		return possible_move;
	}
	
}
