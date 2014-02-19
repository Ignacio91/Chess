import Basic_Objects.Player;




public class Game 
{
	static GameLogic gm;
	 
	public static void main(String[] args) 
	  {
		start();
	 }

	private static void start() 
	{
		boolean is_finished = false;
		boolean give_up = false;
		boolean movement_complete = false;
		boolean both_playe_draw = false;
		Player p1 = new Player(true, "Mike");// two player in the future with client and server
		Player p2 = new Player(false, "Codes");
		Player p;// player actually playing the game
		p = p1;
		createGame(p1);
		int counter = 1;
		while(!gm.getupdateApp())
		{
			//someone win reinitialize the game
			if(is_finished || give_up || both_playe_draw)
			{
				createGame(p1);
				p = p1;
				is_finished = false;
				give_up = false;
				both_playe_draw = false;
				counter = 1;
			}
			
			while(!movement_complete && !give_up && !both_playe_draw)
			{
				movement_complete = gm.movementComplete();
				give_up = gm.getGiveUp();
				both_playe_draw = gm.getDraw();
				//wait that the player has finished his move so other player begins his move
				try {
					Thread.sleep(200);// sleeps the thread so no constant checking if the player has finished
					//the move
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			movement_complete = false;
			is_finished = gm.checkMate() ;
			if(is_finished || give_up)
			{
				if(p1.equals(p))
				{
					p1.addWins();
					p = p1;
				}
				else
				{
					p2.addWins();
					p = p2;
				}
					
			}
			
			if(counter%2 == 0){
				gm.updatePlayer(p1);
				p =p1;
			}
			else
			{
				gm.updatePlayer(p2);
				p = p2;
			}
			counter ++;
		}
			
	}
	

	//create the logic and board
	private static void createGame(Player p) 
	{
		gm = new GameLogic(p);
	}
}
