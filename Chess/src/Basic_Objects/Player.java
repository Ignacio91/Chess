package Basic_Objects;
/**
 * Class player 
 */
public class Player 
{
	 boolean is_check;// variable to show if the player is in check
	 boolean logic;//  what color is the player
	 int wins;
	 String name;
	 public Player(boolean _logic, String _name)
	 {
		 logic = _logic;
		 name = _name;
		 wins = 0;
	 }
	 public int getWins()
	 {
		 return wins;
	 }
	 public String getName()
	 {
		 return name;
	 }
	 public void addWins()
	 {
		 wins++;
	 }
	 public boolean getLogic(){
		 return logic;
	 }
	 public boolean getCheck(){
		 return is_check;
	 }
	 public void setCheck(){
		  is_check = true;
	 }
	public void setCheckFalse() 
	{
		is_check = false;
		
	}
}
