package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Animal class is an abstract class used to create an animal object, superclass
 * to Cat and Dog. Animal object characteristics are different depending on if
 * they have been adopted.
 * 
 * @author Max Farthing, David Soliman
 *
 */
public abstract class Animal implements Comparable<Animal> {
	/** Animal Name **/
	private String name;
	/** boolean is animal house trained **/
	private boolean houseTrained;
	/** boolean is animal good with kids **/
	private boolean goodWithKids;
	/** boolean is animal adopted **/
	private boolean adopted;
	/** animal owner **/
	private String owner;
	/** date animal entered rescue **/
	private Date dateEnterRescue;
	/** date of animals birthday **/
	private Date birthday;
	/** date of animal adoption **/
	private Date dateAdopted;
	/** size of animal **/
	private Size size;
	/** sorted linked list of notes **/
	private SortedLinkedList<Note> notes;

	/** enumeration for size values **/
	public enum Size {
		/** Small Size enumeration **/
		SMALL,
		/** Medium Size enumeration **/
		MEDIUM,
		/** Large Size enumeration **/
		LARGE
	}

	/** enumeration of age categories **/
	public enum AgeCategory {
		/** Young age enumeration **/
		YOUNG,
		/** Adult age enumeration **/
		ADULT,
		/** Senior age enumeration **/
		SENIOR
	}

	/**
	 * First animal constructor used if animal has been adopted
	 * 
	 * @param name            animal name
	 * @param birthday        animal birthday
	 * @param size            animal size
	 * @param houseTrained    boolean if animal is house trained
	 * @param goodWithKids    boolean if animal is good with kids
	 * @param notes           list of notes
	 * @param dateEnterRescue Date of animal entering rescue
	 * @param adopted         boolean if animal is adopted
	 * @param dateAdopted     Date of animal adoption
	 * @param owner           animal owner
	 */
	public Animal(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner) {
		if (name == null || name.trim().isEmpty() || name.contains("\n") || name.contains(",")) {
			throw new IllegalArgumentException("Invalid name");
		}
		if (birthday == null) {
			throw new IllegalArgumentException("Invalid birthday");
		}
		if (size == null) {
			throw new IllegalArgumentException("Invalid size");
		}
		if (notes == null) {
			throw new IllegalArgumentException("Invalid notes");
		}
		if (dateEnterRescue == null) {
			throw new IllegalArgumentException("Invalid dateEnterRescue");
		}
		if (dateEnterRescue.compareTo(birthday) < 0) {
			throw new IllegalArgumentException("Invalid dateEnterRescue");
		}
		if (adopted && (dateAdopted == null || owner == null)) {
			throw new IllegalArgumentException("Invalid adoption information");
		}
		if (!adopted && (dateAdopted != null || owner != null)) {
			throw new IllegalArgumentException("Invalid adoption information");
		}
		if(adopted && dateAdopted != null && dateEnterRescue.daysTo(dateAdopted) < 0) {
			throw new IllegalArgumentException("Invalid adoption information");
		}
		if (owner != null && (owner.trim().isEmpty() || owner.contains("\n") || owner.contains(","))) {
			throw new IllegalArgumentException("Invalid owner");
		}

		this.name = name.trim();
		this.houseTrained = houseTrained;
		this.goodWithKids = goodWithKids;
		setSize(size);
		this.notes = notes;
		this.dateEnterRescue = dateEnterRescue;
		this.adopted = adopted;
		this.dateAdopted = dateAdopted;
		this.owner = owner;
		this.birthday = birthday;
		if (this.owner != null) {
			this.owner = this.owner.trim();
		}
	}

	/**
	 * Second constructor for animal used if animal has not been adopted
	 * 
	 * @param name            animal name
	 * @param birthday        animal birthday
	 * @param size            animal size
	 * @param houseTrained    boolean if animal is house trained
	 * @param goodWithKids    boolean if animal is good with kids
	 * @param notes           list of notes
	 * @param dateEnterRescue Date of animal entering rescue
	 */
	public Animal(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue) {

		this(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, false, null, null);
	}

