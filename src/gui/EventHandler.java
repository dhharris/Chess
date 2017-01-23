package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Chess.Board;
import Chess.Chess;
import Chess.GameState;
import Chess.Location;
import Chess.Piece;
import Chess.Square;

/**
 * 
 * @author dhharri2
 * 
 * Handles move events
 *
 */
public class EventHandler implements ActionListener {
	private static JButton pieceToMoveButton = null;   //variable that persists between actionPerformed calls
	private static Location src, dest;
	private static Board b;
	private static GameState state;
	private static Chess game;
	private static boolean updated = false;

	public static void setBoard(Board board) {
		b = board;
	}

	public static Board getBoard() {
		return b;
	}

	public static void setGameState(GameState s) {
		state = s;
	}

	public static void setChess(Chess g) {
		game = g;
	}


	public void actionPerformed(ActionEvent actionEvent)
	{
		JButton button = (JButton)actionEvent.getSource();

		if (!state.isRunning())
			return; // Can't do anything when it isn't running

		if (updated) {
			state.update(b);
			updated = false;
		}


		if (b == null)
			return; // Game hasn't started yet

		if (pieceToMoveButton == null) {   //if this button press is selecting the piece to move


			Location l = ChessGUI.getSquare(button);
			Square s = b.getSquare(l);

			// Make sure user is clicking on an occupied square
			if (!s.isOccupied())
				return;

			Piece p = s.getPiece();

			// Make sure that the user is pressing the right team's piece
			if (state.getTurn() == GameState.BLACK && !p.getTeam().equals("Black"))
				return;

			if (state.getTurn() == GameState.WHITE && !p.getTeam().equals("White"))
				return;


			//save the button used in piece selection for later use
			pieceToMoveButton = button;
			src = l;

		}
		else {       //if this button press is selecting where to move

			//move the image to the new button (the one just pressed)
			boolean moved = false;
			int team = state.getTurn();
			dest = ChessGUI.getSquare(button);
			//            System.out.println("src = " + src + ", dest =" + dest);

			if (b != null) {
				// Perform the move action
				moved = b.move(src, dest);
			}


			// Move to next turn
			if (moved) {

				state.setCheck(b);
				// At the end of our turn, we should not be in check
				if (state.inCheck(team))
					b = state.undo();


				updated = true;
				state.changeTurn(b);
				game.update();
			}


			pieceToMoveButton = null;    //makes the next button press a piece selection

		}
	}



}
