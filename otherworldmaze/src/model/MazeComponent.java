package model;

public abstract class MazeComponent {

	private int x;
	private int y;
	/** the type of the component to allow for fast check*/
	private int type;
	protected Maze maze;

	public MazeComponent(int x, int y, int type, Maze maze) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.maze = maze;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getType() {
		return type;
	}
}
