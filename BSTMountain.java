package project5;

/**
* This class represents a mountain balanced Binary Search Tree. It contains 
* a BSTNode class with methods for handling the nodes, and also contains
* methods for constructing the mountain, performing rotations to balance the 
* mountain, and recursively add nodes to the mountain or traverse the mountain.
* These methods are called from the main class.
*
* @author Emily Bruce * @version 12/10/2021
*/
public class BSTMountain {
	
	// Create private class variables
	private BSTNode root;
	private String solutions = ""; // set initial solution to empty string
	private int depth = 0; // set initial depth to 0
	
	/**
	* This class represents a node of the mountain. It contains a constructor
	* for the node and methods for comparing the node, setting or updating its
	* height and level, checking if it has been visited by the hiker, and
	* calculating its balance factor.
	*
	* @author Emily Bruce * @version 12/10/2021
	*/
    private class BSTNode implements Comparable < BSTNode > {

    	// Create private variables for the node
        private RestStop data;
        private BSTNode  left;
        private BSTNode  right;
        private int height;
        private int level;
        private boolean visited;

        /**
		* Constructor for a node of the mountain.
		*
		* @param data RestStop object for the node to contain.
		*/
        public BSTNode ( RestStop data ) {
            this.data = data;
        }

        /**
		* Compares two nodes of the mountain.
		*
		* @param other node to be compared.
		* 
		* @throws NullPointerException if the parameter is null.
		*
		* @return a positive integer if the node's is greater than other's,
		* negative if it is less than, and zero if they're equal.
		*/
        public int compareTo ( BSTNode other ) throws NullPointerException {
        	if (other==null) {
        		throw new NullPointerException("Cannot compare to null node.");
        	}
        	return this.data.compareTo(other.data);
        }
        
        /**
		* Sets the height of the node.
		*
		* @param height integer height to be set.
		*/
        public void setHeight(int height) {
        	this.height = height;
		}
        
        /**
		* Updates the height of a node.
		*/
        public void updateHeight() {
        	// If the node is a leaf
	        if (this.left==null && this.right==null) {
	        	this.height = 0; // set the height to 0
	        }
	        // If the node only has a right child
	        else if (this.left == null) {
	        	// Set the height to one more than its child's
	        	this.height = this.right.height + 1;
	        }
	        // If the node only has a left child
	        else if (this.right == null) {
	        	// Set the height to one more than its child's
	        	this.height = this.left.height + 1;
	        }
	        // If the node has two children
	        else {
	        	// Set the height one more than the max of it's children's
	        	this.height = 1 + Math.max( this.left.height, 
	        			this.right.height );
	        }
        }
        
        /**
		* Sets the level of a node.
		*
		* @param level integer level to be set.
		*/
        public void setLevel(int level) {
        	this.level = level;
        }
        
        /**
		* Sets the node's visited boolean to true.
		*/
        public void setVisited() {
			this.visited = true;
		}
        
        /**
		* Calculates the balance factor of a node.
		*
		* @return balance factor (integer value between -2 and 2).
		*/
        public int balanceFactor () {
        	// If the node only has one child
	        if ( this.right == null ) {
	          return -this.height; // balance factor equals the nodes height
	        }
	        if ( this.left == null ) {
	          return this.height; // balance factor equals the nodes height
	        }
	        // If the node has two children, balance factor equals
	        // the difference between the height of the right and left child
	        return this.right.height - this.left.height;
        }
    }
    
    /**
	* Performs left left rotation on a node.
	*
	* @param A node is the root of the subtree to be rotated.
	* 
	* @throws NullPointerException if the parameter is null.
	*
	* @return pointer for the new root of the rotated subtree.
	*/
    public BSTNode rotateLL(BSTNode A) throws NullPointerException {
    	// Throw NullPointerException if A is null
    	if (A==null) {
    		throw new NullPointerException("Cannot rotate null node.");
    	}
        BSTNode B = A.left; // copy left child of A
        A.left = B.right; // left point A to right child of B
        B.right = A; // right point B to A
        // Update heights of the rotated nodes
        A.updateHeight();
        B.updateHeight();
        // If A was the root of the entire mountain
        if (root==A) {
        	root=B; // reset the root to B
        }
        return B; // return the new root of the rotated subtree
    }
    
