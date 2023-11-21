package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

class CatTest {

	/**
	 * Tests get category
	 */
	@Test
	void testGetAgeCategory() {
		Date birthday = new Date(5, 3, 2019);
		Date dateEnterRescue = new Date(7, 15, 2023);
		Cat cat = new Cat("Whiskers", birthday, Size.SMALL, true, false, new SortedLinkedList<Note>(), dateEnterRescue);
		Date today = new Date(7, 15, 2023);
		assertEquals("ADULT", cat.getAgeCategory(today).toString());
	}

	@Test
	void testEquals() {
		Date birthday = new Date(5, 3, 2019);
		Date birthday2 = new Date(5, 3, 2019);
		Date dateEnterRescue = new Date(7, 15, 2023);
		Date dateEnterRescue2 = new Date(8, 15, 2023);

		Cat cat1 = new Cat("Whiskers", birthday, Size.SMALL, true, false, new SortedLinkedList<Note>(),
				dateEnterRescue);
		Cat cat2 = new Cat("Whiskers", birthday2, Size.MEDIUM, false, true, new SortedLinkedList<Note>(),
				dateEnterRescue2);
		cat1.equals(cat2);
		assertEquals(cat1.getBirthday(), cat2.getBirthday());
		assertEquals(cat1.getName(), cat2.getName());
	}

	/**
	 * Tests get animal as array
	 */
	@Test
	void testGetAnimalAsArray() {
		Date birthday = new Date(5, 3, 2019);
		Date dateEnterRescue = new Date(7, 15, 2023);
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(7, 15, 2023), "Note 1"));
		notes.add(new Note(new Date(7, 16, 2023), "Note 2"));
		Cat cat = new Cat("Whiskers", birthday, Size.SMALL, true, false, notes, dateEnterRescue);
		Date today = new Date(7, 15, 2023);
		String[] expected = { "Whiskers", "Cat", "5/3/2019", "4", "ADULT", "No", "0" };
		assertArrayEquals(expected, cat.getAnimalAsArray(today));
	}

}
