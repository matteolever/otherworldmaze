package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Maze;
import view.MainView;
import view.MazeView;

public class MazeController {

	MazeView mazeView;
	Maze mazeModel;

	int MAZE_LENGTH = 200;
	int MAZE_WIDTH = 200;

	public MazeController(MainView mainView) {
		this.mazeModel = new Maze(this.MAZE_LENGTH, this.MAZE_WIDTH);
		this.mazeView = new MazeView();
		mainView.add(mainView);
	}

	MouseListener mazeMouseListener = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};
}