	/**
	 * sets animal adoption info
	 * 
	 * @param owner       owner
	 * @param dateAdopted date adopted
	 * @param adopted     adopted
	 */
	public void setAdoptionInfo(boolean adopted, Date dateAdopted, String owner) {
		// Validate the input values
		if (adopted && (dateAdopted == null || owner == null || owner.trim().isEmpty() || owner.contains("\n")
				|| owner.contains(","))) {
			throw new IllegalArgumentException("Invalid adoption information");
		}
		if (!adopted && (dateAdopted != null || owner != null)) {
			throw new IllegalArgumentException("Invalid adoption information");
		}
		if(adopted && getDateEnterRescue().daysTo(dateAdopted) < 0) {
			throw new IllegalArgumentException();
		}

		this.adopted = adopted;
		this.dateAdopted = dateAdopted;
		this.owner = owner;
		if (this.owner != null) {
			this.owner = this.owner.trim();
		}
	}

	/**
	 * Sets animal size
	 * 
	 * @param size animal size
	 */
	public void setSize(Size size) {
		if (size == null) {
			throw new IllegalArgumentException("Invalid size");
		}
		this.size = size;
	}

	/**
	 * getter for name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for birthday
	 * 
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * getter for size
	 * 
	 * @return the size
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * getter for is house trained
	 * 
	 * @return the houseTrained
	 */
	public boolean isHouseTrained() {
		return houseTrained;
	}

	/**
	 * getter for is good with kids
	 * 
	 * @return the goodWithKids
	 */
	public boolean isGoodWithKids() {
		return goodWithKids;
	}

	/**
	 * getter for notes
	 * 
	 * @return the note
	 */
	public SortedLinkedList<Note> getNotes() {
		return notes;
	}

	/**
	 * getter for Date animal entered rescue
	 * 
	 * @return the dateEnterRescue
	 */
	public Date getDateEnterRescue() {
		return dateEnterRescue;
	}

	/**
	 * getter for date animal adoption
	 * 
	 * @return the dateAdopted
	 */
	public Date getDateAdopted() {
		return dateAdopted;
	}

	/**
	 * getter for owner
	 * 
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * getter for is adopted
	 * 
	 * @return the adopted
	 */
	public boolean adopted() {
		return adopted;
	}

	/**
	 * method adds a note to the list of notes
	 * 
	 * @param note note object
	 * @return boolean if added
	 */
	public boolean addNote(Note note) {
		if (note == null) {
			throw new IllegalArgumentException("Invalid note");
		}
		return notes.add(note);
	}

	/**
	 * Compare to method to compare animals
	 * 
	 * @param animal Animal object
	 * @return int of comparison
	 */
	public int compareTo(Animal animal) {
		int result = birthday.compareTo(animal.birthday);
		if (result == 0) {
			result = name.compareTo(animal.name);
		}
		return result;
	}

	/**
	 * initial implementation of hashcode(may be changed)
	 * 
	 * @return int of animal hashcode
	 */
	@Override
	public int hashCode() {
		 final int prime = 31;
		    int result = 1;
		    result = prime * result + ((name == null) ? 0 : name.hashCode());
		    result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		    return result;
	}

	/**
	 * initial of equals of hashcode(may be changed)
	 * 
	 * @return boolean if animals are equals
	 */
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Animal other = (Animal) obj;
	    return name.equals(other.name) && birthday.equals(other.birthday);
	}

	/**
	 * toString method used for later implementation
	 * 
	 * @return String of animal object
	 */
	public String toString() {

		String s = getName() + " (" + getBirthday() + ")\n" + getNotes().toString();
		return s;
	}

	/**
	 * getter for age of animal
	 * 
	 * @param today Date object
	 * @return int of animal age
	 */
	public int getAge(Date today) {
		if (today == null || today.compareTo(birthday) < 0) {
			throw new IllegalArgumentException("Invalid date");
		}
		return birthday.yearsTo(today);
	}

	/**
	 * method checks available days for adoption
	 * 
	 * @param today Date object
	 * @return int of adoption days
	 */
	public int getDaysAvailableForAdoption(Date today) {
		if (today == null || today.compareTo(dateEnterRescue) < 0) {
			throw new IllegalArgumentException("Invalid date");
		}
		if (adopted) {
			return -1;
		}
		return dateEnterRescue.daysTo(today);
	}

	/**
	 * abstract method to return animal as an array
	 * 
	 * @param today Date object
	 * @return animal as an array
	 */
	public abstract String[] getAnimalAsArray(Date today);

	/**
	 * abstract method to get ageCategory of animal
	 * 
	 * @param today Date object
	 * @return AgeCategory of animal
	 */
	public abstract AgeCategory getAgeCategory(Date today);

}
