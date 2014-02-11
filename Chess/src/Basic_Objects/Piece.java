package Basic_Objects;

public class Piece 
{
	Position position;//0-63
	boolean color;//true = white false = black
	int type;//0 = rook || 1 = knight || 2 = bishop || 3 = queen || 4 = King || 5 = Pawn 
	
	 public Piece(int _type, boolean _color)
	{
		type = _type;
		color =_color;
	}
	
	public void setType(int _type)
	{
		type = _type;
	}
	public void setColor(boolean _color)
	{
		color = _color;
	}
	public void setPosition(Position _position)
	{
		position =_position;
	}
	public boolean getColor()
	{
		return color;
	}
	public int getType()
	{
		return type;
	}
}
