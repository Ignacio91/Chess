import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Basic_Objects.*;

public class ChessGameDemo extends JFrame implements MouseListener, MouseMotionListener 
{
	
  JLayeredPane Panel_layered;//Add overlap for pieces
  JPanel chess_Board;
  JLabel chess_Piece;
  
  JLabel piece;
  JPanel panel;
  int xAdjustment;
  int yAdjustment;
  
  int position_x;
  int position_y;
  
  Position position_init;
  Position position_end;
  
  MovementCheck movement;
  
  Piece piece_obj;

  
  
  int heigth = 600;
  int width = 600;
  
  boolean finished;
  
  GameLogic game_logic;
  
  
  public ChessGameDemo(GameLogic _game_logic)
  {
	  game_logic = _game_logic;
	  finished = false;
	  initializeChess();
 
  }
  /**
	 * Initialized the chess Board and the gui 
	 */
  private void initializeChess() 
  {
	//Set Dimensions
	Dimension boardSize = new Dimension(heigth, width);
	 
	 //  Use a Layered Panel for this this application
	Panel_layered = new JLayeredPane();
	 getContentPane().add(Panel_layered);
	 Panel_layered.setPreferredSize(boardSize);
	 Panel_layered.addMouseListener(this);
	 Panel_layered.addMouseMotionListener(this);
	 
	  initBoard(boardSize);
	 
	  
	 
	initWhite();//init white pieces
	initBlack();//init black pieces
	
  }
  /**
	 * Initializes black pieces
	 */
  private void initBlack() 
  {
	JLabel piece;
	JPanel panel;
	//every pieces has an image and is part of the 64 components of the Jpanel
	piece = new JLabel(new ImageIcon("images/br.gif"));
	panel = (JPanel)chess_Board.getComponent(0);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/br.gif"));
	panel = (JPanel)chess_Board.getComponent(7);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/bh.gif"));
	panel = (JPanel)chess_Board.getComponent(1);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/bh.gif"));
	panel = (JPanel)chess_Board.getComponent(6);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/bb.gif"));
	panel = (JPanel)chess_Board.getComponent(2);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/bb.gif"));
	panel = (JPanel)chess_Board.getComponent(5);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/bq.gif"));
	panel = (JPanel)chess_Board.getComponent(3);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/bk.gif"));
	panel = (JPanel)chess_Board.getComponent(4);
	panel.add(piece);
	
	
	for(int i=0;i<8;i++)//initialize pawns
	{
		piece = new JLabel(new ImageIcon("images/bp.gif"));
		panel = (JPanel)chess_Board.getComponent(i+8);
		panel.add(piece);
	}
  }
  /**
	 * Initializes white pieces
	 */
  private void initWhite() 
  {
	
	piece = new JLabel(new ImageIcon("images/wr.gif"));
	panel = (JPanel)chess_Board.getComponent(63);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/wr.gif"));
	panel = (JPanel)chess_Board.getComponent(56);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/wh.gif"));
	panel = (JPanel)chess_Board.getComponent(57);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/wh.gif"));
	panel = (JPanel)chess_Board.getComponent(62);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/wb.gif"));
	panel = (JPanel)chess_Board.getComponent(61);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/wb.gif"));
	panel = (JPanel)chess_Board.getComponent(58);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/wq.gif"));
	panel = (JPanel)chess_Board.getComponent(59);
	panel.add(piece);
	piece = new JLabel(new ImageIcon("images/wk.gif"));
	panel = (JPanel)chess_Board.getComponent(60);
	panel.add(piece);
	
	
	
	for(int i=0;i<8;i++)
	{
		piece = new JLabel(new ImageIcon("images/wp.gif"));
		panel = (JPanel)chess_Board.getComponent(i+48);
		panel.add(piece);
	}

  }
 
 
  /**
	 * Initializes the  Board GUI
	 */
private void initBoard(Dimension boardSize) 
{
	//Add a chess board to the Layered Pane 
	 
	  chess_Board = new JPanel();
	  Panel_layered.add(chess_Board, JLayeredPane.DEFAULT_LAYER);
	  chess_Board.setLayout( new GridLayout(8, 8) );
	  chess_Board.setPreferredSize( boardSize );
	  chess_Board.setBounds(0, 0, boardSize.width, boardSize.height);
	 
	  //Board
	  for (int i = 0; i < 64; i++) 
	  {
		  JPanel square = new JPanel( new BorderLayout() );
		  chess_Board.add( square );
		 /*looks which color the next square is
		  how the are 8 rows , so therefore in each row you start with the different color to
		  have 32 white and 32 black squares*/
		  int row_round = (i / 8) % 2;
		  if (row_round == 0)//if the rest is divided by two then color first color white
		  {
			  if( i % 2 == 0)
				  square.setBackground(Color.white);
			  else
				  square.setBackground(Color.magenta);
		  }
		  
		  else
		  {
			  if( i % 2 == 0)//first color magenta
				  square.setBackground(Color.magenta);
			  else
				  square.setBackground(Color.white);
		  }
	  }
}
/**
 * Mouse pressed Event to take the initial position
 * source from http://forgetcode.com/Java/848-Chess-game-Swing
 */
  public void mousePressed(MouseEvent e)
  {
	  chess_Piece = null;
	  Component c =  chess_Board.findComponentAt(e.getX(), e.getY());
	  
 
	  if (c instanceof JPanel) //no piece
		  return;
	  //Get the pixels and divided them by 74 to get the actual position
	  //magic number, changes if the board size change
	  position_y = e.getX()/74;
	  position_x = e.getY()/74;
	  
	  position_init = new Position( position_x,position_y);//Gets initial position
	 
	  
	 
	  Point parentLocation = c.getParent().getLocation();// gets the parent location
	  xAdjustment = parentLocation.x - e.getX();
	  yAdjustment = parentLocation.y - e.getY();
	  
	  
	  chess_Piece = (JLabel)c;
	  chess_Piece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	  chess_Piece.setSize(chess_Piece.getWidth(), chess_Piece.getHeight());
	  
	  Panel_layered.add(chess_Piece, JLayeredPane.DRAG_LAYER);
  }
 
