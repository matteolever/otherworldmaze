package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartView extends JPanel {

	JButton startButton;
	JButton editButton;

	public StartView(MainView mainView) {
		initView();
		mainView.add(this);
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel nameLabel = new JLabel("Night Maze");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		// TODO set Font nameLabel.setFont(new Fon);
		this.startButton = new JButton("Start Game");
		this.editButton = new JButton("Create Game");

		this.add(nameLabel, BorderLayout.NORTH);
		this.add(this.startButton, BorderLayout.EAST);
		this.add(this.editButton, BorderLayout.WEST);
	}

	public JButton getStartButton() {
		return this.startButton;
	}

	public JButton getEditButton() {
		return this.editButton;
	}

}
