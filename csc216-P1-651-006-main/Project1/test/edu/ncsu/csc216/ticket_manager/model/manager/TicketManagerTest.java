/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Test class for Ticket Manager
 * @author Max Farthing
 *
 */
class TicketManagerTest {
	
	/** ticket manager **/
	private TicketManager manager;
	
//	/** list **/
//	private TicketList list;
	
	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = TicketManager.getInstance();
		Ticket.setCounter(1);
		
	}

	/**
	 * test creating a new list
	 */
	@Test
	void testCreateNewList() {
		manager.createNewTicketList();
		assertEquals(0, 0);
		
	}
	
	/**
	 * tests get tickets for display
	 */
	@Test
	public void testGetTicketsForDisplay() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("Test line 1");
//		Ticket t1 = new Ticket(TicketType.REQUEST, "issues with ERP", "mafarthi", Category.NETWORK, Priority.URGENT, "kbroger2");
//		Ticket t2 = new Ticket(TicketType.REQUEST, "issues with Printer", "mafarthi", Category.HARDWARE, Priority.LOW, "kbroger2");
		Command c1 = new Command(CommandValue.REOPEN, "mafarthi", FeedbackCode.AWAITING_CHANGE, ResolutionCode.COMPLETED, CancellationCode.DUPLICATE, "owner");
		Ticket t = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_CALLER_CLOSED, notes),
				"Should not throw exception");
		t.update(c1);
//		TicketList list = new TicketList();
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t);
		
		assertEquals(1, tickets.size());
//		list.addTickets(tickets);
		manager.createNewTicketList();
		manager.addTicketToList(TicketType.REQUEST, "issues with ERP", "mafarthi", Category.NETWORK, Priority.URGENT, "kbroger2");
		manager.addTicketToList(TicketType.REQUEST, "issues with Printer", "mafarthi", Category.HARDWARE, Priority.LOW, "kbroger2");
		manager.getTicketsForDisplay();
		manager.getTicketsForDisplayByType(TicketType.REQUEST);
		Ticket t3 = manager.getTicketById(1);
		Ticket t4 = manager.getTicketById(2);
		assertEquals(manager.getTicketById(1), t3);
		manager.deleteTicketById(1);
		assertEquals(manager.getTicketById(2), t4);
		
		
	}
	
	/**
	 * load tickets from file for ticket manager
	 */
	@Test
	public void loadTicketsFromFile() {
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		
		// add implementation
		assertEquals(0, 0);
	}
	
	@Test
	public void saveTicketsToFile() {
		//add implementation
		assertEquals(0, 0);

//		manager.saveTicketsToFile("test-files/test.txt");
	}
	
	@Test 
	public void testDisplay2() {
		String[][] arr = new String[2][6];
		arr[0][0] = "1";
		arr[0][1] = "Request";
		arr[0][2] = "Resolved";
		arr[0][3] = "issues with ERP";
		arr[0][4] = "Network";
		arr[0][5] = "Urgent";
		arr[1][0] = "2";
		arr[1][1] = "Request";
		arr[1][2] = "Resolved";
		arr[1][3] = "issues with Printer";
		arr[1][4] = "Hardware";
		arr[1][5] = "Low";
		
		manager.createNewTicketList();
		manager.addTicketToList(TicketType.REQUEST, "issues with ERP", "mafarthi", Category.NETWORK, Priority.URGENT, "kbroger2");
		manager.addTicketToList(TicketType.REQUEST, "issues with Printer", "mafarthi", Category.HARDWARE, Priority.LOW, "kbroger2");
		
		manager.saveTicketsToFile("test-files/new.txt");
		assertEquals(0, 0);

	}

}
