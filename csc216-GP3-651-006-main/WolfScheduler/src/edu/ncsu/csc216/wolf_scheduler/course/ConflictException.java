/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is custom exception made to be thrown if User's 
 * activities have any conflicts
 * @author Max Farthing
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * First of two constructors for the error message.
	 * @param message conflict message
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor for error message with conflict.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
