package controller;

import enums.CellEnum;
import model.Door;
import model.Key;
import model.Maze;
import view.CellView;
import view.HaloView;
import view.MazeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * this class controlls the whole gameplay. it aligns mazeView and mazeModel. it
 * also creates a player controller and checks for collisions when moving it.
 */
public class MazeController {

	/**
	 * the View of the Maze
	 */
	MazeView mazeView;
	
	/**
	 * the Model of the Maze
	 */
	Maze mazeModel;

	/**
	 * the Controller for the player is added to the Maze.
	 */
	PlayerController player;

	/**
	 * this is the timer, which redraws the maze and takes care that the halo
	 * shrinks
	 */
	Timer timer;

	/**
	 * the mazeView is not directly added to the mainView, as the overlay of
	 * halo and mazeView requires and additional panel that allows a
	 * layeredLayout. therefore this container contains them both and is added
	 * to the mainview.
	 */
	private JLayeredPane container;
	/**
	 * this is the label that shows the number of collected keys.
	 */
	private JLabel keyLabel;
	/**
	 * this is the label that shows the number of closed doors.
	 */
	private JLabel doorLabel;

	private static final String RIGHT = "RIGHT";
	private static final String LEFT = "LEFT";
	private static final String DOWN = "DOWN";
	private static final String UP = "UP";

	/**
	 * the controller. needed here to be able to switch views.
	 */
	Controller controller;

	/**
	 * the initial Size of the halo
	 */
	private int initTime = 200; // TODOD set that somewhere else!

	/**
	 * this class controls the whole gameplay. it aligns mazeView and mazeModel.
	 * it also creates a player controller and checks for collisions when moving
	 * it.
	 * 
	 * @param intGrid.
	 *            this is the grid of integers, from which the mazeModel will be
	 *            created.
	 * @param controller.
	 *            this is the "parent"controller. Needed here to be able to go
	 *            back to the startView.
	 * @param menuBar.
	 *            this is the menuBar that is created in the mainView. The maze
	 *            needs access to it directly to manipulate the keys and door
	 *            labels.
	 */
	public MazeController(int[][] intGrid, Controller controller, JPanel menuBar) {
		mazeModel = new Maze(intGrid);
		mazeView = new MazeView(this, intGrid);

		this.controller = controller;

		setUpMenuBar(menuBar);
		initView();
		startGame();
	}

