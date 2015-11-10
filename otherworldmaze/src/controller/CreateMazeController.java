package controller;

import view.CreateMazeView;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this controller takes care of the actions performed in the CreateMazeView.
 * Such as saving the maze.
 *
 */
public class CreateMazeController {
	/**
	 * the view this controller takes care of.
	 */
	CreateMazeView createMazeView;

	/**
	 * the parent controller. Needed to be able to go back to the startView.
	 */
	Controller controller;

	/**
	 * this controller takes care of the acrions performed in the
	 * CreateMazeView. Such as saving the maze.
	 *
	 */
	public CreateMazeController(Controller controller) {
		createMazeView = new CreateMazeView(this);
		this.controller = controller;
	}

	/**
	 * saves the maze and starts it immediately.
	 * 
	 * @param intGrid.
	 *            the grid of integers, describing a maze, that is to be saved.
	 */
	public void saveAndStartMaze(int[][] intGrid) {
		String mazeName;

		mazeName = JOptionPane.showInputDialog("Give your maze a name");
		if (mazeName != null) {
			saveMaze(mazeName, intGrid);
		}
		startTheMaze(mazeName);
	}

	/**
	 * saves a maze according to the grid given as argument, as a text file and
	 * its name in the list of mazes. .
	 * 
	 * @param mazeName
	 *            the name of the file that will be created.
	 * @param intGrid
	 *            the grid of integers describing the maze, that will be saved
	 *            into the text file.
	 */
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

	/**
	 * loads the mazefrom a textfile with the name given as an argument.
	 * 
	 * @param mazeName
	 *            the name of the maze and therefore the textfile that contais
	 *            information about it.
	 */
	public void startTheMaze(String mazeName) {
		SelectMazeController selectMazeController = new SelectMazeController(controller);
		selectMazeController.loadMazeFile(mazeName);
	}

	/**
	 * goes back to the start screen.
	 */
	public void goBack() {
		controller.startView();
	}

	public CreateMazeView getCreateMazeView() {
		return createMazeView;
	}

}
