package edu.ncsu.csc216.packdoption.model.io;

import static org.junit.jupiter.api.Assertions.*; 
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;



/**
 * Test class for PackDoptionWriter
 * @author Max Farthing
 *
 */
public class PackDoptionWriterTest {

	/** rescue list for testing **/
	RescueList list = new RescueList();
	
//	/** Test file path */
//	private static final String TEST_FILE = "test-files/PackDoption1.txt";
//
//	/**
//	 * Tests write tickets to file
//	 */
//	@Test
//	public void testWriteTicketsToFile() {
//		RescueList rescueList = new RescueList();
//		
//		rescueList.addRescue(new Rescue("Rescue 1"));
//		rescueList.addRescue(new Rescue("Rescue 2"));
//
//		
//
//		PackDoptionWriter.writeRescueFile(TEST_FILE, rescueList);
//		try {
//			PackDoptionWriter.writeRescueFile(TEST_FILE, rescueList);
//		} catch (IllegalArgumentException e) {
//			fail("Unable to read file");
//		}
//
//		File file = new File(TEST_FILE);
//		assertTrue(file.delete());
//	}

//	/**
//	 * Set up method before other tests
//	 */
//	@Before
//	public void setUp() {
//		
//	}

	/**
	 * test valid file 
	 */
	@Test
	public void testPackDoptionWriteValid() {		

		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		Date d1a = new Date("3/14/2004");
		Date d1b = new Date("4/8/2004");
		Date d1c = new Date("5/13/2004");
		Note n1 = new Note(d1a, "Born in NC");
		Animal a1 = new Cat("Loki", d1a, Size.SMALL, false, true, notes, d1b, true, d1c, "Ethan and Family");
		notes.add(n1);
		
		SortedLinkedList<Note> notes2 = new SortedLinkedList<Note>();
		Date d2a = new Date("2/29/2004");
		Date d2b = new Date("7/21/2004");
		Date d2c = new Date("7/28/2004");
		Note n2 = new Note(d2a, "Born in NC");
		notes2.add(n2);
		Animal a3 = new Dog("Duke", d2a, Size.LARGE, true, true, notes2, d2b, true, d2c, "Olivia and Family", Breed.ROTTWEILER);

		SortedLinkedList<Note> notes3 = new SortedLinkedList<Note>();
		Date d3a = new Date("5/14/2011");
		Date d3b = new Date("6/23/2011");
		Animal a2 = new Cat("Lily", d3a, Size.SMALL, false, true, notes3, d3b);
		//blank
		
		
		SortedLinkedList<Note> notes4 = new SortedLinkedList<Note>();
		Date d4a = new Date("3/9/2001");
		Date d4b = new Date("6/1/2001");
		Note n3 = new Note(d4a, "Born in NC");
		notes4.add(n3);
		Animal a4 = new Dog("Jack", d4a, Size.LARGE, true, true, notes4, d4b, Breed.POINTER_GERMAN_SHORTHAIRED);
		
		SortedLinkedList<Note> notes5 = new SortedLinkedList<Note>();
		Date d5a = new Date("4/21/2011");
		Date d5b = new Date("8/27/2011");
		Date d5c = new Date("4/21/2011");
		Date d5d = new Date("8/27/2011");
		Note n4 = new Note(d5c, "Born in VA");
		Note n5 = new Note(d5d, "Entered Shelter");
		notes5.add(n4);
		notes5.add(n5);
		Animal a5 = new Cat("Jack", d5a, Size.MEDIUM, false, true, notes5, d5b);

		SortedLinkedList<Note> notes6 = new SortedLinkedList<Note>();
		//blank
		Date d6a = new Date("6/25/2011");
		Date d6b = new Date("8/12/2011");
		Date d6c = new Date("8/12/2011");
		Animal a6 = new Cat("George", d6a, Size.MEDIUM, true, true, notes6, d6b, true, d6c, "James and Family");

		SortedLinkedList<Note> notes7 = new SortedLinkedList<Note>();
		Date d7a = new Date("2/20/2019");
		Date d7b = new Date("7/8/2019");
		Date d7c = new Date("7/26/2019");
		Date d7e = new Date("7/8/2019");
		Note n6 = new Note(d7a, "Born in VA");
		Note n7 = new Note(d7e, "Entered Shelter");
		notes7.add(n6);
		notes7.add(n7);
		Animal a7 = new Cat("Leo", d7a, Size.SMALL, true, false, notes7, d7b, true, d7c, "Mason and Family");
		

		list.addRescue("Ms. Wuf's Rescue");
		list.getRescue(0).addAnimal(a1);
		list.getRescue(0).addAnimal(a2);
		list.getRescue(0).addAnimal(a7);
		list.getRescue(0).addAppointment(a2);
		list.addRescue("NCSU Rescue");
		list.getRescue(1).addAnimal(a3);
		list.getRescue(1).addAnimal(a4);
		list.getRescue(1).addAnimal(a5);
		list.getRescue(1).addAnimal(a6);
		list.getRescue(1).addAppointment(a4);
	
		
		try {
			PackDoptionWriter.writeRescueFile("test-files/test1.txt", list);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		checkFiles("test-files/test1.txt", "test-files/test2.txt");
	}

/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
}
