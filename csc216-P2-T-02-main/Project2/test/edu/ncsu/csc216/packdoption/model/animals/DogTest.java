package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test class for Dog
 * 
 * @author Max Farthing
 *
 */
class DogTest {

	/**
	 * test age category
	 */
	@Test
	void testGetAgeCategory() {
		Date birthday = new Date(3, 12, 2017);
		Date dateEnterRescue = new Date(7, 15, 2023);
		Dog dog = new Dog("Buddy", birthday, Size.MEDIUM, true, true, new SortedLinkedList<Note>(), dateEnterRescue,
				true, new Date(7, 18, 2023), "John Doe", Breed.RETRIEVER_GOLDEN);
		Date today = new Date(7, 18, 2023);
		assertEquals("ADULT", dog.getAgeCategory(today).toString());
		dog.getBreed();
		dog.getAge(today);
	}

	/**
	 * tests animal as array
	 */
	@Test
	void testGetAnimalAsArray() {
		Date birthday = new Date(3, 12, 2017);
		Date dateEnterRescue = new Date(7, 15, 2023);
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(7, 15, 2023), "Note 1"));
		notes.add(new Note(new Date(7, 16, 2023), "Note 2"));
		Dog dog = new Dog("Buddy", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.RETRIEVER_GOLDEN);
		Date today = new Date(7, 18, 2023);
		String[] expected = { "Buddy", "Dog", "3/12/2017", "6", "ADULT", "No", "3" };
		assertArrayEquals(expected, dog.getAnimalAsArray(today));
	}

	/**
	 * Test for getAgeCategory(Date date) method with a small dog, ageInYears < 4
	 */
	@Test
	void testGetAgeCategorySmallDogYoung() {
		Date birthday = new Date(1, 1, 2023);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Small Dog", birthday, Size.SMALL, true, true, new SortedLinkedList<Note>(), dateEnterRescue,
				Breed.RETRIEVER_GOLDEN);
		Date today = new Date(3, 15, 2023);
		assertEquals("YOUNG", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a small dog, 4 <= ageInYears 9
	 */
	@Test
	void testGetAgeCategorySmallDogAdult() {
		Date birthday = new Date(1, 1, 2018);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Small Dog", birthday, Size.SMALL, true, true, new SortedLinkedList<Note>(), dateEnterRescue,
				Breed.RETRIEVER_GOLDEN);
		Date today = new Date(10, 15, 2023);
		assertEquals("ADULT", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a small dog, ageInYears >= 9
	 */
	@Test
	void testGetAgeCategorySmallDogSenior() {
		Date birthday = new Date(1, 1, 2011);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Small Dog", birthday, Size.SMALL, true, true, new SortedLinkedList<Note>(), dateEnterRescue,
				Breed.RETRIEVER_GOLDEN);
		Date today = new Date(12, 31, 2023);
		assertEquals("SENIOR", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a medium dog, ageInYears < 3
	 */
	@Test
	void testGetAgeCategoryMediumDogYoung() {
		Date birthday = new Date(1, 1, 2023);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Medium Dog", birthday, Size.MEDIUM, true, true, new SortedLinkedList<Note>(),
				dateEnterRescue, Breed.RETRIEVER_GOLDEN);
		Date today = new Date(1, 1, 2026);
		assertEquals("ADULT", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a medium dog, 3 <= ageInYears
	 * < 9
	 */
	@Test
	void testGetAgeCategoryMediumDogAdult() {
		Date birthday = new Date(1, 1, 2018);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Medium Dog", birthday, Size.MEDIUM, true, true, new SortedLinkedList<Note>(),
				dateEnterRescue, Breed.RETRIEVER_GOLDEN);
		Date today = new Date(10, 15, 2024);
		assertEquals("ADULT", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a medium dog, ageInYears >= 9
	 */
	@Test
	void testGetAgeCategoryMediumDogSenior() {
		Date birthday = new Date(1, 1, 2011);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Medium Dog", birthday, Size.MEDIUM, true, true, new SortedLinkedList<Note>(),
				dateEnterRescue, Breed.RETRIEVER_GOLDEN);
		Date today = new Date(12, 31, 2023);
		assertEquals("SENIOR", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a large dog, ageInYears < 3
	 */
	@Test
	void testGetAgeCategoryLargeDogYoung() {
		Date birthday = new Date(1, 1, 2023);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Large Dog", birthday, Size.LARGE, true, true, new SortedLinkedList<Note>(), dateEnterRescue,
				Breed.RETRIEVER_GOLDEN);
		Date today = new Date(1, 1, 2026);
		assertEquals("ADULT", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a large dog, 3 <= ageInYears 6
	 */
	@Test
	void testGetAgeCategoryLargeDogAdult() {
		Date birthday = new Date(1, 1, 2019);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Large Dog", birthday, Size.LARGE, true, true, new SortedLinkedList<Note>(), dateEnterRescue,
				Breed.RETRIEVER_GOLDEN);
		Date today = new Date(10, 15, 2024);
		assertEquals("ADULT", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a large dog, ageInYears >= 6
	 */
	@Test
	void testGetAgeCategoryLargeDogSenior() {
		Date birthday = new Date(1, 1, 2017);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Large Dog", birthday, Size.LARGE, true, true, new SortedLinkedList<Note>(), dateEnterRescue,
				Breed.RETRIEVER_GOLDEN);
		Date today = new Date(12, 31, 2023);
		assertEquals("SENIOR", dog.getAgeCategory(today).toString());
	}

	/**
	 * Test for getAgeCategory(Date date) method with a dog on its birthday (edge
	 * case)
	 */
	@Test
	void testGetAgeCategoryDogOnBirthday() {
		Date birthday = new Date(6, 15, 2020);
		Date dateEnterRescue = new Date(1, 2, 2023);
		Dog dog = new Dog("Dog on Birthday", birthday, Size.MEDIUM, true, true, new SortedLinkedList<Note>(),
				dateEnterRescue, Breed.RETRIEVER_GOLDEN);
		Date today = new Date(6, 15, 2023);
		assertEquals("ADULT", dog.getAgeCategory(today).toString());
	}

}
