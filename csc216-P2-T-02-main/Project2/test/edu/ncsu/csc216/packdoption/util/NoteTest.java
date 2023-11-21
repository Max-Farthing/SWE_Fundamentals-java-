package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for Note
 * 
 * @author maximus
 * @author David Soliman
 *
 */
public class NoteTest {

	/**
	 * Tests the constructor
	 */
	@Test
	void testConstructor() {
		// Valid note
		Date date = new Date(10, 3, 2021);
		Note note = new Note(date, "A valid note");
		assertEquals(date, note.getDate());
		assertEquals("A valid note", note.getMessage());

		// Invalid note (null date)
		assertThrows(IllegalArgumentException.class, () -> new Note(null, "Invalid note"));

		// Invalid note (null message)
		assertThrows(IllegalArgumentException.class, () -> new Note(date, null));

		// Invalid note (empty message)
		assertThrows(IllegalArgumentException.class, () -> new Note(date, ""));

		// Invalid note (whitespace only message)
		assertThrows(IllegalArgumentException.class, () -> new Note(date, "   "));

		// Invalid note (message contains \n)
		assertThrows(IllegalArgumentException.class, () -> new Note(date, "Note\nwith newline"));

		// Invalid note (message contains ,)
		assertThrows(IllegalArgumentException.class, () -> new Note(date, "Note,with,comma"));
	}

	/**
	 * Tests the compare to
	 */
	@Test
	void testCompareTo() {
		Date date1 = new Date(10, 3, 2021);
		Date date2 = new Date(9, 20, 2021);

		Note note1 = new Note(date1, "Note 1");
		Note note2 = new Note(date2, "Note 2");

		// note1 < note2
		assertFalse(note1.compareTo(note2) < 0);
		// note2 > note1
		assertFalse(note2.compareTo(note1) > 0);
	}

	/**
	 * Tests the to string
	 */
	@Test
	void testToString() {
		Date date = new Date(10, 3, 2021);
		Note note = new Note(date, "A note");

		assertEquals("10/3/2021 A note", note.toString());
	}

	/**
	 * Tests the hash code
	 */
	@Test
	void testHashCode() {
		Date date1 = new Date(10, 3, 2021);
		Date date2 = new Date(9, 20, 2021);
		Date date3 = new Date(10, 3, 2021);

		Note note1 = new Note(date1, "Note 1");
		Note note2 = new Note(date2, "Note 2");
		Note note3 = new Note(date3, "Note 3");

		// note1 and note3 have the same hash code
		assertEquals(-1954742711, note3.hashCode());
		// note2 has a different hash code
		assertNotEquals(note1.hashCode(), note2.hashCode());
	}

	/**
	 * Tests the equals
	 */

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testEquals() {
		Date date1 = new Date(10, 3, 2021);
		Date date2 = new Date(10, 3, 2021);
		Date date3 = new Date(9, 20, 2021);

		Note note1 = new Note(date1, "Note 1");
		Note note2 = new Note(date2, "Note 1"); // Same date as note1, same message
		Note note3 = new Note(date3, "Note 1"); // Different date as note1, same message
		Note note4 = new Note(date1, "Note 2"); // Same date as note1, different message
		Note note5 = new Note(date1, "Note 1");
		Note note6 = null;

		// Different class
		assertFalse(note1.equals(new Object()));

		// Same object
		assertTrue(note1.equals(note1));

		// Different objects with the same date and message
		assertTrue(note1.equals(note2));

		// Different objects with different date but the same message
		assertFalse(note1.equals(note3));

		// Different objects with the same date but a different message
		assertFalse(note1.equals(note4));

		// Different objects with the same date and message but different classes
		assertFalse(note1.equals(date1));

		// Different objects with the same date and message but obj is null
		assertFalse(note1.equals(note6));

		// Different objects with the same date but different messages and obj is null
		assertTrue(note1.equals(note5));

		// Different objects with different dates and messages, both obj and note4.date
		// are null
		assertFalse(note4.equals(note6));

		// Different objects with different dates and messages, note4.date is null
		assertFalse(note4.equals(note5));

		// Different objects with different dates and messages, both note4.date and
		// note5.date are not null
		assertFalse(note4.equals(note1));

	}

}
