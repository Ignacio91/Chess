package MovementTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Basic_Objects.*;
import Movement.MovementKnight;
public class MovementKnightTest 
{
	Piece [][] pieces = new Piece[8][8];
	MovementKnight mb;
	ArrayList<Position> result, correct;
	boolean test;
	@Test
	public void testCheckKnight() 
	{
		//createPieceCorrect();
		createPieceIncorrect();
		correct = mb.checkKnight();
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
		result.add(new Position(5,2));result.add(new Position(1,2));
		result.add(new Position(5,4));result.add(new Position(2,4));
		
	}
	public void createPieceIncorrect()
	{
		initialize();
		result.add(new Position(5,2));result.add(new Position(1,2));
		result.add(new Position(5,4));result.add(new Position(2,3));//incorrect
		
		
	}
	public void initialize()
	{
		Piece p  = new Piece(1, true);
		pieces[3][1] = p;
		mb = new MovementKnight(pieces, p, 3, 1);
		result = new ArrayList<Position>();
	}
}