package controller;

import view.MainView;
import view.StartView;

public class Controller {
	MainView mainView;
	StartView startView;

	MazeController maze;
	PlayerController player;

	static public void main(String args[]) {
		Controller controller = new Controller();
		controller.init();
	}

	/** start main screen with menu */
	public void init() {

		// create MainView
		this.mainView = new MainView();
		this.startView = new StartView(this);

		this.mainView.add(this.startView);
		this.mainView.view();

	}

	// MAZE
	public void startMaze() {
		this.mainView.remove(this.startView);

		// createMaze
		this.maze = new MazeController(this.mainView);
	}

	public void startEdit() {
		this.mainView.remove(this.startView);

		CreateMazeConrtoller editMaze = new CreateMazeConrtoller(this.mainView);
	}

	/** initialize the edit mode */
	public void initEdit() {

	}

}
