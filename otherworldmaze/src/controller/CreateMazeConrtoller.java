package controller;

import model.Obstacle;
import view.CreateMazeView;
import view.MainView;

/**
 * this should create an actual maze model, called by maze view.
 *
 */
public class CreateMazeConrtoller {
	MainView mainView;
	CreateMazeView createMazeView;

	public CreateMazeConrtoller(MainView mainView) {
		createMazeView = new CreateMazeView();
		mainView.add(createMazeView);
		mainView.view();
	}

	public void runCreation() {
		while (true) {

		}
	}

	public void createObstactle(int type, int x, int y) {
		Obstacle obstacle = new Obstacle();
		// TODO: we have to actually create the obstacle...

	}

}
