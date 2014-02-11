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
		boolean movement_complete = false;
		Player p1 = new Player(true);// two player in the future with client and server
		Player p2 = new Player(false);
		createGame(p1);
		int counter = 1;
		while(!gm.getupdateApp())
		{
			//someone win reinitialize the game
			if(is_finished)
			{
				createGame(p1);
				is_finished = false;
				counter = 1;
			}
			
			while(!movement_complete)
			{
				movement_complete = gm.movementComplete();
				//wait that the player has finished his move so other player begins his move
				try {
					Thread.sleep(500);// sleeps the thread so no constant checking if the player has finished
					//the move
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			movement_complete = false;
			is_finished = gm.checkMate() ;
			
			if(counter%2 == 0)// to know whoes turn it is keep the rest == 0 
				gm.updatePlayer(p1);
			else
				gm.updatePlayer(p2);
			counter ++;
		}
			
	}
	//create the logic and board
	private static void createGame(Player p) 
	{
		gm = new GameLogic(p);
	}
}
