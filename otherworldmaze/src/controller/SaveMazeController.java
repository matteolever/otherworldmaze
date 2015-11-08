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


public class SaveMazeController {

	// SAVING
	private PrintWriter printerMaze;
	private PrintWriter printerList;
	private String mazeName;
	
	public SaveMazeController (Maze maze){

		int[][] mazeGrid = maze.getGrid();

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