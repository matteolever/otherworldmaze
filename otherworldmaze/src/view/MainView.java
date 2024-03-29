package view;

import controller.Controller;
import enums.CellEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * JFrame that contains everything, and also the menubar that is visible in all
 * pages except the start view.
 */
public class MainView extends JFrame {
	private static final long serialVersionUID = -7629143563219013810L;

	/**
	 * the content pane of the JFrame
	 */
	Container pane;

	/**
	 * the controller that controls the main view. Necessary so the mainView
	 */
	Controller controller;

	/**
	 * JFrame that contains all other jpanels.
	 * 
	 * @param controller.
	 *            the controller that take scare of which view is to be
	 *            displayed.
	 */
	public MainView(Controller controller) {
		this.controller = controller;
		initView();
	}

	public void initView() {
		pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	}

	/**
	 * sets everything visible and packs it.
	 */
	public void view() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	/**
	 * adds a menubar as a component to the main view. if the isMaze is true
	 * then extra labels are added to the menubar
	 * 
	 * @param isMaze.
	 *            indicates whther the menubar is for the mazeivew of not
	 */
	public JPanel addMenuBar(boolean isMaze) {
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
			// THe panel for the keys
			JPanel keyPanel = new JPanel();
			keyPanel.setOpaque(false);

			JLabel keysLabel = new JLabel("Collected Keys: ");
			keysLabel.setFont(keysLabel.getFont().deriveFont(Font.PLAIN, 10f));
			keysLabel.setHorizontalAlignment(JLabel.CENTER);
			keysLabel.setForeground(Color.WHITE);

			JLabel keyN = new JLabel(" ");
			keyN.setFont(keysLabel.getFont());
			keyN.setHorizontalAlignment(JLabel.CENTER);
			keyN.setForeground(Color.WHITE);
			keyN.setName(String.valueOf(CellEnum.KEY.getType()));

			keyPanel.add(keysLabel);
			keyPanel.add(keyN);

			// THe panel for the doors the player has collected
			JPanel doorPanel = new JPanel();
			doorPanel.setOpaque(false);

			JLabel doorsLabel = new JLabel("Closed Doors: ");
			doorsLabel.setFont(keysLabel.getFont().deriveFont(Font.PLAIN, 10f));
			doorsLabel.setHorizontalAlignment(JLabel.CENTER);
			doorsLabel.setForeground(Color.WHITE);

			JLabel doorN = new JLabel(" ");
			doorN.setFont(keysLabel.getFont());
			doorN.setHorizontalAlignment(JLabel.CENTER);
			doorN.setForeground(Color.WHITE);
			doorN.setName(String.valueOf(CellEnum.KEY.getType()));

			doorPanel.add(doorsLabel);
			doorPanel.add(doorN);

			goBackPanel.add(keyPanel, BorderLayout.CENTER);
			goBackPanel.add(doorPanel, BorderLayout.EAST);
		}

		return goBackPanel;
	}

	/**
	 * Listens for clicks on the goBackArrow in the menubar, and then goes back
	 * to the start screen.
	 */
	private MouseAdapter goBackListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			controller.startView();
		}
	};

}
