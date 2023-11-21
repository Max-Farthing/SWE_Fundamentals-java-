package edu.ncsu.csc216.packdoption.model.rescue;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.util.ArrayListQueue;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * A representation of a rescue. Each rescue has a name, a SortedLinkedList of
 * Animals who are part of the rescue, and an ArrayListQueue of Animals that are
 * waiting to see the veterinarian.
 * 
 * @author David Soliman
 *
 */
public class Rescue implements Comparable<Rescue> {

	/** Rescue Name **/
	private String name;

	/** Rescue list of animals **/
	private SortedLinkedList<Animal> animals;

	/** Rescue list of appointments **/
	private ArrayListQueue<Animal> vetAppointments;

	/**
	 * Constructs a rescue with the given name, an empty SortedLinkedList, and an
	 * empty ArrayListQueue.
	 *
	 * @param name the name of the rescue
	 * @throws IllegalArgumentException if name is null, name is whitespace only, or
	 *                                  name contains a newline character
	 */
	public Rescue(String name) {
		if (name == null || name.trim().isEmpty() || name.contains("\n")) {
			throw new IllegalArgumentException("Invalid name");
		}
		this.name = name.trim();
		this.animals = new SortedLinkedList<>();
		this.vetAppointments = new ArrayListQueue<>();
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
	 * Adds an animal to the rescue.
	 *
	 * @param animal the animal to be added
	 * @return true if the animal is added successfully, false if the animal is
	 *         already present in the rescue
	 * @throws IllegalArgumentException if animal is null
	 */
	public boolean addAnimal(Animal animal) {
		if (animal == null) {
			throw new IllegalArgumentException("Animal cannot be null");
		}
		return animals.add(animal);
	}

	/**
	 * Returns the animal at the specified index in the rescue.
	 *
	 * @param i the index of the animal
	 * @return the animal at the specified index
	 * @throws IndexOutOfBoundsException if i is negative or greater than size - 1
	 */
	public Animal getAnimal(int i) {
		return animals.get(i);
	}

	/**
	 * Returns the animal in the rescue with the given name and birthday.
	 *
	 * @param name     the name of the animal
	 * @param birthday the birthday of the animal
	 * @return the animal in the rescue with the given name and birthday, or null if
	 *         not found
	 * @throws IllegalArgumentException if name or birthday is null
	 */
	public Animal getAnimal(String name, Date birthday) {
		if (name == null || birthday == null) {
			throw new IllegalArgumentException("Name and birthday cannot be null");
		}
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			if (animal.getName().equals(name) && animal.getBirthday().equals(birthday)) {
				return animal;
			}
		}
		return null;
	}

	/**
	 * Checks if the rescue contains the specified animal.
	 *
	 * @param a the animal to be checked
	 * @return true if the rescue contains the animal, false otherwise
	 */
	public boolean contains(Animal a) {
		return animals.contains(a);
	}

	/**
	 * Adds a note to the specified animal.
	 *
	 * @param animal the animal to which the note is added
	 * @param note   the note to be added
	 * @return true if the note is added successfully, false if the animal is not in
	 *         the rescue or the note already exists for the animal
	 * @throws IllegalArgumentException if animal or note is null, or if the
	 *                                  animal's notes already contain the note
	 */
	public boolean addNote(Animal animal, Note note) {
		if (animal == null || note == null) {
			throw new IllegalArgumentException("Animal and note cannot be null");
		}
		if (!animals.contains(animal)) {
			return false;
		}
		animal.addNote(note);
		return true;
	}

	/**
	 * Sets the adoption information for the specified animal.
	 *
	 * @param animal      the animal for which adoption information is set
	 * @param adopted     the adoption status (true if adopted, false if not
	 *                    adopted)
	 * @param dateAdopted the date of adoption
	 * @param owner       the owner of the animal
	 * @throws IllegalArgumentException if animal is null
	 */
	public void setAdoptionInfo(Animal animal, boolean adopted, Date dateAdopted, String owner) {
		if (animal == null) {
			throw new IllegalArgumentException("Animal cannot be null");
		}
		if (animals.contains(animal)) {
			animal.setAdoptionInfo(adopted, dateAdopted, owner);
		}

	}

	/**
	 * Returns the number of animals in the rescue.
	 *
	 * @return the number of animals in the rescue
	 */
	public int numAnimals() {
		return animals.size();
	}

