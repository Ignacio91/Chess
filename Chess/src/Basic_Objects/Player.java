package Basic_Objects;
/**
 * Class player 
 */
public class Player 
{
	 boolean is_check;// variable to show if the player is in check
	 boolean logic;//  what color is the player
	 public Player(boolean _logic)
	 {
		 logic = _logic;
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
