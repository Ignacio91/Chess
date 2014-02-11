
public class Player 
{
	 boolean is_check;
	 boolean logic;
	 Player(boolean _logic)
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
