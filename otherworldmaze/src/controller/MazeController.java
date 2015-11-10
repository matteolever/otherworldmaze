package controller;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import enums.CellEnum;
import model.Door;
import model.Key;
import model.Maze;
import view.CellView;
import view.HaloView;
import view.MainView;
import view.MazeView;

public class MazeController {

	MazeView mazeView;
	Maze mazeModel;

	PlayerController player;

	Timer timer;

	private JLayeredPane container;

	private static final String RIGHT = "RIGHT";
	private static final String LEFT = "LEFT";
	private static final String DOWN = "DOWN";
	private static final String UP = "UP";
	Controller controller;

	private int initTime = 200; // TODOD set that somewhere else!

	public MazeController(MainView mainView, int[][] intGrid, Controller controller) {
		mazeModel = new Maze(intGrid, initTime);
		mazeView = new MazeView(this, intGrid);

		this.controller = controller;

		init(mainView);
		startGame();
	}

	public void init(MainView mainView) {
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

		mainView.add(container);
		container.setVisible(true);
		container.revalidate();

		mainView.view();

	}

	public void addHaloToMaze(HaloView haloView) {
		haloView.setBounds(0, 0, 500, 500);
		haloView.setVisible(true);
		container.add(haloView, new Integer(2), 0);
		container.setVisible(true);
		container.revalidate();
	}

	/** start Gameplay */
	public void startGame() {
		player = new PlayerController(this);

		System.out.println("the payer BEGINS in pos " + player.getViewPos());
		setUpTimer();
	}

	/**
	 * moves the player according to keyboard input
	 * 
	 * @param direction
	 */
	public void move(String direction) {
		int oldIndex = ((player.getViewPos()[1] + 1) + player.getViewPos()[0] * mazeModel.getGrid()[0].length) - 1;
		int newIndex = oldIndex;
		switch (direction) {
		case UP:
			newIndex = oldIndex - mazeModel.getGrid().length;
			break;
		case DOWN:
			newIndex = oldIndex + mazeModel.getGrid().length;
			break;
		case LEFT:
			newIndex = oldIndex - 1;
			break;
		case RIGHT:
			newIndex = oldIndex + 1;
			break;
		default:
			break;
		}
		CellView newPlayer = (CellView) mazeView.getComponent(newIndex);

		if (!checkCollision(newPlayer))
			player.move(newPlayer);
	}

	public void wonGame() {
		// TODO what happens when the Game is won?
		System.out.println("THE GAME IS WON!");
		timer.cancel();
		showDialog(
				"♛ \tCongrats! You opened the exit door and won! \t♛♛ \tCongrats! You opened the exit door and won! \t♛",
				"You Won!");
	}

	public void lostGame() {
		System.out.println("You lost!");
		timer.cancel();
		showDialog("\tOh no...the light ran out.", "You Lost!");
	}

	public void showDialog(String msg, String title) {
		Object[] options = { "Go Back", "Restart!" };
		int choice = JOptionPane.showOptionDialog(mazeView, msg, title + " What do you want to do?",
				JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (choice == 0) {
			// Go back to menu
			controller.startView();
		} else if (choice == 1) {
			controller.startMaze();
		}
	}

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
	 * check if the player is running into something like a wall or key or door.
	 * If it does this method will return true
	 */
	private boolean checkCollision(CellView cell) {
		// if key = collect
		// if wall = stop
		// if door player checkKeys
		// if true open door

		int type = cell.getCellType().getType();
		if (type == CellEnum.EMPTY.getType())
			return false;

		else if (type == CellEnum.KEY.getType()) {
			System.out.println("collision with " + type);
			System.out.println("id is " + cell.getCoordinates()[0] + "," + cell.getCoordinates()[1]);

			// add the key to the player and remove it from view
			Key key = (Key) mazeModel.getMazeComponents()
					.get(new Point(cell.getCoordinates()[0], cell.getCoordinates()[1]));
			player.collectKey(key, cell.getCoordinates()[0], cell.getCoordinates()[1]);
			cell.setType(CellEnum.EMPTY.getType());

			return false;

		} else if (type == CellEnum.DOOR.getType()) {
			// add the key to the player and remove it from view
			Door door = (Door) mazeModel.getMazeComponents()
					.get(new Point(cell.getCoordinates()[0], cell.getCoordinates()[1]));
			player.openDoor(door, cell.getCoordinates()[0], cell.getCoordinates()[1]);
			if (mazeModel.isWon()) {
				wonGame();
			}
			return true; // TODO player should go into the door
		}
		mazeView.repaint();
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
