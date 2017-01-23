package Chess;
/**
 * 
 */

/**
 * @author dhharri2
 * Defines a location on the board
 * i.e. A1, B1, ..., H1, A2, B2, ..., H2, ..., A8, B8, ..., H8
 */
public class Location {
	char x;
	int y;

	public Location() {
		this.x = 'A';
		this.y = 1;
	}
	
	public Location(char x, int y) {
		this.x = x;
		this.y = y;
	}

	public Location(int x, int y) {
		this.x = (char) ('A' + x - 1);
		this.y = y;
	}
	
	public Location(Location l) {
		this.x = l.x;
		this.y = l.y;
	}

	public char getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int getXInt() {
		return this.x - 'A';
	}
	public int getYInt() {
		return this.y - 1;
	}

	public Location next(Location max) {
		if (this.getY() >= max.getY() && this.getX() >= max.getX())
			return null; // Check boundaries

		if (this.getX() >= max.getX())
			return new Location('A', this.getY() + 1); // Next row

		return new Location((char)(this.getX() + 1), this.getY()); // Next column
	}

	public String toString() {
		return Character.toString(getX()) + Integer.toString(getY());
	}
	
	public boolean isValid(Location max) {
		return getX() >= 'A' && getX() <= max.getX() && getY() >= 1 && getY() <= max.getY();
	}

	// Returns square above
	public Location up() {
		return new Location(getX(), getY() + 1);
	}

	// Returns square below
	public Location down() {
		return new Location(getX(), getY() - 1);
	}

	// Returns square to the left
	public Location left() {
		return new Location((char)(getX() - 1), getY());
	}

	// Returns square to the right
	public Location right() {
		return new Location((char)(getX() + 1), getY());
	}
	
	public boolean equals(Location l) {
		return this.getX() == l.getX() && this.getY() == l.getY();
	}
}
