package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import view.CreateMazeView;
import view.MainView;

/**
 * this should create an actual maze model, called by maze view.
 *
 */
public class CreateMazeController {
	MainView mainView;
	CreateMazeView createMazeView;

	public CreateMazeController(MainView mainView) {
		createMazeView = new CreateMazeView(this);
		mainView.add(createMazeView);
		mainView.view();
	}

	public void runCreation() {
		while (true) {

		}
	}

	public void createObstactle(int type, int x, int y) {
		// Obstacle obstacle = new Obstacle();
		// TODO: we have to actually create the obstacle...

	}

	public void saveMaze(int[][] intGrid) {

		PrintWriter printerList;
		PrintWriter printerMaze;
		String mazeName;

		mazeName = JOptionPane.showInputDialog("Give your maze a name");

		if (mazeName != null) {
			try {
				// Saves the maze name to the list file
				printerList = new PrintWriter(new BufferedWriter(new FileWriter("mazelist.txt", true)));
				printerList.println(mazeName);
				printerList.close();
				
				printerMaze = new PrintWriter(new BufferedWriter(new FileWriter(mazeName + ".txt", true)));
				printerMaze.println("hallo");
				
				// Saves the maze as a text file
				for (int row = 0; row < intGrid.length; row++) {
					for (int col = 0; col < intGrid[0].length; col++) {
						printerMaze.println("hallo");
						printerMaze.println(row + ":" + col + ":" + intGrid[row][col]);
					}
				}
				printerMaze.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		
		}

	}

}
