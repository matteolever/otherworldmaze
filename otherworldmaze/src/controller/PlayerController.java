package controller;

import enums.CellEnum;
import model.Door;
import model.Key;
import model.Player;
import view.CellView;

/**
 * this class controls the player and all its attributes such as the halo.
 */
public class PlayerController {

	/**
	 * the 'parent controller', the mazeController
	 */
	private MazeController mazeController;

	private Player playerModel;
	private CellView playerCell;

	/**
	 * the controller for the halo, that belongs to a player.
	 */
	private HaloController haloController;

	int INIT_PLAYER_X = 0;
	int INIT_PLAYER_Y = 0;

	public PlayerController(MazeController mazeController) {
		this.mazeController = mazeController;

		playerModel = new Player(INIT_PLAYER_X, INIT_PLAYER_Y, mazeController.mazeModel);
		playerCell = (CellView) mazeController.getMazeView().getComponent(0);
		playerCell.setType(CellEnum.PLAYER.getType());

		haloController = new HaloController(playerModel.getX(), playerModel.getX(), mazeController.getTime(),
				mazeController);
	}

	/**
	 * returns the position of the player in the mazeGrid.
	 * 
	 * @return the position of the player in the mazeGrid.
	 */
	public int[] getViewPos() {
		return playerCell.getCoordinates();
	}

	/**
	 * moves the playerModel and the playerView.
	 * 
	 * @param newPlayerCell.
	 *            the cell where the player is to go.
	 */
	public void move(CellView newPlayerCell) {
		playerModel.setPos(newPlayerCell.getCoordinates()[0], newPlayerCell.getCoordinates()[1]);
		haloController.refreshHalo((int) newPlayerCell.getLocation().getX(), (int) newPlayerCell.getLocation().getY());

		playerCell.setType(CellEnum.EMPTY.getType());
		playerCell = newPlayerCell;
		playerCell.setType(CellEnum.PLAYER.getType());
	}

	/**
	 * lets the player collect a key
	 * 
	 * @param key.
	 *            the key the player wants to collect.
	 */
	public void collectKey(Key key) {
		playerModel.collectKey(key);
	}

	/**
	 * called when the player encounters a door. Checks if the player has a key
	 * for it or not.
	 * 
	 * @param door
	 *            the door the player wants to open.
	 * @return true if the player has a key to open the door, false otherwise.
	 */
	public boolean openDoor(Door door) {
		if (!playerModel.getKeys().isEmpty()) {
			door.openDoor(true);
			playerModel.useKey(null); // TODO should actually be the correct key
			return true;
		}
		return false;
	}

	public Player getPlayerModel() {
		return playerModel;
	}

	public int getPlayerKeys() {
		return playerModel.getKeys().size();
	}

	public boolean shrinkHalo() {
		return haloController.shrinkHalo();
	}

}
