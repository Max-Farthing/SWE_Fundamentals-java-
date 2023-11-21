package edu.ncsu.csc216.packdoption.model.rescue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Test class for RescueList
 * 
 * @author Max Farthing
 * @author David Soliman
 *
 */
public class RescueListTest {

	/**
	 * Tests Constructor
	 */
	@Test
	public void testConstructor() {
		RescueList rescueList = new RescueList();
		assertNotNull(rescueList);
		assertEquals(0, rescueList.size());
	}

	/**
	 * Tests add rescue
	 */
	@Test
	public void testAddRescue() {
		RescueList rescueList = new RescueList();
		Rescue rescue1 = new Rescue("Rescue 1");
		Rescue rescue2 = new Rescue("Rescue 2");

		rescueList.addRescue(rescue1);
		assertEquals(1, rescueList.size());

		rescueList.addRescue(rescue2);
		assertEquals(2, rescueList.size());

		// Attempting to add the same rescue again should throw an exception
		try {
			rescueList.addRescue((Rescue) null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Rescue cannot be null", e.getMessage());
		}

		// Attempting to add a null rescue should throw an exception
		try {
			rescueList.addRescue((String) null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}
	}

	/**
	 * Tests add rescue with name
	 */
	@Test
	public void testAddRescueWithName() {
		RescueList rescueList = new RescueList();
		rescueList.addRescue("Rescue 1");
		assertEquals(1, rescueList.size());

		rescueList.addRescue("Rescue 2");
		assertEquals(2, rescueList.size());

		// Attempting to add a rescue with the same name again should throw an exception
		try {
			rescueList.addRescue("Rescue 1");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Rescue with the same name is already in the list", e.getMessage());
		}

		// Attempting to add a rescue with an invalid name should throw an exception
		try {
			rescueList.addRescue("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}

		try {
			rescueList.addRescue(" ");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}

		try {
			rescueList.addRescue("Rescue\n");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}
	}

	/**
	 * Tests get rescue
	 */
	@Test
	public void testGetRescue() {
		RescueList rescueList = new RescueList();
		Rescue rescue1 = new Rescue("Rescue 1");
		Rescue rescue2 = new Rescue("Rescue 2");

		rescueList.addRescue(rescue1);
		rescueList.addRescue(rescue2);

		assertEquals(rescue1, rescueList.getRescue(0));
		assertEquals(rescue2, rescueList.getRescue(1));

		// Attempting to get a rescue at an invalid index should throw an exception
		try {
			rescueList.getRescue(-1);
			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index", e.getMessage());
		}

		try {
			rescueList.getRescue(2);
			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index", e.getMessage());
		}
	}

	/**
	 * Tests size
	 */
	@Test
	public void testSize() {
		RescueList rescueList = new RescueList();
		assertEquals(0, rescueList.size());

		rescueList.addRescue(new Rescue("Rescue 1"));
		assertEquals(1, rescueList.size());

		rescueList.addRescue(new Rescue("Rescue 2"));
		assertEquals(2, rescueList.size());
	}
}
