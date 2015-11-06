package controller;

import view.CreateMazeView;
import view.MainView;

public class CreateMazeContoller {
	MainView mainView;
	CreateMazeView createMazeView;

	public CreateMazeContoller(MainView mainView) {
		createMazeView = new CreateMazeView();
		mainView.add(createMazeView);
		mainView.view();
	}

	public void runCreation() {
		while (true) {

		}
	}

}
