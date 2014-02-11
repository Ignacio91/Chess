package Movement;
import java.util.ArrayList;
import Basic_Objects.Piece;
import Basic_Objects.Position;
/**
 * MovementRock , control the Movements of the Knight
 * @author Ignacio Ferrero
 */	
public class MovementKnight extends Movement
{
	Piece [][] pieces = new Piece[8][8];
	int x, y;
	Piece piece;
	
	public MovementKnight(Piece[][] _pieces, Piece _piece, int _x, int _y)
	{
		super.piece=_piece;
		super.pieces= _pieces;
		super.x = _x;
		super.y = _y;
		x=_x;
		y=_y;
		
	}
	/**
	 * Gives back every available Movement that the Knight has
	 * checks if the 8 available option the Knight has
	 */	
	public ArrayList<Position> checkKnight() 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>() ;
		if(!checkoutofbounds(x+1, y+2))
		{
			if(pieces[x+1][y+2]== null || checkColorempty(x+1,y+2))	
				possible_move.add(new Position(x+1,y+2));
		}
		if(!checkoutofbounds(x-1, y+2))
		{
			if(pieces[x-1][y+2]== null || checkColorempty(x-1,y+2))	
				possible_move.add(new Position(x-1,y+2));
		}
		if(!checkoutofbounds(x+1, y-2))
		{
			if(pieces[x+1][y-2]== null || checkColorempty(x+1,y-2))
				possible_move.add(new Position(x+1,y-2));
		}
		if(!checkoutofbounds(x-1, y-2))
		{
			if(pieces[x-1][y-2]== null || checkColorempty(x-1,y-2))
				possible_move.add(new Position(x-1,y-2));
		}
		
		if(!checkoutofbounds(x+2, y+1))
		{
			if(pieces[x+2][y+1]== null || checkColorempty(x+2,y+1))	
				possible_move.add(new Position(x+2,y+1));
		}
		if(!checkoutofbounds(x-2, y+1))
		{
			if(pieces[x-2][y+1]== null || checkColorempty(x-2,y+1))	
				possible_move.add(new Position(x-2,y+1));
		}
		if(!checkoutofbounds(x+2, y-1))
		{
			System.out.println("Entra" + pieces[x+2][y-1]);
			
			if(pieces[x+2][y-1]== null || checkColorempty(x+2,y-1)){System.out.println("Entra");
				possible_move.add(new Position(x+2,y-1));}
		}
		if(!checkoutofbounds(x-2, y-1))
		{
			
			if(pieces[x-2][y-1] == null || checkColorempty(x-2,y-1))
				possible_move.add(new Position(x-2,y-1));
		}
		for(int i=0; i<possible_move.size();i++)
		{
			System.out.println("X: " +possible_move.get(i).getX()+ "Y  " + possible_move.get(i).getY());
		}
		return possible_move;
		
	}
}
