package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.MazeController;
import enums.CellEnum;

/**
 * the view of the maze.
 * 
 */
public class MazeView extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String RIGHT = "RIGHT";
	private static final String LEFT = "LEFT";
	private static final String DOWN = "DOWN";
	private static final String UP = "UP";
	private Color BG_COLOR = new Color(250, 250, 250);
	private Color BG_MID = new Color(243, 243, 243);

	/**
	 * the number of rows in the playing field.
	 */
	private int rows;

	/**
	 * the number of colums in the playing field.
	 */
	private int cols;

	/**
	 * this is necessary for the keyListener.
	 */
	int IWF = JComponent.WHEN_IN_FOCUSED_WINDOW;
	String MOVE_UP = "move up";
	String MOVE_DOWN = "move down";
	String MOVE_LEFT = "move left";
	String MOVE_RIGHT = "move right";

	/**
	 * the controller that controls this view.
	 */
	private MazeController controller;

	/**
	 * creates a maze View with an empty grid. This is relevant for the creation
	 * of a maze
	 * 
	 * @param rows
	 *            the number of rows of the empty playing field.
	 * @param cols
	 *            the number of columns in the empty playing field.
	 * @param the
	 *            controller that controls this view.
	 */
	public MazeView(int rows, int cols, MazeController controller) {
		this.controller = controller;
		this.rows = rows;
		this.cols = cols;
		initView();
		createEmptyGrid();
	}

	/**
	 * creates a maze with the obstacles indicated in the intGrid. This happens
	 * when the user starts a game with a selected playing field.
	 * 
	 * @param intGrid
	 *            the grid in numbers, according to the CellEnum that will be
	 *            created.
	 */
	public MazeView(MazeController controller, int[][] intGrid) {
		this.controller = controller;
		this.rows = intGrid.length;
		this.cols = intGrid[0].length;
		initView();
		createFilledGrid(intGrid);
	}

	/**
	 * initilaizes the keyboard listeners (ActionMap).
	 */
	public void initView() {
		this.setLayout(new GridLayout(rows + 1, cols + 1));
		// this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// this.setPreferredSize(new Dimension(GAME_W, GAME_H));
		this.setBackground(BG_COLOR);

		this.getInputMap(IWF).put(KeyStroke.getKeyStroke(UP), MOVE_UP);
		this.getInputMap(IWF).put(KeyStroke.getKeyStroke(DOWN), MOVE_DOWN);
		this.getInputMap(IWF).put(KeyStroke.getKeyStroke(LEFT), MOVE_LEFT);
		this.getInputMap(IWF).put(KeyStroke.getKeyStroke(RIGHT), MOVE_RIGHT);

		this.getActionMap().put(MOVE_UP, moveUp);
		this.getActionMap().put(MOVE_DOWN, moveDown);
		this.getActionMap().put(MOVE_LEFT, moveLeft);
		this.getActionMap().put(MOVE_RIGHT, moveRight);

	}

	/**
	 * creates a grid with CellViews of the type EMPTY.
	 */
	public void createEmptyGrid() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				CellView cellView = new CellView(CellEnum.EMPTY, row, col);
				this.add(cellView);
				System.out.print(CellEnum.EMPTY + " ");
			}
			System.out.println("");
		}
	}

	/**
	 * creates a MazeView with the values int grid.
	 * 
	 * @param grid
	 *            the grid that specifies the look of the maze.
	 */
	public void createFilledGrid(int[][] grid) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				CellView cellView = new CellView(grid[row][col], row, col);
				this.add(cellView);
			}
			System.out.println("");
		}
	}

	/**
	 * creates a two dimensional grid of the mazeView. This is relevant when the
	 * user has created his on grid and wants to save it.
	 * 
	 * @return the int representation of the maze. 
	 */
	public int[][] getIntGrid() {
		int[][] intGrid = new int[rows][cols];

		for (Component c : this.getComponents()) {
			CellView cell = (CellView) c;
			// each cell knows it's coordinates in the grid.
			// therefore iterate over all cellViews and add its type to the gird
			// of integer.
			intGrid[cell.getCoordinates()[0]][cell.getCoordinates()[1]] = cell.getCellType().getType();
		}
		return intGrid;
	}

	@SuppressWarnings("serial")
	Action moveUp = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.move(UP);
		}
	};
	@SuppressWarnings("serial")
	Action moveDown = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.move(DOWN);
		}
	};
	@SuppressWarnings("serial")
	Action moveLeft = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.move(LEFT);
		}
	};
	@SuppressWarnings("serial")
	Action moveRight = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.move(RIGHT);
		}
	};

	@Override
	protected void paintComponent(Graphics g) {
		// creates a gradient as background
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();
		GradientPaint gp = new GradientPaint(0, 0, BG_COLOR, 0, h, BG_MID);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}

}
