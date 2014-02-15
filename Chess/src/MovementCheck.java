import java.util.ArrayList;

import Basic_Objects.*;
import Movement.*;
/**
 *  control the Movements of every piece
 * @author Ignacio Ferrero
 */	
public class MovementCheck 
{
	Piece[][] pieces;//the logic board
	Piece piece;
	Position init;//initial position
	Position end;//initial position
	int x;
	int y;
	ArrayList<Position> possible_move_check_own;
	ArrayList<Position> possible_move_check_opp;
	
	int pos_x_king;
	int pos_y_king;
	
	boolean debug = false;// for debuging == true
	
	MovementCheck(Piece[][] _pieces)
	{
		pieces = _pieces;
	}
	
	/**
	 * Update the pieces
	 */
	public void update(Piece[][] _pieces)
	{
		//updates the board with the pieces
		if(_pieces== null)throw new IllegalArgumentException("X should be less than 1000");
		pieces = _pieces;
	}
	/**
	 * checks if the intended move is valid 
	 * receives the board the initial position and the end position
	 */
	public boolean checkMove(Piece p, Position _init, Position _end)
	{
		//required variables to update
		piece = p; init = _init; end= _end;
		x = init.getX();
		y =  init.getY();
		
		if(checkPossibleMoves() && !checksame())
			return true;
		else
			return false;
	}

