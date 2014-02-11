package Basic_Objects;

public class Position 
{
	int x, y;
	public Position(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	public void updatePosition(int _x, int _y){
		x = _x;
		y = _y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
