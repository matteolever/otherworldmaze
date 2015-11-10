package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import controller.CreateMazeController;
import enums.CellEnum;

public class CreateMazeView extends JPanel {

	// TODO: player should be able to set the size of the playingfield in a text
	// box.
	private static final long serialVersionUID = 1L;

	/** check which component is selected */
	private final float HEADING_FONT = 14.0f;
	private final float FONT = 11.0f;

	/**
	 * create a cellView for each cell option, to be able to use this as a
	 * preview when hovering over the preview panel
	 */
	private CellView[] cellOptions;

	/**
	 * the currently selected object the user wants to place in the playing
	 * field. In the beginning nothing is selected.
	 */
	private CellView cellPreview = null;

	/**
	 * state field to indicate whether the game is allowed to be saved in its
	 * current state. Not ok for example if the payer has not set a door for
	 * each key
	 */
	private boolean isOk = false;

	private CreateMazeController controller;

	private JPanel editPanel;
	private JPanel obstaclePanel;
	private MazeView previewMaze;

	private JPanel doorsPanel;
	private JLabel doorsToSetInd;
	private JLabel keysToSetInd;

	/**
	 * the amount of doors the user has to set
	 */
	int doorsAndKeysToSetN;

	/**
	 * the amount of doors the user has already put into the playing field
	 */
	int doorsAlreadySetN = 0;

	/**
	 * the amount of keys the user has already put into the playing field
	 */
	int keysAlreadySetN = 0;

	int rows = 10; // TODO
	int cols = 10; // TODO

	Timer timer;

	/**
	 * the currently selected object that the user wants to put into the maze
	 */

	public CreateMazeView(CreateMazeController controller) {
		this.controller = controller;
		this.timer = new Timer(100, mouseTracker);

		// create an object for each type as a preview
		this.cellOptions = new CellView[CellEnum.values().length];
		for (CellEnum c : CellEnum.values()) {
			if (c.getType() == CellEnum.SIZE.getType())
				continue;
			this.cellOptions[c.getType()] = createObjectPreview(c.getType());
		}

		initView();
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// new FlowLayout(FlowLayout.LEADING)
		editPanel = new JPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
		editPanel.setBackground(new Color(37,60,84));
		editPanel.setOpaque(true);
		editPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		saveMazeButton = new JButton("Save the maze and Start Playing!");
		saveMazeButton.addMouseListener(saveMazeListener);

		// editPanel.add(createGeneralPanel());
		editPanel.add(createObstaclePanel());
		editPanel.add(createDoorPanel());
		editPanel.add(saveMazeButton);

		previewMaze = new MazeView(rows, cols, null);
		previewMaze.addMouseListener(mazeListener);

		// this.add(goBackPanel, BorderLayout.NORTH);
		this.add(editPanel, BorderLayout.WEST);
		this.add(previewMaze, BorderLayout.EAST);
	}

	private JPanel createGeneralPanel() {
		JPanel generalPanel = new JPanel(new GridLayout(3, 2));
		generalPanel.setOpaque(false);

		JLabel generalLabel = new JLabel("General");
		createFont(generalLabel, true);

		JPanel doorAmountPanel = new JPanel(new BorderLayout());
		doorAmountPanel.setOpaque(false);
		doorAmountPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		JLabel doorAmountLabel = new JLabel("Number of Doors and Keys: ");
		createFont(doorAmountLabel, false);
		JTextField doorAmountField = new JTextField(" ");
		doorAmountField.setPreferredSize(new Dimension(30, 10));
		// doorAmountField.addActionListener(doorsAmountListener);

		doorAmountPanel.add(doorAmountLabel, BorderLayout.WEST);
		doorAmountPanel.add(doorAmountField, BorderLayout.CENTER);

		generalPanel.add(generalLabel);
		generalPanel.add(new JLabel());
		generalPanel.add(doorAmountPanel);
		// generalPanel.add(keyAmountPanel);

		return generalPanel;
	}

