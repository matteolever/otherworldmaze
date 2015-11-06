package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import view.MainView;
import view.StartView;

public class Controller {
	MainView mainView;
	StartView startView;

	MazeController maze;
	PlayerController player;
	Timer timer;

	static public void main(String args[]) {
		Controller controller = new Controller();
		controller.init();
	}

	/** start main screen with menu */
	public void init() {

		// create MainView
		this.mainView = new MainView();

		this.startView = new StartView(this.mainView);
		this.startView.getStartButton().addMouseListener(this.startButtonListener);

		this.mainView.view();

	}

	private MouseAdapter startButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			startMaze();
			Controller.this.mainView.view();
		}
	};

	private MouseAdapter createButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// createMaze();
			Controller.this.mainView.view();
		}
	};

	// MAZE
	public void startMaze() {
		this.mainView.remove(this.startView);

		// createMaze
		this.maze = new MazeController(this.mainView);
		this.player = new PlayerController(this.maze);

		setUpTimer();
	}

	private void setUpTimer() {
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// follow the mouse
				Point mp = Controller.this.mainView.getMousePosition();
				if (mp == null)
					return;

				Point pp = Controller.this.player.getPos();

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

				Controller.this.player.move(dx, dy);
			}
		}, 0, 50);
	}

	/** initialize the edit mode */
	public void initEdit() {

	}

	/** start Gameplay */
	public void startGame() {

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
