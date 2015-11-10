package controller;

import javax.swing.JPanel;

import enums.CellEnum;
import view.MainView;
import view.StartView;

/**
 * this class is the Ancestor of all other classes. 
 * It controls the changing of the various views. It is the starting
 * point of the game.
 *
 */
public class Controller {
	/**
	 * the JFram that contains everything
	 */
	MainView mainView;

	/**
	 * the start view that the player sees first.
	 */
	StartView startView;

	/**
	 * the Controller for the maze view.
	 */
	MazeController maze;

	/**
	 * the controller for the selectMaze View.
	 */
	private SelectMazeController selectMaze;

	/**
	 * the controller for the createMaze view.
	 */
	private CreateMazeController editMaze;

	/** start main screen with menu */
	public void init() {

		// create MainView
		this.mainView = new MainView(this);
		this.startView = new StartView(this);

		this.startView();
	}

	/**
	 * opens the start screen.
	 */
	public void startView() {
		removeOldViews();
		this.mainView.add(this.startView);
		this.mainView.view();
	}

	/**
	 * opens the maze
	 * 
	 * @param mazeGrid,
	 *            the grid of the maze the is to be opend.
	 */
	public void startMaze(int[][] mazeGrid) {
		removeOldViews();

		if (mazeGrid == null) {
			mazeGrid = generateTestMaze();
		}

		// the menubar needs to specifically given to the controller
		JPanel menuBar = mainView.addMenuBar(true);
		this.maze = new MazeController(mazeGrid, this, menuBar);

		mainView.add(menuBar);
		mainView.add(this.maze.getContainer());
		mainView.view();
	}

	/**
	 * opens the create a maze view.
	 */
	public void startEdit() {
		removeOldViews();
		editMaze = new CreateMazeController(this);

		mainView.add(mainView.addMenuBar(false));
		mainView.add(editMaze.getCreateMazeView());
		mainView.view();
	}

	/**
	 * opens the maze selection view.
	 */
	public void startSelect() {
		removeOldViews();
		selectMaze = new SelectMazeController(this);

		mainView.add(mainView.addMenuBar(false));
		mainView.add(selectMaze.getSelectMazeView());
		mainView.view();
	}

	/**
	 * removes the previous view from the main view.
	 */
	public void removeOldViews() {
		this.mainView.getContentPane().removeAll();
	}

	/**
	 * creats a sample maze. relevant if there is no txt file to read a maze
	 * from.
	 * 
	 * @return
	 */
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

	/**
	 * the main method that creates this controller and opens the mainview.
	 * 
	 * @param args
	 */
	static public void main(String args[]) {
		Controller controller = new Controller();
		controller.init();
	}
}
