/**
 * BST.java
 * @author Khang Nguyen
     * @author Surafel Abebe
     * @author Hazel
     * @author Rong Jin
     * @author Kang Su
     * @author Berenice Castro
     * CIS 22C Final Project
 */

import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {

	private class Node {
		private T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;

	/*** CONSTRUCTORS ***/
	/**
	 * Default constructor for BST sets root to null
	 */
	public BST() {
		root = null;
	}

	/**
	 * Copy constructor for BST
	 * 
	 * @param bst the BST to make a copy of
	 */
	public BST(BST<T> bst) {
		if (bst == null) {
			root = null;
		} else {
			copyHelper(bst.root);
		}
	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing data to copy
	 */
	private void copyHelper(Node node) {
		if (node != null) {
			insert(node.data);
			copyHelper(node.left);
			copyHelper(node.right);
		}
	}

	/**
	 * Creates a BST of minimal height given an array of values
	 * 
	 * @param array the list of values to insert
	 * @precondition array must be sorted in ascending order
	 * @throws IllegalArgumentException when the array is unsorted
	 */
	public BST(T[] array) {
		if (array == null) {
			return;
		} else {
			sortArray(array);
			root = balance(array, 0, array.length - 1);

		}
	}

	/**
	 * Helper method that uses to sort the User array
	 * 
	 * @param array array stores user object
	 */
	@SuppressWarnings("unchecked")
	private void sortArray(T[] array) {
		int n = array.length;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (array[j - 1].compareTo(array[j]) > 0) {
					// swap elements
					User temp = (User) array[j - 1];
					array[j - 1] = array[j];
					array[j] = (T) temp;
				}

			}
		}
	}

	/**
	 * Helper method check if array is sorted
	 * 
	 * @param array the array that need to check
	 * @return boolean if array is sorted
	 */
	@SuppressWarnings("unused")
	private boolean isSorted(T[] array) {
		if (array.length <= 1) {
			return true;
		}
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i].compareTo(array[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Helper method to recursive copy the array to BST
	 * 
	 * @param array the array that use to copy from
	 * @param low   minimum index
	 * @param hi    maximum index
	 * @return the Node that store in BST
	 */
	private Node balance(T[] array, int low, int hi) {
		if (low > hi) {
			return null;
		}
		int mid = low + (hi - low) / 2;
		Node node = new Node(array[mid]);
		node.left = balance(array, low, mid - 1);
		node.right = balance(array, mid + 1, hi);
		return node;
	}

	/*** ACCESSORS ***/

	/**
	 * 
	 * Returns the data stored in the root
	 * 
	 * 
	 * 
	 * @precondition !isEmpty()
	 * 
	 * @return the data stored in the root
	 * 
	 * @throws NoSuchElementException when precondition is violated
	 * 
	 */

	public T getRoot() throws NoSuchElementException {

		if (root == null) {
			throw new NoSuchElementException("The tree is empty");
		}
		return root.data;

	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */

	public boolean isEmpty() {
		if (root == null) {
			return true;
		}

		return false;

	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */

	public int getSize() {
		return getSize(root);

	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */

	private int getSize(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + getSize(node.left) + getSize(node.right);

	}

	/**
	 * Returns the height of tree by counting edges.
	 * 
	 * @return the height of the tree
	 */

	public int getHeight() {
		return getHeight(root);
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */

	private int getHeight(Node node) {

		if (node == null) {
			return -1;
		} else {
			int L = getHeight(node.left);
			int R = getHeight(node.right);
			if (L >= R) {
				return L + 1;
			} else {
				return R + 1;
			}
		}
	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */

	public T findMin() throws NoSuchElementException {

		if (isEmpty()) {

			throw new NoSuchElementException("The tree is empty");

		}

		return findMin(root);

	}

	/**
	 * Helper method to findMin method
	 * 
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */

	private T findMin(Node node) {
		if (node.left == null) {
			return node.data;
		} else {
			return findMin(node.left);
		}
	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */

	public T findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("The tree is empty");
		}
		return findMax(root);
	}

	/**
	 * Helper method to findMax method
	 * 
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */

	private T findMax(Node node) {
		if (node.right != null) {
			return findMax(node.right);
		}
		return node.data;
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 */

	public void insert(T data) { // BST
		if (root == null) {
			root = new Node(data);
		} else {
			insert(data, root);
		}
	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */

	private void insert(T data, Node node) { // BST
		if (data.compareTo(node.data) < 0) {
			if (node.left == null) {
				Node n = new Node(data);
				node.left = n;
			} else {
				insert(data, node.left);
			}
		} else {
			if (node.right == null) {
				Node n = new Node(data);
				node.right = n;
			} else {
				insert(data, node.right);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void insertFriend(User newFriend, int originUserId) {
		if (root == null) {
			root = new Node((T) newFriend);
		} else {
			insertFriend(newFriend, root, originUserId);
		}
	}
	
	@SuppressWarnings("unused")
	private void insertFriend(User newFriend, Node node, int originUserId) { // BST
		User user = (User) node.data;
		if(node == null) {
			return;
		}
		insertFriend(newFriend,node.left, originUserId);
		if(user.getId() == originUserId) {
			user.getFriends().insert(newFriend);
			return;
		}
		insertFriend(newFriend,node.right, originUserId);
	}
	/**
	 * Removes a value from the BST
	 * 
	 * @param data the value to remove
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws IllegalStateException when BST is empty
	 */

	public void remove(T data) throws IllegalStateException { // BST

		if (isEmpty()) {
			throw new IllegalStateException("The tree is empty");
		}
		root = remove(data, root);
	}

	/**
	 * Helper method to the remove method
	 *
	 * @param data the data to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */

	private Node remove(T data, Node node) { // BST
		if (node == null) {
			return null;
		} else if (data.compareTo(node.data) < 0) {
			node.left = remove(data, node.left);
		} else if (data.compareTo(node.data) > 0) {
			node.right = remove(data, node.right);
		} else {
			if (node.left == null && node.right == null) {
				node = null;

			} else if (node.left == null) {
				node = node.right;
			} else if (node.right == null) {
				node = node.left;
			} else {
				T temp = findMin(node.right);
				node.data = temp;
				node.right = remove(node.data, node.right);
			}
		}
		return node;

	}

	/*** ADDITIONAL OPERATIONS ***/
	/**
	 * Searches for a specified value in the tree
	 *
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */

	public boolean search(T data) { // BST

		if (root == null) {
			return false;

		}
		return search(data, root);

	}

	/**
	 * Helper method for the search method
	 *
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */

	private boolean search(T data, Node node) { // BST
		if (data.compareTo(node.data) == 0) {
			return true;
		} else if (data.compareTo(node.data) < 0) {
			if (node.left == null) {
				return false;
			} else {
				return search(data, node.left);
			}
		} else {
			if (node.right == null) {
				return false;
			} else {
				return search(data, node.right);
			}
		}
	}

	/**
	 * Determines whether a BST is balanced using the definition given in the course
	 * lesson notes Note that we will consider an empty tree to be trivially
	 * balanced
	 * 
	 * @return whether the BST is balanced
	 */

	public boolean isBalanced() {
		if (root == null) {
			return true;
		}
		return isBalanced(root);
	}

	/**
	 * Helper method for isBalanced to determine if a BST is balanced
	 * 
	 * @param n a Node in the tree
	 * @return whether the BST is balanced at the level of the given Node
	 */

	private boolean isBalanced(Node n) {
		if (n != null) {
			if (Math.abs(getHeight(n.left) - getHeight(n.right)) > 1) {
				return false;
			}
			return isBalanced(n.left) && isBalanced(n.right);
		}
		return true;
	}

	/**
	 * Returns a String containing the data in post order
	 * 
	 * @return a String of data in post order
	 */

	public String preOrderString() { // BST
		StringBuilder N = new StringBuilder();
		preOrderString(root, N);
		N.append("\n");
		return N.toString();

	}

	/**
	 * Helper method to preOrderString Inserts the data in pre order into a String
	 * 
	 * @param node     the current Node
	 * @param preOrder a String containing the data
	 */
	private void preOrderString(Node node, StringBuilder preOrder) {// BST
		if (node == null) {
			return;
		} else {
			preOrder.append(node.data);
			preOrderString(node.left, preOrder);
			preOrderString(node.right, preOrder);
		}
	}

	/**
	 * Returns a String containing the data in order
	 * 
	 * @return a String of data in order
	 */

	public String inOrderString() { // BST
		StringBuilder N = new StringBuilder();
		inOrderString(root, N);
		N.append("\n");
		return N.toString();

	}

	/**
	 * Helper method to inOrderString Inserts the data in order into a String
	 * 
	 * @param node    the current Node
	 * @param inOrder a String containing the data
	 */

	private void inOrderString(Node node, StringBuilder inOrder) {// BST
		if (node == null) {
			return;
		} else {
			inOrderString(node.left, inOrder);
			inOrder.append(((User) node.data).toString());
			// inOrder.append(" ");
			inOrderString(node.right, inOrder);
		}
	}

	/**
	 * Returns a String containing the data in post order
	 * 
	 * @return a String of data in post order
	 */

	public String postOrderString() { // BST

		StringBuilder N = new StringBuilder();
		postOrderString(root, N);
		N.append("\n");
		return N.toString();

	}

	/**
	 * Helper method to postOrderString Inserts the data in post order into a String
	 *
	 * @param node      the current Node
	 * @param postOrder a String containing the data
	 */

	private void postOrderString(Node node, StringBuilder postOrder) { // BST
		if (node == null) {
			return;
		} else {

			postOrderString(node.left, postOrder);

			postOrderString(node.right, postOrder);

			postOrder.append(node.data);
			postOrder.append(" ");

		}

	}

	/**
	 * ADDITIONAL METHOD FOR USER OBJECT
	 */

	/**
	 * Use only for the friend list of user object
	 * 
	 * @return the String with all friends
	 */
	public String FriendListInorderToString() {
		StringBuilder N = new StringBuilder();
		FriendListInorderToString(root, N);
		return N.toString();
	}

	/**
	 * Helper method for FriendListInorderToString()
	 * 
	 * @param node the current node
	 * @param n    the StringBuilder that will append the res
	 */
	private void FriendListInorderToString(Node node, StringBuilder n) {
		if (node == null) {
			return;
		} else {
			FriendListInorderToString(node.left, n);
			n.append(((User) node.data).getFirstName() + " " + ((User) node.data).getLastName() + ", ");
			FriendListInorderToString(node.right, n);
		}
	}
	/**
	 * Look for user with the first name and last name
	 * 
	 * @param firstName the first name of the user that looking for
	 * @param lastName  the last name of the user that looking for
	 * @return the User object
	 */
	public User searchByName(String firstName, String lastName) {

		if (root == null) {
			return null;

		}
		User user = new User(firstName, lastName);
		Node temp = root;
		return searchForUserByName(user, temp);

	}

	/**
	 * Helper method for the searchByName method
	 *
	 * @param User the user to search for
	 * @param node the current node to check
	 * @return the user if that store in the tree
	 */

	private User searchForUserByName(User originUser, Node node) {
		User user = (User) node.data;

		if (originUser.compareTo(user) == 0) {
			return user;
		} else if (originUser.compareTo(user) < 0) {
			if (node.left == null) {
				return null;
			} else {
				return searchForUserByName(originUser, node.left);
			}
		} else {
			if (node.right == null) {
				return null;
			} else {
				return searchForUserByName(originUser, node.right);
			}
		}
	}
	
	public LinkedList<User> makeNewFriend(String fName, String lName) {
		LinkedList<User> list = new LinkedList<>();
		User originUser = new User(fName, lName);
		Node temp = root;
		makeNewFriend(originUser,list, temp);
		return list;
	}
	
	private void makeNewFriend(User originUser, LinkedList<User> list, Node node) {
		if (node == null) {
			return;
		}
		else {
			User user = (User) node.data;
			makeNewFriend(originUser, list, node.left);
			
			if(originUser.compareTo(user)== 0) {
				list.addLast(((User) node.data));
			}
			makeNewFriend(originUser, list, node.right);
			
		}
	}
	
}
	

