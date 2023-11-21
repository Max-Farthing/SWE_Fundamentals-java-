/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * This class maintains a list of tickets that can be manipulated in various ways
 * including adding, removing deleting and more
 * @author Max Farthing
 *
 */
public class TicketList {
	
	/** imported array list of tickets from ticket class **/
	private ArrayList<Ticket> tickets;

	/**
	 * constructor method for a ticket list
	 */
	public TicketList() {
		Ticket.setCounter(1);
		tickets = new ArrayList<Ticket>();
	}
	
	/**
	 * this method takes info about a new ticket and adds it
	 * @param ticketType type of ticket
	 * @param subject string about subject
	 * @param caller caller of ticket
	 * @param category category of ticket
	 * @param priority priority level of ticket
	 * @param owner owner of ticket
	 * @return an integer for the ticket
	 */
	public int addTicket(TicketType ticketType, String subject, String caller, Category category, Priority priority, String owner) {
		Ticket t = new Ticket(ticketType, subject, caller, category, priority, owner);
		tickets.add(t);
		return t.getTicketId();
		
	}
	
	/**
	 * this method adds a ticket to the new specified ticket list
	 * @param list list of tickets being used
	 */
	public void addTickets(List<Ticket> list) {
		tickets.clear();
		for(int i = 0; i < list.size(); i++) {
			Ticket t = list.get(i);
			tickets.add(t);
		}
	}
	
	/**
	 * this method gets all tickets from list
	 * @return list of tickets
	 */
	public List<Ticket> getTickets(){
		return tickets;
	}
	
	/**
	 * this method gets all tickets by their ticket type
	 * @param ticketType type of Ticket
	 * @return list of tickets by type
	 * @throws IllegalArgumentException error
	 */
	public List<Ticket> getTicketsByType(TicketType ticketType){
		ArrayList<Ticket> newTickets = new ArrayList<Ticket>();
		if(ticketType == null) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < tickets.size(); i++) {
			Ticket t = tickets.get(i);
			if(t.getTicketType() == ticketType) {
				newTickets.add(t);
			}
		}
		return newTickets;
	}
	
	/**
	 * gets a ticket by their id
	 * @param id ticket id
	 * @return ticket with id
	 */
	public Ticket getTicketById(int id) {
		for(int i = 0; i < tickets.size(); i++) {
			Ticket t1 = tickets.get(i);
			if(t1.getTicketId() == id) {
				return t1;
			}
		}
		return null;
	}
	
	/**
	 * executes specified command based on command and ticket
	 * @param id ticket id
	 * @param c command specified
	 */
	public void executeCommand(int id, Command c) {
		for(int i = 0; i < tickets.size(); i++) {
			Ticket t = tickets.get(i);
			if(t.getTicketId() == id) {
				t.update(c);
				break;
			}
		}
	}
	
	/**
	 * deletes ticket based upon its id
	 * @param id ticket id
	 */
	public void deleteTicketById(int id) {
		for(int i = 0; i < tickets.size(); i++) {
			Ticket t1 = tickets.get(i);
			if(t1.getTicketId() == id) {
				tickets.remove(t1);
			}
		}
	}
	
}
