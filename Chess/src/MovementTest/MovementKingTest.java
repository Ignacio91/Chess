package MovementTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Basic_Objects.Piece;
import Basic_Objects.Position;
import Movement.MovementKing;

public class MovementKingTest {

	Piece [][] pieces = new Piece[8][8];
	MovementKing mb;
	ArrayList<Position> result, correct;
	boolean test;
	@Test
	public void testCheckKing() 
	{
		createPieceCorrect();
		//createPieceIncorrect();
		correct = mb.checkKing();
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
		initialize();//Possible Positions from the actual position( 0 2)
		result.add(new Position(1,0));result.add(new Position(1,1));
		result.add(new Position(0,1));
		
	}
	public void createPieceIncorrect()
	{
		initialize();
		result.add(new Position(1,0));result.add(new Position(1,1));
		result.add(new Position(0,3));//incorrect
		
	}
	public void initialize()
	{
		Piece p  = new Piece(4, true);
		pieces[0][2] = p;
		mb = new MovementKing(pieces, p, 0, 2);
		result = new ArrayList<Position>();
	}
}
