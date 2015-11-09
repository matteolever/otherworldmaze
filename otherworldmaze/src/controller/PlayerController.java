package controller;

import java.awt.Point;

import model.Door;
import model.Key;
import model.Player;
import view.PlayerView;

/**
 * @author verena
 *
 */
public class PlayerController {

	// the parent is the Maze
	private MazeController mazeController;

	private PlayerView playerView;
	private Player playerModel;

	private HaloController haloController;

	int INIT_PLAYER_X = 0;
	int INIT_PLAYER_Y = 0;

	public PlayerController(MazeController mazeController) {
		this.mazeController = mazeController;

		playerModel = new Player(INIT_PLAYER_X, INIT_PLAYER_Y, mazeController.mazeModel);
		playerView = new PlayerView(INIT_PLAYER_X, INIT_PLAYER_Y); 
		
		mazeController.mazeView.getParent().add(playerView);

		haloController = new HaloController(playerModel.getX(), playerModel.getX(), mazeController);
	}

	public void setPos(int newX, int newY) {
		playerModel.setPos(newX, newY);
		playerView.setLocation(newX, newY);
	}

	public Point getViewPos() {
		return playerView.getLocation();
	}

	public void move(int dx, int dy) {
		int oldX = playerView.getX();
		int oldY = playerModel.getY();

		// System.out.println(dx+" "+dy);
		playerModel.setPos(oldX + dx, oldY + dy);

		playerView.setLocation(oldX + dx, oldY + dy);
		haloController.refreshHalo(oldX + dx, oldY + dy);
	}

	public void collectKey(Key key, int row, int col) {
		// TODO add the key to the player model and remove it from the view
		// playerModel.getKeys().add();
		playerModel.collectKey(key);
	}

	public void openDoor(Door door, int row, int col) {
		if (!playerModel.getKeys().isEmpty()) {
			door.openDoor(true);
			playerModel.useKey(null); //TODO should actually be the correct key
		}
	}

	public PlayerView getPlayerView() {
		return playerView;
	}

	public Player getPlayerModel() {
		return playerModel;
	}

}
