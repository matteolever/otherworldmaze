package controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLayeredPane;

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
	HaloController haloController;
	
	Timer timer;
	private JLayeredPane container;

	// public MazeController(MainView mainView) {
	// init(mainView);
	// }

	public MazeController(MainView mainView, int[][] intGrid) {
		mazeModel = new Maze(intGrid);
		mazeView = new MazeView(this, intGrid);

		init(mainView);
		startGame();
	}

	public void init(MainView mainView) {
		mazeView.setFocusable(true);
		mazeView.getInputMap();
		mazeView.setRequestFocusEnabled(true);

		System.out.println("the maze view size is " + mazeView.getSize());

		container = new JLayeredPane();
		container.setBounds(0, 0, 500, 500);
		container.setPreferredSize(new Dimension(500, 500));
		
		mazeView.setBounds(0, 0, 500, 500);
		mazeView.setVisible(true);

		container.add(mazeView, new Integer(0), 0);

		mainView.add(container);
		container.setVisible(true);
		container.revalidate();

		// mainView.getContainer().setBounds(mazeView.getBounds());
		// mainView.getContainer().setPreferredSize(mazeView.getSize());

		mainView.view();

	}
	
	public void addHaloToMaze(HaloView haloView){
		haloView.setBounds(0, 0, 500, 500);
		haloView.setVisible(true);
		container.add(haloView, new Integer(1), 0);
		container.setVisible(true);
		container.revalidate();
	}

	/** start Gameplay */
	public void startGame() {
		player = new PlayerController(this);
		haloController = new HaloController( 0, 0, this);	
		System.out.println("the payer BEGINS in pos " + player.getViewPos());
		player.setPos(0, 0);
		setUpTimer();
	}

	public void moveDown() {
		Component c = mazeView.getComponentAt(player.getViewPos());
		CellView cell = (CellView) c;
		int type = Integer.parseInt(c.getName());
	}

	/** move the player and check for collisions */
	public void movePlayer(int dx, int dy) {

		Component c = mazeView.getComponentAt(player.getViewPos());
		CellView cell = (CellView) c;
		int type = Integer.parseInt(c.getName());

		if (type == CellEnum.EMPTY.getType())
			player.move(dx, dy);

		else if (type == CellEnum.KEY.getType()) {
			System.out.println("collision with " + type);
			System.out.println("id is " + cell.getCoordinates()[0] + "," + cell.getCoordinates()[1]);

			// add the key to the player and remove it from view
			Key key = (Key) mazeModel.getMazeComponents()
					.get(new Point(cell.getCoordinates()[0], cell.getCoordinates()[1]));
			player.collectKey(key, cell.getCoordinates()[0], cell.getCoordinates()[1]);
			cell.setType(CellEnum.EMPTY.getType());

		} else if (type == CellEnum.DOOR.getType()) {
			// add the key to the player and remove it from view
			Door door = (Door) mazeModel.getMazeComponents()
					.get(new Point(cell.getCoordinates()[0], cell.getCoordinates()[1]));
			player.openDoor(door, cell.getCoordinates()[0], cell.getCoordinates()[1]);
			if (mazeModel.isWon()) {
				wonGame();
			}
		} else {

		}
		mazeView.repaint();
	}

	public void wonGame() {
		// TODO what happens when the Game is won?
		System.out.println("THE GAME IS WON!");
	}

	private void setUpTimer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// follow the mouse
				Point mp = mazeView.getMousePosition();
				if (mp == null)
					return;

				Point pp = player.getViewPos();
				System.out.println("the payer is in pos " + player.getViewPos());

				int dx = 0;
				int dy = 0;

				if (mp.getX() > pp.getX())
					dx = 1;
				else if (mp.getX() == pp.getX())
					dx = 0;
				else
					dx = -1;

				if (mp.getY() > pp.getY())
					dy = 1;
				else if (mp.getY() == pp.getY())
					dy = 0;
				else {
					dy = -1;
				}

				if (dx == 0 && dy == 0)
					return;

				player.move(dx, dy);
			}
		}, 0, 50);
	}

	/**
	 * check if the player is running into something like a wall or key or door
	 */
	private void checkCollision(int dx, int dy) {
		// if key = collect
		// if wall = stop
		// if door player checkKeys
		// if true open door

		Component c = mazeView.getComponentAt(player.getViewPos());
		CellView cell = (CellView) c;
		int type = Integer.parseInt(c.getName());

		if (type == CellEnum.EMPTY.getType())
			player.move(dx, dy);

		else if (type == CellEnum.KEY.getType()) {
			System.out.println("collision with " + type);
			System.out.println("id is " + cell.getCoordinates()[0] + "," + cell.getCoordinates()[1]);

			// add the key to the player and remove it from view
			Key key = (Key) mazeModel.getMazeComponents()
					.get(new Point(cell.getCoordinates()[0], cell.getCoordinates()[1]));
			player.collectKey(key, cell.getCoordinates()[0], cell.getCoordinates()[1]);
			cell.setType(CellEnum.EMPTY.getType());

		} else if (type == CellEnum.DOOR.getType()) {
			// add the key to the player and remove it from view
			Door door = (Door) mazeModel.getMazeComponents()
					.get(new Point(cell.getCoordinates()[0], cell.getCoordinates()[1]));
			player.openDoor(door, cell.getCoordinates()[0], cell.getCoordinates()[1]);
			if (mazeModel.isWon()) {
				wonGame();
			}
		} else {

		}
		mazeView.repaint();
	}

}
