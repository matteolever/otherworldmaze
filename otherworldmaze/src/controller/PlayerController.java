package controller;

import enums.CellEnum;
import model.Door;
import model.Key;
import model.Player;
import view.CellView;

/**
 * @author verena
 *
 */
public class PlayerController {

	// the parent is the Maze
	private MazeController mazeController;

//	private PlayerView playerView;
	private Player playerModel;
	
	private CellView playerCell;
	private HaloController haloController;
	
	/**
	 * This value is necessary to let the player move
	 */
	private int XMaxMovement;
	private int YMaxMovement;

	int INIT_PLAYER_X = 0;
	int INIT_PLAYER_Y = 0;

	public PlayerController(MazeController mazeController) {
		this.mazeController = mazeController;

		playerModel = new Player(INIT_PLAYER_X, INIT_PLAYER_Y, mazeController.mazeModel);
		playerCell = (CellView) mazeController.getMazeView().getComponent(0);
		playerCell.setType(CellEnum.PLAYER.getType());
		
//		playerView = new PlayerView(INIT_PLAYER_X, INIT_PLAYER_Y); 
//		mazeController.addPlayerToMaze(playerView);
		
		
		//add the player to the absolute layout container
		//mazeController.mazeView.add(playerView);
		haloController = new HaloController(playerModel.getX(), playerModel.getX(), mazeController.getTime(), mazeController);
	}
	
	public int[] getViewPos() {
		return playerCell.getCoordinates();
	}

	public void move(CellView newPlayerCell) {
		playerModel.setPos(newPlayerCell.getCoordinates()[0], newPlayerCell.getCoordinates()[1]);
		haloController.refreshHalo((int)newPlayerCell.getLocation().getX(), (int)newPlayerCell.getLocation().getY());
		
		playerCell.setType(CellEnum.EMPTY.getType());
		playerCell = newPlayerCell;
		playerCell.setType(CellEnum.PLAYER.getType());
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

	public Player getPlayerModel() {
		return playerModel;
	}
	
	public void shrinkHalo(int shrink){
		haloController.shrinkHalo();
	}

}
