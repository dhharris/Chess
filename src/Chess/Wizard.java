package Chess;

/*
 * 
 * Can teleport anywhere but can't capture pieces
 */
public class Wizard extends Piece {
	public Wizard(String team, Location l) {
		this.team = team;
		this.l = l;
	}
	
	public boolean isValidMove(Location l) {
		return !l.equals(this.l);
	}

	public boolean isValidCapture(Location l) {
		return false;
	}
}
