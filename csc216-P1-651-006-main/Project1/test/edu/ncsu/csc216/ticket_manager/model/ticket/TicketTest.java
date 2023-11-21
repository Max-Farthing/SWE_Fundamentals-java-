/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.ticket;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Test class for Ticket
 * @author Max Farthing
 *
 */
public class TicketTest {

	/** Constant for ticket type **/
	private static final TicketType TICKETTYPE1 = TicketType.REQUEST;
	
	/** Constant for ticket type **/
	private static final String TICKETTYPESTRING = "Request";
	
//	/** Constant for ticket type **/
//	private static final TicketType TICKETTYPE2 = TicketType.INCIDENT;
	
	/** Constant for ticket subject **/
	private static final String SUBJECT1 = "Issues with printer";
	
//	/** Constant for ticket subject **/
//	private static final String SUBJECT2 = "Issues with database software";
	
	/** Ticket caller **/
	private static final String CALLER = "mafarthi";
	
	/** ticket category hardware **/
	private static final Category CATEGORY1 = Category.HARDWARE;
	
	/** ticket category hardware **/
	private static final String CATEGORYSTRING = "Hardware";
	
//	/** ticket category software **/
//	private static final Category CATEGORY2 = Category.SOFTWARE;
	
	/** ticket priority **/
	private static final Priority PRIORITY = Priority.MEDIUM;
	
	/** ticket priority **/
	private static final String PRIORITYSTRING = "Medium";
	
	/** ticket owner **/
	private static final String OWNER = "kbroger2";

	/** ticket id **/
	private static final int ID = 1;
	
	/** ticket state working **/
	private static final String STATE1 = "Working";

//	/** ticket state resolved **/
//	private static final String STATE2 = "Resolved";
	
//	/** ticket state closed **/
//	private static final String STATE3 = "Closed";

//	/** ticket state new **/
//	private static final String STATE4 = "New";
//	
//	/** ticket state cancelled **/
//	private static final String STATE5 = "Cancelled";
//	
	/** ticket state feedback **/
	private static final String STATE6 = Ticket.FEEDBACK_NAME;
//
	/** test note 1 **/
	private static final String NOTE1 = "Test note line 1";
	
	/** test note 2 **/
	private static final String NOTE2 = "Test note line 2";
	
//	/** test note 3 **/
//	private static final String NOTE3 = "Test note line 3";
	
	/** ticket code **/
	private static final String CODE = "Not Completed";
	
//	/** ticket code **/
//	private static final String CODE2 = Command.F_CALLER;

	/** array of notes **/
	private static final ArrayList<String> NOTES = new ArrayList<String>();
	
	/**
	 * set up before every test
	 * @throws Exception exception
	 */
	@Before
	public void setUp() throws Exception {
		Ticket.setCounter(1);
	}
	
	
	/**
	 * testing ticket constructor 1
	 */
	@Test
	void testTicketConstructor1() {
		Ticket t = assertDoesNotThrow(
				() -> new Ticket(TICKETTYPE1, SUBJECT1, CALLER, CATEGORY1, PRIORITY, OWNER),
				"Should not throw exception");
		assertAll("Ticket", 
				() -> assertEquals(t.getTicketType(), TICKETTYPE1, "incorrect type"), 
				() -> assertEquals(t.getSubject(), SUBJECT1, "incorrect subject"),
				() -> assertEquals(t.getCaller(), CALLER, "incorrect caller"), 
				() -> assertEquals(t.getCategory(), CATEGORYSTRING, "incorrect category"),
				() -> assertEquals(t.getPriority(), PRIORITYSTRING, "incorrect priority"),
				() -> assertEquals(t.getOwner(), "", "incorrect owner"));
	}

