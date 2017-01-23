package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chess.Location;
import Chess.Pawn;

public class PawnTest {

	@Test
	public void testIsValidMove() {
		Pawn p1 = new Pawn("White", new Location('A', 1));
		Pawn p2 = new Pawn("Black", new Location('B', 2));
		
		assertEquals(p1.isValidMove(new Location('A', 2)), true);
		assertEquals(p1.isValidMove(new Location('B', 2)), false);
		
		assertEquals(p2.isValidMove(new Location('B', 1)), true);
		assertEquals(p2.isValidMove(new Location('B', 3)), false);
	}

	@Test
	public void testIsValidCapture() {
		Pawn p1 = new Pawn("White", new Location('A', 1));
		Pawn p2 = new Pawn("Black", new Location('B', 2));
		
		assertEquals(p1.isValidCapture(new Location('B', 2)), true);
		assertEquals(p1.isValidCapture(new Location('B', 1)), false);
		
		assertEquals(p2.isValidCapture(new Location('A', 1)), true);
		assertEquals(p2.isValidCapture(new Location('B', 1)), false);
	}

	@Test
	public void testPawn() {
		Pawn p = new Pawn("White", new Location('A', 1));
		
		assertEquals(p.getTeam(), "White");
		assertEquals(p.getLocation().toString(), "A1");
	}

}
