package view;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * JFrame in which everything takes place
 * 
 * @author verena
 *
 */
public class MainView extends JFrame {
	Container pane;

	public MainView() {
		initView();
	}

	public void initView() {
		this.pane = getContentPane();
		this.pane.setLayout(new BoxLayout(this.pane, BoxLayout.LINE_AXIS));

	}

	// public MazeView createMazeView(){
	//
	// MazeView mazeView = new MazeView();
	// ui = mazeView.initView();
	//
	// pane.add(ui);
	//
	// return mazeView;
	// }

	public void view() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
}
