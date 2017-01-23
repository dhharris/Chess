package Chess;
/**
 * 
 */

/**
 * @author hugh
 *
 */
public class Pawn extends Piece {
	boolean hasMoved;
	public Pawn(String team, Location l) {
		this.team = team;
		this.l = l;
		hasMoved = false;
	}
	
	// Polymorphic function that, given the team name,
	// returns the starting a list of starting locations
	// for a given Piece.
	public Location[] startingLocation(String team) {
		Location[] list = new Location[8];
		
		if (team.equals("White")) {
			for (int i = 0; i < list.length; ++i)
				list[i] = new Location((char)('A' + i), 2);
		}
		else if (team.equals("Black")) {
			for (int i = 0; i < list.length; ++i)
				list[i] = new Location((char)('A' + i), 7);
		}
		else {
			return null;
		}
		
		return list;
	}

	public boolean isValidMove(Location l) {
		// White moves up
		if (team.equals("White")) {
			if (!hasMoved) {
				hasMoved = l.equals(this.l.up()) || l.equals(this.l.up().up());
				return hasMoved;
			}
			else
				return l.equals(this.l.up());
		}
		
		// Black moves down
		if (!hasMoved) {
			hasMoved = l.equals(this.l.down()) || l.equals(this.l.down().down());
			return hasMoved;
		}
		else
			return l.equals(this.l.down());
	}

	public boolean isValidCapture(Location l) {
		// White can capture upper left or upper right
		if (team.equals("White"))
			return l.equals(this.l.up().right()) || l.equals(this.l.up().left());
		return l.equals(this.l.down().right()) || l.equals(this.l.down().left());
	}
}
