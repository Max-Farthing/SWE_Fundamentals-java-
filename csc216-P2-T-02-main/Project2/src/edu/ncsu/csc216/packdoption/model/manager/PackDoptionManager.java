package edu.ncsu.csc216.packdoption.model.manager;

import edu.ncsu.csc216.packdoption.model.io.PackDoptionReader;
import edu.ncsu.csc216.packdoption.model.io.PackDoptionWriter;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * Maintains the data structures for the entire application. PackDoptionManager
 * implements the Singleton design pattern. This means that only one instance of
 * the PackDoptionManager can ever be created.
 * 
 * @author David Soliman
 *
 */
public class PackDoptionManager {
	/** Instance of packdoption Manager **/
	private static PackDoptionManager instance;
	/** List of rescues **/
	private RescueList rescues;
	/** Boolean true if changed **/
	private boolean changed;
	/** String of filenames **/
	private String filename;

	/**
	 * Private constructor to prevent direct instantiation of PackDoptionManager.
	 * Use getInstance() method to obtain the instance.
	 */
	private PackDoptionManager() {
		rescues = new RescueList();
		changed = false;
	}

	/**
	 * Returns the instance of PackDoptionManager using the Singleton design
	 * pattern.
	 *
	 * @return The instance of PackDoptionManager.
	 */
	public static PackDoptionManager getInstance() {
		if (instance == null) {
			instance = new PackDoptionManager();
		}
		return instance;
	}

	/**
	 * Sets rescues to a new RescueList.
	 */
	public void newList() {
		rescues = new RescueList();
	}

	/**
	 * Returns whether data has changed from the last save.
	 *
	 * @return true if data has changed, false otherwise.
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Sets the filename with leading and/or trailing whitespace removed. Throws an
	 * IllegalArgumentException if (a) filename is null or (b) filename is only
	 * whitespace.
	 *
	 * @param filename The filename to set.
	 * @throws IllegalArgumentException If filename is null or only whitespace.
	 */
	public void setFilename(String filename) {
		if (filename == null || filename.trim().isEmpty()) {
			throw new IllegalArgumentException("Invalid filename");
		}
		this.filename = filename.trim();
	}

	/**
	 * Loads the RescueList from a file and sets changed to false. Throws an
	 * IllegalArgumentException if any errors occur while reading the file.
	 *
	 * @param filename The filename from which to load the RescueList.
	 * @throws IllegalArgumentException If any errors occur while reading the file.
	 */
	public void loadFile(String filename) {
		RescueList loadedRescues = PackDoptionReader.readRescueListFile(filename);
		if (loadedRescues != null) {
			rescues = loadedRescues;
			changed = false;
		}
	}

	/**
	 * Writes the RescueList to a file and sets changed to false. Throws an
	 * IllegalArgumentException if any errors occur while writing to the file.
	 *
	 * @param filename The filename to which the RescueList should be saved.
	 * @throws IllegalArgumentException If any errors occur while writing to the
	 *                                  file.
	 */
	public void saveFile(String filename) {
		PackDoptionWriter.writeRescueFile(filename, rescues);
		changed = false;
	}

	/**
	 * getter for file name
	 * 
	 * @return filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * getter for rescue list
	 * 
	 * @return rescues
	 */
	public RescueList getRescueList() {
		// TODO Auto-generated method stub
		return rescues;
	}

	/**
	 * Setter for changed
	 * 
	 * @param b b
	 */
	public void setChanged(boolean b) {
		this.changed = b;

	}
}
