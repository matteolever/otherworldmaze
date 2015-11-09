package view;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JFrame in which everything takes place
 * 
 * @author verena
 *
 */
public class MainView extends JFrame {
	Container pane;
	JPanel container;
	
	public MainView() {
		initView();
	}

	public void initView() {
		pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
	}

	public void view() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
}
