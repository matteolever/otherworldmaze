package controller;

import java.awt.Point;

import model.Player;
import view.PlayerView;

public class PlayerController {

	// the parent is the Maze
	private MazeController mazeController;

	private PlayerView playerView;
	private Player playerModel;

	int INIT_PLAYER_X = 0;
	int INIT_PLAYER_Y = 0;

	public PlayerController(MazeController mazeController) {
		this.mazeController = mazeController;
		this.playerView = new PlayerView(INIT_PLAYER_X, INIT_PLAYER_Y, mazeController.mazeView);
		this.playerModel = new Player(INIT_PLAYER_X, INIT_PLAYER_Y, mazeController.mazeModel);
	}

	public void setPos(int newX, int newY) {
		playerModel.setPos(newX, newY);
		playerView.setLocation(newX, newY);
	}

	public Point getPos(){
		return playerView.getLocation();
	}

	public void move(int dx, int dy) {
		int oldX = playerView.getX();
		int oldY = playerModel.getY();

//		System.out.println(dx+" "+dy);
		playerModel.setPos(oldX + dx, oldY + dy);
		playerView.setLocation(oldX + dx, oldY + dy);
	}
	

}
