package controller;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import model.Maze;
import view.MainView;
import view.MazeView;

public class MazeController {

	MazeView mazeView;
	Maze mazeModel;

	PlayerController player;

	Timer timer;

	int MAZE_LENGTH = 200;
	int MAZE_WIDTH = 200;

	int rows = 200;
	int cols = 200; // TODO!!!!!!

	public MazeController(MainView mainView) {
		mazeModel = new Maze(MAZE_LENGTH, MAZE_WIDTH);
		mazeView = new MazeView(rows, cols);
		mainView.add(mazeView);
		mainView.view();

		player = new PlayerController(this);
		setUpTimer();
	}

	/** start Gameplay */
	public void startGame() {
		setUpTimer();
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

				Point pp = player.getPos();

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
