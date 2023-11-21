/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.io.TicketReader;
import edu.ncsu.csc216.ticket_manager.model.io.TicketWriter;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;
import java.util.ArrayList;
import java.util.List;



/**
 * This class controls the creation and modification of ticket lists
 * @author Max Farthing
 *
 */
public class TicketManager {
	
	/** TicketList of tickets **/
	private TicketList ticketList;
	
	/** instance of ticketManager **/
	private static TicketManager instance;
	
	/**
	 * private constructor for the class
	 */
	private TicketManager() {

		//empty for now
	}
	
	/**
	 * static method to get instance of ticket
	 * @return Ticket manager of the instance 
	 */
	public static TicketManager getInstance() {
		if (instance == null) {
			instance = new TicketManager();
		}
		return instance;
	}
	
	/**
	 * Save tickets to a file
	 * @param fileName String of file name
	 */
	public void saveTicketsToFile(String fileName) {
		List<Ticket> arrlist = new ArrayList<Ticket>();
		arrlist = ticketList.getTickets();
		TicketWriter.writeTicketFile(fileName, arrlist);
	}
	
	/**
	 * loads ticket from file
	 * @param file filename
	 */
	public void loadTicketsFromFile(String file) {
		instance.createNewTicketList();
//		TicketReader.readTicketFile(file);
		ticketList.addTickets(TicketReader.readTicketFile(file));
	}
	
	/**
	 * creates a new ticket list
	 */
	public void createNewTicketList() {
		ticketList = new TicketList();
	}
	
	/**
	 * returns a 2d array of tickets for the GUI
	 * @return 2d String array
	 */
	public String[][] getTicketsForDisplay(){
		instance.createNewTicketList();
		List<Ticket> arrlist = new ArrayList<Ticket>();
		arrlist = instance.ticketList.getTickets();
		
		String[][] arr = new String[arrlist.size()][6];
		for(int i = 0; i < arrlist.size(); i++) {
			Ticket t = arrlist.get(i);
			arr[i][0] = String.valueOf(t.getTicketId());
			arr[i][1] = t.getTicketTypeString();
			arr[i][2] = t.getState();
			arr[i][3] =	t.getSubject();
			arr[i][4] = t.getCategory();
			arr[i][5] = t.getPriority();
		}
		return arr;
	}
	
	/**
	 * returns a 2d array of certain ticket types
	 * @param ticketType type of ticket wanted
	 * @return a 2d String array
	 * @throws IllegalArgumentException error
	 */
	public String[][] getTicketsForDisplayByType(TicketType ticketType){
		if(ticketType == null) {
			throw new IllegalArgumentException();
		}
		
		List<Ticket> arrlist = new ArrayList<Ticket>();
		arrlist = ticketList.getTicketsByType(ticketType);
		
		String[][] arr = new String[arrlist.size()][6];
		for(int i = 0; i < arrlist.size(); i++) {
			Ticket t = arrlist.get(i);
			if(t.getTicketType() == ticketType) {
				arr[i][0] = String.valueOf(t.getTicketId());
				arr[i][1] = t.getTicketTypeString();
				arr[i][2] = t.getState();
				arr[i][3] =	t.getSubject();
				arr[i][4] = t.getCategory();
				arr[i][5] = t.getPriority();
			}
		}
		return arr;
	}
	
	/**
	 * finds ticket by their ID
	 * @param id id of ticket
	 * @return ticket with id 
	 */
	public Ticket getTicketById(int id) {
		
		return ticketList.getTicketById(id);
		
	}
	
	/**
	 * executes command
	 * @param id id of command
	 * @param c name of command
	 */
	public void executeCommand(int id, Command c) {
		ticketList.executeCommand(id, c);
	}
	
	/**
	 * deletes ticket based on id
	 * @param id ticket id
	 */
	public void deleteTicketById(int id) {
		ticketList.deleteTicketById(id);
	}
	
	/**
	 * Adds ticket to list with all relevant info
	 * @param ticketType Type of ticket
	 * @param subject String of subject
	 * @param caller String of caller
	 * @param category category of ticket
	 * @param priority priority level of ticket
	 * @param owner String of owner
	 */
	public void addTicketToList(TicketType ticketType, String subject, String caller, Category category, Priority priority, String owner) {
		Ticket t = new Ticket(ticketType, subject, caller, category, priority, owner);
		ticketList.getTickets().add(t);
	}
}
