package chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Dimension dim, TYPE type, SIDE side) {
		super(dim, type, side);
		if (this.getSide() == SIDE.black) {

			this.setPath("src/50px-Chess_rdt45.svg.png");

		} else if (this.getSide() == SIDE.white) {

			this.setPath("src/Chess_rlt45.svg.png");

		}
	}

	@Override
	public boolean checkIfValidPath(int finalX, int finalY, ArrayList<Piece> pieces) {// works fine after rework,
		boolean check = false;
		if (this.getX() == finalX) {
			if (this.getY() < finalY) {
				for (int i = this.getY() + 1; i <= finalY; i++) {
					for (Piece temp : pieces) {

						if (temp.getDim().equals(new Dimension(finalX, i)) && i != finalY) {
							return false;
						} else if (temp.getDim().equals(new Dimension(finalX, i)) && i == finalY
								&& !(this.getSide().equals(temp.getSide())))
							return true;
					}
					if (i == finalY) {
						for (Piece temp : pieces) {
							if (temp.getDim().equals(new Dimension(finalX, i)))
								return false;
						}
						return true;
					}
				}
			} else if (this.getY() > finalY) {
				for (int i = this.getY() - 1; i >= finalY; i--) {
					for (Piece temp : pieces) {
						if (temp.getDim().equals(new Dimension(finalX, i)) && i != finalY) {
							return false;
						} else if (temp.getDim().equals(new Dimension(finalX, i)) && i == finalY
								&& !(this.getSide().equals(temp.getSide())))
							return true;

					}
					if (i == finalY) {
						for (Piece temp : pieces) {
							if (temp.getDim().equals(new Dimension(finalX, i)))
								return false;
						}
						return true;
					}
				}
			}

		} else if (this.getY() == finalY) {// this part of code is invalid;1/2 of this code works properly
			if (this.getX() < finalX) {// here it works badly
				for (int i = this.getX() + 1; i <= finalX; i++) {
					for (Piece temp : pieces) {
						if (temp.getDim().equals(new Dimension(i, finalY)) && i != finalX)
							return false;
						else if (temp.getDim().equals(new Dimension(i, finalY)) && i == finalX
								&& !(this.getSide().equals(temp.getSide())))
							return true;

					}
					if (i == finalX) {
						for (Piece temp : pieces) {
							if (temp.getDim().equals(new Dimension(i, finalY)))
								return false;
						}
						return true;
					}

				}
			} else if (this.getX() > finalX) {
				for (int i = this.getX() - 1; i >= finalX; i--) {
					for (Piece temp : pieces) {
						if (temp.getDim().equals(new Dimension(i, finalY)) && i != finalX)
							return false;
						else if (temp.getDim().equals(new Dimension(i, finalY)) && i == finalX
								&& !(this.getSide().equals(temp.getSide())))
							return true;

					}
					if (i == finalX) {
						for (Piece temp : pieces) {
							if (temp.getDim().equals(new Dimension(i, finalY)))
								return false;
						}
						return true;
					}
				}
			}
		}

		return check;
	}

	@Override
	public void drawPossiblePath(ArrayList<Piece> pieces, Graphics g) {int i = 0;

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

	@Override
	public ArrayList<Dimension> getPossiblePath(ArrayList<Piece> pieces) {
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
