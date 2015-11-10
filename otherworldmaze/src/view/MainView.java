package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

/**
 * JFrame in which everything takes place
 * 
 * @author verena
 *
 */
public class MainView extends JFrame {
	Container pane;
	JPanel container;
	Controller controller;

	public MainView(Controller controller) {
		this.controller = controller;
		initView();
	}

	public void initView() {
		pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	}

	public void view() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	public void addMenuBar(boolean isMaze) {
		JPanel goBackPanel = new JPanel(new BorderLayout());
		goBackPanel.setBackground(Color.BLACK);
		goBackPanel.setOpaque(true);

		JLabel goBackLabel = new JLabel("\u219A");
		goBackLabel.setFont(goBackLabel.getFont().deriveFont(Font.BOLD, 20f));
		goBackLabel.addMouseListener(goBackListener);
		goBackLabel.setHorizontalAlignment(JLabel.LEFT);
		goBackLabel.setForeground(Color.WHITE);

		goBackPanel.add(goBackLabel, BorderLayout.WEST);
		
		if (isMaze) {
			JPanel keyPanel = new JPanel(new BoxLayout(pane, BoxLayout.X_AXIS));
			keyPanel.setOpaque(false);
			
			JLabel keysLabel = new JLabel("Collected Keys: ");
			keysLabel.setFont(keysLabel.getFont().deriveFont(Font.PLAIN, 10f));
			keysLabel.setHorizontalAlignment(JLabel.CENTER);
			keysLabel.setForeground(Color.WHITE);
			
			

			JPanel doorPanel = new JPanel(new BoxLayout(pane, BoxLayout.X_AXIS));
			doorPanel.setOpaque(false);
			JLabel doorsLabel = new JLabel("Closed Doors: ");
			doorsLabel.setFont(keysLabel.getFont().deriveFont(Font.PLAIN, 10f));
			doorsLabel.setHorizontalAlignment(JLabel.CENTER);
			doorsLabel.setForeground(Color.WHITE);
			
			goBackPanel.add(doorsLabel, BorderLayout.CENTER);
			goBackPanel.add(keysLabel, BorderLayout.EAST);
		}
		this.add(goBackPanel);
	}
	
	private MouseAdapter goBackListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			controller.startView();
		}
	};

}
