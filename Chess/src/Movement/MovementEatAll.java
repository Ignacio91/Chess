package Movement;

import java.util.ArrayList;

import Basic_Objects.Piece;
import Basic_Objects.Position;
/*
 * Can move to every position even eat own peaces
 * 
 */
public class MovementEatAll  extends Movement
{
	public MovementEatAll(Piece[][] _pieces, Piece _piece, int _x, int _y)
	{
		super.piece=_piece;
		super.pieces= _pieces;
		super.x = _x;
		super.y = _y;
		piece = _piece;
		pieces = _pieces;
		x=_x;
		y=_y;
	}
	public ArrayList<Position> checkEatAll() 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>() ;

		for(int i =0;i<8; i++)
		{
			for(int j=0;j<8;j++)
			{
				possible_move.add(new Position(i,j));
			}
		}
		return possible_move;
	}
}
