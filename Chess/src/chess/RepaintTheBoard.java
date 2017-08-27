package chess;

public class RepaintTheBoard implements Runnable {

	GameBoard theBoard;

	public RepaintTheBoard(GameBoard theBoard) {

		this.theBoard = theBoard;
	}

	public void run() {
		theBoard.repaint();

	}

}
