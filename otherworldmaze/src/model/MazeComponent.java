package model;

public abstract class MazeComponent {

	private int x;
	private int y;
	private int type;
	private Maze maze;
//	private Halo halo;

	public MazeComponent(int x, int y, int type, Maze maze) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.maze = maze;
		this.maze.addMazeComponent(this);

		// Initialized here, needs to be defined
//		this.halo = new Halo(this, 0, 0);
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

//	public void setHalo(int size, int shrinkFactor) {
//		this.halo.setSize(size);
//		this.halo.setShrinkFactor(shrinkFactor);
//	}

}
