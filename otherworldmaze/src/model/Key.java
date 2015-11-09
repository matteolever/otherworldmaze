package model;

public class Key extends MazeComponent {

	private int id;
	private boolean isCollected = false;
	
	public Key(int x, int y, Maze maze){
		super(x,y,2,maze);
		this.id = 1;
	}

	public int getId() {
		return id;
	}

	public boolean isCollected() {
		return isCollected;
	}

	public void setCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}
	
	
	
}