    /**
	* Performs left right rotation on a node.
	*
	* @param A node is the root of the subtree to be rotated.
	* 
	* @throws NullPointerException if the parameter is null.
	*
	* @return pointer for the new root of the rotated subtree.
	*/
    public BSTNode rotateLR (BSTNode A) throws NullPointerException {
    	// Throw NullPointerException if A is null
    	if (A==null) {
    		throw new NullPointerException("Cannot rotate null node.");
    	}
        BSTNode B = A.left; // copy left child of A
        BSTNode C = B.right; // copy right child of B
        A.left = C.right; // left point A to right child of C
        B.right = C.left; // right point B to left child of C
        C.left = B; // right point C to B
        C.right = A; // left point C to A
        // Update height of rotated nodes
        A.updateHeight();
        B.updateHeight();
        C.updateHeight();
        // If A was the root of the entire mountain
        if (root==A) {
        	root=C; // reset root to C
        }
        return C; // return the new root of the rotated subtree
    }
    
    /**
	* Performs right left rotation on a node.
	*
	* @param A node is the root of the subtree to be rotated.
	* 
	* @throws NullPointerException if the parameter is null.
	*
	* @return pointer for the new root of the rotated subtree.
	*/
    public BSTNode rotateRL (BSTNode A) throws NullPointerException {
    	// Throw NullPointerException if A is null
    	if (A==null) {
    		throw new NullPointerException("Cannot rotate null node.");
    	}
        BSTNode B = A.right; // copy right child of A
        BSTNode C = B.left; // copy left child of B
        A.right = C.left; // right point A to left child of C
        B.left = C.right; // left point B to right child of C
        C.left = A; // left point C to A
        C.right = B; // right point C to B
        // Update height of rotated nodes
        A.updateHeight();
        B.updateHeight();
        C.updateHeight();
        // If A was the root of the entire mountain
        if (root==A) {
        	root=C; // reset root to C
        }
        return C; // return new root of the rotated subtree
    }
    
    /**
	* Performs right right rotation on a node.
	*
	* @param A node is the root of the subtree to be rotated.
	* 
	* @throws NullPointerException if the parameter is null.
	*
	* @return pointer for the new root of the rotated subtree.
	*/
    public BSTNode rotateRR(BSTNode A) throws NullPointerException {
    	// Throw NullPointerException if A is null
    	if (A==null) {
    		throw new NullPointerException("Cannot rotate null node.");
    	}
        BSTNode B = A.right; // copy right child of A
     	A.right = B.left; // right point A to left child of B
        B.left = A; // left point B to A
        // Update height of rotated nodes
        A.updateHeight();
        B.updateHeight();
        // If A was the root of the entire mountain
        if (root==A) {
        	root=B; // reset root to B
        }
        return B; // return new root of the rotated subtree
    }
    
    /**
	 * Constructs empty mountain.
	 */
    public BSTMountain () {
    	root = null;
	}
    
    /**
	 * Adds the specified RestStop to this tree if it is not already present. 
	 * If this tree already contains the RestStop, the call leaves the 
     * tree unchanged and returns false.
     * 
     * This method was inspired by a method written by Joanna Klukowska.
     * 
	 * @param data RestStop object to be added to this tree.
     * @return true if this tree did not already contain the specified element,
     * and false if the node could not be added.
     * @throws NullPointerException if the specified element is null. 
	 */
    public boolean add ( RestStop data ) throws NullPointerException {
    	
    	// If the data is null, throw a NullPointerExceeption
    	if (data==null) {
    		throw new NullPointerException("Cannot add null.");
    	}
    	
    	// Create new node with the RestStop
    	BSTNode node = new BSTNode(data);
    	node.setHeight(0); // set its height to 0
    	
    	// Call to recursive add method with the root of the mountain
        return addRec(root, node);
    }
    
