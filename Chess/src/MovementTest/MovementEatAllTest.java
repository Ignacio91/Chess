package MovementTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Basic_Objects.Piece;
import Basic_Objects.Position;
import Movement.MovementEatAll;

public class MovementEatAllTest {
	MovementEatAll mb;
	ArrayList<Position> result;
	Piece [][] pieces = new Piece[8][8];
	@Test
	public void test() 
	{
		if(result.size()!= 64)
			fail("Not yet implemented");
	}
	public void createPieceCorrect()
	{
		initialize();
		
	}
	
	public void initialize()
	{
		Piece p  = new Piece(2, true);
		pieces[1][3] = p;
		mb = new MovementEatAll(pieces, p, 0, 2);
		result= mb.checkEatAll();
	}

}