	private JPanel createObstaclePanel() {
		obstaclePanel = new JPanel(new GridLayout(3, 2));
		// obstaclePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		obstaclePanel.setOpaque(false);

		JLabel obstaclesLabel = new JLabel("Obstacles");
		createFont(obstaclesLabel, true);

		JLabel forestLabel = oneOptionPanel("Forest", String.valueOf(CellEnum.FOREST.getType()),
				CellEnum.FOREST.getSrc());

		JLabel viallageLabel = oneOptionPanel("Village", String.valueOf(CellEnum.HOUSE.getType()),
				CellEnum.HOUSE.getSrc());

		JLabel mountainLabel = oneOptionPanel("Mountains", String.valueOf(CellEnum.MOUNTAIN.getType()),
				CellEnum.MOUNTAIN.getSrc());

		JLabel riverLabel = oneOptionPanel("River", String.valueOf(CellEnum.RIVER.getType()), CellEnum.RIVER.getSrc());

		obstaclePanel.add(obstaclesLabel);
		obstaclePanel.add(new JLabel());
		obstaclePanel.add(forestLabel);
		obstaclePanel.add(mountainLabel);
		obstaclePanel.add(viallageLabel);
		obstaclePanel.add(riverLabel);

		return obstaclePanel;
	}

	private JLabel oneOptionPanel(String name, String type, String src) {
		JLabel label = new JLabel();
		createFont(label, false);
		label.setName(type);
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		ImageIcon icon = new ImageIcon(src);
		Image img = icon.getImage() ;
		Image newimg = img.getScaledInstance( CellView.CELLSIZE.width, CellView.CELLSIZE.height,  java.awt.Image.SCALE_SMOOTH ) ;
		icon = new ImageIcon(newimg);

		label.setIcon(icon);
		label.setBounds(0, 0, CellView.CELLSIZE.width, CellView.CELLSIZE.height + 10);
		label.setPreferredSize(new Dimension(CellView.CELLSIZE.width, CellView.CELLSIZE.height + 10));
		label.addMouseListener(obstacleListener);
		return label;
	}

	private JPanel createDoorPanel() {
		doorsPanel = new JPanel(new GridLayout(4, 2));
		// doorsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		doorsPanel.setOpaque(false);
		JLabel doorsLabel = new JLabel("Doors and Keys");
		createFont(doorsLabel, true);

		JPanel doorsToSetPanel = new JPanel(new BorderLayout());
		doorsToSetPanel.setOpaque(false);
		JLabel doorsToSet = new JLabel("Doors to set:  ");
		createFont(doorsToSet, false);
		doorsToSetInd = new JLabel();
		createFont(doorsToSetInd, false);

		doorsToSetPanel.add(doorsToSet, BorderLayout.WEST);
		doorsToSetPanel.add(doorsToSetInd, BorderLayout.CENTER);

		JPanel keysToSetPanel = new JPanel(new BorderLayout());
		keysToSetPanel.setOpaque(false);
		JLabel keysToSet = new JLabel("Keys to set:  ");
		createFont(keysToSet, false);
		keysToSetInd = new JLabel(" ");
		createFont(keysToSetInd, false);

		keysToSetPanel.add(keysToSet, BorderLayout.WEST);
		keysToSetPanel.add(keysToSetInd, BorderLayout.CENTER);

		JLabel doorLabel = oneOptionPanel("Door", String.valueOf(CellEnum.DOOR.getType()), CellEnum.DOOR.getSrc());

		JLabel keyLabel = oneOptionPanel("Key", String.valueOf(CellEnum.KEY.getType()), CellEnum.KEY.getSrc());

		doorsPanel.add(doorsLabel);
		doorsPanel.add(new JLabel());

		doorsPanel.add(doorsToSetPanel);
		doorsPanel.add(keysToSetPanel);

		doorsPanel.add(doorLabel);
		doorsPanel.add(keyLabel);

		return doorsPanel;
	}

	public void createFont(JLabel label, boolean isH) {
		label.setForeground(Color.WHITE);
		if (isH)
			label.setFont(label.getFont().deriveFont(Font.BOLD, HEADING_FONT));
		else
			label.setFont(label.getFont().deriveFont(Font.PLAIN, FONT));
	}

	public CellView createObjectPreview(int cellType) {
		cellPreview = new CellView(cellType, -1, -1);
		// TODO set the correct size
		// obstaclePreview.setPreferredSize(new Dimension(100, 129));
		cellPreview.setBounds(0, 0, CellView.CELLSIZE.width, CellView.CELLSIZE.height);
		return cellPreview;

	}

	/**
	 * this method is called by the controller to initialte all necessary
	 * labels, bozes etc.
	 */
	public void initiateValues(int doorsToSetN, int keysToSetN) {
		this.doorsToSetInd.setText(String.valueOf(doorsToSetN));
		this.keysToSetInd.setText(String.valueOf(keysToSetN));
	}

	public void deselectAll() {
		for (Component c : obstaclePanel.getComponents()) {
			JLabel l = (JLabel) c;
			l.setBackground(null);
			l.setOpaque(false);
		}
		for (Component c : doorsPanel.getComponents()) {
			c.setBackground(null);

			if (c.getName() != null && !c.getName().isEmpty()) {

				JLabel l = (JLabel) c;
				l.setOpaque(false);
			}
		}

	}