	/** sets up the menubar according to the needs of the gamePlay */
	public void setUpMenuBar(JPanel menuBar) {
		// the back button is the first component
		JLabel backLabel = (JLabel) menuBar.getComponent(0);
		backLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer.cancel();
			}
		});

		// the keyPanel is the second component int the menubar
		JPanel keyPanel = (JPanel) menuBar.getComponent(1);
		keyLabel = (JLabel) keyPanel.getComponent(1);

		// the doorPanel is the second component int the menubar
		JPanel doorPanel = (JPanel) menuBar.getComponent(2);
		doorLabel = (JLabel) doorPanel.getComponent(1);
	}

	/**
	 * initilalizes the game, by aliging mazeView and HaloView and adding them
	 * to a newly created container.
	 */
	public void initView() {
		mazeView.setFocusable(true);
		mazeView.getInputMap();
		mazeView.setRequestFocusEnabled(true);

		System.out.println("the maze view size is " + mazeView.getSize());
		int width = (int) CellView.CELLSIZE.getWidth() * mazeModel.getGrid().length;

		container = new JLayeredPane();
		container.setBounds(0, 0, width, width);
		container.setPreferredSize(new Dimension(width, width));

		mazeView.setBounds(0, 0, width, width);
		mazeView.setVisible(true);

		container.add(mazeView, new Integer(0), 0);
		container.setVisible(true);
		container.revalidate();
	}

	/**
	 * Helper method. adds the halo to the container, on top of the mazeView.
	 * 
	 * @param haloView
	 */
	public void addHaloToMaze(HaloView haloView) {
		int width = (int) CellView.CELLSIZE.getWidth() * mazeModel.getGrid().length;
		haloView.setBounds(0, 0, width, width);
		haloView.setVisible(true);
		container.add(haloView, new Integer(2), 0);
		container.setVisible(true);
		container.revalidate();
	}

	/** starts Gameplay, by starting the timer and adding the player */
	public void startGame() {
		player = new PlayerController(this);

		int keys = player.getPlayerKeys();
		keyLabel.setText(String.valueOf(keys));
		doorLabel.setText(String.valueOf(mazeModel.getClosedDoors()));

		System.out.println("the payer BEGINS in pos " + player.getViewPos());
		setUpTimer();
	}

	/**
	 * moves the player according to keyboard input and checks for collisions.
	 * 
	 * @param direction
	 *            specifies which direction the player should move. Either UP,
	 *            DOWN, LEFT or RIGHT
	 */
	public void move(String direction) {
		int oldIndex = ((player.getViewPos()[1] + 1) + player.getViewPos()[0] * mazeModel.getGrid()[0].length) - 1;
		int newIndex = oldIndex;
		switch (direction) {
		case UP:
			if (oldIndex - mazeModel.getGrid().length < 0) {
				newIndex = oldIndex;
			} else {
				newIndex = oldIndex - mazeModel.getGrid().length;
			}
			break;
		case DOWN:
			if (oldIndex + mazeModel.getGrid().length > (mazeModel.getGrid()[0].length * mazeModel.getGrid().length)) {
				newIndex = oldIndex;
			} else {
				newIndex = oldIndex + mazeModel.getGrid().length;
			}
			break;
		case LEFT:
			if ((oldIndex + 1) % mazeModel.getGrid().length == 1) {
				newIndex = oldIndex;
			} else {
				newIndex = oldIndex - 1;
			}
			break;
		case RIGHT:
			if ((oldIndex + 1) % mazeModel.getGrid().length == 0) {
				newIndex = oldIndex;
			} else {
				newIndex = oldIndex + 1;
			}
			break;
		default:
			break;
		}
		CellView newPlayer = (CellView) mazeView.getComponent(newIndex);

		if (!checkCollision(newPlayer))
			player.move(newPlayer);
	}

	/**
	 * called when the game is won. cancels the timer and opens a dialog.
	 */
	public void wonGame() {
		System.out.println("THE GAME IS WON!");
		timer.cancel();
		showDialog("♛ \tCongrats! You opened the exit door and won! \t♛", "You Won!");
	}

	/**
	 * called when the game is lost. cancels the timer and opens a dialog.
	 */
	public void lostGame() {
		System.out.println("You lost!");
		timer.cancel();
		showDialog("\tOh no...the light ran out.", "You Lost!");
	}

	/**
	 * opens a dialog box with the message given as an argument and lets the
	 * user choose whether to start a new game or go back to the menu.
	 * 
	 * @param msg
	 *            the message that is to be displayed in the dialogbox.
	 * @param title
	 *            the title of the box.
	 */
	public void showDialog(String msg, String title) {
		Object[] options = { "Go Back", "Restart with new Board!" };
		int choice = JOptionPane.showOptionDialog(mazeView, msg, title + " What do you want to do?",
				JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (choice == 0) {
			// Go back to menu
			controller.startView();
		} else if (choice == 1) {
			SelectMazeController selectMaze = new SelectMazeController(controller);
			List<String> mazes = selectMaze.readMazeList();
			if (mazes != null && !mazes.isEmpty()) {
				Random random = new Random();
				int newMazeInt = random.nextInt(mazes.size());
				selectMaze.loadMazeFile(mazes.get(newMazeInt));
			} else
				controller.startMaze(this.mazeModel.getGrid());
		}
	}

	/**
	 * sets up the timer, that shrinks the halo
	 */
	private void setUpTimer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// shrink halo
				boolean shrink = player.shrinkHalo();
				if (!shrink)
					lostGame();
			}
		}, 0, 150);
	}

	/**
	 * checks if the player is running into something like a wall or key or
	 * door. If it does this method will return true. If key => collect; if door
	 * ==> check if the player has a key and if true open door
	 * 
	 * @param cell
	 *            this is the cell the player wants to move into.
	 */
	private boolean checkCollision(CellView cell) {
		int type = cell.getCellType().getType();
		if (type == CellEnum.EMPTY.getType())
			return false;

		else if (type == CellEnum.KEY.getType()) {
			// add the key to the player and remove it from view
			Key key = (Key) mazeModel.getMazeComponents()
					.get(new Point(cell.getCoordinates()[0], cell.getCoordinates()[1]));
			player.collectKey(key);
			cell.setType(CellEnum.EMPTY.getType());

			// update the label
			keyLabel.setText(String.valueOf(player.getPlayerKeys()));

			return false;

		} else if (type == CellEnum.DOOR.getType()) {

			// add the key to the player and remove it from view
			Door door = (Door) mazeModel.getMazeComponents()
					.get(new Point(cell.getCoordinates()[0], cell.getCoordinates()[1]));
			
			//this will be true if the player can open the door
			if (player.openDoor(door)) {
				cell.setType(CellEnum.DOOR_OPENED.getType());
				doorLabel.setText(String.valueOf(mazeModel.getClosedDoors()));
				
				if (mazeModel.isWon()) {
					wonGame();
				}
				return true; 
			}

		}
		return true;
	}

	public MazeView getMazeView() {
		return mazeView;
	}

	public int getTime() {
		return this.initTime;
	}

	public JLayeredPane getContainer() {
		return container;
	}

}
