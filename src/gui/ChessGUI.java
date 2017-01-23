package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

import Chess.*;

import java.net.URL;
import javax.imageio.ImageIO;

public class ChessGUI {

	public boolean newPressed;
	public boolean undoPressed;
	public boolean forfeitPressed;
	public boolean scoresPressed;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private static JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][8];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private static final int QUEEN = 1, KING = 0,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5, STAR = 6, WIZARD = 7;
//    private static final int[] STARTING_ROW = {
//        ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, STAR, WIZARD
//    };
    public static final int BLACK = 0, WHITE = 1;

    public ChessGUI() {
        initializeGui();
        newPressed = false;
        undoPressed = false;
        forfeitPressed = false;
        scoresPressed = false;
    }
    
    public boolean newGame() {
    	if (newPressed) {
    		newPressed = false; // Reset boolean value
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean showScores() {
    	if (scoresPressed) {
    		scoresPressed = false;
    		return true;
    	}
    	return false;
    }
    
    public boolean undoPressed() {
    	if (undoPressed) {
    		undoPressed = false;
    		return true;
    	}
    	return false;
    }
    
    public boolean forfeit() {
    	if (forfeitPressed) {
    		forfeitPressed = false;
    		return true;
    	}
    	return false;    }

    public final void initializeGui() {
        // create the images for the chess pieces
        createImages();

        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New") {

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
				// What happens when we press "new?"
                setupNewGame();
            }
        };
        tools.add(newGameAction);
        tools.addSeparator();
        Action undoAction = new AbstractAction("Undo") {

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
				// What happens when we press "undo?"
				undoPressed = true;
            }
        };
        tools.add(undoAction);
        tools.addSeparator();
        Action forfeitAction = new AbstractAction("Surrender") {

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
				// What happens when we press "forfeit?"
				forfeitPressed = true;
			}
        };
        tools.add(forfeitAction);
        tools.addSeparator();
        Action scoresAction = new AbstractAction("Scores") {

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
				// What happens when we press "forfeit?"
				scoresPressed = true;
			}
        };
        tools.add(scoresAction);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9)) {

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
                ));
        // Set the BG to be ochre
        Color ochre = new Color(204,119,34);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);

        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
        		b.addActionListener(new EventHandler());

                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

        /*
         * fill the chess board
         */
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                    SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (9-(ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
        
    }
    
    public final JComponent getGui() {
        return gui;
    }

    private final void createImages() {
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImages[ii][jj] = bi.getSubimage(
                            jj * 64, ii * 64, 64, 64);
                }
            }
            
            /* set star images */
            File f = new File("/Users/hugh/Documents/workspace/Assignment1.2/star_black.png");
            bi = ImageIO.read(f);
            chessPieceImages[BLACK][STAR] = bi;
            
            f = new File("/Users/hugh/Documents/workspace/Assignment1.2/star_white.png");
            bi = ImageIO.read(f);
            chessPieceImages[WHITE][STAR] = bi;
            
            /* set wizard images */
            f = new File("/Users/hugh/Documents/workspace/Assignment1.2/wizard_black.png");
            bi = ImageIO.read(f);
            chessPieceImages[BLACK][WIZARD] = bi;
            
            f = new File("/Users/hugh/Documents/workspace/Assignment1.2/wizard_white.png");
            bi = ImageIO.read(f);
            chessPieceImages[WHITE][WIZARD] = bi;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static Location getSquare(JButton b) {
    	int i, j;
    	int x, y;
    	x = 0;
    	y = 0;
    	
    	
    	for (i = 0; i < 8; ++i)
    		for (j = 0; j < 8; ++j)
    			if (chessBoardSquares[i][j].equals(b)) {
    				x = i;
    				y = j;
    			}
  
    	return new Location(x + 1, 8 - y);
    			
    }

    public void setSquare(Square s) {
    	Location l = s.getLocation();
  
    	int team;
    	int type;
    	
    	int x = l.getXInt();
    	int y = 7 - l.getYInt();
    	boolean b = x < 8 && y < 8;
    	if (!b) {
    		return;
    	}
    	
    	if (s.getPiece() == null) {
    		chessBoardSquares[x][y].setIcon(null);
    		return;
    	}
    	if (s.getPiece().getTeam().equals("Black"))
    		team = BLACK;
    	else
    		team = WHITE;
    	if (s.getPiece() instanceof Bishop)
    		type = BISHOP;
    	else if (s.getPiece() instanceof King)
    		type = KING;
    	else if (s.getPiece() instanceof Knight)
    		type = KNIGHT;
    	else if (s.getPiece() instanceof Pawn)
    		type = PAWN;
    	else if (s.getPiece() instanceof Queen)
    		type = QUEEN;
    	else if (s.getPiece() instanceof Rook)
    		type = ROOK;
    	else if (s.getPiece() instanceof Wizard)
    		type = WIZARD;
    	else
    		type = STAR;
    	
    	chessBoardSquares[x][y].setIcon(new ImageIcon(chessPieceImages[team][type]));
    }
    
    
    /**
     * Initializes the icons of the initial chess board piece places
     */
    private final void setupNewGame() {
		newPressed = true;

    	
        // set up the black pieces
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][0].setIcon(new ImageIcon(
//                    chessPieceImages[BLACK][STARTING_ROW[ii]]));
//        }
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][1].setIcon(new ImageIcon(
//                    chessPieceImages[BLACK][PAWN]));
//        }
//        // set up the white pieces
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][6].setIcon(new ImageIcon(
//                    chessPieceImages[WHITE][PAWN]));
//        }
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][7].setIcon(new ImageIcon(
//                    chessPieceImages[WHITE][STARTING_ROW[ii]]));
//        }
    }
}