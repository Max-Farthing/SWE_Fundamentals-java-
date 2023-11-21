package edu.ncsu.csc216.packdoption.util;

import java.util.Arrays;

/**
 * Custom implementation of Queue using array List
 * 
 * @author Max Farthing
 *
 * @param <E> generic object
 */

public class ArrayListQueue<E> implements Queue<E> {
	/** Array of objects **/
	private E[] list;
	/** size of queue **/
	private int size;

	/**
	 * Constructor for ArrayListQueue
	 */
	@SuppressWarnings("unchecked")
	public ArrayListQueue() {
		list = (E[]) new Object[0];
		size = 0;
	}

	/**
	 * Adds an object to the queue.
	 * 
	 * @param e the generic object to add
	 * @return true if the add operation is successful
	 * @throws NullPointerException if the specified element is null and this queue
	 *                              does not permit null elements
	 */
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException("Cannot add null elements to the queue.");
		}

		ensureCapacity(size + 1);
		list[size] = e;
		size++;
		return true;
	}

	/**
	 * Returns true if the queue is empty.
	 * 
	 * @return true if the queue is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the queue.
	 * 
	 * @return the size of the queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of the queue.
	 * 
	 * @return the head of the queue
	 * @throws NoSuchListElementException if the queue is empty
	 */
	public E element() {
		if (isEmpty()) {
			throw new NoSuchListElementException("Queue is empty.");
		}
		return list[0];
	}

	/**
	 * Retrieves and removes the head of the queue.
	 * 
	 * @return the head of the queue
	 * @throws NoSuchListElementException if the queue is empty
	 */
	public E remove() {
		if (isEmpty()) {
			throw new NoSuchListElementException("Queue is empty.");
		}
		E element = list[0];
		size--;
		// Shift elements to the left to remove the head
		System.arraycopy(list, 1, list, 0, size);
		list[size] = null; // Clear the reference to the last element
		return element;
	}

	/**
	 * Ensures that the underlying array has sufficient capacity to accommodate the
	 * specified number of elements.
	 * 
	 * @param capacity the desired capacity
	 */
	private void ensureCapacity(int capacity) {
		if (capacity > list.length) {
			int newCapacity = Math.max(capacity, list.length * 2);
			list = Arrays.copyOf(list, newCapacity);
		}
	}
}