package chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.lang.Math;

public class Queen extends Piece {

	public Queen(Dimension dim, TYPE type, SIDE side) {
		super(dim, type, side);
		if (this.getSide() == SIDE.white) {
			this.setPath("src/50px-Chess_qlt45.svg.png");
		} else if (this.getSide() == SIDE.black) {
			this.setPath("src/50px-Chess_qdt45.svg.png");
		}

	}

	@Override
	public boolean checkIfValidPath(int finalX, int finalY, ArrayList<Piece> pieces) {// surprisingly this one's worked
																						// instantly :D
		boolean check = false;
		// if(Math.abs(this.getX()-finalX)!=Math.abs(this.getY()-finalY) || !(//it
		// allows diagonal moves and vertical and moves
		// this.getX()!=finalX && this.getY()==finalY) || !(this.getX()==finalX &&
		// this.getY()!=finalY) )return false;//this code..

		if (Math.abs(this.getX() - finalX) == Math.abs(this.getY() - finalY)) {// here goes code from bishop
			check = false;
			if (Math.abs(this.getX() - finalX) != Math.abs(this.getY() - finalY))
				return false;// only diagonals moves allowed
			int chance = 0;
			if (finalX < this.getX() && finalY < this.getY())
				chance = 1;
			else if (finalX > this.getX() && finalY < this.getY())
				chance = 2;
			else if (finalX < this.getX() && finalY > this.getY())
				chance = 3;
			else if (finalX > this.getX() && finalY > this.getY())
				chance = 4;
			else
				return false;// actually i don't need it
			switch (chance) {
			case 1:// this one does eventually works!!
				for (int i = 1; i <= this.getX() - finalX; i++) {
					for (Piece temp : pieces) {
						if (temp.getDim().equals(new Dimension(this.getX() - i, this.getY() - i))
								&& i != this.getX() - finalX) {
							return false;
						}
						if (temp.getDim().equals(new Dimension(this.getX() - i, this.getY() - i))
								&& i == this.getX() - finalX) {
							if (!temp.getSide().equals(this.getSide())) {
								return true;
							}
						}
					}
					if (i == this.getX() - finalX) {
						for (Piece temp : pieces) {
							if (temp.getDim().equals(new Dimension(this.getX() - i, this.getY() - i)))
								return false;
						}
						return true;
					}
				}
				break;
			case 2: // this one seems to work
				for (int i = 1; i <= finalX - this.getX(); i++) {
					for (Piece temp : pieces) {
						if (temp.getDim().equals(new Dimension(this.getX() + i, this.getY() - i))
								&& i != finalX - this.getX()) {
							return false;
						}
						if (temp.getDim().equals(new Dimension(this.getX() + i, this.getY() - i))
								&& i == finalX - this.getX()) {
							if (!temp.getSide().equals(this.getSide())) {
								return true;
							}
						}
					}
					if (i == finalX - this.getX()) {
						for (Piece temp : pieces) {
							if (temp.getDim().equals(new Dimension(this.getX() + i, this.getY() - i)))
								return false;
						}
						return true;
					}
				}
				break;
			case 3:
				for (int i = 1; i <= this.getX() - finalX; i++) {
					for (Piece temp : pieces) {
						if (temp.getDim().equals(new Dimension(this.getX() - i, this.getY() + i))
								&& i != this.getX() - finalX) {
							return false;
						}
						if (temp.getDim().equals(new Dimension(this.getX() - i, this.getY() + i))
								&& i == this.getX() - finalX) {
							if (!temp.getSide().equals(this.getSide())) {
								return true;
							}
						}
					}
					if (i == this.getX() - finalX) {
						for (Piece temp : pieces) {
							if (temp.getDim().equals(new Dimension(this.getX() - i, this.getY() + i)))
								return false;
						}
						return true;
					}
				}
				break;
			case 4:
				for (int i = 1; i <= finalX - this.getX(); i++) {
					for (Piece temp : pieces) {
						if (temp.getDim().equals(new Dimension(this.getX() + i, this.getY() + i))
								&& i != finalX - this.getX()) {
							return false;
						}
						if (temp.getDim().equals(new Dimension(this.getX() + i, this.getY() + i))
								&& i == finalX - this.getX()) {
							if (!temp.getSide().equals(this.getSide())) {
								return true;
							}
						}
					}
					if (i == finalX - this.getX()) {
						for (Piece temp : pieces) {
							if (temp.getDim().equals(new Dimension(this.getX() + i, this.getY() + i)))
								return false;
						}
						return true;
					}
				}
				break;
			default:
				return false;

			}
			return check;
		} else if ((this.getX() != finalX && this.getY() == finalY)
				|| (this.getX() == finalX && this.getY() != finalY)) {// here goes code from rook
			check = false;
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
