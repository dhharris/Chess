package Chess;
/**
 * The Game of Chess, written in the esteemed Java programming language.
 * Created for CS242 Fall 2016
 * @author dhharri2
 */


import javax.swing.*;

import gui.*;


/**
 * Main chess class
 * @author dhharri2
 *
 */
public class Chess {

	private int numTeams;
	String teams[];
	GameState state;
	static ChessGUI cg;
	static JFrame f;
	Board b;
	private int[] scores;



	public Chess() {
		numTeams = 2;
		teams = new String[numTeams];
		teams[0] = "White";
		teams[1] = "Black";
		cg = new ChessGUI();
		state = null;
		b = null;
		scores = new int[2];
		scores[GameState.BLACK] = 0;
		scores[GameState.WHITE] = 0;

		initializeWindow();
		initializeEventHandler();
	}

	/**
	 * Changes header to whatever is in parameter msg
	 * @param msg
	 */
	public static void printMessage(String msg) {
		f.setTitle(msg);
	}

	/**
	 * Initializes game window
	 */
	public void initializeWindow() {
		f = new JFrame("Welcome to Chess");
		f.add(cg.getGui());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationByPlatform(true);
		f.pack();
		f.setMinimumSize(f.getSize());
		f.setVisible(true);
	}

	/**
	 * Initializes event handler object
	 */
	public void initializeEventHandler() {
		// Make board and GameState visible to event handler
		EventHandler.setBoard(b);
		EventHandler.setGameState(state);
		EventHandler.setChess(this);
	}

	/**
	 * Update GUI by drawing the pieces on the board
	 */
	public void updateGUI() {
		Location l = new Location();

		while (l != null) {
			Square s = b.getSquare(l);
			cg.setSquare(s);
			l = l.next(b.max);
		}
	}

	/**
	 * Get board from EventHandler and update the Chess object's variable
	 */
	public void updateBoard() {
		b = EventHandler.getBoard();
	}

	/**
	 * Perform all updates we need to do in the game loop
	 */
	public void update() {
		updateBoard();
		updateGUI();
	}

	/**
	 * Reset the game to the starting board and game state
	 */
	public void reset() {
		b = new Board();
		state = new GameState(b);
		initializeEventHandler();
		update();
		printMessage("White's turn!");
	}

	/**
	 * 
	 * @return Whether the game is running
	 */
	public boolean running() {
		return state == null || state.isRunning();
	}

	/**
	 * 
	 * @return Whether new game was pressed
	 */
	public boolean newGame() {
		return cg.newGame();
	}

	/**
	 * 
	 * @return Whether undo was pressed
	 */
	public boolean undoPressed() {
		return cg.undoPressed();
	}

	/**
	 * 
	 * @return Whether surrender was pressed
	 */
	public boolean forfeitPressed() {
		return cg.forfeit();
	}

	/**
	 * 
	 * @return Whether scores button was pressed
	 */
	public boolean showScoresPressed() {
		return cg.showScores();
	}

	/**
	 * Forfeits the game and updates score counter
	 */
	public void forfeit() {
		int turn = state.getTurn();
		if (turn == GameState.WHITE) {
			printMessage("Black wins!");
			scores[GameState.BLACK]++;
		}
		else {
			printMessage("White wins!");
			scores[GameState.WHITE]++;
		}
		state.forfeit();
	}

	/**
	 * Display dialog box with Black and White's scores
	 */
	public void showScores() {
		JOptionPane.showMessageDialog(f, "White: " + scores[GameState.WHITE] + "\nBlack: " + scores[GameState.BLACK]);
	}

	/**
	 * Main method for Chess game
	 * @param args
	 */
	public static void main(String[] args) {
		Chess game = new Chess();	
		printMessage("Welcome to Chess!");

		/*
		 * Game loop
		 * Checks to see if any buttons were pressed
		 * Every other event is taken care of by the EventHandler class
		 */
		while (true) {
			// Reset game?
			if (game.newGame())
				game.reset();
			
			// Undo move?
			if (game.undoPressed()) {
				// Make sure undo is not null
				Board b = game.state.undo();
				if (b != null) {
					game.b = game.state.undo();
					game.initializeEventHandler();
					game.update();
				}
			}
			
			// Surrender?
			if (game.forfeitPressed()) {
				game.forfeit();
				game.showScores();
			}
			
			// Show scores?
			if (game.showScoresPressed())
				game.showScores();
		}
	}
}
