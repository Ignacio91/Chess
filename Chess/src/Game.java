


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
		Player p1 = new Player(true);
		Player p2 = new Player(false);
		createGame(p1);
		int counter = 1;
		System.out.println( "hola"+gm.getupdateApp());
		while(!gm.getupdateApp())
		{
			System.out.println( "hola"+gm.getupdateApp());
			if(is_finished)
			{
				createGame(p1);
				is_finished = false;
				counter = 1;
			}
			while(!movement_complete)
			{
				movement_complete = gm.movementComplete();
				//System.out.println( "proque" + movement_complete);
			}
			movement_complete = false;
			is_finished = gm.checkMate() ;
			
			if(counter%2 == 0)
				gm.updatePlayer(p1);
			else
				gm.updatePlayer(p2);
			counter ++;
		}
		System.out.println( "hola"+gm.getupdateApp());
			
	}

	private static void createGame(Player p) 
	{
		gm = new GameLogic(p);
	}
}