    /**
	 * Recursive add method called by the helper add method.
     * 
     * @param root node root of the current subtree.
	 * @param data node to be added to this mountain.
     * @return true if mountain did not already contain the specified element,
     * and false if the node could not be added.
	 */
    public boolean addRec (BSTNode root, BSTNode data) {
    	
    	// Create boolean to be returned
    	boolean added;
    	
    	// If the mountain is empty
		if (this.root == null) {
			this.root = data; // add data at the root of the mountain
			data.setLevel(0); // set the level of data to 0
    		return true; // data successfully added to mountain
    	}
		// Mountain is not empty and data is already in the mountain
		else if (data.compareTo(root)==0) {
    		return false; // data not added to the mountain
    	}
    	// If data is less than the root
    	else if (data.compareTo(root) < 0) {
    		// And root does not have a left child
    		if (root.left == null) {
    			root.left = data; // left point root to data
    		}
    		// If root does have a left child
    		else {
    			// Make the left recursive call
    			added = addRec(root.left, data);
    			// Check the balance factor of the root's left child
    			if (root.left.balanceFactor()==-2) {
    				if (root.left.left.balanceFactor()<0) {
    					// left left rotation of left child of root
    					root.left=rotateLL(root.left);
    				}
    				else {
    					// left right rotation of left child of root
    					root.left=rotateLR(root.left);
    				}
    			}
    			if (root.left.balanceFactor()==2) {
    				if (root.left.right.balanceFactor()<0) {
    					// right left rotation of left child of root
    					root.left=rotateRL(root.left);
    				}
    				else {
    					// right right rotation of left child of root
    					root.left=rotateRR(root.left);
    				}
    			}
    			root.updateHeight(); // update root height after any rotations
    			// If root is the root of the entire mountain
    			if (this.root==root) {
    				// Check its balance factor
	    			if (root.balanceFactor()==-2) {
	    				if (root.left.balanceFactor()<0) {
	    					root=rotateLL(root); // rotate root left left 
	    				}
	    				else {
	    					root=rotateLR(root);  // rotate root left right 
	    				}
	    			}
	    			if (root.balanceFactor()==2) {
	    				if (root.right.balanceFactor()<0) {
	    					root=rotateRL(root);  // rotate root right left 
	    				}
	    				else {
	    					root=rotateRR(root);  // rotate root right left
	    				}
	    			}
    			}
    			return added; // return true or false if data was added or not
    		}
    	}
    	// If data is larger than root
    	else if (data.compareTo(root) > 0) {
    		// And if the mountain is empty
    		if (root.right == null) {
    			root.right = data; // make data the root of the mountain
    		}
    		// Mountain is not empty
    		else {
    			// Make right recursive call
    			added = addRec(root.right, data);
    			// Check balance factor of root's right child
    			if (root.right.balanceFactor()==-2) {
    				if (root.right.left.balanceFactor()<0) {
    					root.right=rotateLL(root.right); // rotate left left
    				}
    				else {
    					root.right=rotateLR(root.right);  // rotate left right
    				}
    			}
    			if (root.right.balanceFactor()==2) {
    				if (root.right.right.balanceFactor()<0) {
    					root.right=rotateRL(root.right);  // rotate right left
    				}
    				else {
    					root.right=rotateRR(root.right);  // rotate right right
    				}
    			}
    			// Update height of root after any rotations
    			root.updateHeight();
    			// If root is the root of the entire mountain
    			if (this.root==root) {
    				// Check its balance factor
	    			if (root.balanceFactor()==-2) {
	    				if (root.left.balanceFactor()<0) {
	    					root=rotateLL(root);  // rotate left left
	    				}
	    				else {
	    					root=rotateLR(root);  // rotate left right
	    				}
	    			}
	    			if (root.balanceFactor()==2) {
	    				if (root.right.balanceFactor()<0) {
	    					root=rotateRL(root);  // rotate right left
	    				}
	    				else {
	    					root=rotateRR(root);  // rotate right right
	    				}
	    			}
    			}
    			return added; // return true or false if data was added or not
    		}
    	}
		// Update root's height after any rotations
		root.updateHeight();
    	return true; // data was successfully added to the tree
    }
    
    /**
	 * Helper method of the recursive traverse method.
	 * Sends a hiker down a mountain and finds successful paths.
     * 
	 * @param hiker Hiker to travel down the mountain.
	 * @param mountain Mountain for the hiker to traverse.
     * @return String of all successful paths down the mountain.
	 */
	public String traverse (Hiker hiker, BSTMountain mountain) {
		// Depth of the mountain equals the height of its root
		mountain.depth=root.height;
		// If the mountain is empty
		if (mountain.root == null) {
			return ""; // There are no solutions
		}
		else {
			// Call recursive traversal with the root of the mountain,
			// mountain, hiker, and an empty solutions string
			return traverseRec(mountain.root, mountain, hiker, "");
		}
	}
	
