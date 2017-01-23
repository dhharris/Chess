package Chess;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author dhharri2
 * Contains all relevant information about the game board, such as
 * length - The length of the board
 * width - The width of the board
 * max - lets us define a boundary of the board. i.e. if it's an 8x8 board, max would be "H7"
 * m - Mapping from location to a square on the board
 */
public class Board {
	int length, width;
	Location max;
	Location kingLocations[];
	Map<String, Square> m;

	public Board() {
		length = 8;
		width = 8;
		max = new Location(8, 8);
		mapSquares();
		kingLocations = new Location[2];
		kingLocations[GameState.WHITE] = new Location('E', 1);
		kingLocations[GameState.BLACK] = new Location('E', 8);
	}

	/**
	 * Copy constructor 
	 * @param b
	 */
	public Board(Board b) {
		this.length = b.length;
		this.width = b.width;
		this.max = new Location(8, 8);
		this.m = new HashMap<String, Square>(); // Initialize

		// Map squares to a new map
		Location l = new Location();

		while (l != null) {
			Square s = b.getSquare(l);
			this.m.put(l.toString(), new Square(s));
			l = l.next(max);
		}
		this.kingLocations = new Location[2];
		this.kingLocations[0] = new Location(b.kingLocations[0]);
		this.kingLocations[1] = new Location(b.kingLocations[1]);
	}

	public Board(int length, int width) {
		this.length = length;
		this.width = width;
		max = new Location(length, width);
		mapSquares();

	}
	
	/**
	 * Set king location
	 * @param team
	 * Which team we are setting?
	 * @param l
	 * Where is the king located?
	 */
	public void setKingLocation(String team, Location l) {
		if (team.equals("Black"))
			kingLocations[GameState.BLACK] = l;
		else
			kingLocations[GameState.WHITE] = l;
	}

