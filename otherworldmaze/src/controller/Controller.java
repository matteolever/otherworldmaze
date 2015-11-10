package controller;

import javax.swing.JPanel;

import enums.CellEnum;
import view.MainView;
import view.StartView;

public class Controller {
	MainView mainView;
	StartView startView;

	MazeController maze;
	PlayerController player;
	private SelectMazeController selectMaze;
	private CreateMazeController editMaze;

	/** start main screen with menu */
	public void init() {

		// create MainView
		this.mainView = new MainView(this);
		this.startView = new StartView(this);

		this.startView();

	}

	public void startView() {
		removeOldViews();
		this.mainView.add(this.startView);
		this.mainView.view();
	}

	// MAZE
	public void startMaze(int[][] mazeGrid) {
		removeOldViews();

		if (mazeGrid == null) {
			mazeGrid = generateTestMaze();
		}

		//the menubar needs to specifically given to the controller
		JPanel menuBar = mainView.addMenuBar(true);
		this.maze = new MazeController(mazeGrid, this, menuBar);

		mainView.add(menuBar);
		mainView.add(this.maze.getContainer());
		mainView.view();
	}

	public void startEdit() {
		removeOldViews();
		editMaze = new CreateMazeController(this);

		mainView.add(mainView.addMenuBar(false));
		mainView.add(editMaze.getCreateMazeView());
		mainView.view();
	}

	public void startSelect() {
		removeOldViews();
		selectMaze = new SelectMazeController(this);

		mainView.add(mainView.addMenuBar(false));
		mainView.add(selectMaze.getSelectMazeView());
		mainView.view();
	}

	public void removeOldViews() {
		this.mainView.getContentPane().removeAll();
	}

	public int[][] generateTestMaze() {
		// createMaze TODO we need the array of ints read from file
		int[][] mazeGrid = new int[10][10];

		mazeGrid[7][4] = CellEnum.MOUNTAIN.getType();
		mazeGrid[7][5] = CellEnum.MOUNTAIN.getType();
		mazeGrid[8][5] = CellEnum.MOUNTAIN.getType();
		mazeGrid[8][3] = CellEnum.MOUNTAIN.getType();

		mazeGrid[8][4] = CellEnum.KEY.getType();

		mazeGrid[3][3] = CellEnum.RIVER.getType();
		mazeGrid[3][4] = CellEnum.RIVER.getType();
		mazeGrid[3][5] = CellEnum.RIVER.getType();
		mazeGrid[3][2] = CellEnum.RIVER.getType();

		mazeGrid[7][9] = CellEnum.DOOR.getType();
		mazeGrid[5][1] = CellEnum.HOUSE.getType();
		mazeGrid[2][1] = CellEnum.MOUNTAIN.getType();
		mazeGrid[3][1] = CellEnum.FOREST.getType();
		mazeGrid[4][1] = CellEnum.FOREST.getType();
		mazeGrid[5][1] = CellEnum.FOREST.getType();
		mazeGrid[6][0] = CellEnum.HOUSE.getType();
		mazeGrid[5][8] = CellEnum.HOUSE.getType();
		mazeGrid[5][7] = CellEnum.FOREST.getType();

		mazeGrid[9][9] = CellEnum.MOUNTAIN.getType();

		mazeGrid[4][7] = CellEnum.FOREST.getType();
		mazeGrid[5][7] = CellEnum.FOREST.getType();
		mazeGrid[6][7] = CellEnum.MOUNTAIN.getType();
		mazeGrid[7][7] = CellEnum.MOUNTAIN.getType();
		mazeGrid[8][7] = CellEnum.MOUNTAIN.getType();
		mazeGrid[9][7] = CellEnum.MOUNTAIN.getType();

		return mazeGrid;
	}

	static public void main(String args[]) {
		Controller controller = new Controller();
		controller.init();
	}
}
