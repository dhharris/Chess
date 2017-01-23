package Chess;
/**
 * 
 */

/**
 * @author dhharri2
 *
 */
public abstract class Piece {

	String team; // White or black
	Location l;
	
	public Piece() {
		this.team = "White";
		this.l = new Location('A', 1);
	}
	
	public Piece(String team, Location l) {
		this.team = team;
		this.l = l;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setLocation(Location l) {
		this.l = l;
	}
	
	public Location getLocation() {
		return l;
	}
	
	// Call this function when promoting a pawn to get a new queen
	public Queen promote() {
		return new Queen(this.getTeam(), this.getLocation());
	}
	
	// Polymorphic function that, given the team name,
	// returns the starting a list of starting locations
	// for a given Piece.
	public Location[] startingLocation(String team) {
		return null;
	}

	// Tells whether we have a valid move
	// Polymorphic function depends on the type of Piece
	// Does NOT take obstacles into account
	public boolean isValidMove(Location l) {
		return false;
	}

	// Tells whether we have a valid move to capture a piece
	// Does NOT take obstacles into account
	public boolean isValidCapture(Location l) {
		return false;
	}

	public Boolean isDiag(Location l) {
		if (this.l.equals(l))
			return false;
		
		int dx, dy;
		
		dx = Math.abs((int)(this.l.getX() - l.getX()));
		dy = Math.abs((int)(this.l.getY() - l.getY()));

		return dx == dy;



	}

	public Boolean isHoriz(Location l) {
		if (this.l.equals(l))
			return false;
		
		int dx;
		
		dx = Math.abs((int)(this.l.getX() - l.getX()));
		
		return dx == 0;
	}

	public Boolean isVert(Location l) {
		if (this.l.equals(l))
			return false;
		
		int dy;
		
		dy = Math.abs((int)(this.l.getY() - l.getY()));
		
		return dy == 0;
	}


}
