package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import view.MainView;
import view.SelectMazeView;

public class SelectMazeController {

	private MainView mainView;
	private SelectMazeView selectMazeView;
	private List<String> mazeList;
	Controller controller;

	public SelectMazeController(MainView mainView, Controller controller){

		this.controller = controller;
		mazeList = new ArrayList<String>();
		readMazeList();

		this.mainView = mainView;
		this.selectMazeView = new SelectMazeView(this);
		mainView.add(this.selectMazeView);
		mainView.view();
	}

	public void readMazeList(){
		File file = new File("mazelist.txt");

		try{
			// Creates the file and the scanner
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
		}
		catch (FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}
	}

	/**
	 * Loads and reads a maze file & creates a maze based on it
	 * @param mazeName File name
	 */
	public void loadMazeFile(String mazeName){

		int[][] maze = new int[0][0];

		// Creates a file: the path is the mazename.txt
		File file = new File(mazeName+".txt");
		int numberOfRows = 10;
		int numberOfColumns = 10;

		try{
			// Creates the file and the scanner
			file.createNewFile();
			Scanner scanner1 = new Scanner(file);
			Scanner scanner2 = new Scanner(file);

			// THIS WORKS BEAUTIFULLY
			System.out.println("filename: "+file.getName());

//			while (scanner1.hasNextLine()){
//
//				//TODO WHY IS THIS NOT RUN?
//				System.out.println("BOOOOO");
//
//				String line = scanner1.nextLine();
//
//				System.out.println(line);
//
//				try {
//					System.out.println(line.substring(0,1));
//					System.out.println(line.substring(2,3));
//
//					numberOfRows = Integer.parseInt(line.substring(0,1));
//					numberOfColumns = Integer.parseInt(line.substring(2,3));
//									}
//				catch (IndexOutOfBoundsException e){
//					JOptionPane.showMessageDialog(null, "There is an error with the file.");
//				}
//				scanner1.close();
//			}

			// Places the integers into a grid
			maze = new int[numberOfRows][numberOfColumns];


			while (scanner2.hasNextLine()){

				String line = scanner2.nextLine();
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
			scanner2.close();
		}
		catch (FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null, "There is an error with the file.");
		}

		System.out.println("Random cell in the maze: "+maze[3][3]);

		// CREATING A NEW MAZE
		this.mainView.remove(selectMazeView);
		MazeController newMazeController = new MazeController(mainView, maze, controller);
	}


	public List<String> getMazelist(){
		return this.mazeList;
	}
}
