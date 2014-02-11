
package Movement;
import Basic_Objects.Piece;
import Basic_Objects.Position;

public  abstract class Movement 
{
	 Piece[][] pieces;
	 Piece piece;
	 int x, y;
	
	
	protected boolean checkoutofbounds(int i, int j) 
	{
		if(i>7 || j>7)
			return true;
		else
		{
			if(i<0 || j<0)
				return true;
			else 
				return false;
		}
	}
	protected boolean checkColorempty(int i, int j) 
	{
		if(pieces[i][j]!= null)
		{
			if(pieces[i][j].getColor() != piece.getColor())
				return true;
			else 
				return false;
		}
		return false;
	}
}