	/**
	 * testing ticket constructor 2
	 */
	@Test
	void testTicketConstructor2() {
		NOTES.add(NOTE1);
		NOTES.add(NOTE2);
		Ticket t = assertDoesNotThrow(
				() -> new Ticket(1, STATE6, Ticket.TT_INCIDENT, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.F_PROVIDER, NOTES),
				"Should not throw exception");
		
		assertAll("Ticket", 
				() -> assertEquals(1, t.getTicketId(), "incorrect id"), 
				() -> assertEquals(STATE6, t.getState(), "incorrect state"), 
				() -> assertEquals(Ticket.TT_INCIDENT, t.getTicketTypeString(), "incorrect type"), 
				() -> assertEquals("issues with ERP", t.getSubject(), "incorrect subject"),
				() -> assertEquals("mafarthi", t.getCaller(), "incorrect caller"), 
				() -> assertEquals(Ticket.C_NETWORK, t.getCategory(), "incorrect category"),
				() -> assertEquals(Ticket.P_URGENT, t.getPriority(), "incorrect priority"),
				() -> assertEquals(Command.F_PROVIDER, t.getFeedbackCode(), "incorrect feedback code"),
				() -> assertEquals("kbroger2", t.getOwner(), "incorrect owner"));
	}
	
	/**
	 * Testing errors with constructor 2
	 */
	@Test
	public void testTicketErrors() {
		NOTES.add(NOTE1);
		NOTES.add(NOTE2);
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(-1, STATE1, TICKETTYPESTRING, SUBJECT1, CALLER, CATEGORYSTRING, PRIORITYSTRING, CODE, OWNER, NOTES));
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ID, "wrong", TICKETTYPESTRING, SUBJECT1, CALLER, CATEGORYSTRING, PRIORITYSTRING, CODE, OWNER, NOTES));
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ID, STATE1, "wrong", SUBJECT1, CALLER, CATEGORYSTRING, PRIORITYSTRING, CODE, OWNER, NOTES));
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ID, STATE1, TICKETTYPESTRING, "", CALLER, CATEGORYSTRING, PRIORITYSTRING, CODE, OWNER, NOTES));
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ID, STATE1, TICKETTYPESTRING, SUBJECT1, "", CATEGORYSTRING, PRIORITYSTRING, CODE, OWNER, NOTES));
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ID, STATE1, TICKETTYPESTRING, SUBJECT1, CALLER, "Random", PRIORITYSTRING, CODE, OWNER, NOTES));
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ID, STATE1, TICKETTYPESTRING, SUBJECT1, CALLER, CATEGORYSTRING, "Wrong", CODE, OWNER, NOTES));
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ID, Ticket.RESOLVED_NAME, TICKETTYPESTRING, SUBJECT1, CALLER, CATEGORYSTRING, PRIORITYSTRING, "CODE", OWNER, NOTES));
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ID, Ticket.RESOLVED_NAME, TICKETTYPESTRING, SUBJECT1, CALLER, CATEGORYSTRING, PRIORITYSTRING, CODE, "", NOTES));
	}
	
	/**
	 * Tests another ticket
	 */
	@Test
	public void testTicket3() {
		NOTES.add(NOTE1);
		Ticket t = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_CALLER_CLOSED, NOTES),
				"Should not throw exception");
		
		assertAll("Ticket", 
				() -> assertEquals(1, t.getTicketId(), "incorrect id"), 
				() -> assertEquals(Ticket.RESOLVED_NAME, t.getState(), "incorrect state"), 
				() -> assertEquals(Ticket.TT_REQUEST, t.getTicketTypeString(), "incorrect type"), 
				() -> assertEquals("issues with ERP", t.getSubject(), "incorrect subject"),
				() -> assertEquals("mafarthi", t.getCaller(), "incorrect caller"), 
				() -> assertEquals(Ticket.C_NETWORK, t.getCategory(), "incorrect category"),
				() -> assertEquals(Ticket.P_URGENT, t.getPriority(), "incorrect priority"),
				() -> assertEquals(Command.RC_CALLER_CLOSED, t.getResolutionCode(), "incorrect feedback code"),
				() -> assertEquals("kbroger2", t.getOwner(), "incorrect owner"));
	}
	
	/**
	 * Tests ticket 
	 */
	@Test
	public void testTicket4() {
		NOTES.add(NOTE1);
		Ticket t = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.CC_INAPPROPRIATE, NOTES),
				"Should not throw exception");
		
		assertAll("Ticket", 
				() -> assertEquals(1, t.getTicketId(), "incorrect id"), 
				() -> assertEquals(Ticket.CANCELED_NAME, t.getState(), "incorrect state"), 
				() -> assertEquals(Ticket.TT_REQUEST, t.getTicketTypeString(), "incorrect type"), 
				() -> assertEquals("issues with ERP", t.getSubject(), "incorrect subject"),
				() -> assertEquals("mafarthi", t.getCaller(), "incorrect caller"), 
				() -> assertEquals(Ticket.C_NETWORK, t.getCategory(), "incorrect category"),
				() -> assertEquals(Ticket.P_URGENT, t.getPriority(), "incorrect priority"),
				() -> assertEquals(Command.CC_INAPPROPRIATE, t.getCancellationCode(), "incorrect feedback code"),
				() -> assertEquals("kbroger2", t.getOwner(), "incorrect owner"));
	}
	
	/**
	 * test state changes
	 */
	@Test
	public void testStateChanges(){
		NOTES.add(NOTE1);
		Ticket t = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_CALLER_CLOSED, NOTES),
				"Should not throw exception");
		Ticket t1 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.NEW_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		Ticket t2 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_CALLER_CLOSED, NOTES),
				"Should not throw exception");
		Ticket t3 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_CALLER_CLOSED, NOTES),
				"Should not throw exception");
		Ticket t4 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.F_CHANGE, NOTES),
				"Should not throw exception");
		Ticket t5 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.NEW_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		Ticket t6 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.F_CHANGE, NOTES),
				"Should not throw exception");
		Ticket t7 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.F_CHANGE, NOTES),
				"Should not throw exception");
		Ticket t8 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_CALLER_CLOSED, NOTES),
				"Should not throw exception");
		Ticket t9 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_INCIDENT, "issues with ERP", "mafarthi", Ticket.C_NETWORK, Ticket.P_URGENT, "kbroger2", Command.RC_CALLER_CLOSED, NOTES),
				"Should not throw exception");
		
		Command c = new Command(CommandValue.FEEDBACK, "mafarthi", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, NOTE1);
		Command c1 = new Command(CommandValue.CONFIRM, "mafarthi", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, NOTE1);
		Command c2 = new Command(CommandValue.CANCEL, "mafarthi", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, NOTE1);
		Command c3 = new Command(CommandValue.PROCESS, "mafarthi", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, NOTE1);
		Command c4 = new Command(CommandValue.RESOLVE, "mafarthi", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, NOTE1);
		Command c5 = new Command(CommandValue.REOPEN, "mafarthi", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, NOTE1);
		Command c6 = new Command(CommandValue.RESOLVE, "mafarthi", FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, NOTE1);
		Command c7 = new Command(CommandValue.RESOLVE, "mafarthi", FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_COMPLETED, CancellationCode.DUPLICATE, NOTE1);


		assertThrows(UnsupportedOperationException.class,
				() -> t6.update(c6));
		assertThrows(UnsupportedOperationException.class,
				() -> t7.update(c7));
		assertThrows(UnsupportedOperationException.class,
				() -> t8.update(c6));
		assertThrows(UnsupportedOperationException.class,
				() -> t9.update(c7));
		
		
		t.getState();
		t.update(c);
		assertEquals(t.getState(), Ticket.FEEDBACK_NAME);
		t.update(c5);
		assertEquals(t.getState(), Ticket.WORKING_NAME);
		t.update(c4);
		assertEquals(t.getState(), Ticket.RESOLVED_NAME);
		t.update(c);
		assertEquals(t.getState(), Ticket.FEEDBACK_NAME);
		t.update(c4);
		assertEquals(t.getState(), Ticket.RESOLVED_NAME);
		t.update(c5);
		assertEquals(t.getState(), Ticket.WORKING_NAME);
		t.update(c2);
		assertEquals(t.getState(), Ticket.CANCELED_NAME);

		assertThrows(UnsupportedOperationException.class,
				() -> t.update(c5));

		assertThrows(UnsupportedOperationException.class,
				() -> t1.update(c));
		t1.update(c2);
		assertEquals(t1.getState(), Ticket.CANCELED_NAME);
		assertThrows(UnsupportedOperationException.class,
				() -> t2.update(c2));
		t2.update(c1);
		assertEquals(t2.getState(), Ticket.CLOSED_NAME);
		assertThrows(UnsupportedOperationException.class,
				() -> t2.update(c));
		assertThrows(UnsupportedOperationException.class,
				() -> t3.update(c5));
		t2.update(c5);
		assertThrows(UnsupportedOperationException.class,
				() -> t3.update(c1));
		assertEquals(t2.getState(), Ticket.WORKING_NAME);

		
		assertThrows(UnsupportedOperationException.class,
				() -> t3.update(c5));
		t3.update(c);
		assertEquals(t3.getState(), Ticket.FEEDBACK_NAME);
		
		t5.update(c3);
		assertEquals(t5.getState(), Ticket.WORKING_NAME);

		t4.update(c2);
		assertEquals(t4.getState(), Ticket.CANCELED_NAME);
