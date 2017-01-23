package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chess.Knight;
import Chess.Location;
import Chess.Square;

public class SquareTest {

	@Test
	public void testSquare() {
		Knight k = new Knight("White", new Location('A', 1));
		Location l = new Location();
		
		Square s = new Square(l, k);
		
		assertEquals(s.getLocation(), l);
		assertEquals(s.getPiece(), k);
	}

	@Test
	public void testIsOccupied() {
		Knight k = new Knight("White", new Location('A', 1));
		Location l = new Location();
		
		Square s1 = new Square(l, k);
		Square s2 = new Square(new Location(), null);
		
		assertEquals(s1.isOccupied(), true);
		assertEquals(s2.isOccupied(), false);
	}

	@Test
	public void testKill() {
		Knight k = new Knight("White", new Location('A', 1));
		Location l = new Location();
		
		Square s = new Square(l, k);
		
		assertEquals(s.isOccupied(), true);
		s.kill();
		assertEquals(s.isOccupied(), false);
		
		}

}
