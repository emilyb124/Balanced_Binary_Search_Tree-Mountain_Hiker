package project5;

import java.util.ArrayList;

/**
* This class represents a Hiker object, which has a list of supplies. 
* It contains methods to set, get, add to,
* and remove from supplies, as well as a constructor
* for the Hiker.
*
* @author Emily Bruce * @version 11/23/2021
*/
public class Hiker {
	
	// Create private ArrayList of strings for Hikers supplies
	private ArrayList<String> supplies = new ArrayList<String>();
	
    /**
	* Constructor for the Hiker
	*/
	public Hiker() {
	}
	
    /**
	* Getter for Hiker's supplies.
	*
	* @return supplies ArrayList of supply strings.
	*/
	public ArrayList<String> getSupplies() {
		return this.supplies;
	}
	
    /**
	* Sets hiker's supplies.
	*
	* @param supplies ArrayList of supply strings.
	*/
	public void setSupplies(ArrayList<String> supplies) {
		this.supplies=supplies;
	}
	
    /**
	* Adds to the hiker's supplies.
	*
	* @param supplies ArrayList of supply strings.
	*/
	public void addSupplies(ArrayList<String> supplies) {
		this.supplies.addAll(supplies);
	}
	
    /**
	* Removes a supply from a hiker's supplies.
	*
	* @param supply String to be removed.
	*/
	public void removeSupply(String supply) {
		int index = this.supplies.indexOf(supply);
		try {
			this.supplies.remove(index);
		}
		catch (Exception e) {
			System.err.println("Trying to remove a supply that "
					+ "hiker doesn't have.");
		}
	}
	
    /**
	* Removes multiple supplies from a hiker's supplies.
	*
	* @param supplies ArrayList of supply strings.
	*/
	public void removeSupplies(ArrayList<String> supplies) {
		try {
			this.supplies.removeAll(supplies);
		}
		catch (Exception e) {
			System.err.println("Trying to remove supplies that "
					+ "hiker doesn't have.");
		}
	}
}
