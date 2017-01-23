package Chess;
/**
 * 
 */

/**
 * @author dhharri2
 *
 */
public class Square {
	Location l;
	Piece p;
	
	public Square(Location l, Piece p) {
		this.l = l;
		this.p = p;
	}
	
	public Square(Square s) {
		this.l = new Location(s.l);
		
		Piece p = s.getPiece();
		
		if (p == null) {
			this.p = null;
			return;
		}
		String team = p.getTeam();
		
		if (p instanceof Bishop)
			this.p = new Bishop(team, l);
		else if (p instanceof King)
			this.p = new King(team, l);
		else if (p instanceof Knight)
			this.p = new Knight(team, l);
		else if (p instanceof Pawn)
			this.p = new Pawn(team, l);
		else if (p instanceof Queen)
			this.p = new Queen(team, l);
		else if (p instanceof Rook)
			this.p = new Rook(team, l);
		else if (p instanceof Star)
			this.p = new Star(team, l);
		else
			this.p = new Wizard(team, l);
	}
	
	public boolean isOccupied() {
		return p != null;
	}
	
	public void setLocation(Location l) {
		this.l = l;
	}
	
	public Location getLocation() {
		return l;
	}
	
	public void setPiece(Piece p) {
		this.p = p;
	}
	
	public Piece getPiece() {
		return p;
	}
	
	
	
	// Removes the piece on this square
	public void kill() {
		p = null;
	}
	
	
}
