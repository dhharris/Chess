/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chess.Location;

/**
 * @author dhharri2
 *
 */
public class LocationTest {
	
	/**
	 * Test method for {@link Chess.Location#Location()}.
	 */
	@Test
	public void testLocation() {
		Location l = new Location();
		assertEquals(l.getX(),'A');
		assertEquals(l.getY(), 1);
	}

	/**
	 * Test method for {@link Chess.Location#Location(char, int)}.
	 */
	@Test
	public void testLocationCharInt() {
		Location l = new Location('B', 2);
		assertEquals(l.getX(), 'B');
		assertEquals(l.getY(), 2);
	}

	/**
	 * Test method for {@link Chess.Location#Location(int, int)}.
	 */
	@Test
	public void testLocationIntInt() {
		Location l = new Location(1, 1);
		assertEquals(l.getX(), 'A');
		assertEquals(l.getY(), 1);
	}

	/**
	 * Test method for {@link Chess.Location#Location(Chess.Location)}.
	 */
	@Test
	public void testLocationLocation() {
		Location l1 = new Location('H', 7);
		Location l2 = new Location(l1);
		assertEquals(l1.getX(), l2.getX());
		assertEquals(l1.getY(), l2.getY());
	}

	/**
	 * Test method for {@link Chess.Location#getX()}.
	 */
	@Test
	public void testGetX() {
		// Assume getters work
	}
	
	/**
	 * Test method for {@link Chess.Location#getXInt()}.
	 */
	@Test
	public void testGetXInt() {
		Location l = new Location('A', 1);
		assertEquals(l.getXInt(), 0);
	}
	
	/**
	 * Test method for {@link Chess.Location#getYInt()}.
	 */
	@Test
	public void testGetYInt() {
		Location l = new Location('A', 1);
		assertEquals(l.getYInt(), 0);
	}

	/**
	 * Test method for {@link Chess.Location#getY()}.
	 */
	@Test
	public void testGetY() {
		// Assume getters work
	}

	/**
	 * Test method for {@link Chess.Location#next(Chess.Location)}.
	 */
	@Test
	public void testNext() {
		Location max = new Location('H', 7);
		
		// Test A1 -> B1 (next column)
		Location l = new Location(); // A1 is default
		l = l.next(max);
		
		// Should be at B1 now
		assertEquals(l.getX(), 'B');
		assertEquals(l.getY(), 1);
		
		// Test H1 -> A2 (next row)
		l = new Location('H', 1);
		l = l.next(max);
		
		// Should be at A2 now
		assertEquals(l.getX(), 'A');
		assertEquals(l.getY(), 2);
		
		// Test boundaries (H7.next() == null)
		l = new Location('H', 7);
		l = l.next(max);
		
		// Should be null now
		assertEquals(l, null);
		
	}

	/**
	 * Test method for {@link Chess.Location#toString()}.
	 */
	@Test
	public void testToString() {
		Location l = new Location('B', 2);
		assertEquals(l.toString(), "B2");
	}

	/**
	 * Test method for {@link Chess.Location#isValid(Chess.Location)}.
	 */
	@Test
	public void testIsValid() {
		Location max = new Location('H', 7);
		Location valid = new Location('C', 3);
		Location inValid1 = new Location('H', 8);
		Location inValid2 = new Location('J', 5);
		
		assertEquals(valid.isValid(max), true);
		assertEquals(inValid1.isValid(max), false);
		assertEquals(inValid2.isValid(max), false);
	}

	/**
	 * Test method for {@link Chess.Location#up()}.
	 */
	@Test
	public void testUp() {
		Location l = new Location('C', 2);
		l = l.up();
		
		assertEquals(l.getX(), 'C');
		assertEquals(l.getY(), 3);
	}

	/**
	 * Test method for {@link Chess.Location#down()}.
	 */
	@Test
	public void testDown() {
		Location l = new Location('C', 2);
		l = l.down();
		
		assertEquals(l.getX(), 'C');
		assertEquals(l.getY(), 1);
	}

	/**
	 * Test method for {@link Chess.Location#left()}.
	 */
	@Test
	public void testLeft() {
		Location l = new Location('C', 2);
		l = l.left();
		
		assertEquals(l.getX(), 'B');
		assertEquals(l.getY(), 2);
	}

	/**
	 * Test method for {@link Chess.Location#right()}.
	 */
	@Test
	public void testRight() {
		Location l = new Location('C', 2);
		l = l.right();
		
		assertEquals(l.getX(), 'D');
		assertEquals(l.getY(), 2);
	}

	/**
	 * Test method for {@link Chess.Location#equals(Chess.Location)}.
	 */
	@Test
	public void testEqualsLocation() {
		Location l1 = new Location('C',2);
		Location l2 = new Location('C', 2);
		Location l3 = new Location('D', 2);
		Location l4 = new Location('C', 3);
		
		assertEquals(l1.equals(l2), true);
		assertEquals(l1.equals(l3), false);
		assertEquals(l1.equals(l4), false);
	}

}
