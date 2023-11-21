package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Dog class that extends Animal and creates dog object
 * 
 * @author David Soliman
 *
 */
public class Dog extends Animal {
	/** Breed of the dog */
	private Breed breed;

	/**
	 * First constructor to create dog object of an animal
	 * 
	 * @param name            name
	 * @param birthday        birthday
	 * @param size            size
	 * @param houseTrained    house trained
	 * @param goodWithKids    good with kids
	 * @param notes           notes
	 * @param dateEnterRescue dates enter rescue
	 * @param adopted         adopted
	 * @param dateAdopted     date adopted
	 * @param owner           owner
	 * @param breed           breed
	 */
	public Dog(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner,
			Breed breed) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted, owner);
		if (breed == null) {
			throw new IllegalArgumentException("Invalid breed");
		}
		this.breed = breed;
	}

	/**
	 * Second dog constructor to create dog object
	 * 
	 * @param name            name
	 * @param birthday        birthday
	 * @param size            size
	 * @param houseTrained    house trained
	 * @param goodWithKids    good with kids
	 * @param notes           notes
	 * @param dateEnterRescue dates enter rescure
	 * @param breed           breed
	 */
	public Dog(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, Breed breed) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
		if (breed == null) {
			throw new IllegalArgumentException("Invalid breed");
		}
		this.breed = breed;
	}

	/**
	 * Getter for age category.
	 * 
	 * @param date date
	 * @return age category
	 */
	@Override
	public AgeCategory getAgeCategory(Date date) {
		int ageInYears = getAge(date);
		Size size = getSize();

		if (size == Size.SMALL) {
			if (ageInYears < 4) {
				return AgeCategory.YOUNG;
			} else if (ageInYears < 9) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		} else if (size == Size.MEDIUM) {
			if (ageInYears < 3) {
				return AgeCategory.YOUNG;
			} else if (ageInYears < 9) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		} else if (size == Size.LARGE) {
			if (ageInYears < 3) {
				return AgeCategory.YOUNG;
			} else if (ageInYears < 6) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		}
		return AgeCategory.ADULT;
	}

	/**
	 * Getter for animal as array.
	 * 
	 * @param date date
	 * @return string array of animals
	 */
	@Override
	public String[] getAnimalAsArray(Date date) {
		 if (date == null || date.compareTo(getBirthday()) < 0) {
		        throw new IllegalArgumentException("Invalid date");
		    }
		    
		    String[] animalArray = new String[7];
		    animalArray[0] = getName();
		    animalArray[1] = "Dog";
		    animalArray[2] = getBirthday().toString();
		    animalArray[3] = String.valueOf(getAge(date));
		    animalArray[4] = getAgeCategory(date).toString();
		    animalArray[5] = adopted() ? "Yes" : "No";
		    animalArray[6] = adopted() ? "" : String.valueOf(getDaysAvailableForAdoption(date));
		    return animalArray;
	}

	/**
	 * Getter for breed
	 * 
	 * @return breed
	 */
	public Breed getBreed() {
		return breed;
	}

	/** Enum of Breeds of dogs **/
	public enum Breed {
		/** Indicates that the command should be a beagle breed */
		BEAGLE,
		/** Indicates that the command should be a bulldog breed */
		BULLDOG,
		/** Indicates that the command should be a bulldog french bulldog breed */
		FRENCH_BULLDOG,
		/** Indicates that the command should be a french bulldog breed */
		GERMAN_SHEPHERD,
		/** Indicates that the command should be a german shepherd breed */
		POINTER_GERMAN_SHORTHAIRED,
		/** Indicates that the command should be a shorthaired german pointer breed */
		POODLE,
		/** Indicates that the command should be a poodle breed */
		RETRIEVER_GOLDEN,
		/** Indicates that the command should be a golden retriever breed */
		RETRIEVER_LABRADOR,
		/** Indicates that the command should be a labrador retriever breed */
		ROTTWEILER,
		/** Indicates that the command should be a yorkshire terrier breed */
		YORKSHIRE_TERRIER,
		/** Indicates that the command should be a mixed breed */
		MIXED,
		/** Indicates that the command should be a other breed */
		OTHER
	}

}
