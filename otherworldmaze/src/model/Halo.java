package model;

public class Halo extends MazeComponent {

	private int size;
	private int shrinkFactor = 1;

	public Halo(int x, int y, Maze maze) {
		super(x, y, 2, maze);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void shrink() {
		this.size -= shrinkFactor;
	}

	public int getShrinkFactor() {
		return shrinkFactor;
	}

	public void setShrinkFactor(int shrinkFactor) {
		this.shrinkFactor = shrinkFactor;
	}

}
