package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

		// new FlowLayout(FlowLayout.LEADING)
		editPanel = new JPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.LINE_AXIS));
		previewMaze = new MazeView();

		JPanel obstaclePanel = new JPanel(new GridLayout(4, 2));
		obstaclePanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		JLabel obstaclesLabel = new JLabel("Obstacles");
		obstaclePanel.add(obstaclesLabel);

		JPanel doorsPanel = new JPanel(new FlowLayout());
		doorsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		JLabel doorsLabel = new JLabel("Doors and Keys");
		doorsLabel.setHorizontalAlignment(FlowLayout.LEFT);
		doorsPanel.add(doorsLabel, FlowLayout.LEFT);

		editPanel.add(obstaclePanel);
		editPanel.add(doorsLabel);

		this.add(editPanel);
		this.add(previewMaze, BorderLayout.EAST);

	}

}
