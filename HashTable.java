import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashTable<T extends Comparable<T>> {

	private ArrayList<LinkedList<T>> Table;
	private int size = 11;

	/**
	 * Constructor for the HashTable class. Initializes the Table to be sized
	 * according to value passed in as a parameter Inserts size empty Lists into the
	 * table. Sets size to 0
	 * 
	 * @param size the table size
	 */
	public HashTable(int size) {
		if (size <= 0) {
			return;
		}
		Table = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			LinkedList<T> list = new LinkedList<>();
			Table.add(list);
		}
		this.size = 0;
	}

	/** Accessors */

	/**
	 * returns the absolute value of the hashCode for a given Object scaled to the
	 * size of the Table
	 * 
	 * @param t the Object
	 * @return the index in the Table
	 */
	private int hash(T t) {
		return Math.abs(t.hashCode()) % Table.size();
	}

	/**
	 * counts the number of elements at this index
	 * 
	 * @param index the index in the Table
	 * @precondition 0 <= index < Table.length
	 * @return the count of elements at this index
	 * @throws IndexOutOfBoundsException
	 */
	public int countBucket(int index) throws IndexOutOfBoundsException {
		if (!(index >= 0 && index < Table.size())) {
			throw new IndexOutOfBoundsException("counBucket(): index out of range");
		}
		return Table.get(index).getLength();
	}

	/**
	 * returns total number of elements in the Table
	 * 
	 * @return total number of elements
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Determines whether the table has any elements
	 * 
	 * @return whether the table is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * searches for a specified element in the Table
	 * 
	 * @param t the element to search for
	 * @return whether the specified element exists in the table
	 */
	public boolean search(T t) {
		if (t == null) {
			return false;
		}
		int bucket = hash(t);
		int trueOrFalse = Table.get(bucket).linearSearch(t);
		if (trueOrFalse == -1) {
			return false;
		} else {
			return true;
		}
	}

	/** Manipulation Procedures */

	/**
	 * inserts a new element in the Table calls the hash method to determine
	 * placement
	 * 
	 * @param t the element to insert
	 */
	public void insert(T t) {
		if (t == null) {
			return;
		}
		int index = hash(t);
		Table.get(index).addLast(t);
		size++;
	}

	/**
	 * removes the element t from the Table calls the hash method on the key to
	 * determine correct placement has no effect if t is not in the Table
	 * 
	 * @param t the key to remove
	 * @precondition t must be in the table H the element is not in the table
	 */
	public void remove(T t) {
		if (!search(t)) {
			return;
		} else {
			int bucket = hash(t);
			Table.get(bucket).positionIterator();
			while (!Table.get(bucket).offEnd()) {
				if (Table.get(bucket).getIterator().equals(t)) {
					Table.get(bucket).removeIterator();
					size--;
					return;
				}
				Table.get(bucket).advanceIterator();
			}
		}
	}

	/** Additional Methods */

	/**
	 * Resets the Table back to empty
	 */
	public void clear() {
		Table = new ArrayList<>(Table.size());
		for (int i = 0; i < Table.size(); i++) {
			LinkedList<T> list = new LinkedList<>();
			Table.add(list);
		}
		size = 0;
	}

	/**
	 * Prints to the console all the keys at a specified bucket in the Table. Each
	 * element displayed on its own line, with a blank line separating each element
	 * Above the elements, prints the message "Printing bucket #<bucket>:" Note that
	 * there is no <> in the output
	 * 
	 * @param bucket the index in the Table
	 * @throws IndexOutOfBoundsException
	 */
	public void printBucket(int bucket) throws IndexOutOfBoundsException {
		if (bucket < 0 || bucket >= Table.size()) {
			throw new IndexOutOfBoundsException("printBucket(): index is out of bound");

		} else if (Table.get(bucket).isEmpty()) {
			System.out.print("Printing bucket #" + bucket + ":\nThis bucket is empty.\n");
		} else {
			System.out.println("Printing bucket #" + bucket + ":\n");
			System.out.print(Table.get(bucket).toString());
		}

	}

	/**
	 * Prints the first element at each bucket along with a count of the total
	 * elements with the message "+ <count> -1 more at this bucket." Each bucket
	 * separated with to blank lines. When the bucket is empty, prints the message
	 * "This bucket is empty." followed by two blank lines
	 */
	public void printTable() {
		for (int i = 0; i < Table.size(); i++) {

			if (Table.get(i).isEmpty()) {
				System.out.print("Bucket: " + i + "\nThis bucket is empty.\n");
			} else {
				System.out.println("Bucket: " + i);
				int more = Table.get(i).getLength() - 1;
				System.out.print(Table.get(i).getFirst());
				System.out.println("+ " + more + " more at this bucket");
			}
			System.out.println();
		}
	}

	/**
	 * Starting at the first bucket, and continuing in order until the last bucket,
	 * concatenates all elements at all buckets into one String
	 */
	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < Table.size(); i++) {
			if (Table.get(i).isEmpty()) {
				output += "";
			} else {
				output += Table.get(i).toString();
			}
		}
		return output;
	}
	
	public User searchAccount(T t) {
        if (!(t instanceof User)) {
            return null;
        }
        int bucket = hash(t);
        Table.get(bucket).positionIterator();
        User origin = (User) t;
        for(int i = 0; i < Table.get(bucket).getLength(); i++) {
            User u = (User)Table.get(bucket).getIterator();
            if(origin.getUserName().equals(u.getUserName()) && origin.getPassWord().equals(u.getPassWord())) {
                return u;
            }
            Table.get(bucket).advanceIterator();;

        }
        return null;
    }
	
	public void insertInterest(int bucket, T user) {
		Table.get(bucket).addLast(user);
	}
	
	
}