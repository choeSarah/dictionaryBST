package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;


/**
 * Represents a generic binary search tree
 * @author Sarah Choe and Ishita Juluru
 * @version March 23, 2023
 *
 * @param <Type>
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>
 {
	/**
	 * Represents a node in the BST
	 *
	 * @param <Type>
	 */
	public static class Node<Type> {
		Type data;
		Node <Type> left; //left child
		Node <Type> right; //right child
		Node <Type> parent; //parent node

		/**
		 * Constructor for the node class
		 * @param data
		 */
		public Node (Type data) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.parent = null;
			
		}
	}
	
	public Node <Type> root; //root node
	private int size = size(); //size of the BST
	
	/**
	 * Constructor for the BST; initializes the root to be null
	 */
	public BinarySearchTree() {
		root = new Node<Type>(null);
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually inserted); otherwise, returns false
	 */
	@Override
	public boolean add(Type item) {
		if (root.data == null) { //if the BST is empty
			root.data = item; //set the added item to be the root
			return true; 
		}
		int count = size; //save the current size 
		
		add(root, item);
		
		return count != size ; //if the size was altered, the element was added
	}
	
	/**
	 * Private method for the add function
	 * @param traverseNode
	 * @param item
	 */
	private void add(Node <Type> traverseNode, Type item) {
		while (traverseNode!=null) {
			if (traverseNode.data.equals(item)) { //if the item is already in the set
				return; //size is not altered
			}
			
			if (item.compareTo(traverseNode.data) < 0) { //traverse to the left
				if (traverseNode.left != null) { //left already exists
					traverseNode = traverseNode.left; //further traversing
				} else {
					traverseNode.left = new Node <Type> (item); //initialize the left
					traverseNode.left.parent = traverseNode; //set the parent
					size++; //increment the size
					return;
				}
			} 
			
			if (item.compareTo(traverseNode.data) > 0) { //traverse to the right
				if (traverseNode.right != null) { //right already exists
					traverseNode = traverseNode.right; //further traversing
				} else {
					traverseNode.right = new Node <Type> (item); //initialize the right
					traverseNode.right.parent = traverseNode; //set the parent
					size++; //increment the size
					return;
				}
			} 
		}
		
		return;
	}
	
	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items - the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually inserted); otherwise,
	 *         returns false
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean addedAll = true;
		
		for (Type element : items) { //for each element in items
			if (add(element) == false) { //if one element can't be added
				addedAll = false;
			}
		}
		return addedAll;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		root.data = null;
		root.left = null;
		root.right = null;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input item;
	 *         otherwise, returns false
	 */
	@Override
	public boolean contains(Type item) {
		if (root.data == null) { //if the tree is empty, nothing can be inside
			return false;
		}
		
		Node <Type> traverseNode = root;
		while (traverseNode != null) {
			if (item.equals(traverseNode.data)) { //if node with item is found
				return true; 
			} else if (item.compareTo(traverseNode.data) < 0) { //if item is smaller than node
				traverseNode = traverseNode.left; //traverse left
			} else { //if item is larger than node
				traverseNode = traverseNode.right; //traverse right
			}
		}
		return false; //method couldn't find the item
	}

	/**
	 * Determines if for each item in the specified collection, there is an item in
	 * this set that is equal to it.
	 * 
	 * @param items - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		boolean containsAll = true;
		for (Type element: items) { //for each element in items
			if (contains(element) == false) { //if an element can't be found
				containsAll = false;
			}
		}
		return containsAll;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		Node <Type> traverseNode = root;
		
		if (isEmpty() == true) { //if the tree is empty
			throw new NoSuchElementException(); //throw an exception
		}

		while (traverseNode.left != null) { //traverse left
			traverseNode = traverseNode.left;
		}
		return traverseNode.data;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		if (root == null || root.data == null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		Node <Type> traverseNode = root;
		
		if (isEmpty() == true) { //if tree is empty
			throw new NoSuchElementException(); //throw an exception
		}

		while (traverseNode.right != null) { //traverse right
			traverseNode = traverseNode.right;
		}
		return traverseNode.data;
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually removed); otherwise, returns false
	 */
	@Override
	public boolean remove(Type item) {
		//if the tree is empty
		if (isEmpty() == true) {
			return false;
		}
		
		//if the item is not in the tree
		if (contains(item) == false) {
			return false;
		}
		int count = size; //save the current size;
		
		delete (root, item);
		
		return count!=size; //if the size was altered, it was removed
		
	}
	
	/**
	 * Private method for remove that goes in the tree and delete nodes
	 * @param traverseNode
	 * @param item
	 */
	private void delete (Node<Type> traverseNode, Type item) {

		if (item.compareTo(traverseNode.data) < 0) { //traverse to the left if needed
			delete(traverseNode.left, item);
		} else if (item.compareTo(traverseNode.data) > 0) { //traverse to the right if needed
			delete(traverseNode.right, item);

		} else if (item.compareTo(traverseNode.data) == 0) { //a match was found

			if (traverseNode.left == null && traverseNode.right == null) { //node is a leaf
				
				if (traverseNode.parent != null) { //if the node has parents
					if (traverseNode.parent.left != null && traverseNode.parent.left.equals(traverseNode)) { //if the node is a left child
						traverseNode.parent.left = null; 
					} else if (traverseNode.parent.right != null &&  traverseNode.parent.right.equals(traverseNode)){ //if the node is a right child
						traverseNode.parent.right = null;
					}	
				} else { //if the node is a root
					root = null;
				}
				
			}
			
			else if (traverseNode.left == null && traverseNode.right != null) { //node has a right child
				Node <Type> parent = traverseNode.parent;
				
				if (parent != null && parent.left != null && parent.left.equals(traverseNode)) { //node is a left child
					parent.left = traverseNode.right;
					traverseNode.right.parent = traverseNode.parent;
				} else if (parent != null && parent.right != null && parent.right.equals(traverseNode)) { //node is a right child
					parent.right = traverseNode.right;
					traverseNode.right.parent = traverseNode.parent;

				} else { //is a root
					traverseNode.right.parent = traverseNode.parent;
					root = traverseNode.right;

				}
				
			} else if (traverseNode.right == null && traverseNode.left != null) { //node has a left child
				Node <Type> parent = traverseNode.parent;
				
				if (parent != null && parent.left != null && parent.left.equals(traverseNode)) { //node is a left child
					parent.left = traverseNode.left;
					traverseNode.left.parent = traverseNode.parent;
				} else if (parent != null && parent.right != null && parent.right.equals(traverseNode)) { //node is a right child
					parent.right = traverseNode.left;
					traverseNode.left.parent = traverseNode.parent;


				} else { //root case
					traverseNode.left.parent = traverseNode.parent;
					root = traverseNode.left;
				}

			}
			
			else if (traverseNode.left != null && traverseNode.right !=null) { //node has two children
				Node <Type>  successor = successor (traverseNode); //find the successor
				Type data = successor.data; //save its data
				delete (successor, successor.data); //delete the successor
				traverseNode.data = data; //set the node's value to be the successor's data

			}
			
			size--; //change the size;
			return;
		}
		
		if (traverseNode.left == null && traverseNode.right == null) {
			return;
		}
		 return;
	}
	
	/**
	 * Helper method that finds the sucessor of a node
	 * @param node
	 * @return
	 */
	private Node <Type> successor(Node <Type> node) {
		if (node.right != null) { 
			node = node.right;

		}
		
		while (node.left != null) {
			node = node.left;
		}
		
		return node;
	}
	
	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually removed); otherwise,
	 *         returns false
	 */
	@Override

	public boolean removeAll(Collection<? extends Type> items) {
		boolean removedAll = false;
		
		for (Type element : items) {
			if (remove(element) == true) {
				removedAll = true;
			}
		}
		return removedAll;
	}
	

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size(root);
	}
	
	private int size(Node <Type> traverseNode) {
		if (traverseNode == null || traverseNode.data == null) {
			return 0;
		}
		
		return 1 + size(traverseNode.left) + size(traverseNode.right);
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList <Type> allItems = new ArrayList <Type>();
		Node <Type> traverseNode = root;
		
		inOrderTraversal(traverseNode, allItems, 0);
		
		return allItems;
	}
	
	/**
	 * Helper method that adds the node data in the BST to an ArrayList in order
	 * @param traverseNode
	 * @param allItems
	 * @param index
	 */
	private void inOrderTraversal(Node <Type> traverseNode, ArrayList <Type> allItems, int index) {
		if (traverseNode == null || traverseNode.data == null) { //if there is nothing in the tree, add nothing to the arraylist
			return;
		}
		
		inOrderTraversal(traverseNode.left, allItems, index); // travel to the left
		allItems.add(traverseNode.data);
		inOrderTraversal(traverseNode.right, allItems, index); // travel to the right

		
	}
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 * 
	 * @return String
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("graph d {\n");
		ArrayList <Node<Type>> allNodes = allNodes();
		
		// for every vertex 
		for(Node <Type> node : allNodes) {
			if (node.left != null) {
				dot.append("\t\"" + node.data + "\" -- \"" + node.left.data + "\"\n");
			} 
			
			if (node.right != null) {
				dot.append("\t\"" + node.data + "\" -- \"" + node.right.data + "\"\n");
			}

		}
		
		return dot.toString() + "}";
	}
	
	/**
	 * Returns an ArrayList containing all of the nodes in this set, in sorted
	 * order.
	 * 
	 * @return
	 */
	private ArrayList<Node<Type>> allNodes() {
		ArrayList <Node<Type>> allItems = new ArrayList <Node<Type>>();
		Node <Type> traverseNode = root;
		
		traversal(traverseNode, allItems, 0);
		
		return allItems;
	}

	/**
	 * Helper method that adds the nodes in the BST to an ArrayList in order
	 * @param traverseNode
	 * @param allItems
	 * @param index
	 */
	private void traversal(Node<Type> traverseNode, ArrayList<Node<Type>> allItems, int index) {
		if (traverseNode == null || traverseNode.data == null) { //if there is nothing in the tree, add nothing to the arraylist
			return;
		}
		
		traversal(traverseNode.left, allItems, index); // travel to the left
		allItems.add(traverseNode);
		traversal(traverseNode.right, allItems, index); // travel to the right
		
	}


}
