package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends MazeComponent {

	public boolean alive;
	public List<Key> keys;

	public Player(int x, int y, Maze maze) {
		super(x, y, 1, maze);
		this.alive = true;
		this.keys = new ArrayList<Key>();
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void collectKey(Key key){
		keys.add(key);
		key.setCollected(true);
		
		int[] pos = new int[2];
		pos[0] = key.getX();
		pos[1] = key.getY();
		maze.getMazeComponents().remove(pos);
	}
	
	public void useKey(Key key){
		if(key == null){
			keys.remove(0);
		}
		keys.remove(key);
	}


	public List<Key> getKeys() {
		return this.keys;
	}

	public void move() {
		// move right -> just for testing
		this.setX(this.getX() + 1);
	}

}
