package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chess.Knight;
import Chess.Location;

public class KnightTest {

	@Test
	public void testIsValidMove() {
		Knight k = new Knight("White", new Location('A', 1));

		assertEquals(k.isValidMove(new Location('B', 3)), true);
		assertEquals(k.isValidMove(new Location('B', 4)), false);
	}

	@Test
	public void testIsValidCapture() {
		Knight k = new Knight("White", new Location('A', 1));

		assertEquals(k.isValidMove(new Location('B', 3)), true);
		assertEquals(k.isValidMove(new Location('B', 4)), false);
	}

	@Test
	public void testKnight() {
		Knight k = new Knight("White", new Location('A', 1));
		
		assertEquals(k.getTeam(), "White");
		assertEquals(k.getLocation().toString(), "A1");
	}

}
