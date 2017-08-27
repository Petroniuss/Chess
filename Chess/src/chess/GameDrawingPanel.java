package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GameDrawingPanel extends JComponent {
	String path = "F:/User/Desktop/ChessPieces/";
	public static ArrayList<Piece> pieces = new ArrayList<>();

	int k = 0;
	int i = 0;
	boolean selected = false;
	Turn turn = Turn.white;

	public GameDrawingPanel() {
		setUpPieces();

	}

	public void paint(Graphics g) {

		drawBackground(g);
		g.setColor(Color.red);

		for (Piece temporaryPiece : pieces) {
			if (temporaryPiece.checkIfKingEndangered(pieces)) {
				g.drawString("CHECK", 100, 200);
			}
			g.drawImage(temporaryPiece.getIm(temporaryPiece.getPath()), temporaryPiece.getX() * 80,
					temporaryPiece.getY() * 80, 80, 80, null);
			if (temporaryPiece.getDim().equals(new Dimension(GameBoard.mouseX, GameBoard.mouseY))
					&& ((temporaryPiece.getSide().equals(SIDE.white) && turn.equals(Turn.white))
							|| (temporaryPiece.getSide().equals(SIDE.black) && turn.equals(Turn.black)))) {
				for (Piece temp : pieces) {
					temp.setSelected(false);
				}
				temporaryPiece.setSelected(true);
				selected = true;
				temporaryPiece.drawPossiblePath(pieces, g);

			}
			if (temporaryPiece.isSelected()) {
				ArrayList<Dimension> possPaths = new ArrayList<>();
				possPaths.clear();
				possPaths.addAll(temporaryPiece.getPossiblePath(pieces));
				// k++;
				// if(k==1000){System.out.println(possPaths);k=0;temporaryPiece.setSelected(false);}//debugging
				// purposes
				for (Dimension path : possPaths) {
					if (path.equals(new Dimension(GameBoard.mouseX, GameBoard.mouseY))) {

						temporaryPiece.setSelected(false);
						turn = (turn.equals(Turn.white)) ? Turn.black : Turn.white;
						System.out.println(turn);
						temporaryPiece.setDim(new Dimension(GameBoard.mouseX, GameBoard.mouseY));
						for (Piece temp : pieces) {
							if (temp.getDim().equals((new Dimension(GameBoard.mouseX, GameBoard.mouseY)))
									&& !temp.equals(temporaryPiece)) {
								GameBoard.mouseX = -1;
								GameBoard.mouseY = -1;
								pieces.remove(temp);
							}
						}

						System.out.println(GameBoard.mouseX);

					}
				}
			}
			/*
			 * Things to be done: -U've to make game-over; -Only one Piece can be selected
			 * at time, currently it isn't;it's a bug to be fixed;done -We can think of
			 * adding some features later on; -Possibly check why there are some errors
			 * popping up when piece is deleted; -And there's eventually this bug that
			 * selects wrong fields -Make players take turns alternately;there's some bug
			 * with black pieces;fixed;
			 */

		}

	}

	public void drawBackground(Graphics g) {

		g.setColor(Color.GRAY);
		for (int j = 0; j < 8; j++) {
			for (int i = (j % 2 == 0) ? 1 : 0; i < 8; i += 2) {
				g.fillRect(i * 80, j * 80, 80, 80);
			}
		}
	}

	public void setUpPieces() {
		while (i < 8) {
			if (i == 0) {

				pieces.add(new Rook(new Dimension(i, i), TYPE.rook, SIDE.white));
				pieces.add(new Rook(new Dimension(i, 7), TYPE.rook, SIDE.black));
				pieces.add(new Rook(new Dimension(7, 0), TYPE.rook, SIDE.white));
				pieces.add(new Rook(new Dimension(7, 7), TYPE.rook, SIDE.black));

				pieces.add(new Horse(new Dimension(1, 0), TYPE.horse, SIDE.white));
				pieces.add(new Horse(new Dimension(6, 0), TYPE.horse, SIDE.white));
				pieces.add(new Horse(new Dimension(1, 7), TYPE.horse, SIDE.black));
				pieces.add(new Horse(new Dimension(6, 7), TYPE.horse, SIDE.black));

				pieces.add(new Bishop(new Dimension(2, 0), TYPE.bishop, SIDE.white));
				pieces.add(new Bishop(new Dimension(5, 0), TYPE.bishop, SIDE.white));
				pieces.add(new Bishop(new Dimension(5, 7), TYPE.bishop, SIDE.black));
				pieces.add(new Bishop(new Dimension(2, 7), TYPE.bishop, SIDE.black));

				pieces.add(new Queen(new Dimension(4, 0), TYPE.queen, SIDE.white));
				pieces.add(new Queen(new Dimension(4, 7), TYPE.queen, SIDE.black));

				pieces.add(new King(new Dimension(3, 0), TYPE.king, SIDE.white));// tests
				pieces.add(new King(new Dimension(3, 7), TYPE.king, SIDE.black));// tests
			}
			pieces.add(new Pawn(new Dimension(i, 1), TYPE.pawn, SIDE.white));
			pieces.add(new Pawn(new Dimension(i, 6), TYPE.pawn, SIDE.black));
			i++;
		}
	}

}
