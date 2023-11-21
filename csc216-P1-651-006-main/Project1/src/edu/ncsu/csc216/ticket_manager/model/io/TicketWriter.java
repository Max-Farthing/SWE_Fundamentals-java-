/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * This class writes ticket files to a new file after users are done manipulating ticket files
 * @author Max Farthing
 *
 */
public class TicketWriter {

	/**
	 * this method writes to a file using a list of tickets and a filename
	 * @param file name of file
	 * @param list name of ticket list
	 * @throws IllegalArgumentException error
	 */
	public static void writeTicketFile(String file, List<Ticket> list) {
		PrintStream fileWriter;

		try {
			fileWriter = new PrintStream(new File(file));
		} catch(IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		
		for(int i = 0; i < list.size(); i++) {
			fileWriter.println(list.get(i).toString());
		}
		
		fileWriter.close();
	}
}
