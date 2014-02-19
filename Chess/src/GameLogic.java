
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Basic_Objects.Piece;
import Basic_Objects.Player;
import Basic_Objects.Position;

/**
 *  Class that sets the game Logic
 * @author Ignacio Ferrero
 */	
public class GameLogic 
{
	Position position_init;
	Position position_end;
	  
	MovementCheck movement;//movement controller
	
	boolean not_turn ;// which turn it is
	
	boolean debug = false;
	  
	Piece[][] pieces= new Piece[8][8];//matrix with all pieces
	
	Piece piece;
	
	Undo undo_movement;// controlling the undo
	
	boolean is_finished ;
	boolean movement_complete ;
	
	boolean app_finished;
	boolean undo_done;
	boolean give_up,both_playe_draw;//Player gives up
	
	
	//GUI member
	Board frame;
	JMenuBar menu_Bar;
	JMenu menu;
	JButton undo, restart, score, draw;
	
	Player p;
	
	GameLogic(Player _p)
	{
		p = _p;
		app_finished = false;
		is_finished = false;
		movement_complete = false;
		give_up = false;
		both_playe_draw = false;
		initializeBoard();
		initializeLogic(true);//initializes the matrix white pieces only one function
		initializeLogic(false);//initializes the matrix black pieces only one function
		movement = new MovementCheck(pieces);
	}
	public void updatePlayer(Player _p)
	{
		p = _p;
	}
	/**
	 * initializes the GUI board
	 */
	private void initializeBoard() 
	{
		menu_Bar = new JMenuBar();
		menu = new JMenu("Menu");
		restart = new JButton("Restart");
		undo = new JButton("Undo");
		score = new JButton("Look Score");
		draw = new JButton("Draw");
		
		menu.add(restart);
		menu.add(score);
		menu.add(undo);
		menu.add(draw);
		
		
		addActionButton();
		
		menu_Bar.add(menu);
		
		
		frame = new Board(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menu_Bar);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}
	/**
	 * sets an action Listener for the button undo
	 */
	private void addActionButton() 
	{
		undo.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	if(undo != null && piece != null)
            	{
	                if( piece.getColor() == undo_movement.getTurn())
	                {
						pieces[undo_movement.getInit().getX()][undo_movement.getInit().getX()] = undo_movement.getPiece();
						pieces[undo_movement.getFinal().getX()][undo_movement.getFinal().getX()] = null;
						
						frame.undoMovement(undo_movement);
				
	                }
            	}
            	
            }
        }); 
		restart.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	give_up = true;
            	
            }
        }); 
		score.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	frame.showScore(p);
            	
            }
        }); 
		draw.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	both_playe_draw= true;
            	
            }
        });
		
	}
	/**
	 * Function to make clear the application was close the logic must end to
	 */
	public void updateApp() 
	{
		app_finished = true;
		
	}
	/**
	 * return if the application has been closed
	 */
	public boolean getupdateApp() 
	{
		return app_finished;
		
	}
	/**
	 * Every time A piece is clicked and dragged to a position update is 
	 * called to know if the move is valid
	 * Receives the Initial and the End position of the piece
	 */
	public boolean update( Position _init, Position _end)
	{
	
		position_init = _init; position_end= _end;
		piece = pieces[position_init.getX()][position_init.getY()];//gets the piece from the matrix
		
		if(piece.getColor() != p.getLogic())//looks if it is the players turn
			not_turn = true;
		else
			not_turn = false;
		
		if(!not_turn )// its  your turn
		{
			
			if(movement.checkMove(piece, position_init, position_end))//looks if the movement is valid
			{
				
				pieces[position_init.getX()][position_init.getY()] = null;
				pieces[position_end.getX()][position_end.getY()] = piece;
				
				movement.update(pieces);
				
				undo_movement = new Undo(position_init , position_end, piece);
				undo_movement.SetTurn(piece.getColor());
				
				if(movement.lookCheck(_end, piece.getColor()))//look if after the move the king is checked
				{
					p.setCheck();// the player is in check 
					frame.checkDialog();  
					if(movement.checkmate(piece))//look if is also checkmate
					{
						frame.checkMateDialog();  
					}
					
				}
				else
				{
					p.setCheckFalse();// the player is not in check update
				}
				movement_complete = true;// the player finished his move
				return true;//and the move was valid
			}
			if(debug)
			  {
				  lookboard();
			  }
		}
		
		if(not_turn)
		{
			//it is not  the players  turn
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
	/**
	 * Function for debugging to show how the pieces in the matrix are
	 */
	private void lookboard() 
	{
		for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if( pieces[i][j] != null)
						System.out.println("i: " + i + "j: " + j + "  " + pieces[i][j].getType());
				}
		  }
	}
	/**
	 * FCheckmate game is finished
	 */
	public boolean GameFinished()
	{
		return is_finished;
	}
	/**
	 * shows if the movement of the player is complete
	 */
	public boolean movementComplete() 
	{
		if(movement_complete)
		{	
			movement_complete = false;
			return true;
		}
		else return false;
	}
	
	/**
	 * Looks if there is a checkmate
	 */
	public boolean checkMate() 
	{
		if(p.getCheck())
		{
			if(movement.checkmate(piece))
			{	
				frame.checkMateDialog();
				return true;
			}
			else return false;
		}
		else return false;
		
	}
	
	public Boolean getUndo()
	{
		return undo_done;
	}
	public Boolean getGiveUp()
	{
		if(give_up)
		{
			give_up = false;
			return true;
		}else
			return false;
		
	}
	public Boolean getDraw()
	{
		if(both_playe_draw)
		{
			both_playe_draw = false;
			return true;
		}else
			return false;
		
	}
}
