package edu.ncsu.csc216.ticket_manager.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;

/**
 * Test class for Command
 * @author Max Farthing
 *
 */
class CommandTest {
	
	/** constant for command value **/
	private static final CommandValue PROCESS = CommandValue.PROCESS;
	
	/** constant for command value **/
	private static final CommandValue FEEDBACK = CommandValue.FEEDBACK;
	
	/** constant for command value **/
	private static final CommandValue RESOLVE = CommandValue.RESOLVE;
	
	/** constant for command value **/
	private static final CommandValue CANCEL = CommandValue.CANCEL;
	
	/** constant for owner **/
	private static final String OWNER = "Mafarthi";
	
	/** constant for resolution code **/
	private static final ResolutionCode NOT_COMPLETED = ResolutionCode.NOT_COMPLETED;
	
	/** constant for note **/
	private static final String NOTE = "Test note";
	
	/** constant for feedback code **/
	private static final FeedbackCode AWAITING_CALLER = FeedbackCode.AWAITING_CALLER;
	
	/** constant for cancellation code **/
	private static final CancellationCode DUPLICATE = CancellationCode.DUPLICATE;

	/**
	 * test constructor and getters
	 */
	@Test
	public void testCommand() {
		Command c = assertDoesNotThrow(
				() -> new Command(PROCESS, OWNER, AWAITING_CALLER, NOT_COMPLETED, DUPLICATE, NOTE),
				"Should not throw exception");
		
		assertAll("Command", 
				() -> assertEquals(PROCESS, c.getCommand(), "incorrect command"), 
				() -> assertEquals(OWNER, c.getOwnerId(), "incorrect owner id"),
				() -> assertEquals(AWAITING_CALLER, c.getFeedbackCode(), "incorrect feedback"), 
				() -> assertEquals(NOT_COMPLETED, c.getResolutionCode(), "incorrect credits"),
				() -> assertEquals(DUPLICATE, c.getCancellationCode(), "incorrect instructor id"),
				() -> assertEquals(NOTE, c.getNote(), "incorrect meeting days"));
	}
	
	/**
	 * testing for throwing exceptions in constructor
	 */
	@Test
	public void testCommandError() {
		assertThrows(IllegalArgumentException.class,
				() -> new Command(null, OWNER, AWAITING_CALLER, NOT_COMPLETED, DUPLICATE, NOTE));
		assertThrows(IllegalArgumentException.class,
				() -> new Command(PROCESS, null, AWAITING_CALLER, NOT_COMPLETED, DUPLICATE, NOTE));
		assertThrows(IllegalArgumentException.class,
				() -> new Command(FEEDBACK, OWNER, null, NOT_COMPLETED, DUPLICATE, NOTE));
		assertThrows(IllegalArgumentException.class,
				() -> new Command(RESOLVE, OWNER, AWAITING_CALLER, null, DUPLICATE, NOTE));
		assertThrows(IllegalArgumentException.class,
				() -> new Command(CANCEL, OWNER, AWAITING_CALLER, NOT_COMPLETED, null, NOTE));
		assertThrows(IllegalArgumentException.class,
				() -> new Command(CANCEL, OWNER, AWAITING_CALLER, NOT_COMPLETED, DUPLICATE, null));
		
	}
	
}