	/**
	 * Returns the number of animals in the rescue that are available for adoption
	 * (have not been adopted).
	 *
	 * @return the number of animals available for adoption
	 */
	public int numAnimalsAvailable() {
		int count = 0;
		for (int i = 0; i < animals.size(); i++) {
			if (!animals.get(i).adopted()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the number of animals in the rescue that have been adopted.
	 *
	 * @return the number of animals adopted
	 */
	public int numAnimalsAdopted() {
		int count = 0;
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).adopted()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns a SortedLinkedList of all animals in the rescue that are available
	 * for adoption (have not been adopted).
	 *
	 * @return a SortedLinkedList of available animals
	 */
	public SortedLinkedList<Animal> animalsAvailable() {
		SortedLinkedList<Animal> availableAnimals = new SortedLinkedList<>();
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			if (!animal.adopted()) {
				availableAnimals.add(animal);
			}
		}
		return availableAnimals;
	}

	/**
	 * Returns a SortedLinkedList of all cats in the rescue that are available for
	 * adoption (have not been adopted).
	 *
	 * @return a SortedLinkedList of available cats
	 */
	public SortedLinkedList<Animal> availableCats() {
		SortedLinkedList<Animal> availableCats = new SortedLinkedList<>();
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			if (!animal.adopted() && animal instanceof Cat) {
				availableCats.add(animal);
			}
		}
		return availableCats;
	}

	/**
	 * Returns a SortedLinkedList of all dogs in the rescue that are available for
	 * adoption (have not been adopted).
	 *
	 * @return a SortedLinkedList of available dogs
	 */
	public SortedLinkedList<Animal> availableDogs() {
		SortedLinkedList<Animal> availableDogs = new SortedLinkedList<>();
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			if (!animal.adopted() && animal instanceof Dog) {
				availableDogs.add(animal);
			}
		}
		return availableDogs;
	}

