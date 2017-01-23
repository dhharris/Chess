package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chess.Bishop;
import Chess.Location;

public class BishopTest {

	@Test
	public void testIsValidMove() {
		Bishop b = new Bishop("White", new Location('A', 1));
		
		assertEquals(b.isValidMove(new Location('F', 6)), true);
		assertEquals(b.isValidMove(new Location('F', 7)), false);
		assertEquals(b.isValidMove(new Location('A', 1)), false);

		
	}

	@Test
	public void testIsValidCapture() {
		Bishop b = new Bishop("White", new Location('A', 1));
		
		assertEquals(b.isValidMove(new Location('F', 6)), true);
		assertEquals(b.isValidMove(new Location('F', 7)), false);
		assertEquals(b.isValidMove(new Location('A', 1)), false);
	}

	@Test
	public void testBishop() {
		Bishop b = new Bishop("White", new Location('A', 1));
		
		assertEquals(b.getTeam(), "White");
		assertEquals(b.getLocation().toString(), "A1");
	}

}
