package controller;

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
		this.mainView = new MainView();
		this.startView = new StartView(this);

		this.startView();

	}

	public void startView() {
		removeOldViews();
		this.mainView.add(this.startView);
		this.mainView.view();
	}

	// MAZE
	public void startMaze() {
		removeOldViews();

		// createMaze TODO we need the array of ints read from file
		int[][] testMaze = new int[10][10];
		// testMaze[1][1] = CellEnum.HOUSE.getType();
		// testMaze[1][2] = CellEnum.MOUNTAIN.getType();
		// testMaze[1][3] = CellEnum.FOREST.getType();

		testMaze[7][4] = CellEnum.MOUNTAIN.getType();
		testMaze[7][5] = CellEnum.MOUNTAIN.getType();
		testMaze[8][5] = CellEnum.MOUNTAIN.getType();
		testMaze[8][3] = CellEnum.MOUNTAIN.getType();

		testMaze[8][4] = CellEnum.KEY.getType();

		testMaze[3][3] = CellEnum.RIVER.getType();
		testMaze[3][4] = CellEnum.RIVER.getType();
		testMaze[3][5] = CellEnum.RIVER.getType();
		testMaze[3][2] = CellEnum.RIVER.getType();

		testMaze[7][9] = CellEnum.DOOR.getType();
		testMaze[5][1] = CellEnum.HOUSE.getType();
		testMaze[2][1] = CellEnum.MOUNTAIN.getType();
		testMaze[3][1] = CellEnum.FOREST.getType();
		testMaze[4][1] = CellEnum.FOREST.getType();
		testMaze[5][1] = CellEnum.FOREST.getType();
		testMaze[6][0] = CellEnum.HOUSE.getType();
		testMaze[5][8] = CellEnum.HOUSE.getType();
		testMaze[5][7] = CellEnum.FOREST.getType();

		testMaze[9][9] = CellEnum.MOUNTAIN.getType();

		testMaze[4][7] = CellEnum.FOREST.getType();
		testMaze[5][7] = CellEnum.FOREST.getType();
		testMaze[6][7] = CellEnum.MOUNTAIN.getType();
		testMaze[7][7] = CellEnum.MOUNTAIN.getType();
		testMaze[8][7] = CellEnum.MOUNTAIN.getType();
		testMaze[9][7] = CellEnum.MOUNTAIN.getType();
		// testMaze[14][14] = CellEnum.MOUNTAIN.getType();

		this.maze = new MazeController(this.mainView, testMaze, this);
	}

	public void startEdit() {
		removeOldViews();

		editMaze = new CreateMazeController(this.mainView);
	}

	public void startSelect() {

		removeOldViews();

		selectMaze = new SelectMazeController(this.mainView, this);
	}

	public void removeOldViews() {
		// check if the user is coming back from another screen. then it needs
		// to be removed.
		if (this.mainView.isAncestorOf(this.startView))
			this.mainView.remove(this.startView);
		if (this.maze != null) {
			if (this.mainView.isAncestorOf(this.maze.getContainer()))
				this.mainView.remove(this.maze.getContainer());
		} else if (this.editMaze != null) {

			if (this.mainView.isAncestorOf(this.editMaze.getCreateMazeView()))
				this.mainView.remove(this.editMaze.getCreateMazeView());

		} else if (this.selectMaze != null) {

			if (this.mainView.isAncestorOf(this.selectMaze.getSelectMazeView()))
				this.mainView.remove(this.selectMaze.getSelectMazeView());
		}
	}

	static public void main(String args[]) {
		Controller controller = new Controller();
		controller.init();
	}
}
