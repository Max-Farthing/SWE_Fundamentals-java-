package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Test class for ticket writer
 * @author Max Farthing
 *
 */
class TicketWriterTest {
	
	/** arraylist of notes **/
	private final ArrayList<String> notes = new ArrayList<String>();
	/** note line 1 **/
	private final String note1 = "note test line 1";
	/** note line 2 **/
	private final String note2 = "note test line 2";
	/** note line 2 **/
	private final String note3 = "note test line 1";
	/** note line 2 **/
	private final String note4 = "note test line 2";
	/** arraylist of notes **/
	private final ArrayList<String> notes2 = new ArrayList<String>();

	/**
	 * Tests writing tickets to file
	 */
	@Test
	void testWriteTicketFile() {
		notes.add(note1);
		notes.add(note2);
		notes2.add(note3);
		notes2.add(note4);
		Ticket t1 = new Ticket(3, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.F_CHANGE, notes);

		Ticket t2 = new Ticket(4, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_HARDWARE, Ticket.P_LOW, "kbroger2", Command.RC_CALLER_CLOSED, notes2);
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t1);
		tickets.add(t2);
		assertEquals(2, tickets.size());
		
		try {
			TicketWriter.writeTicketFile("test-files/test.txt", tickets);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		checkFiles("test-files/actual.txt", "test-files/test.txt");
	}
	
	@Test
	public void testInvalidFile() {
		notes.add(note1);
		notes.add(note2);
		notes2.add(note3);
		notes2.add(note4);
		Ticket t1 = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.F_CHANGE, notes);

		Ticket t2 = new Ticket(2, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_HARDWARE, Ticket.P_LOW, "kbroger2", Command.RC_CALLER_CLOSED, notes2);
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t1);
		tickets.add(t2);
		assertEquals(2, tickets.size());
		
		try {
			TicketWriter.writeTicketFile("file", tickets);
		} catch (IllegalArgumentException e) {
			//
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
