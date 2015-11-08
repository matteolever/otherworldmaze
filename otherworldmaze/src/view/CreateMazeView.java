package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import enums.CellEnum;

public class CreateMazeView extends JPanel {

	// TODO: player should be able to set the size of the playingfield in a text
	// box.

	/** check which component is selected */
	private final float HEADING_FONT = 14.0f;
	private final float FONT = 11.0f;

	/**
	 * create a cellView for each cell option, to be able to use this as a
	 * preview when hovering over the preview panel
	 */
	private CellView[] cellOptions;
	private CellView cellPreview = null;

	private JPanel editPanel;
	private JPanel obstaclePanel;
	private MazeView previewMaze;

	int rows = 10; // TODO
	int cols = 10; // TODO

	Timer timer;

	private int selected;

	public CreateMazeView() {
		timer = new Timer(100, mouseTracker);

		//create an object for each type as a preview
		cellOptions = new CellView[CellEnum.values().length];
		for (CellEnum c : CellEnum.values()) {
			cellOptions[c.getType()] = createObjectPreview(c.getType());
		}

		initView();
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// new FlowLayout(FlowLayout.LEADING)
		editPanel = new JPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
		previewMaze = new MazeView();
		// previewMaze.setLayout(null);
		previewMaze.addMouseListener(mazeListener);

		editPanel.add(createObstaclePanel());
		editPanel.add(createDoorPanel());

		this.add(editPanel, BorderLayout.WEST);
		this.add(previewMaze, BorderLayout.EAST);
	}

	private JPanel createObstaclePanel() {
		obstaclePanel = new JPanel(new GridLayout(4, 2));
		obstaclePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		JLabel obstaclesLabel = new JLabel("Obstacles");
		createFont(obstaclesLabel, true);

		JLabel forestLabel = new JLabel("Forest");
		createFont(forestLabel, false);
		forestLabel.setName(String.valueOf(CellEnum.FOREST.getType()));
		ImageIcon icon = new ImageIcon("imgs/forest.png");
		forestLabel.setIcon(icon);
		forestLabel.addMouseListener(obstacleListener);

		JLabel viallageLabel = new JLabel("Village");
		createFont(viallageLabel, false);
		viallageLabel.setName(String.valueOf(CellEnum.VILLAGE.getType()));
		ImageIcon villageIcon = new ImageIcon("imgs/forest.png");
		viallageLabel.setIcon(villageIcon);
		viallageLabel.addMouseListener(obstacleListener);

		JLabel mountainLabel = new JLabel("Mountains");
		createFont(mountainLabel, false);
		mountainLabel.setName(String.valueOf(CellEnum.MOUNTAIN.getType()));
		ImageIcon mountainIcon = new ImageIcon("imgs/forest.png");
		mountainLabel.setIcon(mountainIcon);
		mountainLabel.addMouseListener(obstacleListener);

		obstaclePanel.add(obstaclesLabel);
		obstaclePanel.add(forestLabel);
		obstaclePanel.add(mountainLabel);
		obstaclePanel.add(viallageLabel);

		// mountainLabel.setBackground(Color.RED);
		// mountainLabel.setOpaque(true);
		// mountainLabel.setBounds(100, 100, 100, 100);
		// mountainLabel.setLocation(100, 100);
		// previewMaze.add(mountainLabel);

		return obstaclePanel;
	}

	private JPanel createDoorPanel() {
		// Doors
		JPanel doorsPanel = new JPanel(new GridLayout(4, 2));
		doorsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

		JLabel doorsLabel = new JLabel("Doors and Keys");
		createFont(doorsLabel, true);

		JLabel doorLabel = new JLabel("Door");
		doorLabel.setName(String.valueOf(CellEnum.DOOR.getType()));
		createFont(doorLabel, false);
		ImageIcon icon = new ImageIcon("imgs/forest.png");
		doorLabel.setIcon(icon);
		// doorLabel.addMouseListener();

		JLabel keyLabel = new JLabel("Key");
		keyLabel.setName(String.valueOf(CellEnum.KEY.getType()));
		createFont(keyLabel, false);
		ImageIcon keyIcon = new ImageIcon("imgs/forest.png");
		keyLabel.setIcon(keyIcon);
		// keyLabel.addMouseListener();

		doorsPanel.add(doorsLabel);
		doorsPanel.add(doorLabel);
		doorsPanel.add(keyLabel);

		return doorsPanel;
	}

	public void createFont(JLabel label, boolean isH) {
		if (isH)
			label.setFont(label.getFont().deriveFont(Font.BOLD, HEADING_FONT));
		else
			label.setFont(label.getFont().deriveFont(Font.PLAIN, FONT));
	}

	public CellView createObjectPreview(int cellType) {
		cellPreview = new CellView(cellType);
		// TODO set the correct size
		// obstaclePreview.setPreferredSize(new Dimension(100, 129));
		cellPreview.setBounds(0, 0, 100, 129);
		return cellPreview;

	}

	public void deselectAll() {
		for (Component c : obstaclePanel.getComponents()) {
			JLabel l = (JLabel) c;
			l.setBackground(null);
			l.setOpaque(false);
		}
	}

	/**
	 * should plant an obstacle and call the createMazeController to create an
	 * actual model of that
	 */
	private MouseAdapter obstacleListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel c = (JLabel) e.getSource();
			selected = Integer.parseInt(c.getName());
			cellPreview = cellOptions[Integer.parseInt(((Component) e.getSource()).getName())];

			deselectAll();
			c.setBackground(Color.GREEN);
			c.setOpaque(true);

			System.out.println("selected " + ((Component) e.getSource()).getName());
		}
	};

	private MouseAdapter mazeListener = new MouseAdapter() {
		@Override
		/**
		 * when the user clicks, the object should will be added to the cell he clicked into
		 */
		public void mouseClicked(MouseEvent e) {
			System.out.println("selected in click " + cellPreview.getCellType().toString());

			if (cellPreview != null) {
				
				
//				// create the component that is selected
//				CellView cell = createObjectPreview(selected);
//				cell.setLocation(getMousePosition());
//				previewMaze.add(cell, 0);
				
//				GridLayout layout = (GridLayout) previewMaze.getLayout();
				CellView c = (CellView) previewMaze.getComponentAt(previewMaze.getMousePosition());
				c.setType(cellPreview.getCellType().getType());
				
				//TODO create the component in the model
				repaint();
			}
		}

		@Override
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
				cellPreview.setLocation(previewMaze.getMousePosition());
				repaint();
			}
			GridLayout l = (GridLayout) previewMaze.getLayout();

		}
	};
	

}
