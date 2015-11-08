package controller;

import view.MainView;
import view.StartView;

public class Controller {
	MainView mainView;
	StartView startView;

	MazeController maze;
	PlayerController player;

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

		// createMaze TODO we need the array of ints read from file
		this.maze = new MazeController(this.mainView, new int[10][10]);
	}

	public void startEdit() {
		this.mainView.remove(this.startView);

		CreateMazeController editMaze = new CreateMazeController(this.mainView);
	}
	
	public void startSelect() {
		this.mainView.remove(this.startView);
		
		SelectMazeController selectMaze = new SelectMazeController(this.mainView);
	}

	/** initialize the edit mode */
	public void initEdit() {

	}

	
	static public void main(String args[]) {
		Controller controller = new Controller();
		controller.init();
	}
}
