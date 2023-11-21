package edu.ncsu.csc216.packdoption.model.rescue;

import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * The list of rescues.
 * 
 * @author David Soliman
 *
 */
public class RescueList {

	/** SortedLinkedList of Rescues. **/
	private SortedLinkedList<Rescue> rescues;

	/**
	 * Constructs a new RescueList with an empty SortedLinkedList of Rescues.
	 */
	public RescueList() {
		rescues = new SortedLinkedList<>();
	}

	/**
	 * Adds a rescue to the list of rescues.
	 * 
	 * @param r the rescue to be added
	 * @throws IllegalArgumentException if r is null or r is already in the list
	 */
	public void addRescue(Rescue r) {
		if (r == null) {
			throw new IllegalArgumentException("Rescue cannot be null");
		}
		if (rescues.contains(r)) {
			throw new IllegalArgumentException("Rescue is already in the list");
		}
		rescues.add(r);
	}

	/**
	 * Adds a rescue with the given name to the list of rescues.
	 * 
	 * @param name the name of the rescue to be added
	 * @throws IllegalArgumentException if name is null, name is whitespace only,
	 *                                  name contains a newline character, or a
	 *                                  rescue with the same name is already in the
	 *                                  list
	 */
	public void addRescue(String name) {
		if (name == null || name.trim().isEmpty() || name.contains("\n")) {
			throw new IllegalArgumentException("Invalid name");
		}
		Rescue newRescue = new Rescue(name.trim());
		if (rescues.contains(newRescue)) {
			throw new IllegalArgumentException("Rescue with the same name is already in the list");
		}
		rescues.add(newRescue);
	}

	/**
	 * Returns the rescue at the specified index.
	 * 
	 * @param idx the index of the rescue
	 * @return the rescue at the specified index
	 * @throws IndexOutOfBoundsException if idx is negative or greater than size - 1
	 */
	public Rescue getRescue(int idx) {
		if (idx < 0 || idx >= rescues.size()) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		return rescues.get(idx);
	}

	/**
	 * Returns the number of rescues in the list.
	 * 
	 * @return the number of rescues in the list
	 */
	public int size() {
		return rescues.size();
	}
}