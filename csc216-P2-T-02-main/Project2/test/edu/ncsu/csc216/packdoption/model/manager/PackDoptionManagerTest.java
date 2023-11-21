package edu.ncsu.csc216.packdoption.model.manager;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;


/**
 * test class for PackDoptionManager
 * 
 * @author Max Farthing
 *
 */
class PackDoptionManagerTest {

	/**
	 * Tests get an instance
	 */
	@Test
	public void testGetInstance() {
		PackDoptionManager instance1 = PackDoptionManager.getInstance();
		PackDoptionManager instance2 = PackDoptionManager.getInstance();
		assertEquals(instance1, instance2);
		instance1.newList();
		instance1.isChanged();
		instance1.getFilename();
		instance1.getRescueList();
		instance1.setChanged(false);
		instance1.setFilename("File1");
		instance1.saveFile("File1");
	}
	
	@Test
	void testLoadFile() {
	    PackDoptionManager instance = PackDoptionManager.getInstance();

	    // Test loading a valid file
	    String validFilename = "File1";
	    instance.loadFile(validFilename);
	
	    // Test loading an invalid file
	    String invalidFilename = "invalid_rescue_list.txt";
	    // Add assertions to check that an IllegalArgumentException is thrown when loading an invalid file
	    assertThrows(IllegalArgumentException.class, () -> instance.loadFile(invalidFilename));

	    // Test loading a non-existing file
	    String nonExistingFilename = "non_existing_file.txt";
	    // Add assertions to check that an IllegalArgumentException is thrown when loading a non-existing file
	    assertThrows(IllegalArgumentException.class, () -> instance.loadFile(nonExistingFilename));

	}

}
