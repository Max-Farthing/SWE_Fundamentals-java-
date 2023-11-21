package edu.ncsu.csc216.packdoption.util;

/**
 * This class creates a linked list that is sorted through nodes
 * 
 * @author Max Farthing
 * @author David Soliman
 *
 * @param <E> E
 */
public class SortedLinkedList<E extends Comparable<E>> implements SortedList<E> {
	/** first Node of list **/
	private Node<E> head;

	/**
	 * Constructor sorted linked list
	 */
	public SortedLinkedList() {
		head = null;
	}

	/**
	 * returns list size
	 * 
	 * @return size of list
	 */
	public int size() {
		int count = 0;
		Node<E> current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	/**
	 * returns true if empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * returns true if list contains object
	 * 
	 * @param element element
	 * @return boolean
	 */
	public boolean contains(E element) {
		Node<E> current = head;
		while (current != null) {
			if (current.value.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * returns true if element is added to list
	 * 
	 * @param element specified object
	 * @return boolean
	 */
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException("Null elements are not allowed in the list.");
		}

		// Check if the element already exists in the list
		if (indexOf(element) != -1) {
			throw new IllegalArgumentException("Duplicate elements are not allowed in the list.");
		}

		Node<E> newNode = new Node<>(element, null);

		if (head == null || element.compareTo(head.value) < 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node<E> current = head;
			while (current.next != null && element.compareTo(current.next.value) >= 0) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}

		return true;
	}

	/**
	 * gets index from list
	 * 
	 * @param idx index
	 * @return E generic type
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index is out of range");
		}
		Node<E> current = head;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.value;
	}

	/**
	 * removes index from list
	 * 
	 * @param idx index
	 * @return E generic type
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index is out of range");
		}
		E removedElement;
		if (idx == 0) {
			removedElement = head.value;
			head = head.next;
		} else {
			Node<E> current = head;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			removedElement = current.next.value;
			current.next = current.next.next;
		}
		return removedElement;
	}

	/**
	 * returns index of element
	 * 
	 * @param element specified object
	 * @return int of element
	 */
	public int indexOf(E element) {
		Node<E> current = head;
		int index = 0;
		while (current != null) {
			if (current.value.equals(element)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	/**
	 * Creates new instance of cursor
	 * 
	 * @return new instance of cursor
	 */
	public SimpleListIterator<E> iterator() {
		return new Cursor();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		Node<E> current = head;
		while (current != null) {
			result = prime * result + current.value.hashCode();
			current = current.next;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		SortedLinkedList other = (SortedLinkedList) obj;

		if (size() != other.size())
			return false;

		Node<E> currentThis = head;
		@SuppressWarnings("unchecked")
		Node<E> currentOther = other.head;
		while (currentThis != null) {
			if (!currentThis.value.equals(currentOther.value))
				return false;
			currentThis = currentThis.next;
			currentOther = currentOther.next;
		}

		return true;
	}

	/**
	 * Returns a string representation of the list in the format "-A\n-B\n...-X",
	 * where "A" is the first list item, "B" is the second, and so on.
	 * 
	 * @return a string representation of the list
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<E> current = head;
		while (current != null) {
			if (current.next != null) {
				sb.append("-").append(current.value.toString()).append("\n");
				current = current.next;
			} else {
				sb.append("-").append(current.value.toString());
				current = current.next;
			}
		}
		return sb.toString();
	}

	/**
	 * Inner Node Class
	 * 
	 * @author Max Farthing
	 *
	 * @param <E> E
	 */
	@SuppressWarnings("hiding")
	public class Node<E> {
		/** next node **/
		private Node<E> next;
		/** value of element **/
		private E value;

		/**
		 * node constructor for linkedList
		 * 
		 * @param value element of node
		 * @param next  Node
		 */
		public Node(E value, Node<E> next) {
			this.next = next;
			this.value = value;

		}

	}

	/**
	 * Cursor class provides a cursor for iterating through a list without changing
	 * the list
	 * 
	 * @author Max Farthing
	 *
	 */
	private class Cursor implements SimpleListIterator<E> {
		/** current Node **/
		private Node<E> current;

		public Cursor() {
			current = head;
		}

		/**
		 * returns true if next exists
		 * 
		 * @return boolean if cursor hasNext
		 */
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * next cursor
		 * 
		 * @return E generic type
		 */
		public E next() {
			if (hasNext()) {
				E value = current.value;
				current = current.next;
				return value;

			}
			throw new NoSuchListElementException("No element available with call to next.");

		}

	}

}
