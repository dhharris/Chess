package Chess;

import java.util.Stack;

/**
 * 
 * @author dhharri2 Contains all relevant information about the state of the
 *         game
 */
public class GameState {
	public static final int BLACK = 0, WHITE = 1;

	Stack<Board> s;
	boolean checkmate;
	boolean stalemate;
	boolean forfeited;
	boolean checkBlack;
	boolean checkWhite;
	int turn;

	public GameState(Board b) {
		s = new Stack<Board>();
		s.push(new Board(b));
		checkmate = false;
		stalemate = false;
		checkBlack = false;
		checkWhite = false;
		forfeited = false;
		turn = WHITE; // White moves first
	}

	public int getTurn() {
		return this.turn;
	}

	/**
	 * Update function is called every turn Pushes the previous board on the
	 * stack in case we need to undo, and checks Check conditions
	 * 
	 * @param b
	 */
	public void update(Board b) {
		setCheck(b);
		s.push(new Board(b));
	}

	/**
	 * Signal that the game is over
	 */
	public void forfeit() {
		forfeited = true;
	}

	/**
	 * Undo's the last move by popping off the stack
	 * 
	 * @return The previous move's game board
	 */
	public Board undo() {
		if (!s.empty()) {
			Board b = s.pop();
			changeTurn(b); // Reset turn to previous move

			setCheck(b);
			return b; // Return last turn's board
		}
		return null;
	}

	/**
	 * Changes the turn, and prints helpful information
	 * 
	 * @param b
	 */
	public void changeTurn(Board b) {
		setCheck(b);

		if (turn == WHITE)
			turn = BLACK;
		else
			turn = WHITE;

		if (checkBlack)
			Chess.printMessage("Black is in check!");
		else if (checkWhite)
			Chess.printMessage("White is in check!");

		else if (turn == WHITE)
			Chess.printMessage("White's turn!");
		else
			Chess.printMessage("Black's turn!");
	}

	/**
	 * 
	 * @return Whether or not the game is running
	 */
	public boolean isRunning() {
		return !checkmate && !stalemate && !forfeited;
	}

	/**
	 * Function to determine whether (team) is in check
	 * 
	 * @param team
	 * @return boolean value
	 */
	public boolean inCheck(int team) {
		if (team == BLACK)
			return checkBlack;
		return checkWhite;
	}

	/**
	 * Check for check conditions using the isInCheck method from the Board
	 * class Sets appropriate boolean variables afterwards
	 * 
	 * @param b
	 *            The board to be tested
	 */
	public void setCheck(Board b) {
		int c = b.isInCheck();

		if (c == BLACK)
			checkBlack = true;
		else if (c == WHITE)
			checkWhite = true;
		else {
			checkBlack = false;
			checkWhite = false;
		}
	}

	/**
	 * Checkmate is implemented with the surrender button. So this function
	 * doesn't do anything.
	 */
	public void setCheckmate() {
		checkmate = true;
	}
}
