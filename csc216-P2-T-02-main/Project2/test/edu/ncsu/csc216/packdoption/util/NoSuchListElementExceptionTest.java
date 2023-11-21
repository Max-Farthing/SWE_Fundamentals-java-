package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * test class for NoSuchListElementException
 * 
 * @author Max Farthing
 * @author David Soliman
 *
 */
public class NoSuchListElementExceptionTest {

	/**
	 * tests default exception message
	 */
	@Test
	void testNoSuchElementExceptionDefault() {
		NoSuchListElementException ee = new NoSuchListElementException();
		assertEquals("No such element in list.", ee.getMessage());

	}

	/**
	 * Tests exception with string passed
	 */
	@Test
	public void testNoSuchElementExceptionString() {
		NoSuchListElementException ee = new NoSuchListElementException("Custom exception message");
		assertEquals("Custom exception message", ee.getMessage());
	}

}
