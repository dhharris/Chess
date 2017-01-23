package Chess;
/**
 * 
 */

/**
 * @author hugh
 *
 */
public class Bishop extends Piece {
	public Bishop(String team, Location l) {
		this.team = team;
		this.l = l;
	}
	
	// Polymorphic function that, given the team name,
	// returns the starting a list of starting locations
	// for a given Piece.
	public Location[] startingLocation(String team) {
		Location[] list = new Location[2];

		if (team.equals("White")) {
			list[0] = new Location('C', 1);
			list[1] = new Location('F', 1);
		}
		else if (team.equals("Black")) {
			list[0] = new Location('C', 8);
			list[1] = new Location('F', 8);
		}
		else {
			return null;
		}

		return list;

	}
	
	// Tells whether we have a valid move
	// Polymorphic function depends on the type of Piece
	// Does NOT take obstacles into account
	public boolean isValidMove(Location l) {
		return isDiag(l);
	}

	// Tells whether we have a valid move to take a piece
	// Does NOT take obstacles into account
	public boolean isValidCapture(Location l) {
		return isValidMove(l);
	}
}
