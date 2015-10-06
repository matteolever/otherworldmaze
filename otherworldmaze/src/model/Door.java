package model;

public class Door extends MazeComponent {
	
	private int id;
	private boolean isOpen;
	
	public Door(int x, int y, Maze maze){
		super(x,y,3,maze);
		this.id = 1;
		this.isOpen = false;
	}

	public int getId() {
		return id;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void openDoor(boolean isOpen) {
		this.isOpen = isOpen;
	}

}
