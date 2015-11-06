package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * the view of the maze
 * 
 * @author verena
 *
 */
public class MazeView extends JPanel {

	private int GAME_W = 500;
	private int GAME_H = 500;
	private Color BG_COLOR = new Color(0, 30, 100);

	private Color bg;

	public MazeView() {
		initView();
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		// this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setPreferredSize(new Dimension(this.GAME_W, this.GAME_H));
		this.setBackground(this.BG_COLOR);
	}

	public void addPlayer(PlayerView player) {
		this.add(player);
	}

}
