package model;

import java.awt.Point;
import java.util.HashMap;

import enums.CellEnum;

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

	/** a HashMap of mazecomponent with the coordinates as key */
	private HashMap<Point, MazeComponent> mazeComponents;
	
	private int initTime = 0;

	// create an empty Maze
	public Maze(int rows, int cols, int initTime) {
		this.grid = new int[rows][cols];
		this.initTime = initTime;
		this.mazeComponents = new HashMap<Point, MazeComponent>();
		this.player = new Player(0, 0, this);

		fillMaze(grid);
	}

	public Maze(int[][] intGrid, int initTime) {
		this.grid = intGrid;
		this.initTime = initTime;
		this.mazeComponents = new HashMap<Point, MazeComponent>();
		this.player = new Player(0, 0, this);

		fillMaze(intGrid);
	}

	public boolean endGame() {
		if (this.player.isAlive() == false) {
			return true;
		}
		return false;
	}

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

				System.out.print(" x ");
			}
			System.out.println("");
		}
	}

	// public void addMazeComponent(MazeComponent component){
	// this.mazeComponents.put(component);
	// }

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
	
	public void openOneDoor(){
		this.closedDoors -=1;
	}
	
	public boolean isWon() {
		boolean won = false;
		if (this.closedDoors == 0){
			won = true;
		}
		return won;
	}

	public int getClosedDoors() {
		return closedDoors;
	}
	

}