	/**
	 * Returns a SortedLinkedList of animals in the rescue that have been adopted.
	 *
	 * @return a SortedLinkedList of adopted animals
	 */
	public SortedLinkedList<Animal> animalsAdopted() {
		SortedLinkedList<Animal> adoptedAnimals = new SortedLinkedList<>();
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			if (animal.adopted()) {
				adoptedAnimals.add(animal);
			}
		}
		return adoptedAnimals;
	}

	/**
	 * Returns a SortedLinkedList of all animals in the rescue that are available
	 * for adoption and have been available between min and max days.
	 *
	 * @param today the current date
	 * @param min   the minimum number of days in rescue
	 * @param max   the maximum number of days in rescue
	 * @return a SortedLinkedList of available animals within the specified range of
	 *         days in rescue
	 * @throws IllegalArgumentException if today is null, today is before one of the
	 *                                  animal's dateEnterRescue, max is less than
	 *                                  min, or min is less than zero
	 */
	public SortedLinkedList<Animal> availableAnimalsDayRange(Date today, int min, int max) {
		if (today == null) {
			throw new IllegalArgumentException("Today's date cannot be null");
		}
		if (min < 0 || max < min) {
			throw new IllegalArgumentException("Invalid day range");
		}

		SortedLinkedList<Animal> availableAnimals = new SortedLinkedList<>();
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			if (!animal.adopted() && animal.getDaysAvailableForAdoption(today) >= min
					&& animal.getDaysAvailableForAdoption(today) <= max) {
				availableAnimals.add(animal);
			}
		}
		return availableAnimals;
	}

	/**
	 * Returns a SortedLinkedList of all animals in the rescue that are available
	 * for adoption and are between min and max years old .
	 *
	 * @param today the current date
	 * @param min   the minimum age in years
	 * @param max   the maximum age in years
	 * @return a SortedLinkedList of available animals within the specified range of
	 *         ages
	 * @throws IllegalArgumentException if today is null, today is before one of the
	 *                                  animal's birthday, max is less than min, or
	 *                                  min is less than zero
	 */
	public SortedLinkedList<Animal> availableAnimalsAge(Date today, int min, int max) {
		if (today == null) {
			throw new IllegalArgumentException("Today's date cannot be null");
		}
		if (min < 0 || max < min) {
			throw new IllegalArgumentException("Invalid age range");
		}

		SortedLinkedList<Animal> availableAnimals = new SortedLinkedList<>();
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			if (!animal.adopted() && animal.getAge(today) >= min && animal.getAge(today) <= max) {
				availableAnimals.add(animal);
			}
		}
		return availableAnimals;
	}

	/**
	 * Compares this Rescue object with the specified Rescue object based on names.
	 *
	 * @param o the Rescue object to be compared
	 * @return a negative integer, zero, or a positive integer if this object is
	 *         less than, equal to, or greater than the specified object
	 */
	public int compareTo(Rescue o) {
		return this.name.compareTo(o.name);
	}

	/**
	 * Returns the hash code value for this Rescue object.
	 *
	 * @return the hash code value for this object
	 */
	public int hashCode() {
		return name.hashCode();
	}

	/**
	 * Indicates whether some other object is "equal to" one based on names.
	 *
	 * @param obj the object to compare
	 * @return true if this object is the same as the obj argument, false otherwise
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Rescue rescue = (Rescue) obj;
		return name.equals(rescue.name);
	}

	/**
	 * Returns the string representation of the Rescue object, which is the name of
	 * the rescue.
	 *
	 * @return the name of the rescue
	 */
	public String toString() {
		return name;
	}

	/**
	 * Returns a 2D array of strings representing all animals in the rescue. The
	 * first dimension represents the animal. The second dimension represents the
	 * different elements: . If the list is empty, an empty 2D array is returned.
	 *
	 * @param today the current date
	 * @return a 2D array of strings representing all animals in the rescue
	 */
	public String[][] getAnimalsAsArray(Date today) {
		String[][] animalsArray = new String[animals.size()][7];
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			animalsArray[i][0] = animal.getName();
			animalsArray[i][1] = animal instanceof Cat ? "Cat" : "Dog";
			animalsArray[i][2] = animal.getBirthday().toString();
			animalsArray[i][3] = Integer.toString(animal.getAge(today));
			animalsArray[i][4] = animal.getAgeCategory(today).toString();
			animalsArray[i][5] = animal.adopted() ? "Yes" : "No";
			animalsArray[i][6] = animal.adopted() ? "" : Integer.toString(animal.getDaysAvailableForAdoption(today));
		}
		return animalsArray;

	}

	/**
	 * Adds an animal to the vetAppointments queue.
	 *
	 * @param animal the animal to be added to the queue
	 * @return true if the animal is added successfully, false if the animal is not
	 *         in the rescue
	 * @throws NullPointerException if animal is null
	 */
	public boolean addAppointment(Animal animal) {
		if (animal == null) {
			throw new NullPointerException("Animal cannot be null");
		}
		if (animals.contains(animal)) {
			vetAppointments.add(animal);
			return true;
		}
		return false;
	}

	/**
	 * Returns a 2D array of strings representing animals in the veterinary queue.
	 * The first dimension represents the animal (in the same order as the queue).
	 * The second dimension represents the different elements If the queue is empty,
	 * an empty 2D array is returned.
	 *
	 * @param today the current date
	 * @return a 2D array of strings representing animals in the veterinary queue
	 */
	public String[][] getAppointmentsAsArray(Date today) {
		String[][] appointmentsArray = new String[vetAppointments.size()][7];
		ArrayListQueue<Animal> tempQueue = new ArrayListQueue<>();
		int i = 0;
		while (!vetAppointments.isEmpty()) {
			Animal animal = vetAppointments.remove();
			appointmentsArray[i][0] = animal.getName();
			appointmentsArray[i][1] = animal instanceof Cat ? "Cat" : "Dog";
			appointmentsArray[i][2] = animal.getBirthday().toString();
			appointmentsArray[i][3] = Integer.toString(animal.getAge(today));
			appointmentsArray[i][4] = animal.getAgeCategory(today).toString();
			appointmentsArray[i][5] = animal.adopted() ? "Yes" : "No";
			appointmentsArray[i][6] = animal.adopted() ? ""
					: Integer.toString(animal.getDaysAvailableForAdoption(today));
			tempQueue.add(animal);
			i++;
		}
		while (!tempQueue.isEmpty()) {
			vetAppointments.add(tempQueue.remove());
		}
		return appointmentsArray;
	}

	/**
	 * Getter for vetAppointments.
	 * 
	 * @return the vetAppointments queue
	 */
	public ArrayListQueue<Animal> getAppointments() {
		return vetAppointments;
	}
}
