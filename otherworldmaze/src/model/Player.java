package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends MazeComponent {

	public boolean alive;
	public List keys;

	public Player(int x, int y, Maze maze) {
		super(x, y, 1, maze);
		this.alive = true;
		this.keys = new ArrayList();
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public List getKeys() {
		return this.keys;
	}

	public void move() {
		// move right -> just for testing
		this.setX(this.getX() + 1);
	}

}
