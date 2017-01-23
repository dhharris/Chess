package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chess.Location;
import Chess.Rook;

public class RookTest {

	@Test
	public void testIsValidMove() {
		Rook r = new Rook("White", new Location('A', 1));
		
		// Horiz
		assertEquals(r.isValidMove(new Location('H', 1)), true);
		
		// Vert
		assertEquals(r.isValidMove(new Location('A', 8)), true);
		
		// Invalid
		assertEquals(r.isValidMove(new Location('B', 2)), false);
	}

	@Test
	public void testIsValidCapture() {
		Rook r = new Rook("White", new Location('A', 1));
		
		// Horiz
		assertEquals(r.isValidMove(new Location('H', 1)), true);
		
		// Vert
		assertEquals(r.isValidMove(new Location('A', 8)), true);
		
		// Invalid
		assertEquals(r.isValidMove(new Location('B', 2)), false);
	}

	@Test
	public void testRook() {
		Rook r = new Rook("White", new Location('A', 1));
		
		assertEquals(r.getTeam(), "White");
		assertEquals(r.getLocation().toString(), "A1");
	}

}
