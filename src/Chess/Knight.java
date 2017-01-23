package Chess;
/**
 * 
 */

/**
 * @author hugh
 *
 */
public class Knight extends Piece {
	public Knight(String team, Location l) {
		this.team = team;
		this.l = l;
	}
	
	// Polymorphic function that, given the team name,
	// returns the starting a list of starting locations
	// for a given Piece.
	public Location[] startingLocation(String team) {
		Location[] list = new Location[2];

		if (team.equals("White")) {
			list[0] = new Location('B', 1);
			list[1] = new Location('G', 1);
		}
		else if (team.equals("Black")) {
			list[0] = new Location('B', 8);
			list[1] = new Location('G', 8);
		}
		else {
			return null;
		}

		return list;

	}

	public boolean isValidMove(Location l) {
		// Up two, right one
		return l.equals(this.l.up().up().right()) ||

				// Up two, left one
				l.equals(this.l.up().up().left()) ||

				// Down two, right one
				l.equals(this.l.down().down().right()) ||

				// Down two, left one
				l.equals(this.l.down().down().left()) ||

				// Right two, up one
				l.equals(this.l.right().right().up()) ||

				// Right two, down one
				l.equals(this.l.right().right().down()) ||

				// Left two, up one
				l.equals(this.l.left().left().up()) ||

				// Left two, down one
				l.equals(this.l.left().left().down());
	}

	public boolean isValidCapture(Location l) {
		// Upper left or upper right
		return isValidMove(l);
	}
}