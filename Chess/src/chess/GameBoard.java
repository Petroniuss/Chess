package chess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class GameBoard extends JFrame {

	public static int WIDTH = 640;
	public static int HEIGHT = 670;
	public static int mouseX, mouseY, finalX = 0, finalY = 0;
	int i = 0;

	public static void main(String[] args) {

		new GameBoard();

	}

	public GameBoard() {
		Dimension d = new Dimension(WIDTH, HEIGHT);

		this.setTitle("PiedPiper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(d);
		this.setPreferredSize(d);
		this.setMaximumSize(d);
		this.setMaximumSize(d);
		this.setResizable(false);

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				mouseX = clamp(e.getX());
				mouseY = clamp(e.getY());

				System.out.print("mouseX = " + mouseX);
				System.out.println(" ,mouseY = " + mouseY);

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		GameDrawingPanel gamePanel = new GameDrawingPanel();

		this.add(gamePanel, BorderLayout.CENTER);

		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

		executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);

		this.setVisible(true);

	}

	public int clamp(int mousePos) {

		return mousePos / 80;

	}

}