	// private ActionListener doorsAmountListener = new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// JTextField field = (JTextField) e.getSource();
	// }
	// };
	//
	// private void checkTextField(JTextField field){
	// try {
	// doorsAndKeysToSetN = Integer.parseInt(field.getText());
	//
	// if(doorsAndKeysToSetN > 3){
	// doorsAndKeysToSetN = 3;
	// }
	//
	// } catch (NumberFormatException e1) {
	// JOptionPane.showMessageDialog(this, "Only Numbers allowed", "Wrong
	// Input",
	// JOptionPane.ERROR_MESSAGE);
	// doorsAndKeysToSetN = 1;
	// }
	// }

	private void updateDoorsAndKeysToSet() {
		int doorsStillToPut = keysAlreadySetN - doorsAlreadySetN;
		if (doorsStillToPut < 0)
			doorsStillToPut = 0;
		doorsToSetInd.setText(String.valueOf(doorsStillToPut));

		int keysStillToPut = doorsAlreadySetN - keysAlreadySetN;
		if (keysStillToPut < 0)
			keysStillToPut = 0;
		keysToSetInd.setText(String.valueOf(keysStillToPut));

		if (!isOkToSave())
			saveMazeButton.setEnabled(false);
		else
			saveMazeButton.setEnabled(true);

	}

	/**
	 * checks whether number of doors and keys is aligned
	 * @return
	 */
	private boolean isOkToSave() {
		if (doorsAlreadySetN == keysAlreadySetN)
			return true;
		else
			return false;
	}

	/**
	 * called when user wants to save the maze 
	 */
	private MouseAdapter saveMazeListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			controller.saveAndStartMaze(previewMaze.getIntGrid());
		}
	};

	/**
	 * should plant an obstacle and call the createMazeController to create an
	 * actual model of that
	 */
	private MouseAdapter obstacleListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel c = (JLabel) e.getSource();
			cellPreview = cellOptions[Integer.parseInt(((Component) e.getSource()).getName())];

			deselectAll();
			c.setBackground(Color.LIGHT_GRAY);
			c.setOpaque(true);

			System.out.println("selected " + ((Component) e.getSource()).getName());
		}
	};

	private MouseAdapter mazeListener = new MouseAdapter() {
		@Override
		/**
		 * when the user clicks, the object should will be added to the cell he
		 * clicked into
		 */
		public void mouseClicked(MouseEvent e) {
			System.out.println("selected in click " + cellPreview.getCellType().toString());

			if (cellPreview != null) {

				// // create the component that is selected
				// CellView cell = createObjectPreview(selected);
				// cell.setLocation(getMousePosition());
				// previewMaze.add(cell, 0);

				// GridLayout layout = (GridLayout) previewMaze.getLayout();
				CellView c = (CellView) previewMaze.getComponentAt(previewMaze.getMousePosition());
				c.setType(cellPreview.getCellType().getType());

				if (cellPreview.getCellType().equals(CellEnum.DOOR)) {
					doorsAlreadySetN++;
					updateDoorsAndKeysToSet();
				} else if (cellPreview.getCellType().equals(CellEnum.KEY)) {
					keysAlreadySetN++;
					updateDoorsAndKeysToSet();
				}
				repaint();
			}
		}

		@Override
		/**
		 * When the mouse leaves the previewMaze the preview of the selected
		 * Item should vanish
		 */
		public void mouseExited(MouseEvent e) {
			System.out.println("exit");

			if (cellPreview != null) {
				// if (previewMaze.isAncestorOf(obstacleView))
				cellPreview.setVisible(false);
				previewMaze.remove(cellPreview);

				timer.stop();
			}
		}

		@Override
		/**
		 * when the mouse enters the previewMaze the previewImage should follow
		 * the mouse. A Timer is started to ensure this.
		 */
		public void mouseEntered(MouseEvent e) {
			System.out.println("enter");

			if (cellPreview != null) {
				// the selected item follows the mouse
				timer.start();
				cellPreview.setVisible(true);
				previewMaze.add(cellPreview);
			}
		}
	};

	private ActionListener mouseTracker = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (cellPreview != null) {
				int x = previewMaze.getMousePosition().x - cellPreview.getWidth() / 2;
				int y = previewMaze.getMousePosition().y - cellPreview.getHeight() / 2;
				cellPreview.setLocation(x, y);
				repaint();
			}
		}
	};

	private JButton saveMazeButton;

}
