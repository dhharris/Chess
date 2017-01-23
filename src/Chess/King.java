package Chess;
/**
 * 
 */

/**
 * @author hugh
 *
 */
public class King extends Piece {
	public King(String team, Location l) {
		this.team = team;
		this.l = l;
	}
	
	// Polymorphic function that, given the team name,
	// returns the starting a list of starting locations
	// for a given Piece.
	public Location[] startingLocation(String team) {
		Location[] list = new Location[1];

		if (team.equals("White")) {
			list[0] = new Location('E', 1);
		}
		else if (team.equals("Black")) {
			list[0] = new Location('E', 8);
		}
		else {
			return null;
		}

		return list;

	}
	
	public boolean isValidMove(Location l) {
		// Up 
		return l.equals(this.l.up()) ||

				// Down
				l.equals(this.l.down()) ||

				// Left
				l.equals(this.l.left()) ||

				// Right
				l.equals(this.l.right()) ||

				// Up-right
				l.equals(this.l.up().right()) ||

				// Up-left
				l.equals(this.l.up().left()) ||

				// Down-right
				l.equals(this.l.down().right()) ||

				// Down-left
				l.equals(this.l.down().left());
	}

	public boolean isValidCapture(Location l) {
		// Upper left or upper right
		return isValidMove(l);
	}
}
