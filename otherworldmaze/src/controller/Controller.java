package controller;

import enums.CellEnum;
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
		int[][] testMaze = new int[10][10];
//		testMaze[1][1] = CellEnum.HOUSE.getType();
//		testMaze[1][2] = CellEnum.MOUNTAIN.getType();
//		testMaze[1][3] = CellEnum.FOREST.getType();
		
		testMaze[1][1] = CellEnum.KEY.getType();
		
		testMaze[1][3] = CellEnum.DOOR.getType();
		testMaze[5][1] = CellEnum.HOUSE.getType();
		testMaze[2][1] = CellEnum.MOUNTAIN.getType();
		testMaze[3][1] = CellEnum.FOREST.getType();
		testMaze[4][1] = CellEnum.FOREST.getType();
		testMaze[5][1] = CellEnum.FOREST.getType();
		
		this.maze = new MazeController(this.mainView, testMaze);
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
