package MovementTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Basic_Objects.Piece;
import Basic_Objects.Position;
import Movement.MovementSimple;

public class MovementSimpleTest 
{
	Piece [][] pieces = new Piece[8][8];
	MovementSimple mb;
	ArrayList<Position> result, correct;
	boolean test;
	
	@Test
	public void testCheckSimple()
	{
		createPieceCorrect();
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
	public void createPieceCorrect()
	{
		initialize();
		result.add(new Position(1,1));result.add(new Position(2,0));
		
		
	}
	public void createPieceIncorrect()
	{
		initialize();
		result.add(new Position(1,1));
		
	}
	public void initialize()
	{
		Piece p  = new Piece(6, true);
		pieces[0][2] = p;
		mb = new MovementSimple(pieces, p, 0, 2);
		result = new ArrayList<Position>();
	}
	
	private boolean samePos(Position i, Position j) 
	 {
		if(i.getX()== j.getX() && i.getY()== j.getY())
			return true;
		else
			return false;
	}

}
