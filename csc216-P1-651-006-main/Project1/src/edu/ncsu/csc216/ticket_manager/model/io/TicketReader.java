/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * This class reads in files and takes Ticket information and stores them in array list to be used 
 * throughout this program
 * @author Max Farthing
 *
 */
public class TicketReader {

	/**
	 * this method takes filename as a string and sorts through it finding ticket info
	 * @param file name of file
	 * @return an array list of tickets found in file
	 * @throws IllegalArgumentException error
	 */
	public static ArrayList<Ticket> readTicketFile(String file) {
		Scanner fileReader;
		try {
			fileReader = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		ArrayList<String> notes = new ArrayList<String>();
		try {
			int id = 0;
			String state = null;
			String ticketType = null;
			String subject = null; 
			String caller = null;
			String category = null;
			String priority = null;
			String owner = null;
			String code = null;
			String note = "";
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				if(line.startsWith("*")) {
					
					if(note.length() > 0) {
						notes.add(note);
						note = "";
					}
					
					if(id > 0 && notes != null && notes.size() > 0) {
						Ticket t = new Ticket(id, state, ticketType, subject, caller, category, priority, owner, code, notes);
						tickets.add(t);
					}
					Scanner console = new Scanner(line.substring(1));
					console.useDelimiter("#");
					id = 0;
					 
					 state = null;
					 ticketType = null;
					 subject = null; 
					 caller = null;
					 category = null;
					 priority = null;
					 owner = null;
					 code = null;
					 notes = new ArrayList<String>();
					id = Integer.parseInt(console.next());
					state = console.next();
					ticketType = console.next();
					subject = console.next();
					caller = console.next();
					category = console.next();
					priority = console.next();
					owner = console.next();
					if(console.hasNext()) {
						code = console.next();
					}
					if(console.hasNext()) {
						console.close();
						fileReader.close();
						throw new IllegalArgumentException();
					}
				} else if(line.startsWith("-")) {
					if(note.length() > 0) {
						notes.add(note);
						note = "";
					}
					note = line.substring(1);
				} else if(note.length() > 0) {
					note += "\n" + line;
				} else {
					fileReader.close();
					throw new IllegalArgumentException();
				}
				
			}
			
			if(note.length() > 0) {
				notes.add(note);
				note = "";
			}
			if(id > 0 && notes != null && notes.size() > 0) {
				Ticket t = new Ticket(id, state, ticketType, subject, caller, category, priority, owner, code, notes);
				tickets.add(t);
			}
		
		} catch (IllegalArgumentException E) {
			//placeholder
		}
		
			return tickets;
	}
}
