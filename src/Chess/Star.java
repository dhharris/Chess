package Chess;

/*
 * 
 * Can move diagonally or horizontally
 */
public class Star extends Piece {
	public Star(String team, Location l) {
		this.team = team;
		this.l = l;
	}
	
	public boolean isValidMove(Location l) {
		return isDiag(l) || isHoriz(l);
	}

	public boolean isValidCapture(Location l) {
		return isValidMove(l);
	}
}
