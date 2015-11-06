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

	public MazeController(MainView mainView) {
		this.mazeModel = new Maze(this.MAZE_LENGTH, this.MAZE_WIDTH);
		this.mazeView = new MazeView();
		mainView.add(this.mazeView);
		mainView.view();

		this.player = new PlayerController(this);
	}

	/** start Gameplay */
	public void startGame() {
		setUpTimer();
	}

	private void setUpTimer() {
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// follow the mouse
				Point mp = MazeController.this.mazeView.getMousePosition();
				if (mp == null)
					return;

				Point pp = MazeController.this.player.getPos();

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

				MazeController.this.player.move(dx, dy);
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
