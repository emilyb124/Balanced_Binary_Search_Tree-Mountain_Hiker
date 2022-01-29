package project5;

import java.io.File;
import java.util.*;

/**
* This class is the main class that reads in a mountain from a text file
* in command line, creates individual rest stops using the RestStop class
* and builds the mountain by calling methods from the BSTMountain class.
* It creates a hiker using the Hiker class, and finds solutions by 
* sending the hiker down the mountain with the traversal methods in
* the BSTMountain class. It prints the solutions of all successful paths.
*
* @author Emily Bruce * @version 12/10/2021
*/
public class MountainHike {
	
	/**
	* This is the main method for the program.
	*
	* @param args test file from command line.
	*/
	public static void main (String [] args) {
		
		// Create instance of the file from command line
		File textFile = new File(args[0]);
		
		// Check that file exists
		if (!(textFile.exists())) {
			System.err.println("The file does not exist.");
			System.exit(1); // exit the program
		}
		
		//Check that the file can be read
		if (!(textFile.canRead())) {
			System.err.println("The file cannot be read.");
			System.exit(1); // exit the program
		}
		
		// Create scanner instance to read in the text file
		Scanner scanner = null;
		
		// Create a new Scanner object with the file
		try {
			scanner = new Scanner(textFile);
		}
		
		// Check that the file can be opened for reading
		catch (Exception FileNotFoundException){
			System.err.println("The file cannot be opened for reading.");
			System.exit(1); // exit the program
		}
		
		// Create an empty ArrayList of RestStop objects
		ArrayList<RestStop> restStopList = new ArrayList<RestStop>();
		
		// Loop through all lines of the input file
		while (scanner.hasNextLine()) {
			// Create a null array of strings to store the split line
			String[] line = null;
			// Split the line by blank space characters
			line = scanner.nextLine().split(" ");
			
			// Create a null RestStop
			RestStop reststop = null;
			
			// Make a new RestStop object using the first string of each line
			try {
				reststop = new RestStop(line[0]);
			}
			catch (Exception NullPointerException) {
				continue; // continue the loop with the next line of the file
			}
			
			// Create empty ArrayLists of strings to store the obstacles
			// and supplies of the RestStop
			ArrayList<String> supplies = new ArrayList<String>();
			ArrayList<String> obstacles = new ArrayList<String>();
			
			// Integer for index of the first obstacle in the line
			int obsIndex = -1; // set to -1 until obstacle is found
			
			// Loop through every string in the line after the label
			for (int i=1; i<line.length; i++) {
				// Add supplies to the ArrayList of supplies
				if (line[i].equals("food") || line[i].equals("axe")
						 || line[i].equals("raft")) {
					// But only add the supply if no obstacle has been found
					if (obsIndex<0) {
						supplies.add(line[i]);
					}
				}
				// Add any fallen trees to the obstacles list
				else if (line[i].equals("fallen") && line[i+1].equals("tree")) {
					obstacles.add(line[i] + " " + line[i+1]);
					obsIndex = i; // no more obstacles to be added
					i++; // increment i to skip over the tree string
				}
				// Add any rivers to the obstacles list
				else if (line[i].equals("river")) {
					obstacles.add(line[i]);
					obsIndex = i;  // no more obstacles to be added
				}
			}
			
			// Set the reststop's supplies and obstacles
			reststop.setSupplies(supplies);
			reststop.setObstacles(obstacles);
			// Add the reststop to the list of reststops
			restStopList.add(reststop);
		}
		
		scanner.close(); // close the scanner
		
		// Create an empty mountain
		BSTMountain mountain = new BSTMountain();
		
		// Add the reststops from restStopList to the mountain
		for (int i=0; i<restStopList.size(); i++) {
			mountain.add(restStopList.get(i));
		}
		
		// Create a new hiker object
		Hiker hiker = new Hiker();
		// Create a string for the results
		String results = mountain.traverse(hiker, mountain);
		
		// Print the results 
		if (results==null) {
			// If there are no successful paths down the mountain
			System.out.println("No possible paths down the mountain.");
		}
		else {
			System.out.println(results);
		}
	}
}