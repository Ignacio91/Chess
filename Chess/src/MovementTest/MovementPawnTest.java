package MovementTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Basic_Objects.*;
import Movement.MovementPawn;
public class MovementPawnTest 
{
	Piece [][] pieces = new Piece[8][8];
	MovementPawn mb;
	ArrayList<Position> result, correct;
	boolean test;
	@Test
	public void testCheckPawn() 
	{
		createPieceCorrect();
		//createPieceIncorrect();
		correct = mb.checkPawn();
		System.out.println(correct.get(0).getX());
		System.out.println(correct.get(1).getX());
		System.out.println(correct.get(2).getX());

		if(result.size()==correct.size())
		{
			for(int i=0; i<result.size();i++)
			{
				
				test = false;
				for(int j=0; j<correct.size();j++)
				{
					System.out.println(correct.get(i).getX());
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
		result.add(new Position(2,1));result.add(new Position(2,2));
		result.add(new Position(3,2));
		
	}
	public void createPieceIncorrect()
	{
		initialize();
		result.add(new Position(1,1));result.add(new Position(2,0));
		
		
	}
	public void initialize()
	{
		Piece p1 = new Piece(3, true);
		Piece p  = new Piece(5, false);
		pieces[1][2] = p;
		pieces[2][1] = p1;
		
		mb = new MovementPawn(pieces, p, 1, 2);
		result = new ArrayList<Position>();
	}
}