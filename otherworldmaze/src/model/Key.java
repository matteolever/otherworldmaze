package model;

public class Key extends MazeComponent {

	private int id;
	
	public Key(int x, int y, Maze maze){
		super(x,y,2,maze);
		this.id = 1;
	}

	public int getId() {
		return id;
	}
	
	
}
