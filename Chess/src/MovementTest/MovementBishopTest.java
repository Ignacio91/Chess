package MovementTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Basic_Objects.*;
import Movement.MovementBishop;
public class MovementBishopTest 
{
	Piece [][] pieces = new Piece[8][8];
	MovementBishop mb;
	ArrayList<Position> result, correct;
	boolean test;
	@Test
	public void testCheckBishop() 
	{
		createPieceCorrect();
		//createPieceIncorrect();
		correct = mb.checkBishop();
		if(result.size()==correct.size())
		{
			for(int i=0; i<result.size();i++)
			{
				test = false;
				for(int j=0; j<correct.size();j++)
				{
					if(samePos(result.get(i),correct.get(j)))
						test = true;
				}
				if(!test)
				{
					fail("Results are incorrect");
				}
				
			}
		}
		else
		{
			fail("Results are incorrect");
		}
	}

	 private boolean samePos(Position i, Position j) 
	 {
		if(i.getX()== j.getX() && i.getY()== j.getY())
			return true;
		else
			return false;
	}

	public void createPieceCorrect()
	{
		initialize();
		result.add(new Position(1,1));result.add(new Position(2,0));
		/*result.add(new Position(1,3));result.add(new Position(2,4));
		result.add(new Position(3,5));result.add(new Position(4,6));
		result.add(new Position(5,7));*/
		
	}
	public void createPieceIncorrect()
	{
		initialize();
		result.add(new Position(1,1));result.add(new Position(2,0));
		result.add(new Position(1,3));result.add(new Position(2,5));//incorrect
		result.add(new Position(3,5));result.add(new Position(4,6));
		result.add(new Position(5,7));
		
	}
	public void initialize()
	{
		Piece p  = new Piece(2, true);
		pieces[0][2] = p;
		pieces[1][3] = p;
		mb = new MovementBishop(pieces, p, 0, 2);
		result = new ArrayList<Position>();
	}
}
