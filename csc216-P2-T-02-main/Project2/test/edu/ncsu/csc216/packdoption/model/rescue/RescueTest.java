package edu.ncsu.csc216.packdoption.model.rescue;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test class for Rescue
 * 
 * @author Max Farthing
 * @author David Soliman
 *
 */
public class RescueTest {

	/**
	 * Tests rescue
	 */
	@Test
	public void testRescue() {
		Rescue rescue = new Rescue("Rescue Name");
		assertEquals("Rescue Name", rescue.getName());
	}

	/**
	 * Tests add animal
	 */
	@Test
	public void testAddAnimal() {
		Rescue rescue = new Rescue("Rescue Name");
		assertTrue(rescue.addAnimal(new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true,
				new SortedLinkedList<Note>(), new Date(1, 1, 2021))));
	}

	/**
	 * Tests get animal
	 */
	@Test
	public void testGetAnimal() {
		Rescue rescue = new Rescue("Rescue Name");
		Cat cat = new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021));
		rescue.addAnimal(cat);
		assertEquals(cat, rescue.getAnimal(0));
	}

	/**
	 * Tests get aninal by name and birthday
	 */
	@Test
	public void testGetAnimalByNameAndBirthday() {
		Rescue rescue = new Rescue("Rescue Name");
		Cat cat = new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021));
		rescue.addAnimal(cat);
		assertEquals(cat, rescue.getAnimal("Cat1", new Date(1, 1, 2021)));
		assertNull(rescue.getAnimal("Cat2", new Date(1, 1, 2021)));
	}

	/**
	 * Tests Contains
	 */
	@Test
	public void testContains() {
		Rescue rescue = new Rescue("Rescue Name");
		Cat cat = new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021));
		rescue.addAnimal(cat);
		assertTrue(rescue.contains(cat));
		assertFalse(rescue.contains(new Cat("Cat2", new Date(1, 1, 2021), Size.SMALL, true, true,
				new SortedLinkedList<Note>(), new Date(1, 1, 2021))));
	}

	/**
	 * Tests add note
	 */
	@Test
	public void testAddNote() {
		Rescue rescue = new Rescue("Rescue Name");
		Date d = new Date(1, 1, 2021);
		Cat cat = new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021));
		rescue.addAnimal(cat);
		assertTrue(rescue.addNote(cat, new Note(d, "Note 1")));
		assertFalse(rescue.addNote(new Cat("Cat2", new Date(1, 1, 2021), Size.SMALL, true, true,
				new SortedLinkedList<Note>(), new Date(1, 1, 2021)), new Note(d, "Note 2")));
	}

	/**
	 * Tests set adoption info
	 */
	@Test
	public void testSetAdoptionInfo() {
		Rescue rescue = new Rescue("Rescue Name");
		Cat cat = new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021));
		rescue.addAnimal(cat);
		rescue.setAdoptionInfo(cat, true, new Date(1, 1, 2022), "Owner1");
		assertTrue(cat.adopted());
		assertEquals(new Date(1, 1, 2022), cat.getDateAdopted());
		assertEquals("Owner1", cat.getOwner());
	}

	/**
	 * Tests num animals
	 */
	@Test
	public void testNumAnimals() {
		Rescue rescue = new Rescue("Rescue Name");
		assertEquals(0, rescue.numAnimals());
		rescue.addAnimal(new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021)));
		assertEquals(1, rescue.numAnimals());
	}

	/**
	 * Tests num animal available
	 */
	@Test
	public void testNumAnimalsAvailable() {
		Rescue rescue = new Rescue("Rescue Name");
		assertEquals(0, rescue.numAnimalsAvailable());
		rescue.addAnimal(new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021)));
		assertEquals(1, rescue.numAnimalsAvailable());
		rescue.setAdoptionInfo(rescue.getAnimal(0), true, new Date(1, 1, 2022), "Owner1");
		assertEquals(0, rescue.numAnimalsAvailable());
	}

	/**
	 * Tests num animals adopted
	 */
	@Test
	public void testNumAnimalsAdopted() {
		Rescue rescue = new Rescue("Rescue Name");
		assertEquals(0, rescue.numAnimalsAdopted());
		rescue.addAnimal(new Cat("Cat1", new Date(1, 1, 2021), Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021)));
		assertEquals(0, rescue.numAnimalsAdopted());
		Date d = new Date(1, 1, 2022);
		rescue.setAdoptionInfo(rescue.getAnimal(0), true, d, "Owner1");
		assertEquals(1, rescue.numAnimalsAdopted());
		rescue.availableCats();
		rescue.availableDogs();
		rescue.animalsAdopted();
		rescue.availableAnimalsDayRange(d, 2, 5);
		rescue.availableAnimalsAge(d, 2, 5);
		rescue.getAnimalsAsArray(d);
		rescue.getAppointmentsAsArray(d);
		rescue.getAppointments();
		rescue.equals(rescue);
		rescue.toString();
		rescue.hashCode();

	}

	/**
	 * Tests animals available
	 */
	@Test
	public void testAnimalsAvailable() {
		Rescue rescue = new Rescue("Rescue Name");
		SortedLinkedList<Animal> availableAnimals = rescue.animalsAvailable();
		assertTrue(availableAnimals.isEmpty());
		Date d = new Date(1, 1, 2021);
		rescue.addAnimal(
				new Cat("Cat1", d, Size.SMALL, true, true, new SortedLinkedList<Note>(), new Date(1, 1, 2021)));
		availableAnimals = rescue.animalsAvailable();
		assertFalse(availableAnimals.isEmpty());
		assertEquals(1, availableAnimals.size());
		rescue.setAdoptionInfo(rescue.getAnimal(0), true, new Date(1, 1, 2022), "Owner1");
		availableAnimals = rescue.animalsAvailable();
		assertTrue(availableAnimals.isEmpty());
		rescue.addAppointment(
				new Cat("Cat1", d, Size.SMALL, true, true, new SortedLinkedList<Note>(), new Date(1, 1, 2021)));

	}

	/**
	 * Tests get appointments as array
	 */
	@Test
	public void testGetAppointmentsAsArray() {
		Rescue rescue = new Rescue("Rescue Name");
		Date today = new Date(7, 20, 2023);

		// Add some animals to the rescue and appointments queue
		Cat cat1 = new Cat("Cat1", new Date(1, 1, 2021), Animal.Size.SMALL, true, true, new SortedLinkedList<Note>(),
				new Date(1, 1, 2021));
		Cat cat2 = new Cat("Cat2", new Date(10, 5, 2019), Animal.Size.LARGE, false, true, new SortedLinkedList<Note>(),
				new Date(5, 5, 2021));
		Cat dog1 = new Cat("Dog1", new Date(10, 25, 2017), Animal.Size.MEDIUM, true, true, new SortedLinkedList<Note>(),
				new Date(1, 7, 2021));
		rescue.addAnimal(cat1);
		rescue.addAnimal(cat2);
		rescue.addAnimal(dog1);

		rescue.addAppointment(cat1);
		rescue.addAppointment(dog1);

		// Get the appointments array
		String[][] appointmentsArray = rescue.getAppointmentsAsArray(today);

		// Verify the size of the array matches the number of appointments in the queue
		assertEquals(2, appointmentsArray.length);

		// Verify the appointment details for each animal
		assertEquals("Cat1", appointmentsArray[0][0]);
		assertEquals("Cat", appointmentsArray[0][1]);
		assertEquals("1/1/2021", appointmentsArray[0][2]);
		assertEquals("2", appointmentsArray[0][3]); // Age on 2023-07-20
		assertEquals("YOUNG", appointmentsArray[0][4]); // Age category on 2023-07-20
		assertEquals("No", appointmentsArray[0][5]);
		assertEquals("930", appointmentsArray[0][6]); // Days available for adoption (adopted)

		assertEquals("Dog1", appointmentsArray[1][0]);
		assertEquals("Cat", appointmentsArray[1][1]);
		assertEquals("10/25/2017", appointmentsArray[1][2]);
		assertEquals("5", appointmentsArray[1][3]); // Age on 2023-07-20
		assertEquals("ADULT", appointmentsArray[1][4]); // Age category on 2023-07-20
		assertEquals("No", appointmentsArray[1][5]);
		assertEquals("924", appointmentsArray[1][6]); // Days available for adoption (adopted)

	}
}