//		t4.update(c3);

	}
	
	/**
	 * tests getter for category
	 */
	@Test
	public void testgetCategory() {
		Ticket t = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_URGENT, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		Ticket t1 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_DATABASE, Ticket.P_URGENT, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		Ticket t2 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_SOFTWARE, Ticket.P_URGENT, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		Ticket t3 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_HARDWARE, Ticket.P_URGENT, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		
		
		assertEquals(t.getCategory(), Ticket.C_INQUIRY);
		assertEquals(t1.getCategory(), Ticket.C_DATABASE);
		assertEquals(t2.getCategory(), Ticket.C_SOFTWARE);
		assertEquals(t3.getCategory(), Ticket.C_HARDWARE);

	}
	
	/**
	 * tests getter for priority
	 */
	@Test
	public void testGetPriority() {
		Ticket t = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_LOW, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		Ticket t1 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_HIGH, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		Ticket t2 = assertDoesNotThrow(
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_MEDIUM, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		
		assertEquals(t.getPriority(), Ticket.P_LOW);
		assertEquals(t1.getPriority(), Ticket.P_HIGH);
		assertEquals(t2.getPriority(), Ticket.P_MEDIUM);

	}
	
	/**
	 * test for ticket errors
	 */
	@Test
	public void testTicketErrors2() {
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_LOW, "kbroger2", Command.RC_WORKAROUND, null),
				"Should not throw exception");
		assertThrows(IllegalArgumentException.class,
				() -> new Ticket(2, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_LOW, "kbroger2", Command.RC_WORKAROUND, NOTES),
				"Should not throw exception");
		
			Ticket t2 = new Ticket(3, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_LOW, "kbroger2", Command.F_CALLER, NOTES);
			assertEquals(Command.F_CALLER, t2.getFeedbackCode());	
			
			Ticket t = new Ticket(4, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_LOW, "kbroger2", Command.F_CHANGE, NOTES);
			assertEquals(Command.F_CHANGE, t.getFeedbackCode());
	}			
	
	/**
	 * test for getting notes
	 */
	@Test
	public void testGetNotes() {
		Ticket t2 = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_LOW, "kbroger2", Command.F_CALLER, NOTES);
		assertEquals(t2.getNotes(), "-Test note line 1\n-Test note line 1\n");
	}
	
	/**
	 * Tests to String
	 */
	@Test
	public void testGetTicketToString() {
		ArrayList<String> newNotes = new ArrayList<String>();
		newNotes.add(NOTE1);
		newNotes.add(NOTE1);
		NOTES.remove(0);
		NOTES.remove(1);
		Ticket t2 = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, "issues with ERP", "mafarthi", Ticket.C_INQUIRY, Ticket.P_LOW, "kbroger2", Command.F_CALLER, newNotes);
		assertEquals(t2.toString(), "*1#Feedback#Request#issues with ERP#mafarthi#Inquiry#Low#kbroger2#Awaiting Caller\n-Test note line 1\n-Test note line 1\n");
	}
}
