package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for ArrayListQueue
 * 
 * @author Max Farthing
 * @author David Soliman
 *
 */
public class ArrayListQueueTest {

	/**
	 * Tests Add
	 */
	@Test
	public void testAdd() {
		ArrayListQueue<String> queue = new ArrayListQueue<>();
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());

		queue.add("A");
		assertFalse(queue.isEmpty());
		assertEquals(1, queue.size());
		assertEquals("A", queue.element());

		queue.add("B");
		assertFalse(queue.isEmpty());
		assertEquals(2, queue.size());
		assertEquals("A", queue.element());
	}

	/**
	 * Tests remove
	 */
	@Test
	public void testRemove() {
		ArrayListQueue<String> queue = new ArrayListQueue<>();
		queue.add("A");
		queue.add("B");
		queue.add("C");

		assertEquals("A", queue.remove());
		assertEquals("B", queue.remove());
		assertEquals("C", queue.remove());
		assertTrue(queue.isEmpty());
	}

	/**
	 * Tests element
	 */
	@Test
	public void testElement() {
		ArrayListQueue<String> queue = new ArrayListQueue<>();
		queue.add("A");
		assertEquals("A", queue.element());

		queue.add("B");
		assertEquals("A", queue.element());

		queue.remove();
		assertEquals("B", queue.element());
	}

}
