package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import enums.CellEnum;

/**
 * the view of the maze
 * 
 * @author verena
 *
 */
public class MazeView extends JPanel {

	private int GAME_W = 500; //TODO!≥!
	private int GAME_H = 500;
	private Color BG_COLOR = new Color(0, 30, 100);

	private Color bg;

	private int rows;
	private int cols;

	public MazeView(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;

		initView();
	}

	public void initView() {
		this.setLayout(new GridLayout(rows, cols));
		// this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// this.setPreferredSize(new Dimension(GAME_W, GAME_H));
		this.setBackground(BG_COLOR);

	}

	public void fillEmptyGrid() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				CellView cellView = new CellView(CellEnum.EMPTY);
			}
		}
	}

	public void addPlayer(PlayerView player) {
		this.add(player);
	}

}