  //Move the chess piece around
  /**
   * Mouse pressed Event to take the initial position
   * source from http://forgetcode.com/Java/848-Chess-game-Swing
   * gets the piece while dragging the piece to final position so it does not disappear
   */
  public void mouseDragged(MouseEvent me) 
  {
	  if (chess_Piece == null) return;
	  	chess_Piece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
 }
  /**
   * Mouse pressed Event to take the initial position
   * source from http://forgetcode.com/Java/848-Chess-game-Swing
   * Grabs end position to allow or disallow the move
   * updates the board if the movement is allowed
   */
  public void mouseReleased(MouseEvent e) 
  {
	  if(chess_Piece == null) return;
 
	  chess_Piece.setVisible(false);
	  
	  position_y = e.getX()/74;
	  position_x = e.getY()/74;
	  
	  position_end = new Position(position_x,position_y);
	  System.out.println("X"  +position_x +"Y "+ position_y);
	  
	  if(game_logic.update(position_init, position_end))//if movement accepted
	  {
		  Component c =  chess_Board.findComponentAt(e.getX(), e.getY());
		  if (c instanceof JLabel)
		  {
			  Container parent = c.getParent();
			  parent.remove(0);
			  parent.add( chess_Piece );
		  }
		  else 
		  {
			  Container parent = (Container)c;
			  parent.add( chess_Piece );
		  }
		 
		  chess_Piece.setVisible(true);
		  
			  
	  }
	  else//movement not accepted return piece to original position
	  {
		  panel = (JPanel)chess_Board.getComponent(position_init.getX()*8+ position_init.getY());
		  panel.add(chess_Piece);
			
		  chess_Piece.setVisible(true);
	  }
	 
  }

 public Boolean getFinish()
 {
	 return finished;
 }
 public void checkDialog()
 {
	 JOptionPane.showMessageDialog(this, "Check!!");
 }
 public void checkMateDialog()
 {
	 JOptionPane.showMessageDialog(this, "You Win");
 }
 public void checkErrorTurn()
 {
	 JOptionPane.showMessageDialog(this, "It's not you turn");
 }
  public void mouseClicked(MouseEvent e) {
  
  }
  public void mouseMoved(MouseEvent e) {
 }
  public void mouseEntered(MouseEvent e){
  
  }
  public void mouseExited(MouseEvent e) {
  
  }
  public void windowClosing(WindowEvent e) 
  {
	  game_logic.updateApp();
  }
  
 
  
}