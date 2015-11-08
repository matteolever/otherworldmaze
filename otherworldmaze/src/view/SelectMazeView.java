package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SelectMazeController;
import enums.CellEnum;

/**
 * View class for 
 */
public class SelectMazeView extends JPanel {

	private SelectMazeController controller;
	private List<String> mazelist;

	public SelectMazeView(SelectMazeController controller){

		this.controller = controller;
		this.mazelist = this.controller.getMazelist();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		int i = 0;
		while (i < mazelist.size()){
			String mazename = mazelist.get(i);
			JButton mazeButton = new JButton(mazename);
			mazeButton.addMouseListener(this.mazeButtonListener);
			this.add(mazeButton);			
			i++;
		}
	}
	
	/**
	 * Listener for clicking maze selection buttons
	 */
	private MouseAdapter mazeButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			// Creates a new maze based on the selected maze
			controller.loadMazeFile(getName());
		}
	};

}
