package Chess;
/**
 * 
 */

/**
 * @author hugh
 *
 */
public class Queen extends Piece {
	public Queen(String team, Location l) {
		this.team = team;
		this.l = l;
	}
	
	// Polymorphic function that, given the team name,
	// returns the starting a list of starting locations
	// for a given Piece.
	public Location[] startingLocation(String team) {
		Location[] list = new Location[1];

		if (team.equals("White")) {
			list[0] = new Location('D', 1);
		}
		else if (team.equals("Black")) {
			list[0] = new Location('D', 8);
		}
		else {
			return null;
		}

		return list;

	}

	public boolean isValidMove(Location l) {
		// Up two, right one
		return isVert(l) || isHoriz(l) || isDiag(l);
	}

	public boolean isValidCapture(Location l) {
		// Upper left or upper right
		return isValidMove(l);
	}
}