    /**
	 * Recursive traversal method called by the helper.
     * 
     * @param node node of the current subtree.
	 * @param mountain BSTMountain to be traversed.
	 * @param hiker Hiker to traverse the mountain.
	 * @param solutions String of all successful paths found so far.
     * @return string of successful paths down the mountain.
	 */
	public String traverseRec (BSTNode node, BSTMountain mountain, Hiker hiker,
			String solutions) {
		// If the solutions string is empty
		if (solutions.equals("")) {
			// add the label of the current RestStop to solutions
			solutions += node.data.getLabel();
		}
		else {
			// Add the label to solutions but with a space in front
			solutions += " " + node.data.getLabel();
		}
		
		// If the node has never been visited before
		if (node.visited==false) {
			// Add the reststop's supplies to the hiker's supplies
			hiker.addSupplies(node.data.getSupplies());
			// Reset the reststop's supplies to the hiker's supplies,
			// which now contain the reststop's original supplies as well
			node.data.setSupplies(hiker.getSupplies());
			node.setVisited(); // node has now been visited
		}
		// If the node has been visited before
		else {
			// Hiker resets supplies to the reststop's supplies
			hiker.setSupplies(node.data.getSupplies());
		}
		
		// Create true boolean variables for existence of rivers and trees
		boolean trees = true;
		boolean rivers = true;
		
		// Hiker attempts to cross obstacles as long as the rest stop has them
		do {
			// If there is a tree at the node
			if (node.data.getObstacles().contains("fallen tree")) {
				// If the hiker has an axe
				if (hiker.getSupplies().contains("axe")) {
					// Remove the tree obstacle from the reststop
					node.data.removeObstacle("fallen tree");
					// And remove the axe from the hikers supplies
					hiker.removeSupply("axe");
				}
				// If hiker has no axe
				else {
					return null; // not a solution
				}
			}
			else {
				trees = false; // no more trees at the reststop
			}
			// If there is a river at the node
			if (node.data.getObstacles().contains("river")) {
				// If the hiker has a raft
				if (hiker.getSupplies().contains("raft")) {
					// Remove the river obstacle from the reststop
					node.data.removeObstacle("river");
					// And remove the raft from the hikers supplies
					hiker.removeSupply("raft");	
				}
				// If hiker has no raft
				else {
					return null; // not a solution
				}
			}
			else {
				rivers = false; // no more rivers at the reststop
			}
		} while (trees==true || rivers==true);
		
		// Now the hiker has crossed all the reststop's obstacles
		
		// If the hiker is at a leaf
		if (node.left==null && node.right==null) {
			// If the leaf is a cliff
			if (!(node.level==mountain.depth)) {
				return null;
			}
			// If the leaf is the bottom of the mountain
			else if (!(mountain.solutions.equals(""))) {
				mountain.solutions += "\n"; // add a new line to solutions
			}
			// Add the path to the mountain's solutions
			mountain.solutions += solutions;
			return solutions; 
		}
		
		// Not a leaf, hiker must eat food before going on
		if (hiker.getSupplies().contains("food")) {
			// remove food from hiker's supplies if there is any
			hiker.removeSupply("food");
		}
		// Otherwise run out of food, not a solution
		else {
			return null;
		}
		
		// If node has a left child
		if (!(node.left==null)) {
			node.left.level = node.level+1; // update left child's level
			// If the hiker has never visited the left child before
			if (node.left.visited==false) {
				// Add the hikers supplies to the reststop's supplies
				node.left.data.addSupplies(hiker.getSupplies());
				node.left.setVisited(); // the node has now been visited
			}
			// Make left recursive call
			traverseRec(node.left, mountain, hiker, solutions);
		}
		
		// If node has a right child
		if (!(node.right==null)) {
			node.right.level=node.level+1; // update right child's level
			if (node.visited==true) {
				// If the node's been visited, reset the hiker to that rest stop
				hiker.setSupplies(node.data.getSupplies());
			}
			
			// If the hiker has never visited the right child before
			if (node.right.visited==false) {
				// Add the hikers supplies to the rest stop
				node.right.data.addSupplies(hiker.getSupplies());
				node.right.setVisited(); // Node has now been visited
			}
			else {
				// If node has been visited before, reset hiker to that rest stop
				hiker.setSupplies(node.data.getSupplies());
			}
			// Make right recursive call
			traverseRec(node.right, mountain, hiker, solutions);
		}
		return mountain.solutions; // return string of all solutions
	}
}