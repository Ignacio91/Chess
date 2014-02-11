
import static org.junit.Assert.*;

import org.junit.Test;
import Basic_Objects.*;

/**
 * 
 */

/**
 * @author ignacioferrero
 *
 */
public class MovementCheckTest {

	/**
	 * Test method for {@link MovementCheck#update(Piece[][])}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionIsThrown() 
	{
		Piece [][] p = new Piece[8][8];
		MovementCheck mp = new MovementCheck(p);
		mp.update(null);
	}
	/**
	 * Test method for {@link MovementCheck#checkMove(Piece, Position, Position)}.
	 */
	@Test
	public void testCheckMove() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link MovementCheck#check(Position, boolean)}.
	 */
	@Test
	public void testCheck() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link MovementCheck#checkMate(boolean)}.
	 */
	@Test
	public void testCheckMate() 
	{
		fail("Not yet implemented");
	}

}
