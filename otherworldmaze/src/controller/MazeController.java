package controller;

import java.awt.Component;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import enums.CellEnum;
import model.Door;
import model.Key;
import model.Maze;
import view.CellView;
import view.MainView;
import view.MazeView;

public class MazeController {

	MazeView mazeView;
	Maze mazeModel;

	PlayerController player;
	HaloController halo;

	Timer timer;

	// public MazeController(MainView mainView) {
	// init(mainView);
	// }

	public MazeController(MainView mainView, int[][] intGrid) {
		mazeModel = new Maze(intGrid);
		mazeView = new MazeView(this, intGrid);

		mainView.add(mazeView);
		mainView.view();

		init(mainView);
		startGame();
	}

	public void init(MainView mainView) {
		mazeView.setFocusable(true);
		mazeView.getInputMap();
		mazeView.setRequestFocusEnabled(true);
	}

	/** start Gameplay */
	public void startGame() {
		player = new PlayerController(this);
		setUpTimer();
	}

	public void movePlayer(int dx, int dy) {
		
		Component c = mazeView.getComponentAt(player.getViewPos());
		CellView cell = (CellView)c;
		int type = Integer.parseInt(c.getName());
		
		if (type == CellEnum.EMPTY.getType())
			player.move(dx, dy);
		
		else if(type == CellEnum.KEY.getType()){
			System.out.println("collision with "+type);

			System.out.println("id is "+cell.getCoordinates()[0]+","+cell.getCoordinates()[1]); 
		
			//add the key to the player and remove it from view
			Key key = (Key) mazeModel.getMazeComponents().get(new Point(cell.getCoordinates()[0],cell.getCoordinates()[1]));
			player.collectKey(key, cell.getCoordinates()[0], cell.getCoordinates()[1]);
			cell.setType(CellEnum.EMPTY.getType());
			
		} else if(type == CellEnum.DOOR.getType()){
			//add the key to the player and remove it from view
			Door door = (Door) mazeModel.getMazeComponents().get(new Point(cell.getCoordinates()[0],cell.getCoordinates()[1]));
			player.openDoor(door, cell.getCoordinates()[0], cell.getCoordinates()[1]);
			if(mazeModel.isWon()){
				wonGame();
			}
			//cell.setType(CellEnum.EMPTY.getType());
		} else {
			
		}
		mazeView.repaint();
	}
	
	public void wonGame(){
		//TODO what happens when the Game is won?
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

	/** move the player and check for collisions */
	public void movePlayer() {
	}

	/**
	 * check if the player is running into something like a wall or key or door
	 */
	private void checkCollision() {
		// if key = collect
		// if wall = stop
		// if door player checkKeys
		// if true open door
	}

}
