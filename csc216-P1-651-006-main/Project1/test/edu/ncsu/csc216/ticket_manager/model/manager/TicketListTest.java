package edu.ncsu.csc216.ticket_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Test class for TicketList
 * @author Max Farthing
 *
 */
class TicketListTest {
	
//	/** arraylist of notes **/
//	private final ArrayList<String> notes = new ArrayList<String>();
//	/** note line 1 **/
//	private final String note1 = "note test line 1";
//	/** note line 2 **/
//	private final String note2 = "note test line 2";

	/**
	 * test adding tickets and ticketlist constructor 
	 */
	@Test
	public void testAddTicket() {
//		Ticket t1 = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.F_CHANGE, notes);
//		Ticket t2 = new Ticket(2, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_HARDWARE, Ticket.P_LOW, "kbroger2", Command.RC_CALLER_CLOSED, notes);
//		Ticket t3 = new Ticket(2, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "issues with ERP", "caller", Ticket.C_SOFTWARE, Ticket.P_MEDIUM, "json123", Command.RC_CALLER_CLOSED, notes);
//		Ticket t4 = new Ticket(2, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "issues with ERP", "caller", Ticket.C_DATABASE, Ticket.P_HIGH, "json123", Command.RC_CALLER_CLOSED, notes);

		TicketList t = new TicketList();
		t.addTicket(TicketType.INCIDENT, "issues with ERP", "caller", Category.DATABASE, Priority.HIGH, "json123");
		t.addTicket(TicketType.REQUEST, "issues with ERP", "mafarthi", Category.NETWORK, Priority.URGENT, "kbroger2");
		assertEquals(0, 0);
	}
	
	/**
	 * testing for adding tickets by the list
	 */
	@Test
	public void testAddTicketsList() {
		Ticket t1 = new Ticket(TicketType.REQUEST, "issues with ERP", "mafarthi", Category.NETWORK, Priority.URGENT, "kbroger2");
		Ticket t2 = new Ticket(TicketType.REQUEST, "issues with Printer", "mafarthi", Category.HARDWARE, Priority.LOW, "kbroger2");
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t1);
		tickets.add(t2);
		assertEquals(2, tickets.size());
		
		TicketList t = new TicketList();
		t.addTickets(tickets);
		
	}
	
	
	@Test
	public void testGetTicketsByType() {
		Ticket t1 = new Ticket(TicketType.REQUEST, "issues with ERP", "mafarthi", Category.NETWORK, Priority.URGENT, "kbroger2");
		Ticket t2 = new Ticket(TicketType.REQUEST, "issues with Printer", "json123", Category.HARDWARE, Priority.HIGH, "fakename");
		Ticket t3 = new Ticket(TicketType.INCIDENT, "Test subject", "json123", Category.SOFTWARE, Priority.MEDIUM, "fakename");
		Ticket t4 = new Ticket(TicketType.INCIDENT, "Pizza", "mafarthi", Category.DATABASE, Priority.LOW, "kbroger2");
		
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t1);
		tickets.add(t2);
		tickets.add(t3);
		tickets.add(t4);
		assertEquals(4, tickets.size());
		
		ArrayList<Ticket> ticketsincident = new ArrayList<Ticket>();
		tickets.add(t3);
		tickets.add(t4);
		
		TicketList list2 = new TicketList();
		list2.addTickets(ticketsincident);
		
		TicketList t = new TicketList();
		t.addTickets(tickets);
		
		t.getTicketsByType(TicketType.INCIDENT);
		
		
	}
	
	/**
	 * tests getting ticket by their id
	 */
	@Test
	public void testGetTicketById() {
		Ticket t1 = new Ticket(TicketType.REQUEST, "issues with ERP", "mafarthi", Category.NETWORK, Priority.URGENT, "kbroger2");
		Ticket t2 = new Ticket(TicketType.REQUEST, "issues with Printer", "json123", Category.HARDWARE, Priority.HIGH, "fakename");
		Ticket t3 = new Ticket(TicketType.INCIDENT, "Test subject", "json123", Category.SOFTWARE, Priority.MEDIUM, "fakename");
		Ticket t4 = new Ticket(TicketType.INCIDENT, "Pizza", "mafarthi", Category.DATABASE, Priority.LOW, "kbroger2");
		
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t1);
		tickets.add(t2);
		tickets.add(t3);
		tickets.add(t4);
		assertEquals(4, tickets.size());
		
		TicketList t = new TicketList();
		t.addTickets(tickets);
		
		assertEquals(t.getTicketById(1), t1);
	}
	
	/**
	 * tests removing ticket by their id
	 */
	@Test
	public void testDeleteTicketById() {
		Ticket t1 = new Ticket(TicketType.REQUEST, "issues with ERP", "mafarthi", Category.NETWORK, Priority.URGENT, "kbroger2");
		Ticket t2 = new Ticket(TicketType.REQUEST, "issues with Printer", "json123", Category.HARDWARE, Priority.HIGH, "fakename");
		Ticket t3 = new Ticket(TicketType.INCIDENT, "Test subject", "json123", Category.SOFTWARE, Priority.MEDIUM, "fakename");
		Ticket t4 = new Ticket(TicketType.INCIDENT, "Pizza", "mafarthi", Category.DATABASE, Priority.LOW, "kbroger2");
		
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t1);
		tickets.add(t2);
		tickets.add(t3);
		tickets.add(t4);
		assertEquals(4, tickets.size());
		
		TicketList t = new TicketList();
		t.addTickets(tickets);
		
		t.deleteTicketById(3);
		assertNull(t.getTicketById(3));
	}

}
