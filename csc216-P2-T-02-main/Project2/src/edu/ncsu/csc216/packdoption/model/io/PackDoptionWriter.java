package edu.ncsu.csc216.packdoption.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Date;


/**
 * Writer class that writes rescure list to file
 * 
 * @author David Soliman
 * @author Max Farthing
 *
 */
public class PackDoptionWriter {

	/**
	 * Writes the list of rescue to the specified file.
	 * 
	 * @param fileName filename
	 * @param list     list
	 * @throws IllegalArgumentException for IO exceptions
	 * 
	 */
	public static void writeRescueFile(String fileName, RescueList list) {
		PrintStream fileWriter;
		
		try {
			fileWriter = new PrintStream(new File(fileName));
		} catch(IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		String blank = "";
		for(int i = 0; i < list.size(); i++) {
			fileWriter.println("# " + list.getRescue(i).toString());
			
			for(int j = 0; j < list.getRescue(i).numAnimals(); j++){
				Animal a = list.getRescue(i).getAnimal(j);
				if(list.getRescue(i).getAnimal(j) instanceof Dog) {
					Dog d = (Dog) a;
					if(a.adopted()) {
						blank = "* Dog," + a.getName() + "," + a.getBirthday() + "," + a.getSize() + "," + a.isHouseTrained() +
								"," + a.isGoodWithKids() + "," + a.getDateEnterRescue() + "," + a.adopted() + "," + a.getDateAdopted() +
								"," + a.getOwner() + "," + d.getBreed() + ",NOTES";
					} else {
						blank = "* Dog," + a.getName() + "," + a.getBirthday() + "," + a.getSize() + "," + a.isHouseTrained() +
								"," + a.isGoodWithKids() + "," + a.getDateEnterRescue() + "," + d.getBreed() + ",NOTES";
					}
				} else {
					if(a.adopted()) {
						blank = "* Cat," + a.getName() + "," + a.getBirthday() + "," + a.getSize() + "," + a.isHouseTrained() +
								"," + a.isGoodWithKids() + "," + a.getDateEnterRescue() + "," + a.adopted() + "," + a.getDateAdopted() +
								"," + a.getOwner() + ",NOTES";
					} else {
						blank = "* Cat," + a.getName() + "," + a.getBirthday() + "," + a.getSize() + "," + a.isHouseTrained() +
								"," + a.isGoodWithKids() + "," + a.getDateEnterRescue() + ",NOTES";
					}
				}
				for(int z = 0; z < a.getNotes().size(); z++) {
					if(z == 0) {
						blank += "," + a.getNotes().get(z).toString();
					} else if(z < a.getNotes().size()) {
						blank += "," + a.getNotes().get(z).toString();
					} else {
						blank += a.getNotes().get(z).toString();
					}
				}
				fileWriter.println(blank);
			}
			for(int h = 0; h < list.getRescue(i).getAppointments().size(); h++) {
				String name = list.getRescue(i).getAppointments().element().getName();
				Date appt = list.getRescue(i).getAppointments().element().getBirthday();
				fileWriter.println("- " + name + "," + appt);
			}
				fileWriter.println();
			
		}
		
		fileWriter.close();
		// Write the rescue list to the file
		// Throw IllegalArgumentException on any error
	}
}
