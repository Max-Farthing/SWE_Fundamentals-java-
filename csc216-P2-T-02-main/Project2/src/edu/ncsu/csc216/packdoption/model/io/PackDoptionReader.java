package edu.ncsu.csc216.packdoption.model.io;

import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;

import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Pack doption reader class that reads a file.
 * 
 * @author David Soliman
 *
 */
public class PackDoptionReader {
	/**
	 * Reads rescue list file according to the file.
	 * 
	 * @param fileName filename
	 * @return a rescue list read from the file
	 * @throws IllegalArgumentException on any error or incorrect formatting
	 */
	public static RescueList readRescueListFile(String fileName) {
		Scanner fileReader;
		try {
			fileReader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Error opening file.");
		}
		RescueList rescues = new RescueList();
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>(); // clear lists after each iteration?
		SortedLinkedList<Animal> animals = new SortedLinkedList<Animal>();
//		ArrayListQueue<Animal> appointments = new ArrayListQueue<Animal>();
		try {
			int index = -1;
			String animalType = null;
			String name = null;
			String birthday = null;
			String size = null;
			Size aSize = null;
			boolean houseTrained;
			boolean goodWithKids;
			String notePlaceHolder = null;
			String dateEnterRescue = null;
			boolean adopted = false;
			String dateAdopted = null;
			Date adoptedDate = null;
			String owner = null;
			String breed = null;
			Breed aBreed = null;
			Note note = null;
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();

				if (line.startsWith("#")) {
					rescues.addRescue(line.substring(1));
					index++;

				} else if (line.startsWith("*")) {
					if (index < 0) {
						throw new IllegalArgumentException();
					}
					adopted = false;
					notes = new SortedLinkedList<Note>();
					Scanner console = new Scanner(line.substring(1));
					console.useDelimiter(",");
					animalType = console.next().trim();
					if (!"Cat".equals(animalType) && !"Dog".equals(animalType)) {
						console.close();
						throw new IllegalArgumentException();
					}
					name = console.next();
					birthday = console.next(); // create date object
					Date bday = new Date(birthday);
					size = console.next();
					aSize = convertSize(size); // private method
					if (!console.hasNextBoolean()) {
						console.close();
						throw new IllegalArgumentException();
					}
					houseTrained = console.nextBoolean();
					if (!console.hasNextBoolean()) {
						console.close();
						throw new IllegalArgumentException();
					}
					goodWithKids = console.nextBoolean();
					dateEnterRescue = console.next(); // create date object
					Date rescueDate = new Date(dateEnterRescue);
					if (console.hasNextBoolean()) {
						adopted = console.nextBoolean();
						if (adopted) {
							dateAdopted = console.next(); // create date object
							adoptedDate = new Date(dateAdopted);
							owner = console.next();
							if ("Dog".equalsIgnoreCase(animalType)) {
								breed = console.next();
								aBreed = dogBreed(breed); // private method
							}

						} else {
							if ("Dog".equalsIgnoreCase(animalType)) {
								breed = console.next();
								aBreed = dogBreed(breed); // private method
							}
						}
					} else if ("Dog".equalsIgnoreCase(animalType)) {
						breed = console.next();
						aBreed = dogBreed(breed); // private method
					}

					notePlaceHolder = console.next();
					if (notePlaceHolder == null) {
						console.close();
						throw new IllegalArgumentException();
					}
					while (console.hasNext()) { // if line keeps going then rest of line should be notes
						// separate the Date and Note string to create a note object
						String[] arr = console.next().split(" ");
						Date noteDate = new Date(arr[0]);
						String blank = "";
						for (int i = 1; i < arr.length; i++) {
							if (arr.length != 0 && i != arr.length - 1) {
								blank += arr[i] + " ";
							} else {
								blank += arr[i];
							}
						}
						note = new Note(noteDate, blank);
						notes.add(note);
					}

					if (adopted) {
						if ("Dog".equalsIgnoreCase(animalType)) {
							Animal a1 = new Dog(name, bday, aSize, houseTrained, goodWithKids, notes, rescueDate,
									adopted, adoptedDate, owner, aBreed);
							animals.add(a1);
							rescues.getRescue(index).addAnimal(a1);
						} else {
							Animal a2 = new Cat(name, bday, aSize, houseTrained, goodWithKids, notes, rescueDate,
									adopted, adoptedDate, owner);
							animals.add(a2);
							rescues.getRescue(index).addAnimal(a2);
						}
					} else {
						if ("Dog".equalsIgnoreCase(animalType)) {
							Animal a1 = new Dog(name, bday, aSize, houseTrained, goodWithKids, notes, rescueDate,
									aBreed);
							animals.add(a1);
							rescues.getRescue(index).addAnimal(a1);
						} else {
							Animal a2 = new Cat(name, bday, aSize, houseTrained, goodWithKids, notes, rescueDate);
							animals.add(a2);
							rescues.getRescue(index).addAnimal(a2);
						}

					}
					console.close();
				} else if (line.startsWith("-")) {
					Scanner console = new Scanner(line.substring(1));
					console.useDelimiter(",");
					String apptName = null;
					String apptBday = null;
					apptName = console.next().trim();
					apptBday = console.next();
					if (apptBday == null || !Date.isValidDate(apptBday)) {
						console.close();
						throw new IllegalArgumentException();
					}
					for (int i = 0; i < animals.size(); i++) {
						String animalName = animals.get(i).getName();
						String animalBirthday = animals.get(i).getBirthday().toString();
						if (apptName.equals(animalName) && apptBday.equals(animalBirthday)) {
							rescues.getRescue(index).addAppointment(animals.get(i));
						}
					}
					if (fileReader.hasNext("#") && fileReader.nextLine().startsWith("#")) {
						console.close();
						throw new IllegalArgumentException();
					}
					// match appointment to animal then add to ArrayListQueue appointments
				} else {
					if (index < 0) {
						throw new IllegalArgumentException();
					}
					// placeholder for now
					continue;
				}
			}

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		fileReader.close();
		return rescues;
		// Read the rescue list from the file and return it
		// Throw IllegalArgumentException on any error or incorrect formatting
	}

