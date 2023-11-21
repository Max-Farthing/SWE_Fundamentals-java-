/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.command;

/**
 * This class creates the Command object as well provides enumeration 
 * for the various Codes that a ticket can provide
 * 
 * @author Max Farthing
 *
 */
public class Command {
	
	/** Constant for Caller Awaiting **/
	public static final String F_CALLER = "Awaiting Caller";
	
	/** Constant for Awaiting change **/
	public static final String F_CHANGE = "Awaiting Change";
	
	/** Constant for Awaiting Provider **/
	public static final String F_PROVIDER = "Awaiting Provider";
	
	/** Constant for Completed **/
	public static final String RC_COMPLETED = "Completed";
	
	/** Constant for not Completed **/
	public static final String RC_NOT_COMPLETED = "Not Completed";
	
	/** Constant for Solved **/
	public static final String RC_SOLVED = "Solved";
	
	/** Constant for Workaround **/
	public static final String RC_WORKAROUND = "Workaround";
	
	/** Constant for Not Solved **/
	public static final String RC_NOT_SOLVED = "Not Solved";
	
	/** Constant for Caller Closed **/
	public static final String RC_CALLER_CLOSED = "Caller Closed";
	
	/** Constant for Duplicate **/
	public static final String CC_DUPLICATE = "Duplicate";
	
	/** Constant for Inappropriate **/
	public static final String CC_INAPPROPRIATE = "Inappropriate";
	
	/** private field for owner id **/
	private String ownerid;
	
	/** private field for note **/
	private String note;
	
	/** Command Value field **/
	private CommandValue commandValue;
	
	/** Resolution Code field **/
	private ResolutionCode resolutionCode;
	
	/** feedback code field **/
	private FeedbackCode feedbackCode;
	
	/** Cancellation Code field **/
	private CancellationCode cancellationCode;

	/**
	 * Enumeration for all possible command values
	 * @author maximus
	 *
	 */
	public enum CommandValue { PROCESS, FEEDBACK, RESOLVE, CONFIRM, REOPEN, CANCEL }

	/**
	 * Enumeration for all possible Feedback values
	 * @author maximus
	 *
	 */
	public enum FeedbackCode { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_PROVIDER }

	/**
	 * Enumeration for all possible Resolution values
	 * @author maximus
	 * 
	 */
	public enum ResolutionCode { COMPLETED, NOT_COMPLETED, SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }

	/**
	 * Enumeration for all possible Cancellation values
	 * @author maximus
	 *
	 */
	public enum CancellationCode { DUPLICATE, INAPPROPRIATE }
	
	/**
	 * Constructor for Command object
	 * @param c command value passed in as parameter
	 * @param ownerId id of owner
	 * @param feedbackCode feedback code value
	 * @param resolutionCode resolution code value 
	 * @param cancellationCode cancellation code value
	 * @param note string for ticket notes
	 * @throws IllegalArgumentException for error
	 */
	public Command(CommandValue c, String ownerId, FeedbackCode feedbackCode, ResolutionCode resolutionCode, CancellationCode cancellationCode, String note) {
		if(c == null) {
			throw new IllegalArgumentException();
		}
		if(c == CommandValue.PROCESS && (ownerId == null || ownerId.isEmpty())) {
			throw new IllegalArgumentException();
		}
		if(c == CommandValue.FEEDBACK && feedbackCode == null) {
			throw new IllegalArgumentException();
		}
		if(c == CommandValue.RESOLVE && resolutionCode == null) {
			throw new IllegalArgumentException();
		}
		if(c == CommandValue.CANCEL && cancellationCode == null) {
			throw new IllegalArgumentException();
		}
		if(note == null || note.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		
		this.commandValue = c;
		this.ownerid = ownerId;
		this.feedbackCode = feedbackCode;
		this.resolutionCode = resolutionCode;
		this.cancellationCode = cancellationCode;
		this.note = note;

	}
	
	/**
	 * getter for command id
	 * @return command value
	 */
	public CommandValue getCommand() {
		return commandValue;
	}
	
	/**
	 * getter for owner id 
	 * @return owner id string
	 */
	public String getOwnerId() {
		return ownerid;
	}
	
	/**
	 * getter for resolution code
	 * @return resolution code value
	 */
	public ResolutionCode getResolutionCode() {
		return resolutionCode;
	}
	
	/**
	 * getter for note method
	 * @return String of note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * getter for feedback code
	 * @return feedback of code
	 */
	public FeedbackCode getFeedbackCode() {
		return feedbackCode;
	}
	
	/** 
	 * getter for cancellation code
	 * @return Cancellation code
	 */
	public CancellationCode getCancellationCode() {
		return cancellationCode;
	}
	

}
