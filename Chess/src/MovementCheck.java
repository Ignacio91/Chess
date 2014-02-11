import java.util.ArrayList;

import Basic_Objects.*;
import Movement.*;
/**
 *  control the Movements of every piece
 * @author Ignacio Ferrero
 */	
public class MovementCheck 
{
	Piece[][] pieces;
	Piece piece;
	Position init;
	Position end;
	int x;
	int y;
	ArrayList<Position> possible_move_check_own;
	ArrayList<Position> possible_move_check_opp;
	
	int pos_x_king;
	int pos_y_king;
	
	boolean debug;
	
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
		ArrayList<Position> possible_move= getPossibleMoves();
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
	public boolean check(Position _end, boolean Color)
	{
		//important en position
		x = _end.getX();
		y =  _end.getY();
		//ArrayList<Position> possible_move= getPossibleMoves(x,y, piece);
		ArrayList<Position> possible_move= getPossibleMoves();
		pos_x_king =-1;// initialize
		pos_y_king =-1;
		//finds the position of the king
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(pieces[i][j] != null)
				{
					
					if( pieces[i][j].getType() == 4 && pieces[i][j].getColor() != Color)
					{
						
						pos_x_king =i;
						pos_y_king =j;
						break;
					}
				}
				
			}
		}
		
		if(pos_x_king!=-1)
		{
			for(int i=0; i<possible_move.size();i++)
			{
				if(debug)
				{
					System.out.println("X: " +possible_move.get(i).getX()+ "Y  " + possible_move.get(i).getY());
				}
				//looks if position of king is included in next move from the piece 
				if(possible_move.get(i).getX() == pos_x_king && possible_move.get(i).getY() == pos_y_king)
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
	private ArrayList<Position> getPossibleMoves() 
	{
		ArrayList<Position> possible_move = new ArrayList<Position>();
		int type = piece.getType();
		
		switch (type)
		{
			case 0://Rock type
				MovementRock mr =  new MovementRock( pieces,  piece,  x,  y);
				possible_move =  mr.checkRook();
				break;
			case 1://Knight type
				MovementKnight mk =  new MovementKnight( pieces,  piece,  x,  y);
				possible_move =  mk.checkKnight();
				break;
			case 2://Bishop type
				MovementBishop mp =  new MovementBishop( pieces,  piece,  x,  y);
				possible_move =  mp.checkBishop();
				break;
			case 3://Queen type
				possible_move = checkQueen();
				break;
			case 4://King type
				MovementKing mki =  new MovementKing( pieces,  piece,  x,  y);
				possible_move =  mki.checkKing();
				break;
			case 5://Pawn type
				MovementPawn mpa =  new MovementPawn( pieces,  piece,  x,  y);
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

	/*public boolean checkmate(Piece p) 
	{
		if(checkMoveOpponent(p))
			if(checkMoveKing(p))
				return true;
		return false;
	}*/
	private boolean checkMoveKing(Piece p) 
	{
		possible_move_check_own = new ArrayList<Position>();
		possible_move_check_opp = new ArrayList<Position>();
		MovementKing mki =  new MovementKing( pieces,  piece,  x,  y);
		possible_move_check_own =  mki.checkKing();
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if( pieces[i][j] != null)
				{
					if( pieces[i][j].getColor() != p.getColor())
						return true;
						//possible_move_check_opp = getPossibleMoves(i, j, pieces[i][j]);
				}
			}
		}
		
		return false;
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
