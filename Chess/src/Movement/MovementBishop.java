package Movement;
import java.util.ArrayList;
import Basic_Objects.Piece;
import Basic_Objects.Position;

/**
 * MovementRock , control the Movements of the Bishop
 * @author Ignacio Ferrero
 */	
public class MovementBishop extends Movement
{
	Piece [][] pieces = new Piece[8][8];
	int x, y;
	Piece piece;
	
	public MovementBishop(Piece[][] _pieces, Piece _piece, int _x, int _y)
	{
		super.piece=_piece;
		super.pieces= _pieces;
		super.x = _x;
		super.y = _y;
		x=_x;
		y=_y;
		
	}
	/**
	 * Gives back every available Movement that the Bishop has
	 * with a for that expands like an X
	 */
	public ArrayList<Position> checkBishop() 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>() ;
		
		//four booleans vector with two variables for 4 possible paths up down left right
		//boolean[0] = position is empty
		//boolean[1] = we have found a piece in the position so no need to look further in the iteration
		
		boolean check_pos1[] = new boolean[2]; check_pos1[0] = false; check_pos1[1] = false;
		boolean check_neg1[] = new boolean[2]; check_neg1[0] = false; check_neg1[1] = false;
		boolean check_pos2[] = new boolean[2]; check_pos2[0] = false; check_pos2[1] = false;
		boolean check_neg2[] = new boolean[2]; check_neg2[0] = false; check_neg2[1] = false;
		
		for(int i =1;i+x<8 || x-i>-1 || i+y<8 || y-i>-1; i++)
		{
			
			if(!check_pos1[1] && !checkoutofbounds(x+i, y+i))
			{
				check_pos1 = checkMovement(x+i, y+i);
				if(check_pos1[0])
					possible_move.add(new Position(x+i,y+i));
			}
			
			
			if(!check_neg1[1] &&!checkoutofbounds(x-i, y-i))
			{
				check_neg1 = checkMovement(x-i, y-i);
				if(check_neg1[0])
					possible_move.add(new Position(x-i,y-i));
			}
			
			if(!check_pos2[1]&&!checkoutofbounds(x+i, y-i))
			{
				check_pos2 = checkMovement(x+i, y-i);
				if(check_pos2[0])
					possible_move.add(new Position(x+i,y-i));
			}
			
			if(!check_neg2[1]&&!checkoutofbounds(x-i, y+i))
			{
				check_neg2 = checkMovement(x-i, y+i);
				if(check_neg2[0])
					possible_move.add(new Position(x-i,y+i));
			}
			
			
			
		}
		for(int i=0; i<possible_move.size();i++)
		{
			System.out.println("X: " +possible_move.get(i).getX()+ "Y  " + possible_move.get(i).getY() + "  "+x +"  "+ y);
		}
			return possible_move;

	}
	protected boolean[] checkMovement(int i, int j) 
	{

		boolean check_Color_empty;
		boolean []valid = new boolean[2] ;
		System.out.println("Hello "+ i + " "+ j);
		valid[0] = false; valid[1] = false;
		check_Color_empty = checkColorempty(i,j);
		if(pieces[i][j]== null || check_Color_empty)
		{	
			valid[0] = true;
			if(check_Color_empty)
				valid[1] = false;
		}
		else
		{
			valid[1] = false;
		}
		check_Color_empty = false;

		return valid;
	}
}
