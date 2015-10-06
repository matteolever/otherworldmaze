package controller;
import model.Maze;

public class Controller {
	
	Maze maze;
	int MAZE_LENGTH = 200;
	int MAZE_WIDTH = 200;
	
	/** start main screen with menu */
	public void init(){
		
	}
	
	/** initialize the edit mode*/
	public void initEdit(){
		
	}
	
	/** initialize the whole maze. */
	public void initMaze(){
		this.maze = new Maze(MAZE_LENGTH, MAZE_WIDTH);
		
	}
	
	/** start Gameplay */
	public void startGame(){
		
	}
	
	/** move the player and check for collisions*/
	public void movePlayer(){
	}
	
	/** check if the player is running into something like a wall or key or door */
	private void checkCollision(){
		//if key = collect
		//if wall = stop
		//if door player checkKeys
			//if true open door
	}
	
	
	
	
	
	

}
