package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.AgeCategory;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test class for Animal
 * 
 * @author Max Farthing
 *
 */
public class AnimalTest {

	/**
	 * Test constructor with adoption
	 */
	@Test
	public void testConstructorWithAdoptionInfo() {
		Date birthday = new Date(1, 1, 2020);
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		Date dateEnterRescue = new Date(1, 1, 2021);
		Date dateAdopted = new Date(2, 1, 2021);
		String owner = "John Doe";
		Animal animal = new Dog("Max", birthday, Animal.Size.LARGE, true, true, notes, dateEnterRescue, true,
				dateAdopted, owner, Breed.BEAGLE);

		assertEquals("Max", animal.getName());
		assertEquals(Animal.Size.LARGE, animal.getSize());
		assertTrue(animal.isHouseTrained());
		assertTrue(animal.isGoodWithKids());
		assertEquals(notes, animal.getNotes());
		assertEquals(dateEnterRescue, animal.getDateEnterRescue());
		assertTrue(animal.adopted());
		assertEquals(dateAdopted, animal.getDateAdopted());
		assertEquals(owner, animal.getOwner());
	}

	/**
	 * Test constructor without adoption
	 */
	@Test
	public void testConstructorWithoutAdoptionInfo() {
		Date birthday = new Date(1, 1, 2010);
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		Date dateEnterRescue = new Date(1, 1, 2021);
		Animal animal = new Dog("Max", birthday, Animal.Size.LARGE, true, true, notes, dateEnterRescue, Breed.BEAGLE);

		assertEquals("Max", animal.getName());
		assertEquals(Animal.Size.LARGE, animal.getSize());
		assertTrue(animal.isHouseTrained());
		assertTrue(animal.isGoodWithKids());
		assertEquals(notes, animal.getNotes());
		assertEquals(dateEnterRescue, animal.getDateEnterRescue());
		assertFalse(animal.adopted());
		assertNull(animal.getDateAdopted());
		assertNull(animal.getOwner());
		animal.setAdoptionInfo(true, dateEnterRescue, "Max");
		assertEquals(birthday, animal.getBirthday());
		Note note = new Note(dateEnterRescue, "Birthday");
		animal.addNote(note);
		animal.getAge(birthday);
		animal.hashCode();
		animal.compareTo(animal);
		animal.equals(animal);

	}

	/**
	 * Test constructor without adoption2
	 */
	@Test
	public void testConstructorWithoutAdoptionInfo2() {
		Date birthday = new Date(1, 1, 2010);
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		Date dateEnterRescue = new Date(1, 1, 2021);
		Animal animal = new Cat("Max", birthday, Animal.Size.LARGE, true, true, notes, dateEnterRescue);

		assertEquals("Max", animal.getName());
		assertEquals(Animal.Size.LARGE, animal.getSize());
		assertTrue(animal.isHouseTrained());
		assertTrue(animal.isGoodWithKids());
		assertEquals(notes, animal.getNotes());
		assertEquals(dateEnterRescue, animal.getDateEnterRescue());
		assertFalse(animal.adopted());
		assertNull(animal.getDateAdopted());
		assertNull(animal.getOwner());
		animal.toString();
		Date d = new Date(2, 10, 2021);
		animal.getDaysAvailableForAdoption(d);
	}

	/**
	 * Test for getAgeCategory(Date date) method with an animal of small size and
	 * age < 4
	 */
	@Test
	void testGetAgeCategorySmallYoung() {
		Date birthday = new Date(1, 1, 2023);
		Date dateEnterRescue = new Date(1, 1, 2024);
		Animal animal = new Dog("Small Animal", birthday, Animal.Size.SMALL, true, true, new SortedLinkedList<Note>(),
				dateEnterRescue, Breed.BULLDOG);

		Date today = new Date(2, 1, 2023);
		assertEquals(AgeCategory.YOUNG, animal.getAgeCategory(today));
	}

	/**
	 * Tests invalid inputs
	 */
	@Test
	public void testConstructorWithInvalidInputs() {
		// Test invalid name
		assertThrows(IllegalArgumentException.class, () -> {
			new Dog(null, new Date(1, 1, 2020), Animal.Size.LARGE, true, true, new SortedLinkedList<Note>(),
					new Date(1, 1, 2021), false, null, null, Breed.BEAGLE);
		});

		// Test invalid birthday
		assertThrows(IllegalArgumentException.class, () -> {
			new Dog("Max", null, Animal.Size.LARGE, true, true, new SortedLinkedList<Note>(), new Date(1, 1, 2021),
					false, null, null, Breed.BEAGLE);
		});

		// Test invalid size
		assertThrows(IllegalArgumentException.class, () -> {
			new Dog("Max", new Date(1, 1, 2020), null, true, true, new SortedLinkedList<Note>(), new Date(1, 1, 2021),
					false, null, null, Breed.BEAGLE);
		});

		// Test invalid notes
		assertThrows(IllegalArgumentException.class, () -> {
			new Dog("Max", new Date(1, 1, 2020), Animal.Size.LARGE, true, true, null, new Date(1, 1, 2021), false, null,
					null, Breed.BEAGLE);
		});

		// Test invalid dateEnterRescue
		assertThrows(IllegalArgumentException.class, () -> {
			new Dog("Max", new Date(1, 1, 2020), Animal.Size.LARGE, true, true, new SortedLinkedList<Note>(), null,
					false, null, null, Breed.BEAGLE);
		});

		// Test dateEnterRescue < birthday
		assertThrows(IllegalArgumentException.class, () -> {
			new Dog("Max", new Date(1, 1, 2020), Animal.Size.LARGE, true, true, new SortedLinkedList<Note>(),
					new Date(1, 1, 2019), false, null, null, Breed.BEAGLE);
		});

		// Test invalid adoption information (adopted = true, but missing dateAdopted
		// and owner)
		assertThrows(IllegalArgumentException.class, () -> {
			new Dog("Max", new Date(1, 1, 2020), Animal.Size.LARGE, true, true, new SortedLinkedList<Note>(),
					new Date(1, 1, 2021), true, null, null, Breed.BEAGLE);
		});

		// Test invalid adoption information (adopted = false, but dateAdopted and owner
		// are not null)
		assertThrows(IllegalArgumentException.class, () -> {
			new Dog("Max", new Date(1, 1, 2020), Animal.Size.LARGE, true, true, new SortedLinkedList<Note>(),
					new Date(1, 1, 2021), false, new Date(2, 1, 2021), "John Doe", Breed.BEAGLE);
		});

//		assertThrows(IllegalArgumentException.class, () -> {
//			new Dog("Max", new Date(1, 1, 2020), Animal.Size.LARGE, true, true, new SortedLinkedList<Note>(),
//					new Date(1, 1, 2021), true, new Date(1, 1, 2021), "John Doe", Breed.BEAGLE);
//		});

		
	}

}
