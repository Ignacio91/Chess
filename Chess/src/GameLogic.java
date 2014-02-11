import javax.swing.JFrame;

import Basic_Objects.Piece;
import Basic_Objects.Position;


public class GameLogic 
{
	Position position_init;
	Position position_end;
	  
	MovementCheck movement;
	
	 boolean not_turn ;
	
	boolean debug = false;
	  
	Piece[][] pieces= new Piece[8][8];
	
	Piece piece;
	
	boolean is_finished ;
	boolean movement_complete ;
	
	 boolean app_finished ;
	
	ChessGameDemo frame;
	
	Player p;
	
	GameLogic(Player _p)
	{
		p = _p;
		app_finished = false;
		is_finished = false;
		movement_complete = false;
		initializeBoard();
		initializeLogic(true);//initializes the matrix white pieces only one function
		initializeLogic(false);//initializes the matrix balck pieces only one function
		movement = new MovementCheck(pieces);
	}
	public void updatePlayer(Player _p)
	{
		p = _p;
	}
	private void initializeBoard() 
	{
		 frame = new ChessGameDemo(this);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.pack();
		 frame.setResizable(true);
		 frame.setLocationRelativeTo( null );
		 frame.setVisible(true);
	}

	public void updateApp() 
	{
		app_finished = true;
		
	}
	public boolean getupdateApp() 
	{
		return app_finished;
		
	}
	public boolean update( Position _init, Position _end)
	{
	
		position_init = _init; position_end= _end;
		piece = pieces[position_init.getX()][position_init.getY()];
		if(piece.getColor() != p.getLogic())
			not_turn = true;
		else
			not_turn = false;
		
		if(!not_turn)
		{
			System.out.println( "Hkjasd" + movement.checkMove(piece, position_init, position_end));
			if(movement.checkMove(piece, position_init, position_end))
			{
				
				pieces[position_init.getX()][position_init.getY()] = null;
				pieces[position_end.getX()][position_end.getY()] = piece;
				movement.update(pieces);
				
				/*if(movement.checkmate(piece))
				{
					p.setCheck();
					frame.checkDialog();  
				}
				else
				{
					p.setCheckFalse();
				}
				
				*/
				movement_complete = true;
				return true;
			}
			if(debug)
			  {
				  lookboard();
			  }
		}
		
		if(not_turn)
		{
			frame.checkErrorTurn();
			return false;
		}
		
		return false;
	}
	 /**
	* Initializes logic white and black depends on the variable logic 
	 * logic = false --> black
	*/
	private void initializeLogic(boolean logic) 
	  {
		for(int i=0; i<5;i++)
		{
			if(logic)//white
			{
				pieces[7][i]= new Piece(i, logic) ;
				if(i<3)
				{
					pieces[7][7-i]= new Piece(i, logic) ;
					pieces[6][7-i]= new Piece(5, logic) ;
				}
				pieces[6][i]= new Piece(5, logic) ;
			
			}
			else//black
			{
				pieces[0][i]= new Piece(i, logic) ;
				if(i<3)
				{
					pieces[0][7-i]= new Piece(i, logic) ;
					pieces[1][7-i]= new Piece(5, logic) ;
				}
			pieces[1][i]= new Piece(5, logic) ;
			}
		}
		if(debug)
			lookboard();
		
	}
	
	private void lookboard() 
	{
		for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if( pieces[i][j] != null)
						System.out.println("i: " + i + "j: " + j + "  " + pieces[i][j].getType());
				}
		  }
	}
	public boolean GameFinished()
	{
		return is_finished;
	}

	public boolean movementComplete() 
	{
		if(movement_complete)
		{	
			movement_complete = false;
			return true;
		}
		else return false;
	}
	public boolean checkMate() 
	{
		if(p.getCheck())
		{
			if(movement.check(position_end, piece.getColor()))
			{	
				frame.checkMateDialog();
				return true;
			}
			else return false;
		}
		else return false;
		
	}
}
