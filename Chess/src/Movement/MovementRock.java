package Movement;
import java.util.ArrayList;
import Basic_Objects.Piece;
import Basic_Objects.Position;
/**
 * MovementRock , control the Movements of the Rock
 * @author Ignacio Ferrero
 */	
public class MovementRock extends Movement
{
	Piece [][] pieces = new Piece[8][8];
	int x, y;
	Piece piece;
	
	public MovementRock(Piece[][] _pieces, Piece _piece, int _x, int _y)
	{
		super.piece=_piece;
		super.pieces= _pieces;
		super.x = _x;
		super.y = _y;
		x=_x;
		y=_y;
		
	}
	/**
	 * Gives back every available Movement that the Rock has
	 * in an Array of Positions
	 */	
	public ArrayList<Position> checkRook() 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>() ;
		
		//four booleans vector with two variables for 4 possible paths up down left right
		//boolean[0] = position is empty
		//boolean[1] = we have found a piece in the position so no need to look further in the iteration
		boolean check_pos1[] = new boolean[2]; check_pos1[0] = false; check_pos1[1] = false;
		boolean check_neg1[] = new boolean[2]; check_neg1[0] = false; check_neg1[1] = false;
		boolean check_pos2[] = new boolean[2]; check_pos2[0] = false; check_pos2[1] = false;
		boolean check_neg2[] = new boolean[2]; check_neg2[0] = false; check_neg2[1] = false;
		
		for(int i =1;i+x<8 || x-i>-1 || i+y<8 || y-i>-1; i++)// all possible paths
		{
			if(!check_pos1[1] && !checkoutofbounds(x, y+i))//rigth
			{
				check_pos1 = checkMovement(x, y+i);
				if(check_pos1[0])
					possible_move.add(new Position(x,y+i));
			}
			
			
			if(!check_neg1[1] &&!checkoutofbounds(x-i, y))//down
			{
				check_neg1 = checkMovement(x-i, y);
				if(check_neg1[0])
					possible_move.add(new Position(x-i,y));
			}
			
			if(!check_pos2[1]&&!checkoutofbounds(x+i, y))//up
			{
				check_pos2 = checkMovement(x+i, y);
				if(check_pos2[0])
					possible_move.add(new Position(x+i,y));
			}
			
			if(!check_neg2[1]&&!checkoutofbounds(x, y-i))//left
			{
				check_neg2 = checkMovement(x, y-i);
				if(check_neg2[0])
					possible_move.add(new Position(x,y-i));
			}
			
			
			
		}
		return possible_move;

	}
	/**
	 * Returns the boolean vector mentioned above
	 * checks if  it is empty then add the position
	 * if not look if its from another color, set boolean[1] to false piece found
	 */	
	protected boolean[] checkMovement(int i, int j) 
	{

		boolean check_Color_empty;
		boolean []valid = new boolean[2] ;

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
