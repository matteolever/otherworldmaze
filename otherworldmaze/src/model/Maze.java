package model;
import java.util.ArrayList;
import java.util.List;

import enums.CellEnum;


public class Maze {
	
	private int[][] grid;
	private Player player;
	private List<MazeComponent> mazeComponents;
	
	//create an empty Maze
	public Maze(int rows, int cols){
		this.grid = new int[rows][cols];
		this.mazeComponents = new ArrayList<MazeComponent>();
		this.player = new Player(0,0,this);
		
		fillMaze();
	}
	
	public Maze(int[][] intGrid){
		this.grid = intGrid;
		this.mazeComponents = new ArrayList<MazeComponent>();
		this.player = new Player(0,0,this);
		
		fillMaze();
	}
	
	public boolean endGame(){
		if (this.player.isAlive()==false){
			return true;
		}
		return false;
	}
	
	public void addMazeComponent(MazeComponent component){
		this.mazeComponents.add(component);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void fillMaze(){
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				//check each number and create according maze components
				int c = grid[row][col];
				
				if(c == CellEnum.FOREST.getType()){
					this.mazeComponents.add(new Obstacle(row, col, CellEnum.FOREST.getType(), this));
				} else if(c == CellEnum.MOUNTAIN.getType()){
					this.mazeComponents.add(new Obstacle(row, col, CellEnum.MOUNTAIN.getType(), this));
				} else if(c == CellEnum.HOUSE.getType()){
					this.mazeComponents.add(new Obstacle(row, col, CellEnum.HOUSE.getType(), this));
				} else if(c == CellEnum.RIVER.getType()){
					this.mazeComponents.add(new Obstacle(row, col, CellEnum.RIVER.getType(), this));
				} else if(c == CellEnum.DOOR.getType()){
					this.mazeComponents.add(new Door(row, c, this));
				} else if(c == CellEnum.KEY.getType()){
					this.mazeComponents.add(new Key(row, col, this));
				}
				
				System.out.print(" x ");
			}
			System.out.println("");
		}
	}
	
	
}
