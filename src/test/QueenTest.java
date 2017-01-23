package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chess.Location;
import Chess.Queen;

public class QueenTest {

	@Test
	public void testIsValidMove() {
		Queen q = new Queen("White", new Location('A', 1));
		
		// Horiz
		assertEquals(q.isValidMove(new Location('A', 7)), true);
		
		// Vert
		assertEquals(q.isValidMove(new Location('H', 1)), true);
		
		// Diag
		assertEquals(q.isValidMove(new Location('H', 8)), true);
		
		// Invalid
		assertEquals(q.isValidMove(new Location('H', 6)), false);

	}

	@Test
	public void testIsValidCapture() {
		Queen q = new Queen("White", new Location('A', 1));
		
		// Horiz
		assertEquals(q.isValidMove(new Location('A', 7)), true);
		
		// Vert
		assertEquals(q.isValidMove(new Location('H', 1)), true);
		
		// Diag
		assertEquals(q.isValidMove(new Location('H', 8)), true);
		
		// Invalid
		assertEquals(q.isValidMove(new Location('H', 6)), false);
	}

	@Test
	public void testQueen() {
		Queen q = new Queen("White", new Location('A', 1));
		
		assertEquals(q.getTeam(), "White");
		assertEquals(q.getLocation().toString(), "A1");
	}

}
