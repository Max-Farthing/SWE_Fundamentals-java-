package edu.ncsu.csc216.packdoption.model.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * test class for PackDoptionReader
 * @author Max Farthing
 *
 */
public class PackDoptionReaderTest {
	
	/** valid test file **/
	private final String validTestFile = "test-files/PackDoption2.txt";
	/** test file line 1 **/
	private final String line1 = "Ms. Wuf's Rescue";
//	/** test file line 2 **/
//	private final String line2 = "* Cat,Loki,3/14/2004,SMALL,false,true,4/8/2004,true,5/13/2004,Ethan and Family,NOTES,3/14/2004 Born in NC";
//	/** test file line 3 **/
//	private final String line3 = "* Cat,Lily,5/14/2011,SMALL,false,true,6/23/2011,NOTES";
//	/** test file line 4 **/
//	private final String line4 = "* Cat,Leo,2/20/2019,SMALL,true,false,7/8/2019,true,7/26/2019,Mason and Family,NOTES,2/20/2019 Born in VA,7/8/2019 Entered Shelter";
//	/** test file line 5 **/
//	private final String line5 = "- Lily,5/14/2011";
	/** test file line 6 **/
	private final String line6 = "NCSU Rescue";
//	/** test file line 7 **/
//	private final String line7 = "* Dog,Jack,3/9/2001,LARGE,true,true,6/1/2001,POINTER_GERMAN_SHORTHAIRED,NOTES,3/9/2001 Born in NC";
//	/** test file line 8 **/
//	private final String line8 = "* Dog,Duke,2/29/2004,LARGE,true,true,7/21/2004,true,7/28/2004,Olivia and Family,ROTTWEILER,NOTES,2/29/2004 Born in NC";
//	/** test file line 9 **/
//	private final String line9 = "* Cat,Jack,4/21/2011,MEDIUM,false,true,8/27/2011,NOTES,4/21/2011 Born in VA,8/27/2011 Entered Shelter";
//	/** test file line 10 **/
//	private final String line10 = "* Cat,George,6/25/2011,MEDIUM,true,true,8/12/2011,true,8/12/2011,James and Family,NOTES";
//	/** test file line 11 **/
//	private final String line11 = "- Jack,3/9/2001";
//	/** test file line 12 **/
//	private final String line12 = "- George,6/25/2011";
//	/** test file line 13 **/
//	private final String line13 = "- Duke,2/29/2004";
//	/** array animals **/
//	private final String[] animals = {line2, line3, line4, line6, line7, line8, line9, line10};
	/** array of the rescues **/
	private final String[] rescues = {line1, line6};
//	/** array representing appointments **/
//	private final String[] appts = {line5, line11, line12, line13 };

	/**
	 * Testing valid ticket input
	 */
	@Test
	void testReadValidFile() {
		RescueList rescue = PackDoptionReader.readRescueListFile(validTestFile);
		assertEquals(2, rescue.size());
		for(int i = 0; i < rescue.size(); i++) {
			assertEquals(rescues[i], rescue.getRescue(i).toString());
//			for(int j = 0; j < rescue.getRescue(i).numAnimals(); j++) {
//				assertEquals(animals[j], rescue.getRescue(i).getAnimal(j));
//			}
//			for(int h = 0; i < rescue.getRescue(i).getAppointments().size(); h++) {
//				assertEquals(appts[h], rescue.getRescue(i).getAppointments().toString());
//			}
		}
		
		assertEquals(1, rescue.getRescue(0).getAppointments().size());
		assertEquals(3, rescue.getRescue(1).getAppointments().size());

	}
	
	/**
	 * testing invalid cases
	 */
	@Test
	public void testInvalidCases() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			RescueList rescue = PackDoptionReader.readRescueListFile("test-files/invalid.txt");
			rescue.getClass();
			});
		
		assertThrows(IllegalArgumentException.class, () -> {
			RescueList rescue = PackDoptionReader.readRescueListFile("test-files/invalid2.txt");
			rescue.getClass();
			});
		
		assertThrows(IllegalArgumentException.class, () -> {
			RescueList rescue = PackDoptionReader.readRescueListFile("test-files/invalid3.txt");
			rescue.getClass();
			});
		
		assertThrows(IllegalArgumentException.class, () -> {
			RescueList rescue = PackDoptionReader.readRescueListFile("test-files/invalid4.txt");
			rescue.getClass();
			});
		
		assertThrows(IllegalArgumentException.class, () -> {
			RescueList rescue = PackDoptionReader.readRescueListFile("test-files/invalid5.txt");
			rescue.getClass();
			});
	}

}
