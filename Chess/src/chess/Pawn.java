package chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pawn extends Piece {

	public Pawn(Dimension dim, TYPE type, SIDE side) {

		super(dim, type, side);
		if (this.getSide() == SIDE.white) {
			this.setPath("src/Chess_plt45.svg.png");
		} else if (this.getSide() == SIDE.black) {
			this.setPath("src/Chess_pdt45.svg.png");
		}

	}

	public boolean checkIfValidPath(int finalX, int finalY, ArrayList<Piece> pieces) {

		boolean check = false;
		int whichSide = (this.side.equals(SIDE.white)) ? 1 : -1;
		if (this.getSide() == SIDE.white) {
			if (this.getX() == finalX && this.getY() + 1 == finalY) {
				for (Piece temp : pieces) {
					if (temp.getDim().equals(new Dimension(finalX, finalY)))
						return false;
				}
				return true;
			} else if (this.getX() == finalX && this.getY() == 1 && this.getY() + 2 == finalY) {
				for (Piece temp : pieces) {
					if (temp.getDim().equals(new Dimension(finalX, finalY))
							|| temp.getDim().equals(new Dimension(finalX, finalY - 1)))
						return false;
				}
				return true;
			}

		} else if (this.getSide() == SIDE.black) {
			if (this.getX() == finalX && this.getY() - 1 == finalY) {
				for (Piece temp : pieces) {
					if (temp.getDim().equals(new Dimension(finalX, finalY)))
						return false;
				}
				return true;
			} else if (this.getX() == finalX && this.getY() == 6 && this.getY() - 2 == finalY) {
				for (Piece temp : pieces) {
					if (temp.getDim().equals(new Dimension(finalX, finalY))
							|| temp.getDim().equals(new Dimension(finalX, finalY + 1)))
						return false;
				}
				return true;
			}

		}

		for (Piece temp : pieces)// this loop checks if u can take a position already taken by a piece of enemy
		{
			if (temp.getDim().equals(new Dimension(finalX, finalY))) {
				if (!temp.getSide().equals(this.getSide())) {
					if ((temp.getX() == this.getX() - 1 || temp.getX() == this.getX() + 1)
							&& temp.getY() == this.getY() + whichSide) {
						check = true;
					}
				}
			}
		}

		return check;
	}

	public void drawPossiblePath(ArrayList<Piece> pieces, Graphics g) {// draw
		int i = 0;

		while (i < 8) {
			for (int j = 0; j < 8; j++) {
				if (this.checkIfValidPath(i, j, pieces)) {
					if (this.getSide().equals(SIDE.white))
						g.drawImage(this.getIm("src/skyBall.png"), i * 80, j * 80, 80, 80,
								null);
					else if (this.getSide().equals(SIDE.black))
						g.drawImage(this.getIm("src/lightning.png"), i * 80, j * 80, 80, 80,
								null);
				}

			}
			i++;
		}

	}

	public ArrayList<Dimension> getPossiblePath(ArrayList<Piece> pieces) {// get Array with possible coordinates to move
																			// in
		ArrayList<Dimension> dims = new ArrayList<>();
		int i = 0;
		while (i < 8) {
			for (int j = 0; j < 8; j++) {
				if (this.checkIfValidPath(i, j, pieces))
					dims.add(new Dimension(i, j));
			}
			i++;
		}

		return dims;
	}

	@Override
	public boolean checkIfKingEndangered(ArrayList<Piece> pieces) {
		// TODO Auto-generated method stub
		return false;
	}

}