	/**
	 * checks if the intended move is valid if it within the possible moves 
	 */
	private boolean checkPossibleMoves() 
	{
		//ArrayList<Position> possible_move= getPossibleMoves(x, y, piece);// return all the possible moves
		ArrayList<Position> possible_move= getPossibleMoves(piece, x ,y);
		for(int i=0; i<possible_move.size();i++)
		{
			if(possible_move.get(i).getX() == end.getX() && possible_move.get(i).getY() == end.getY())
				return true;// checks if the end postion is inside the arrays of possible moves
		}
		
		return false;
	}
	/**
	 * checks if the king is in check
	 */
	public boolean lookCheck(Position _end, boolean Color)
	{
		//important en position
		x = _end.getX();
		y =  _end.getY();
		ArrayList<Position> possible_move= getPossibleMoves(piece, x, y);
		
		pos_x_king =-1;// initialize
		pos_y_king =-1;
		
		boolean king_found = false;
		//finds the position of the king
		for(int i=0;i<8 && !king_found;i++)
		{
			for(int j=0;j<8 && !king_found;j++)
			{
				if(pieces[i][j] != null)
				{
					
					if( pieces[i][j].getType() == 4 && pieces[i][j].getColor() != Color)
					{
						
						pos_x_king =i;
						pos_y_king =j;
						king_found= true;
					}
				}
				
			}
		}
		if(debug)
			System.out.println("King found" + king_found);
		if(pos_x_king!=-1)
		{
			for(int i=0; i<possible_move.size();i++)
			{
				if(debug)
					System.out.println(possible_move.get(i));
				if(debug)
				{
					System.out.println("X!: " +possible_move.get(i).getX()+ "Y!  " + possible_move.get(i).getY());
				}
				//looks if position of king is included in next move from the piece 
				if(possible_move.get(i).check(new Position(pos_x_king,pos_y_king)))
					return true;
			}
		}
		return false;
		
		
	}
	/**
	 * checks for the game ending condition
	 * needs farther work
	 */
	public boolean checkMate(boolean Color)
	{
		//checks if the king is still on the board
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{

				if(pieces[i][j] != null)
				{
					if( pieces[i][j].getType() == 4 && pieces[i][j].getColor() != Color)
						return true;
				}
				
				
			}
		}
		return false;
		
		
	}
	/**
	 * Checks which type the piece is and gets the possible move of the piece type
	 * taking care of the all possible moves 
	 */
	private ArrayList<Position> getPossibleMoves(Piece p , int x1, int y1) 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>();
		int type = piece.getType();
		
		switch (type)
		{
			case 0://Rock type
				MovementRock mr =  new MovementRock( pieces,  p,  x1,  y1);
				possible_move =  mr.checkRook();
				break;
			case 1://Knight type
				MovementKnight mk =  new MovementKnight( pieces,  p,  x1,  y1);
				possible_move =  mk.checkKnight();
				break;
			case 2://Bishop type
				MovementBishop mp =  new MovementBishop( pieces,  p,  x1,  y1);
				possible_move =  mp.checkBishop();
				break;
			case 3://Queen type
				possible_move = checkQueen();
				break;
			case 4://King type
				MovementKing mki =  new MovementKing( pieces,  p,  x1,  y1);
				possible_move =  mki.checkKing();
				break;
			case 5://Pawn type
				MovementPawn mpa =  new MovementPawn( pieces,  p,  x1,  y1);
				possible_move =  mpa.checkPawn();
				
				
		}
		
		return possible_move;
	}
	/**
	 * Combines movement from Rock and Bishop for the movements of the queen
	 */
	private ArrayList<Position> checkQueen() 
	{
		MovementBishop mp =  new MovementBishop( pieces,  piece,  x,  y); 
		ArrayList<Position> possible_move = new ArrayList<Position>(mp.checkBishop()) ;
		
		MovementRock mr =  new MovementRock( pieces,  piece,  x,  y);
		possible_move.addAll(mr.checkRook());//Combine
		return possible_move;
	}



	/**
	 * checks if there is a piece of the same color at the end position
	 * Reason if there is we prevent unnecessary processing
	 */
	private boolean checksame() 
	{
		if(pieces[end.getX()][end.getY()]!= null)
		{
			if(pieces[init.getX()][init.getY()].getColor() == pieces[end.getX()][end.getY()].getColor() )
				return true;
			else 
				return false;
		}
		else
			return false;
	}
	/**
	 * Checks if there is Checkmate, piece p to know the color of the player == player
	 */
	public boolean checkmate(Piece p) 
	{
		if(checkMoveOpponent(p))//checks if he any of the piece of the player can "eat" the piece that is causing the check
			if(checkMoveKing(p))//checks if the king can move to a position where he is not in check
				return true;//blocking remains
		return false;
	}
	/**
	 * Checks if the king can move to a position where he is not in check therefore not in check
	 */
	private boolean checkMoveKing(Piece p) 
	{
		possible_move_check_own = new ArrayList<Position>();// Kings movements
		possible_move_check_opp = new ArrayList<Position>();//Oponents movement
		
		MovementKing mki =  new MovementKing( pieces,  piece,  x,  y);
		possible_move_check_own =  mki.checkKing();
		
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if( pieces[i][j] != null)
				{
					if( pieces[i][j].getColor() == p.getColor())
						possible_move_check_opp.addAll(getPossibleMoves(pieces[i][j], i, j));
				}
			}
		}
		boolean empty;
		for(int i = 0;i<possible_move_check_own.size();i++)
		{
			empty = false;
			for(int j = 0;j<possible_move_check_opp.size();j++)
			{
				if(debug)
					System.out.println(possible_move_check_opp.get(j).getX() +"  " + possible_move_check_opp.get(j).getY());
				if(possible_move_check_own.get(i).check(possible_move_check_opp.get(j)))//the oppopnen can move to the position where the king could move
				{//not valid keep looking
					empty = true;
					break;
				}
			}
			if(!empty)//the opponent can no check the king valid move not checkmate
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if there some of your pieces could move into the position of the piece that did the check
	 * if it is so then no checkmate
	 */
	private boolean checkMoveOpponent(Piece p) 
	{
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if( pieces[i][j] != null)
				{
					if( pieces[i][j].getColor() != p.getColor()){
						if(checkMove(pieces[i][j], new Position(i,j),new Position(end.getX(), end.getY())))
							return false;
					}
				}
			}
		}
		return true;
	}
	
	
	
}
