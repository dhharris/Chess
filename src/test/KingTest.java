package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chess.King;
import Chess.Location;

public class KingTest {

	@Test
	public void testIsValidMove() {
		King k = new King("White", new Location('A', 1));
		
		assertEquals(k.isValidMove(new Location('B', 1)), true);
		assertEquals(k.isValidMove(new Location('D', 1)), false);
	}

	@Test
	public void testIsValidCapture() {
		King k = new King("White", new Location('A', 1));
		
		assertEquals(k.isValidMove(new Location('B', 1)), true);
		assertEquals(k.isValidMove(new Location('D', 1)), false);
	}

	@Test
	public void testKing() {
		King k = new King("White", new Location('A', 1));
		
		assertEquals(k.getTeam(), "White");
		assertEquals(k.getLocation().toString(), "A1");
	}

}
