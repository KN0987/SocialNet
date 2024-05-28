/**
 * LinkedList.java
 * @author Khang Nguyen
     * @author Surafel Abebe
     * @author Hazel
     * @author Rong Jin
     * @author Kang Su
     * @author Berenice Castro
     * CIS 22C Final Project
 */
import java.util.NoSuchElementException;

public class LinkedList<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTORS ****/
	/**
	 * Instantiates a new LinkedList with default values
	 * 
	 * @postcondition create a linked list
	 */
	public LinkedList() {
		length = 0;
		first = null;
		last = null;
		iterator = null;
	}

	/**
	 * Converts the given array into a LinkedList
	 * 
	 * @param array the array of values to insert into this LinkedList
	 * @postcondition new linked list is created with data in given array
	 */
	public LinkedList(T[] array) {

		if (array == null) {
			return;
		} else if (array.length == 0) {
			length = 0;
			first = null;
			last = null;
			iterator = null;
		} else {
			for (T data : array) {
				addLast(data);
			}
		}
	}

	/**
	 * Instantiates a new LinkedList by copying another List
	 * 
	 * @param original the LinkedList to copy
	 * @postcondition a new List object, which is an identical, but separate, copy
	 *                of the LinkedList original
	 */
	public LinkedList(LinkedList<T> original) {
		if (original == null) {
			return;
		}
		if (original.length == 0) {
			length = 0;
			first = null;
			last = null;
			iterator = null;
		} else {
			Node temp = original.first;
			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}
			iterator = null;
		}
	}

	/**** ACCESSORS ****/
	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition length cannot be zero
	 * @return the value stored at node first
	 * @throws NoSuchElementException when length = 0
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst: first is null");
		}
		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition length cannot be zero
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when length = 0
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast: last is null");
		}
		return last.data;
	}

	/**
	 * Returns the data stored in the iterator node
	 * 
	 * @precondition iterator is not off-end
	 * @throw NullPointerException when iterator is null
	 */
	public T getIterator() throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("getIterator: iterator is null");
		}
		return iterator.data;
	}

	/**
	 * Returns the current length of the LinkedList
	 * 
	 * @return the length of the LinkedList from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the LinkedList is currently empty
	 * 
	 * @return whether the LinkedList is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Returns whether the iterator is offEnd, i.e. null
	 * 
	 * @return whether the iterator is null
	 */
	public boolean offEnd() {
		return iterator == null;
	}

	/**** MUTATORS ****/
	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the LinkedList
	 * @postcondition new first node added
	 */
	public void addFirst(T data) {
		Node temp = new Node(data);
		if (length == 0) {
			first = last = temp;
		} else {
			first.prev = temp;
			temp.next = first;
			first = temp;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the LinkedList
	 * @postcondition new last node added
	 */
	public void addLast(T data) {
		Node temp = new Node(data);
		if (length == 0) {
			first = last = temp;
		} else {
			last.next = temp;
			temp.prev = last;
			last = temp;
		}
		length++;
	}

	/**
	 * Inserts a new element after the iterator
	 * 
	 * @param data the data to insert
	 * @precondition iterator cannot be null
	 * @throws NullPointerException throw exception when iterator is null
	 */
	public void addIterator(T data) throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("addIterator: iterator is offend. Cannot add");
		} else if (iterator == last) {
			addLast(data);
		} else {
			Node n = new Node(data);
			n.prev = iterator;
			n.next = iterator.next;
			iterator.next.prev = n;
			iterator.next = n;
		}
		length++;
	}

	/**
	 * removes the element at the front of the LinkedList
	 * 
	 * @precondition length != 0
	 * @postcondition remove the first node, and have the original second first node
	 *                to be the first node
	 * @throws NoSuchElementException if length == 0
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst: list is empty");
		} else if (length == 1) {
			first = last = null;
		} else {
			if (iterator == first) {
				iterator = null;
			}
			first = first.next;
			first.prev = null;
		}
		length--;
	}

	/**
	 * removes the element at the end of the LinkedList
	 * 
	 * @precondition length != 0
	 * @postcondition remove the last one
	 * @throws NoSuchElementException if length == 0
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast: list is empty");
		} else if (length == 1) {
			first = last = null;
		} else {
			if (iterator == last) {
				iterator = null;
			}
			last = last.prev;
			last.next = null;
		}
		length--;
	}

	/**
	 * removes the element referenced by the iterator
	 * 
	 * @precondition iterator cannot be null
	 * @postcondition remove the node where iterator is currently at
	 * @throws NullPointerException when iterator is null
	 */
	public void removeIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("removeIterator: iterator is null. Cannot remove");
		} else if (iterator == last) {
			removeLast();
		} else if (iterator == first) {
			removeFirst();
		} else {
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			length--;
		}
		iterator = null;
	}

	/**
	 * places the iterator at the first node
	 * 
	 * @precondition <No need because if length == 0, first = null by default, so
	 *               this code will cost no error>
	 * @postcondition <set iterator same memory address with first>
	 * @throws NullPointerException <No need to throw Exception>
	 */
	public void positionIterator() {
		iterator = first;
	}

	/**
	 * Moves the iterator one node towards the last
	 * 
	 * @precondition iterator is null
	 * @postcondition move the iterator to next Node
	 * @throws NullPointerException when iterator is offEnd
	 */
	public void advanceIterator() throws NullPointerException {
		if (offEnd()) { // precondition
			throw new NullPointerException("advanceIterator: iterator is null. Cannot advance");
		}
		iterator = iterator.next;
	}

	/**
	 * Moves the iterator one node towards the first
	 * 
	 * @precondition iterator is null
	 * @postcondition iterator move back to previous node
	 * @throws NullPointerException if iterator is null
	 */
	public void reverseIterator() throws NullPointerException {
		if (offEnd()) { // precondition
			throw new NullPointerException("reverseIterator: iterator is null. Cannot reverse");
		}
		iterator = iterator.prev;
	}

	/**** ADDITIONAL OPERATIONS ****/
	/**
	 * Converts the LinkedList to a String, with each value separated by a blank
	 * line At the end of the String, place a new line character
	 * 
	 * @return the LinkedList as a String
	 */
	@Override
	public String toString() {
		String output = "";
		Node temp = first;
		while (temp != null) {
			output += temp.data;
			temp = temp.next;
		}
		return output + "\n";
	}

	/**
	 * Determines whether the given Object is another LinkedList, containing the
	 * same data in the same order
	 * 
	 * @param o another Object
	 * @return whether there is equality
	 */
	@SuppressWarnings("unchecked") // good practice to remove warning here
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof LinkedList)) {
			return false;
		} else {
			LinkedList<T> oCast = (LinkedList<T>) o;
			if (this.length != oCast.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = oCast.first;
				while (temp1.data == temp2.data) {
					if (temp1 == last) {
						return true;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return false;
			}
		}
	}

	/** CHALLENGE METHODS */
	/**
	 * Zippers two LinkedLists to create a third List which contains alternating
	 * values from this list and the given parameter For example: [1,2,3] and
	 * [4,5,6] -> [1,4,2,5,3,6] For example: [1, 2, 3, 4] and [5, 6] -> [1, 5, 2, 6,
	 * 3, 4] For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
	 * 
	 * @param list the second LinkedList in the zipper
	 * @return a new LinkedList, which is the result of zippering this and list
	 * @postcondition this and list are unchanged
	 */
	public LinkedList<T> zipperLists(LinkedList<T> list) {
		LinkedList<T> thirdList = new LinkedList<T>();
		this.positionIterator();
		list.positionIterator();
		while (this.offEnd() == false || list.offEnd() == false) {
			if (this.iterator != null) {
				thirdList.addLast(this.getIterator());
			}
			if (list.iterator != null) {
				thirdList.addLast(list.getIterator());
			}
			if (!this.offEnd()) {
				this.advanceIterator();
			}
			if (!list.offEnd()) {
				list.advanceIterator();
			}
		}
		return thirdList;
	}

	/**
	 * Determines whether any of the next or prev links in the List are broken, i.e.
	 * referencing the wrong Node or null Used by the programmer for error checking
	 * 
	 * @return whether a broken links exist
	 */
	public boolean containsBrokenLinks() {
		if (isEmpty()) {
			return false;
		}
		else {
			Node temp = first;
			if (length == 1) {
				if (temp.prev != null || temp.next != null) {
					return true;
				} else {
					return false;
				}
			}
			else {
				for (int i = 0; i < length; i++) {
					if (i == 0) {
						if (temp != temp.next.prev) {
							return true;
						}
					}
					else if (i == length - 1) {
						if (temp.prev.next != temp) {
							return true;
						}
					}
					else {
						if (temp.next.prev != temp) {
							return true;
						}
						temp = temp.next;
					}
				}
				return false;
			}
		}
	}

	/**
	* Points the iterator at first
	* and then advances it to the
	* specified index
	* @param index the index where
	* the iterator should be placed
	* @precondition 0 < index <= length
	* @throws IndexOutOfBoundsException
	* when precondition is violated
	*/
	public void iteratorToIndex(int index) throws IndexOutOfBoundsException{
		if (index < 0 || index >= length) {
			throw new IndexOutOfBoundsException("iteratorToIndex(): index not available!");
		}
	    positionIterator();
	    int i = 0;
	    while (i != index) {
		    advanceIterator();
	    }
	}
	
	/**
	* Searches the List for the specified
	* value using the linear search algorithm
	* @param value the value to search for
	* @return the location of value in the
	* List or -1 to indicate not found
	* Note that if the List is empty we will
	* consider the element to be not found
	* post: position of the iterator remains
	* unchanged
	*/
	public int linearSearch(T value) {
		if(length == 0) {
        	return -1;
        }
        Node iterator = this.first;
        for(int i = 0; i < this.length; i++) {
        	if(value.equals(iterator.data)) {
        		return i;
        	}
        	iterator = iterator.next;
        }
    	return -1; 
	}
}