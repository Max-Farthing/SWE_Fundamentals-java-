/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for Conflict Exception
 * @author Max Farthing
 *
 */
class ConflictExceptionTest {

	/**
	 * Test method for Conflict exception constructor with parameter.
	 */
	@Test
	void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for Conflict exception constructor with no parameter.
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
