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
	Controller controller;

	public CreateMazeController(Controller controller) {
		createMazeView = new CreateMazeView(this);
		this.controller = controller;
	}

	public void saveAndStartMaze(int[][] intGrid) {
		String mazeName;

		mazeName = JOptionPane.showInputDialog("Give your maze a name");
		if (mazeName != null) {
			saveMaze(mazeName, intGrid);
		}
		startTheMaze(mazeName);

	}

	public void saveMaze(String mazeName, int[][] intGrid) {
		PrintWriter printerList;
		PrintWriter printerMaze;

		try {
			// Saves the maze name to the list file
			printerList = new PrintWriter(new BufferedWriter(new FileWriter("mazelist.txt", true)));
			printerList.println(mazeName);
			printerList.close();

			printerMaze = new PrintWriter(new BufferedWriter(new FileWriter(mazeName + ".txt", true)));

			// Saves the maze as a text file
			for (int row = 0; row < intGrid.length; row++) {
				for (int col = 0; col < intGrid[0].length; col++) {
					printerMaze.println(row + ":" + col + ":" + intGrid[row][col]);
				}
			}
			printerMaze.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public void startTheMaze(String mazeName) {
		SelectMazeController selectMazeController = new SelectMazeController(controller);
		selectMazeController.loadMazeFile(mazeName);
	}
	
	public void goBack(){
		controller.startView();
	}

	public CreateMazeView getCreateMazeView() {
		return createMazeView;
	}
	
	

}
