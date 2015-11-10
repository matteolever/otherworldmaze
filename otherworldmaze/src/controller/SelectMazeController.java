package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import view.MainView;
import view.SelectMazeView;

public class SelectMazeController {

	MainView mainView;
	SelectMazeView selectMazeView;
	private List<String> mazeList;
	Controller controller;

	public SelectMazeController(MainView mainView, Controller controller) {

		this.mainView = mainView;
		this.selectMazeView = new SelectMazeView(this);
		mainView.add(this.selectMazeView);
		mainView.view();

		this.controller = controller;
		this.mazeList = new ArrayList<String>();

		// Saves the maze list from a text file to a list
		readMazeList();
	}

	public void readMazeList() {
		File file = new File("mazelist.txt");

		try {
			// Creates the file and the scanner
			file.createNewFile();
			Scanner diskf = new Scanner(file);

			while (diskf.hasNextLine()) {
				String line = diskf.nextLine();
				try {
					this.mazeList.add(line);
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "There is an error with the file.");
				}
			}
			diskf.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}
	}

	/**
	 * Loads and reads a maze file & creates a maze based on it
	 * 
	 * @param mazeName
	 *            File name
	 */
	public void loadMazeFile(String mazeName) {

		int[][] maze = new int[0][0];

		// Creates a file: the path is the mazename.txt
		File file = new File(mazeName);
		int numberOfRows = 0;
		int numberOfColumns = 0;

		try {
			// Creates the file and the scanner
			file.createNewFile();
			Scanner diskf = new Scanner(file);

			// Counts the rows and the columns
			while (diskf.hasNextLine()) {
				String line = diskf.nextLine();
				try {
					numberOfRows = Integer.parseInt(line.substring(0, 1));
					numberOfColumns = Integer.parseInt(line.substring(2, 3));
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "There is an error with the file.");
				}
			}

			// Places the integers into a grid
			maze = new int[numberOfRows][numberOfColumns];

			while (diskf.hasNextLine()) {

				String line = diskf.nextLine();
				try {
					int rowIndex = Integer.parseInt(line.substring(0, 1));
					int columnIndex = Integer.parseInt(line.substring(2, 3));
					int content = Integer.parseInt(line.substring(4, 5));

					maze[rowIndex][columnIndex] = content;
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "There is an error with the file.");
				}

			}
			diskf.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}

		// CREATING A NEW MAZE
		MazeController newMazeController = new MazeController(mainView, maze, controller);
	}

	public List<String> getMazelist() {
		return this.mazeList;
	}

	public SelectMazeView getSelectMazeView() {
		return selectMazeView;
	}
	
	
}
