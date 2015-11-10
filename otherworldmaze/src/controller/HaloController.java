package controller;

import model.Halo;
import view.CellView;
import view.HaloView;

/**
 * the HaloController takes care of the actions regarding the halo.
 * 
 * @author verena
 *
 */
public class HaloController {

	private MazeController mazeController;
	private HaloView haloView;
	private Halo haloModel;
	private int haloX, haloY;

	/**
	 * 
	 * @param x
	 *            the row position of the Halo
	 * @param y
	 *            the col position of the Halo
	 * @param radius
	 *            the radius of the Halo
	 * @param mazeController
	 *            the parent parent controller so that the halo can be added on
	 *            top of the mazeView.
	 */
	public HaloController(int x, int y, int radius, MazeController mazeController) {
		this.haloX = x;
		this.haloY = y;
		haloView = new HaloView(haloX, haloY, radius);

		haloModel = new Halo(haloX, haloY, mazeController.mazeModel);
		this.mazeController = mazeController;
		mazeController.addHaloToMaze(haloView);
	}

	/**
	 * refreshes the position of the halo.
	 * 
	 * @param newX,
	 *            the nex row position in the grid.
	 * @param newY,
	 *            the new col position in the grid.
	 */
	public void refreshHalo(int newX, int newY) {
		this.haloX = newX;
		this.haloY = newY;

		haloModel.setPos(newX, newY);

		haloView.setX(haloX);
		haloView.setY(haloY);

		this.haloView.repaint();
	}

	/**
	 * shrinks the halo. when it is to small this method returns false (and the game should stop)
	 * @return true if the halo still has a decent size.
	 */
	public boolean shrinkHalo() {
		this.haloView.setRadius(haloView.getRadius() - haloModel.getShrinkFactor());
		this.haloView.repaint();
		this.haloModel.shrink();

		if (this.haloView.getRadius() < CellView.CELLSIZE.getWidth() / 3)
			return false;
		return true;

		// System.out.println("HaloController.shrinkHalo(): the radius is
		// "+haloView.getRadius());
	}
}
