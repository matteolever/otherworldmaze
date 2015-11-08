package model;

public class Halo extends MazeComponent {
	
//	private MazeComponent component;
	private int size;
	private int shrinkFactor;

	public Halo(int x, int y, Maze maze){
		super(x,y,2,maze);
	}
	
//	public Halo(MazeComponent component, int size, int shrinkFactor){
//		this.component =  component;
//		this.size = size;
//		this.shrinkFactor = shrinkFactor;
//	}
//
//	public MazeComponent getComponent() {
//		return component;
//	}
//
//	public void setComponent(MazeComponent component) {
//		this.component = component;
//	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getShrinkFactor() {
		return shrinkFactor;
	}

	public void setShrinkFactor(int shrinkFactor) {
		this.shrinkFactor = shrinkFactor;
	}

}