	/**
	 * Initializes hash map of all squares
	 * Using their appropriate starting locations
	 */
	public void mapSquares() {
		m = new HashMap<String, Square>(); // Initialize
		Location l = new Location('A', 1);

		//White team
		m.put(l.toString(), new Square(l, new Rook("White", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Knight("White", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Bishop("White", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Queen("White", l)));
		l = l.next(max);
		
		m.put(l.toString(), new Square(l, new King("White", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Bishop("White", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Star("White", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Wizard("White", l)));
		l = l.next(max);

		// Map 8 pawns
		for (int i = 0; i < 8; ++i) {
			m.put(l.toString(), new Square(l, new Pawn("White", l)));
			l = l.next(max);
		}

		// Space between teams
		for (; !l.toString().equals("A7"); l = l.next(max)) {
			m.put(l.toString(),  new Square(l, null)); // Empty square
		}


		// Black team
		l = new Location('A', 7);

		// Place 8 pawns
		for (int i = 0; i < 8; ++i) {
			m.put(l.toString(), new Square(l, new Pawn("Black", l)));
			l = l.next(max);
		}

		m.put(l.toString(), new Square(l, new Rook("Black", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Knight("Black", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Bishop("Black", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Queen("Black", l)));
		l = l.next(max);
		
		m.put(l.toString(), new Square(l, new King("Black", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Bishop("Black", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Star("Black", l)));
		l = l.next(max);

		m.put(l.toString(), new Square(l, new Wizard("Black", l)));
		l = l.next(max);
	}

	/**
	 * Puts piece p at location l
	 * @param l
	 * @param p
	 */
	public void setSquare(Location l, Piece p) {
		Square s = getSquare(l);
		s.setPiece(p);
	}

	/**
	 * Searches hash map for a square at a given location
	 * @param l
	 * @return The square at location l
	 */
	public Square getSquare(Location l) {
		return m.get(l.toString());
	}

	/**
	 * Same as above but with a string location
	 * @param l (String)
	 * @return
	 */
	public Square getSquare(String l) {
		return m.get(l);
	}

	/**
	 * Steps through board to see if there are any obstacles in the way of our move
	 * @param src
	 * @param dest
	 * @return Whether or not there is a piece in between src and dest
	 */
	public boolean hasObstacle(Location src, Location dest) {
		// Direction variables
		boolean posX = false;
		boolean posY = false;
		boolean horiz = false;
		boolean vert = false;

		// Check for whether the piece at src is a knight or wizard
		Square s = getSquare(src);
		Piece p = s.getPiece();
		if (p instanceof Knight || p instanceof Wizard)
			return false; // No obstacles!!

		if (dest.getX() == src.getX())
			vert = true;
		if (dest.getY() == src.getY())
			horiz = true;

		if (dest.getX() > src.getX())
			posX = true;

		if (dest.getY() > src.getY())
			posY = true;

		if (horiz) {
			if (posX) {
				do { // Move right
					src = src.right();
					
					// see if we're at dest 
					if (src.equals(dest))
						break;
					
					// See if we're out of bounds
					if (getSquare(src) == null)
						break;
					
					// Check if new src is occupied
					if (src != null && getSquare(src).isOccupied())
						return true; // There's an obstacle in our way!
				} while (src != null && !src.equals(dest));
				return false;
			}
			else {
				do { // Move left
					src = src.left();
					
					// see if we're at dest 
					if (src.equals(dest))
						break;
					
					// See if we're out of bounds
					if (getSquare(src) == null)
						break;
					
					// Check if new src is occupied
					if (src != null && getSquare(src).isOccupied())
						return true; // There's an obstacle in our way!
				} while (src != null && !src.equals(dest));
				return false;
			}
		}

		if (vert) {
			if (posY) {
				do { // Move up
					src = src.up();
					
					// see if we're at dest 
					if (src.equals(dest))
						break;
					
					// See if we're out of bounds
					if (getSquare(src) == null)
						break;
					
					// Check if new src is occupied
					if (src != null && getSquare(src).isOccupied())
						return true; // There's an obstacle in our way!
				} while (src != null && !src.equals(dest));
				return false;
			}
			else {
				do { // Move down
					src = src.down();
					
					// see if we're at dest 
					if (src.equals(dest))
						break;
					
					// See if we're out of bounds
					if (getSquare(src) == null)
						break;
					
					// Check if new src is occupied
					if (src != null && getSquare(src).isOccupied())
						return true; // There's an obstacle in our way!
				} while (src != null && !src.equals(dest));
				return false;
			}
		}

		if (posX && posY) {
			do { // Move diagonal in the positive direction
				src = src.up();
				src = src.right();
				
				// see if we're at dest 
				if (src.equals(dest))
					break;
				
				// See if we're out of bounds
				if (getSquare(src) == null)
					break;
				
				// Check if new src is occupied
				if (src != null && getSquare(src).isOccupied())
					return true; // There's an obstacle in our way!
			} while (src != null && !src.equals(dest));
			return false;
		}
		
		if (posX && !posY) {
			do { // Move diagonal down and right
				src = src.down();
				src = src.right();
				
				// see if we're at dest 
				if (src.equals(dest))
					break;
				
				// See if we're out of bounds
				if (getSquare(src) == null)
					break;
				
				// Check if new src is occupied
				if (src != null && getSquare(src).isOccupied())
					return true; // There's an obstacle in our way!
			} while (src != null && !src.equals(dest));
			return false;
		}
		
		if (!posX && posY) {
			do { // Move diagonal left and up
				src = src.up();
				src = src.left();
				
				// see if we're at dest 
				if (src.equals(dest))
					break;
				
				// See if we're out of bounds
				if (getSquare(src) == null)
					break;
				
				// Check if new src is occupied
				if (src != null && getSquare(src).isOccupied())
					return true; // There's an obstacle in our way!
			} while (src != null && !src.equals(dest));
			return false;
		}
		
		else {
			do { // Move diagonal down and left
				src = src.down();
				src = src.left();
				
				// see if we're at dest 
				if (src.equals(dest))
					break;
				
				// See if we're out of bounds
				if (getSquare(src) == null)
					break;
				
				// Check if new src is occupied
				if (src != null && getSquare(src).isOccupied())
					return true; // There's an obstacle in our way!
			} while (src != null && !src.equals(dest));
			return false;
		}

	}

	/**
	 * Move the piece at src to dest
	 * @param src
	 * @param dest
	 * @return Whether or not the move was successful 
	 */
	public boolean move(Location src, Location dest) {
		Square a = getSquare(src);
		Square b = getSquare(dest);
		Piece p1 = a.getPiece();
		Piece p2 = b.getPiece();

		if (src.equals(dest))
			return false; // Can't move to same square
		if (!a.isOccupied())
			return false; // Nothing at src!

		if (b.isOccupied() && (!p1.getTeam().equals(p2.getTeam())) && p1.isValidCapture(dest) && !hasObstacle(src, dest)) {
			// Take the piece at dest
			b.kill();
			b.setPiece(p1);
			a.setPiece(null);
			p1.setLocation(dest);

			// If it's a pawn, and we are at the end, promote it.
			if (p1 instanceof Pawn) {
				if ((p1.getTeam().equals("White") && dest.getY() == 8) || (p1.getTeam().equals("Black") && dest.getY() == 1))
					b.setPiece(p1.promote());
			}
			return true;
		}

		else if (!b.isOccupied () && p1.isValidMove(dest) && !hasObstacle(src, dest)) {
			// Move to dest
			b.setPiece(p1);
			a.setPiece(null);
			p1.setLocation(dest);
			
			// If it's a king, change king locations array
			if (p1 instanceof King)
				setKingLocation(p1.getTeam(), dest);
			

			// If it's a pawn, and we are at the end, promote it.
			if (p1 instanceof Pawn) {
				if ((p1.getTeam().equals("White") && dest.getY() == 8) || (p1.getTeam().equals("Black") && dest.getY() == 1))
					b.setPiece(p1.promote());
			}
			return true;
		}

		return false; // Invalid move
	}

	/**
	 * Check to see if any team is in check.
	 * @return an integer value corresponding to the team that is in check.
	 * If no one is in check, returns -1.
	 */
	public int isInCheck() {
		Location iter = new Location('A', 1);

		// Iterate over every piece
		for (;iter != null; iter = iter.next(max)) {
			Square s = getSquare(iter);
			if (!s.isOccupied())
				continue;
			Piece p = s.getPiece();
			Location l = p.getLocation();

			// If it's a "White" piece, check if it 
			// can capture the "Black" king
			if (p.getTeam().equals("White") && p.isValidCapture(kingLocations[GameState.BLACK]) && !hasObstacle(l, kingLocations[GameState.BLACK]))
				return GameState.BLACK;

			// If it's a "Black" piece, check if it
			// can capture the "White" king
			else if (p.getTeam().equals("Black") && p.isValidCapture(kingLocations[GameState.WHITE]) && !hasObstacle(l, kingLocations[GameState.WHITE]) )
				return GameState.WHITE;

		}
		return -1; // Didn't find anything that can capture an opposing king.
	}



}
