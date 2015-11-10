package model;

import enums.CellEnum;

import java.awt.*;
import java.util.HashMap;

/**
 * this class represents the maze as a model.
 *
 */
public class Maze {

	/**
	 * the grid of the maze, displayed as integers
	 */
	private int[][] grid;

	/**
	 * the player of the maze
	 */
	private Player player;

	/**
	 * the amount of doors that still need to be opened to win. 100 is just an
	 * arbitrary initializer.
	 */
	private int closedDoors = 0;

	/**
	 * a HashMap of mazecomponent with the coordinates as key. very useful to
	 * access one component.
	 */
	private HashMap<Point, MazeComponent> mazeComponents;

	/**
	 * creates a maze according to the grid of integers (intGrid) given as an
	 * argument. Each integer in the grid is a placeholder for an object. For
	 * the meaning of integers see CellEnum in the enums package.
	 * 
	 * @param intGrid.
	 *            the grid of integers, that is the base of the game.
	 */
	public Maze(int[][] intGrid) {
		this.grid = intGrid;
		this.mazeComponents = new HashMap<Point, MazeComponent>();
		this.player = new Player(0, 0, this);

		fillMaze(intGrid);
	}

	/**
	 * fills the maze according to the grid of integers. They are translated
	 * according to the CellEnum.
	 * 
	 * @param intGrid.
	 *            the grid of integers, that represants one game.
	 */
	public void fillMaze(int[][] intGrid) {
		for (int row = 0; row < intGrid.length; row++) {
			for (int col = 0; col < intGrid[0].length; col++) {
				// check each number and create according maze components
				int c = intGrid[row][col];

				Point pos = new Point(row, col);
				if (c == CellEnum.FOREST.getType()) {
					Obstacle o = new Obstacle(row, col, CellEnum.FOREST.getType(), this);
					this.mazeComponents.put(pos, o);

				} else if (c == CellEnum.MOUNTAIN.getType()) {
					this.mazeComponents.put(pos, new Obstacle(row, col, CellEnum.MOUNTAIN.getType(), this));
				} else if (c == CellEnum.HOUSE.getType()) {
					this.mazeComponents.put(pos, new Obstacle(row, col, CellEnum.HOUSE.getType(), this));
				} else if (c == CellEnum.RIVER.getType()) {
					this.mazeComponents.put(pos, new Obstacle(row, col, CellEnum.RIVER.getType(), this));
				} else if (c == CellEnum.DOOR.getType()) {
					this.mazeComponents.put(pos, new Door(row, c, this));
					this.closedDoors += 1;
				} else if (c == CellEnum.KEY.getType()) {
					this.mazeComponents.put(pos, new Key(row, col, this));
				}
			}
		}
	}

	/**
	 * returns the player of a maze
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int[][] getGrid() {
		return this.grid;
	}

	public HashMap<Point, MazeComponent> getMazeComponents() {
		return mazeComponents;
	}

	public void openOneDoor() {
		this.closedDoors -= 1;
	}

	public boolean isWon() {
		boolean won = false;
		if (this.closedDoors == 0) {
			won = true;
		}
		return won;
	}

	public int getClosedDoors() {
		return closedDoors;
	}

}
