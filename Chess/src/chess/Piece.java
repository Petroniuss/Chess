package chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Piece {

	Dimension dim;
	TYPE type;
	SIDE side;
	Image im;
	String path;
	boolean isSelected;

	public Piece(Dimension dim, TYPE type, SIDE side) {

		this.dim = dim;
		this.type = type;
		this.side = side;

	}

	public int getX() {

		return (int) dim.getWidth();
	}

	public void setX(int X) {
		dim.setSize(X, dim.getHeight());
	}

	public int getY() {

		return (int) dim.getHeight();
	}

	public void setY(int Y) {

		dim.setSize(dim.getWidth(), Y);
	}

	public TYPE getType() {

		return type;
	}

	public Image getIm(String path) {

		Image tempImage = null;
		tempImage = new ImageIcon(path).getImage();
		return tempImage;
	}

	public SIDE getSide() {
		return side;
	}

	public void setSide(SIDE side) {
		this.side = side;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public abstract boolean checkIfValidPath(int finalX, int finalY, ArrayList<Piece> pieces);

	public abstract boolean checkIfKingEndangered(ArrayList<Piece> pieces);

	public abstract void drawPossiblePath(ArrayList<Piece> pieces, Graphics g);

	public abstract ArrayList<Dimension> getPossiblePath(ArrayList<Piece> pieces);

	public String getPath() {
		return path;
	}

	public boolean isSelected() {

		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
