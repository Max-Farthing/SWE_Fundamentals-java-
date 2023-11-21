package edu.ncsu.csc216.packdoption.util;

/**
 * Note class creates notes to be used for Animal information
 * 
 * @author Max Farthing
 * @author David Soliman
 *
 */
public class Note implements Comparable<Note> {
	/** String message for note **/
	private String message;
	/** Date object **/
	private Date date;

	/**
	 * Constructor for note object using date and message
	 * 
	 * @param date    Date object
	 * @param message contents of note
	 */
	public Note(Date date, String message) {
		if (date == null || message == null || message.trim().isEmpty() || message.contains("\n")
				|| message.contains(",")) {
			throw new IllegalArgumentException("Invalid note");
		}
		this.date = date;
		this.message = message.trim();
	}

	/**
	 * getter for Date
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * getter for message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Compares notes and returns int
	 * 
	 * @param o Note object
	 * @return int of comparison
	 */
	public int compareTo(Note o) {
		if (this.date.compareTo(o.date) != 0) {
			return this.date.compareTo(o.date);
		} else {
			return this.message.compareTo(o.message);
		}
	}

	/**
	 * returns String representation of Note object
	 * 
	 * @return formatted string
	 */
	public String toString() {
		return date.toString() + " " + message;
	}

	/**
	 * creates hashcode for note object
	 * 
	 * @return hashcode of Note object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	/**
	 * Checks if two notes are equal
	 * 
	 * @return boolean if notes are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

}
