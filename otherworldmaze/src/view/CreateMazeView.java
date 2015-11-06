package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

public class CreateMazeView extends JPanel {

	/** check which component is selected */
	private final int FOREST = 0;
	private final int MOUNTAIN = 1;
	private final int VILLAGE = 2;
	private final int RIVER = 3;

	private final float HEADING_FONT = 14.0f;
	private final float FONT = 11.0f;

	private ObstacleView[] obstacles;
	private ObstacleView obstaclePreview = null;

	private JPanel editPanel;
	private MazeView previewMaze;

	int rows = 200; // TODO
	int cols = 200; // TODO

	Timer timer;

	private int selected;
	private boolean showSelection = false;

	public CreateMazeView() {
		timer = new Timer(100, mouseTracker);

		obstacles = new ObstacleView[4];
		for (int i = 0; i < obstacles.length; i++) {
			obstacles[i] = createObstacle(i);
		}

		initView();
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// new FlowLayout(FlowLayout.LEADING)
		editPanel = new JPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
		previewMaze = new MazeView(rows, cols);
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
		forestLabel.setName(String.valueOf(FOREST));
		ImageIcon icon = new ImageIcon("imgs/forest.png");
		forestLabel.setIcon(icon);
		forestLabel.addMouseListener(obstacleListener);

		JLabel viallageLabel = new JLabel("Village");
		createFont(viallageLabel, false);
		viallageLabel.setName(String.valueOf(VILLAGE));
		ImageIcon villageIcon = new ImageIcon("imgs/forest.png");
		viallageLabel.setIcon(villageIcon);
		viallageLabel.addMouseListener(obstacleListener);

		JLabel mountainLabel = new JLabel("Mountains");
		createFont(mountainLabel, false);
		mountainLabel.setName(String.valueOf(MOUNTAIN));
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
		createFont(doorLabel, false);
		ImageIcon icon = new ImageIcon("imgs/forest.png");
		doorLabel.setIcon(icon);
		// doorLabel.addMouseListener();

		JLabel keyLabel = new JLabel("Key");
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

	public ObstacleView createObstacle(int type) {
		obstaclePreview = new ObstacleView(type);
		// TODO set the correct size
		obstaclePreview.setPreferredSize(new Dimension(100, 129));
		obstaclePreview.setBounds(0, 0, 100, 129);
		return obstaclePreview;

	}

	public void deselectAll() {
		for (Component c : obstaclePanel.getComponents()) {
			JLabel l = (JLabel) c;
			l.setBackground(null);
			l.setOpaque(false);
		}
	}

	private MouseAdapter obstacleListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel c = (JLabel) e.getSource();
			selected = Integer.parseInt(c.getName());
			obstaclePreview = obstacles[Integer.parseInt(((Component) e.getSource()).getName())];

			deselectAll();
			c.setBackground(Color.GREEN);
			c.setOpaque(true);

			System.out.println("selected " + ((Component) e.getSource()).getName());
		}
	};

	private MouseAdapter mazeListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("selected " + ((Component) e.getSource()).getName());

			if (obstaclePreview != null) {
				// create the component that is selected
				ObstacleView o = createObstacle(selected);
				o.setLocation(getMousePosition());
				previewMaze.add(o, 0);
				repaint();
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("exit");
			showSelection = false;

			if (obstaclePreview != null) {
				// if (previewMaze.isAncestorOf(obstacleView))
				obstaclePreview.setVisible(false);
				previewMaze.remove(obstaclePreview);

				timer.stop();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("enter");

			if (obstaclePreview != null) {
				// the selected item follows the mouse
				showSelection = true;
				timer.start();
				obstaclePreview.setVisible(true);
				previewMaze.add(obstaclePreview);
			}
		}

	};

	private ActionListener mouseTracker = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (obstaclePreview != null) {
				obstaclePreview.setLocation(previewMaze.getMousePosition());
				repaint();
			}
			GridLayout l = (GridLayout) previewMaze.getLayout();

		}
	};
	private JPanel obstaclePanel;

}
