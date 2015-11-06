package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateMazeView extends JPanel {

	JPanel editPanel;
	MazeView previewMaze;

	public CreateMazeView() {
		initView();
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.editPanel = new JPanel(new BoxLayout(this.editPanel, BoxLayout.Y_AXIS));
		this.previewMaze = new MazeView();

		JPanel obstaclePanel = new JPanel(new GridLayout(4, 2));

		JLabel obstacle;

		this.add(this.editPanel, BorderLayout.WEST);
		this.add(this.previewMaze, BorderLayout.EAST);

	}

}
