package view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class StartView extends JPanel {
	Controller controller;

	JButton startButton;
	JButton editButton;

	public StartView(Controller controller) {
		this.controller = controller;
		initView();
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel nameLabel = new JLabel("Night Maze");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		// TODO set Font nameLabel.setFont(new Fon);
		this.startButton = new JButton("Start Game");
		this.startButton.addMouseListener(this.startButtonListener);

		this.editButton = new JButton("Create Game Board");
		this.editButton.addMouseListener(this.createButtonListener);

		this.add(nameLabel, BorderLayout.NORTH);
		this.add(this.startButton, BorderLayout.EAST);
		this.add(this.editButton, BorderLayout.WEST);
	}

	private MouseAdapter startButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			StartView.this.controller.startMaze();
		}
	};

	private MouseAdapter createButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			StartView.this.controller.startEdit();
		}
	};

	public JButton getStartButton() {
		return this.startButton;
	}

	public JButton getEditButton() {
		return this.editButton;
	}

}
