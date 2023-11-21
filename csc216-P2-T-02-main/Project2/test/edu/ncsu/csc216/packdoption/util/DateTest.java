package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * test class for Date
 * @author Max Farthing
 * @author David Soliman
 *
 */
public class DateTest {

	/**
	 * Testing date constructors
	 */
	@Test
	void testDateConstructors() {
		Date d1 = new Date(2, 11, 2020);
		assertEquals(2, d1.getMonth());
		assertEquals(11, d1.getDay());
		assertEquals(2020, d1.getYear());
		
		Date d2 = new Date("8/22/2012");
		assertEquals(8, d2.getMonth());
		assertEquals(22, d2.getDay());
		assertEquals(2012, d2.getYear());
		
		assertFalse(d1.equals(d2));
		d1.hashCode();
	}
	
	/**
	 * Tests compareTo methods
	 */
	@Test
	public void testCompareTo() {
		Date d1 = new Date(2, 11, 2020);
		Date d2 = new Date(2, 11, 2019);
		Date d3 = new Date(8, 11, 2020);
		Date d4 = new Date(2, 20, 2020);

		assertTrue(d1.compareTo(d2) > 0);
		assertTrue(d2.compareTo(d1) < 0);
		assertTrue(d1.compareTo(d3) < 0);
		assertTrue(d3.compareTo(d1) > 0);
		assertTrue(d1.compareTo(d4) < 0);
		assertTrue(d4.compareTo(d1) > 0);

	}
	
	/**
	 * Testing toString method
	 */
	@Test
	public void testToString() {
		Date d1 = new Date(2, 11, 2020);
		assertEquals("2/11/2020", d1.toString());
		Date d2 = new Date(2, 9, 2020);
		assertEquals("2/9/2020", d2.toString());
		

	}
	
	/**
	 * tests yearsTo method
	 */
	@Test
	public void testYearsTo() {
		Date d1 = new Date(2, 11, 2020);
		Date d2 = new Date(6, 17, 2018);
		Date d3 = new Date(8, 11, 2014);
		Date d4 = new Date(11, 1, 2025);
		
		assertEquals(-1, d1.yearsTo(d2));
		assertEquals(1, d2.yearsTo(d1));
		assertEquals(5, d3.yearsTo(d1));
		assertEquals(-5, d1.yearsTo(d3));
		assertEquals(5, d1.yearsTo(d4));
		assertEquals(-5, d4.yearsTo(d1));
		assertEquals(-3, d2.yearsTo(d3));
		assertEquals(3, d3.yearsTo(d2));
		assertEquals(7, d2.yearsTo(d4));
		assertEquals(11, d3.yearsTo(d4));
		assertEquals(-11, d4.yearsTo(d3));


	}

}
