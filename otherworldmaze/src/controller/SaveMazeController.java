package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.Maze;


/**
 * Saves a maze (an array of integers) as a text file for future use
 * @author maarithirvonen
 */
public class SaveMazeController {

	private PrintWriter printerMaze;
	private PrintWriter printerList;
	private String mazeName;
	
	/** Creates a textfile for a maze
	 * @param mazeGrid Grid of integers to be saved
	 */
	public SaveMazeController (int[][] mazeGrid){

		mazeName = JOptionPane.showInputDialog("Give your maze a name");

		if (mazeName != null){
			
			// Saves the maze name to the list file
			try{
				printerList = new PrintWriter(new BufferedWriter(new FileWriter("mazelist.txt", true)));
				printerList.println(mazeName);
				printerList.close();
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
			
			// Creates the maze file printer
			try{
				printerMaze = new PrintWriter(new BufferedWriter(new FileWriter(mazeName+".txt", true)));
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		// Saves the maze as a text file
		int i = 0;
		int j = 0;
		while (i < mazeGrid.length){
			while (j < mazeGrid[0].length){
				printerMaze.println(i+":"+j+":"+mazeGrid[i][j]);
				printerMaze.close();
			}
		}
	}

	
}