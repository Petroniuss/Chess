package chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class King extends Piece {

	public King(Dimension dim, TYPE type, SIDE side) {
		super(dim, type, side);
		if (this.side == SIDE.white) {
			this.setPath("src/Chess_klt45.svg.png");
		} else if (this.side == SIDE.black) {
			this.setPath("src/45px-Chess_kdt45.svg.png");
		}
	}

	@Override
	public boolean checkIfValidPath(int finalX, int finalY, ArrayList<Piece> pieces) {
		if ((this.getDim().equals(new Dimension(finalX - 1, finalY - 1)))
				|| (this.getDim().equals(new Dimension(finalX, finalY - 1)))
				|| (this.getDim().equals(new Dimension(finalX + 1, finalY - 1)))
				|| (this.getDim().equals(new Dimension(finalX - 1, finalY)))
				|| (this.getDim().equals(new Dimension(finalX + 1, finalY)))
				|| (this.getDim().equals(new Dimension(finalX - 1, finalY + 1)))
				|| (this.getDim().equals(new Dimension(finalX, finalY + 1)))
				|| (this.getDim().equals(new Dimension(finalX + 1, finalY + 1)))) {
			for (Piece temp : pieces) {
				if (temp.getDim().equals(new Dimension(finalX, finalY)) && temp.getSide().equals(this.getSide()))
					return false;
			}
			return true;
		} else
			return false;

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

	public void checkIfKingEndangered() {
	}

	@Override
	public boolean checkIfKingEndangered(ArrayList<Piece> pieces) {
		Dimension X = this.getDim();
		for (Piece temp : pieces) {
			for (Dimension dim : temp.getPossiblePath(pieces)) {
				if (X.equals(dim))
					return true;
			}
		}
		return false;
	}

}
