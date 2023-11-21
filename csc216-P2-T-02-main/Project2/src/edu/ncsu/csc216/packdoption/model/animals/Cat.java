package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Cat class that extends Animal and creates cat object
 * 
 * @author David Soliman
 *
 */
public class Cat extends Animal {

	/**
	 * Main constructor to create cat object of an animal
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
	 */
	public Cat(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted, owner);
	}

	/**
	 * Second cat constructor to create cat object
	 * 
	 * @param name            name
	 * @param birthday        birthday
	 * @param size            size
	 * @param houseTrained    house trained
	 * @param goodWithKids    good with kids
	 * @param notes           notes
	 * @param dateEnterRescue dates enter rescure
	 */
	public Cat(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
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
		if (ageInYears < 4) {
			return AgeCategory.YOUNG;
		} else if (ageInYears < 9) {
			return AgeCategory.ADULT;
		} else {
			return AgeCategory.SENIOR;
		}
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
		    animalArray[1] = "Cat";
		    animalArray[2] = getBirthday().toString();
		    animalArray[3] = String.valueOf(getAge(date));
		    animalArray[4] = getAgeCategory(date).toString();
		    animalArray[5] = adopted() ? "Yes" : "No";
		    animalArray[6] = adopted() ? "" : String.valueOf(getDaysAvailableForAdoption(date));
		    return animalArray;
	}

}