	/**
	 * Private helper calss to return breed
	 * 
	 * @param breed breed
	 * @return breed
	 */
	private static Breed dogBreed(String breed) {
		Breed dogBreed = null;
		switch (breed) {
		case "BEAGLE":
			dogBreed = Breed.BEAGLE;
			break;
		case "BULLDOG":
			dogBreed = Breed.BULLDOG;
			break;
		case "FRENCH_BULLDOG":
			dogBreed = Breed.FRENCH_BULLDOG;
			break;
		case "GERMAN_SHEPHERD":
			dogBreed = Breed.GERMAN_SHEPHERD;
			break;
		case "POINTER_GERMAN_SHORTHAIRED":
			dogBreed = Breed.POINTER_GERMAN_SHORTHAIRED;
			break;
		case "POODLE":
			dogBreed = Breed.POODLE;
			break;
		case "RETRIEVER_GOLDEN":
			dogBreed = Breed.RETRIEVER_GOLDEN;
			break;
		case "RETRIEVER_LABRADOR":
			dogBreed = Breed.RETRIEVER_LABRADOR;
			break;
		case "ROTTWEILER":
			dogBreed = Breed.ROTTWEILER;
			break;
		case "YORKSHIRE_TERRIER":
			dogBreed = Breed.YORKSHIRE_TERRIER;
			break;
		case "MIXED":
			dogBreed = Breed.MIXED;
			break;
		case "OTHER":
			dogBreed = Breed.OTHER;
			break;
		default:
			break;
		}
		return dogBreed;
	}

	/**
	 * Private helper class to convert size
	 * 
	 * @param size size
	 * @return size
	 */
	private static Size convertSize(String size) {
		Size aSize = null;
		switch (size) {
		case "LARGE":
			aSize = Animal.Size.LARGE;
			break;
		case "MEDIUM":
			aSize = Animal.Size.MEDIUM;
			break;
		case "SMALL":
			aSize = Animal.Size.SMALL;
			break;
		default:
			break;
		}
		return aSize;
	}
}
