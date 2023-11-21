/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Test class for ticket reader
 * @author Max Farthing
 *
 */
class TicketReaderTest {
	
	/** valid ticket **/
	private final String validTestFile = "test-files/ticket1.txt";
	
	/** valid ticket **/
	private final String validTestFile2 = "test-files/ticket2.txt";
	
	/** invalid ticket **/
	private final String invalidTestFile = "test-files/ticket10.txt";
	
	/** expected results for ticket1.txt line 1 **/
	private final String validTicket1 = "*1#New#Incident#GitHub down#sesmith5#Software#Urgent##\n"
			+ "-GitHub is not responding when I navigate to github.ncsu.edu\n";
	/** expected results for ticket1.txt line 2 **/
	private final String validTicket2 = "*2#Working#Request#Workshop account#sesmith5#Inquiry#Low#ccgurley#\n"
			+ "-Create a workshop account for access to a GitHub repo\n"
			+ "-Assigned to ccgurley.\n"
			+ "-How long is the account needed for?\n"
			+ "-Until November 1\n";
	/** expected results for ticket1.txt line 3 **/
	private final String validTicket3 = "*3#Feedback#Request#Add Gradescope plugin to Moodle#ahoward#Software#Medium#itecs#Awaiting Provider\n"
			+ "-Add Gradescope plugin to Moodle to import grades\n"
			+ "-Checking with plugin provider\n";
	/** expected results for ticket1.txt line 4 **/	
	private final String validTicket4 = "*4#Resolved#Incident#Lights not working in EBI 1011#jtking#Hardware#Medium#facilities#Workaround\n"
			+ "-Lights are not working in EB I 1011. \n"
			+ "-Cannot install dimmer switch.  Will leave on.\n";		
	/** expected results for ticket1.txt line 5 **/
	private final String validTicket5 = "*5#Closed#Request#New VM#sesmith5#Inquiry#High#jtking#Completed\n"
			+ "-I would like to request a new VM for my class\n"
			+ "-Assigned to jtking\n"
			+ "-VM created\n"
			+ "-Request completed\n";
	/** expected results for ticket1.txt line 6 **/		
	private final String validTicket6 = "*6#Canceled#Request#Pizza#wpack#Inquiry#Urgent##Inappropriate\n"
			+ "-Deliver a large pizza to EBII 1221!\n"
			+ "-No!\n";
	
	/** array to hold expected results **/
	private final String[] validTickets = {validTicket1, validTicket2, validTicket3, validTicket4, validTicket5,
			validTicket6};
	
	/** line 1 **/
	private final String t2l1 = "*3#Closed#Request#Subject line#caller#Inquiry#Medium#owner#Not Completed\n"
			+ "-a note\n"
			+ "-a note with\n"
			+ "a new line\n"
			+ "-a third note\n";
	/** line 2 **/
	private final String t2l2 = "*6#Canceled#Incident#Subject line#caller#Software#Low#owner#Duplicate\n"
			+ "-a note 2\n"
			+ "-a second note\n"
			+ "-a note with\n"
			+ "a new line\n";
	/** line 3 **/
	private final String t2l3 = "*7#New#Incident#Subject line#caller#Software#Low##\n"
			+ "-a note 3\n";
	/** array of ticket 2 lines **/
	private final String[] validTickets2 = {t2l1, t2l2, t2l3};
	
	/**
	 * test valid records
	 */
	@Test
	public void testTicketReaderValidTickets() {
		ArrayList<Ticket> tickets = TicketReader.readTicketFile(validTestFile);
		assertEquals(6, tickets.size());
		
		for(int i = 0; i < tickets.size(); i++) {
			assertEquals(validTickets[i], tickets.get(i).toString());
		}
	}
	
	/**
	 * test valid records
	 */
	@Test
	public void testTicketReaderValidTickets2() {
		ArrayList<Ticket> tickets = TicketReader.readTicketFile(validTestFile2);
		assertEquals(3, tickets.size());
		
		for(int i = 0; i < tickets.size(); i++) {
			assertEquals(validTickets2[i], tickets.get(i).toString());
		}
	}
	
	/**
	 * test for invalid course records
	 */
	@Test
	public void testTicketReaderInvalidTickets() {
		ArrayList<Ticket> tickets;
		tickets = TicketReader.readTicketFile(invalidTestFile);
		assertEquals(0, tickets.size());
	}

}
