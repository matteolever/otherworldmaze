package controller;

import view.SelectMazeView;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *Controller class for the maze selection
 */
public class SelectMazeController {

	private SelectMazeView selectMazeView;
	private List<String> mazeList;
	Controller controller;

	/**
	 * Creates the controller for the maze selection
	 * @param controller The controller of the game
	 */
	public SelectMazeController(Controller controller){

		this.controller = controller;
		mazeList = new ArrayList<String>();
		readMazeList();
		this.selectMazeView = new SelectMazeView(this);
	}

	/**
	 * Reads the maze list from a text file
	 * @return The maze as a list of strings
	 */
	public List<String> readMazeList(){
		File file = new File("mazelist.txt");

		try{
			file.createNewFile();
			Scanner scanner1 = new Scanner(file);

			while (scanner1.hasNextLine()){
				String line = scanner1.nextLine();
				try {
					mazeList.add(line);
				}
				catch (IndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "There is an error with the file.");
				}
			}
			scanner1.close();
			return mazeList;
		}
		catch (FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}
		return null;
	}

	/**
	 * Loads and reads a maze file & creates a maze based on it
	 * @param mazeName File name
	 */
	public void loadMazeFile(String mazeName){

		int[][] maze = new int[0][0];

		File file = new File(mazeName+".txt");
		int numberOfRows = 10;
		int numberOfColumns = 10;

		try{
			file.createNewFile();
			Scanner scanner = new Scanner(file);

			maze = new int[numberOfRows][numberOfColumns];

			while (scanner.hasNextLine()){

				String line = scanner.nextLine();
				try {
					int rowIndex = Integer.parseInt(line.substring(0,1));
					int columnIndex = Integer.parseInt(line.substring(2,3));
					int content = Integer.parseInt(line.substring(4,5));

					maze[rowIndex][columnIndex] = content;
				}
				catch (IndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "There is an error with the file.");
				}

			}
			scanner.close();
		}
		catch (FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}
		
		controller.startMaze(maze);
	}


	/**
	 * Getter for the list of maze names
	 * @return The list of maze names
	 */
	public List<String> getMazelist(){
		return this.mazeList;
	}
	
	
	/**
	 * Getter for the corresponding view class
	 * @return SelectedMazeView - the view class of this controller
	 */
	public SelectMazeView getSelectMazeView() {
		return selectMazeView;
	}
}
