package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import enums.CellEnum;

/**
 * the view of the maze
 * 
 * @author verena
 *
 */
public class MazeView extends JPanel {

	private int GAME_W = 500; //TODO!≥!
	private int GAME_H = 500;
	private Color BG_COLOR = new Color(0, 30, 100);

	private Color bg;

	private int rows =10;
	private int cols=10;

	public MazeView() {
		initView();
		createEmptyGrid();
	}
	
	public MazeView(int[][] intGrid) {
		initView();
		createFilledGrid(intGrid);
	}

	public void initView() {
		this.setLayout(new GridLayout(rows, cols));
		// this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// this.setPreferredSize(new Dimension(GAME_W, GAME_H));
		this.setBackground(BG_COLOR);

	}

	public void createEmptyGrid() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				CellView cellView = new CellView(CellEnum.EMPTY);
				this.add(cellView);
				System.out.print(" x ");
			}
			System.out.println("");
		}
		
	}
	
	/**
	 * creates a MazeView with the values given as a parameter.
	 * @param grid the gird with which the values are 
	 */
	public void createFilledGrid(int[][] grid){
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				//TODO; how can I get the Enum when I only know the number of the type?
			
				CellView cellView = new CellView(grid[row][col]); 
				this.add(cellView);
				
				System.out.print(" x ");
			}
			System.out.println("");
		}
	}

	public void addPlayer(PlayerView player) {
		this.add(player);
	}

}
