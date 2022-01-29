package project5;

import java.util.ArrayList;

/**
* This class represents a RestStop object, which has a label,
* a list of supplies, and a list of obstacles. It contains methods to
* set, get, add to, remove from, and compare these characteristics,
* as well as a constructor for a RestStop.
*
* @author Emily Bruce * @version 11/23/2021
*/
public class RestStop {
	// Create private class variables
	private String label;
	private ArrayList<String> supplies = new ArrayList<String>();
	private ArrayList<String> obstacles = new ArrayList<String>();
	
    /**
	* Constructor for a RestStop object
	*
	* @param label String label of the RestStop.
	* 
	* @throws NullPointerException if the label is null.
	*/
	public RestStop(String label) throws NullPointerException {
		if (label==null) {
			throw new NullPointerException("Cannot use null label.");
		}
		this.label = label;
	}
	
    /**
	* Getter for label of a RestStop.
	*
	* @return label String of the RestStop.
	*/
	public String getLabel() {
		return this.label;
	}
	
    /**
	* Getter for supplies of a RestStop.
	*
	* @return supplies ArrayList of the RestStop.
	*/
	public ArrayList<String> getSupplies() {
		return this.supplies;
	}
	
    /**
	* Getter for obstacles of a RestStop.
	*
	* @return obstacles ArrayList of the RestStop.
	*/
	public ArrayList<String> getObstacles() {
		return this.obstacles;
	}
	
    /**
	* Removes a supply from the RestStop.
	*
	* @param supply String supply to be removed.
	*/
	public void removeSupply(String supply) {
		int index = this.supplies.indexOf(supply);
		this.supplies.remove(index);
	}
	
    /**
	* Removes an obstacle from the RestStop.
	*
	* @param obstacle String obstacle to be removed.
	*/
	public void removeObstacle(String str) {
		int index = this.obstacles.indexOf(str);
		this.obstacles.remove(index);
	}
	
    /**
	* Sets the RestStop's supplies.
	*
	* @param supplies ArrayList of Strings.
	*/
	public void setSupplies(ArrayList<String> supplies) {
		this.supplies=supplies;
	}
	
    /**
	* Adds to the RestStop's supplies.
	*
	* @param supplies ArrayList of Strings.
	*/
	public void addSupplies(ArrayList<String> supplies) {
		this.supplies.addAll(supplies);
	}
	
    /**
	* Sets the RestStop's obstacles.
	*
	* @param obstacles ArrayList of Strings.
	*/
	public void setObstacles(ArrayList<String> obstacles) {
		this.obstacles = obstacles;
	}
	
    /**
	* Compares two reststop objects based on the String comparison
	* of their labels.
	*
	* @param other RestStop object to be compared against.
	*/
    public int compareTo ( RestStop other ) {
    	return this.getLabel().compareTo(other.getLabel());
    }

}
