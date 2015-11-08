package model;
import java.util.ArrayList;
import java.util.List;


public class Maze {
	
	private int[][] grid;
	private Player player;
	private List mazeComponents;
	
	public Maze(int rows, int cols){
		this.grid = new int[rows][cols];
		this.mazeComponents = new ArrayList();
		this.player = new Player(0,0,this);
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
	
	
}
