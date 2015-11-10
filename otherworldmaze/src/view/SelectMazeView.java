package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SelectMazeController;

/**
 * View class for the maze selection
 */
public class SelectMazeView extends JPanel {

	private List<String> mazelist;
	private SelectMazeController controller;
	private JLabel selectMazeLabel;
	private JPanel listpanel;

	/**
	 * Creates the view class
	 * @param controller The controller of the view class
	 */
	public SelectMazeView(SelectMazeController controller){

		this.controller = controller;
		this.mazelist = this.controller.getMazelist();
		this.setPreferredSize(new Dimension(500,500));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.selectMazeLabel = new JLabel("Select a maze");
		this.selectMazeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		this.selectMazeLabel.setForeground(Color.BLACK);
		this.selectMazeLabel.setOpaque(false);
		this.selectMazeLabel.setPreferredSize(new Dimension(500,70));
		this.add(this.selectMazeLabel);

		this.listpanel = new JPanel();
		this.listpanel.setOpaque(false);
		this.listpanel.setLayout(new FlowLayout());
		this.add(this.listpanel);

		int i = 0;
		while (i < this.mazelist.size()){
			String mazename = this.mazelist.get(i);
			JButton mazeButton = new JButton(mazename);

			mazeButton.addMouseListener(this.mazeButtonListener);

			mazeButton.setFont(new Font("Sans Serif", Font.PLAIN, 16));
			mazeButton.setForeground(Color.DARK_GRAY);
			mazeButton.setOpaque(false);
			mazeButton.setContentAreaFilled(false);
			mazeButton.setBorderPainted(false);

			mazeButton.setOpaque(true);
			this.listpanel.add(mazeButton);
			i++;
		}
	}


	/**
	 * Listener for clicking maze selection buttons
	 */
	private MouseAdapter mazeButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {

			JButton selectedButton = (JButton)e.getSource();
			String selectedButtonName = selectedButton.getText();

			// Creates a new maze based on the selected maze
			controller.loadMazeFile(selectedButtonName);

		}
	};

}
