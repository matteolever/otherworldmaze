package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
	JButton selectButton;

	public StartView(Controller controller) {
		this.controller = controller;
		initView();
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		this.setBackground(Color.WHITE);
		this.setOpaque(true);

		JLabel nameLabel = new JLabel("MAZE");
		nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 20f));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		this.startButton = new JButton("Start Game");
		this.startButton.addMouseListener(this.startButtonListener);

		this.editButton = new JButton("Create Game Board");
		this.editButton.addMouseListener(this.createButtonListener);
		
		this.selectButton = new JButton("Select a Maze");
		this.selectButton.addMouseListener(this.selectButtonListener);

		this.add(nameLabel, BorderLayout.NORTH);
		this.add(this.startButton, BorderLayout.EAST);
		this.add(this.editButton, BorderLayout.WEST);
		this.add(this.selectButton, BorderLayout.CENTER);
	}

	private MouseAdapter startButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			StartView.this.controller.startMaze(null);
		}
	};

	private MouseAdapter createButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			StartView.this.controller.startEdit();
		}
	};
	
	private MouseAdapter selectButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			StartView.this.controller.startSelect();
		}
	};

	public JButton getStartButton() {
		return this.startButton;
	}

	public JButton getEditButton() {
		return this.editButton;
	}

}
