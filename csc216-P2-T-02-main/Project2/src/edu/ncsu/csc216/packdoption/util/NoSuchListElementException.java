package edu.ncsu.csc216.packdoption.util;

/**
 * Custom exception for no element found in a list
 * @author Max Farthing
 *
 */
public class NoSuchListElementException extends RuntimeException {
	/** id of exception **/
	private static final long serialVersionUID = 1L;
	
	/**
	 * default constructor for error message
	 */
	public NoSuchListElementException() {
		this("No such element in list.");
	}
	
	/**
	 * constructor for custom message
	 * @param message message
	 */
	public NoSuchListElementException(String message) {
		super(message);
	}
}
