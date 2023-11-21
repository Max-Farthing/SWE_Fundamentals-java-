/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.ticket;

import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;

/**
 * Ticket Class Creates the ticket object as well implements the TicketState Interface
 * to hold all six possible ticket states as inner classes
 * @author Max Farthing
 *
 */
public class Ticket {
	
	/** ticket interface state **/
	private TicketState state;
	
	/** new state **/
	private final NewState newState = new NewState();
	
	/** working state **/
	private final WorkingState workingState = new WorkingState();
	
	/** closed state **/
	private final ClosedState closedState = new ClosedState();
	
	/** resolved state **/
	private final ResolvedState resolvedState = new ResolvedState();
	
	/** canceled state **/
	private final CanceledState canceledState = new CanceledState();
	
	/** new state **/
	private final FeedbackState feedbackState = new FeedbackState();

	/** constant for Request TicketType **/
	public static final String TT_REQUEST = "Request";
	
	/** constant for Incident TicketType **/
	public static final String TT_INCIDENT = "Incident";
	
	/** constant for Inquiry Category **/
	public static final String C_INQUIRY = "Inquiry";
	
	/** constant for Software Category **/
	public static final String C_SOFTWARE = "Software";
	
	/** constant for Hardware Category **/
	public static final String C_HARDWARE = "Hardware";
	
	/** constant for Network Category **/
	public static final String C_NETWORK = "Network";
	
	/** constant for Database Category **/
	public static final String C_DATABASE = "Database";
	
	/** constant for Priority Urgent **/
	public static final String P_URGENT = "Urgent";
	
	/** constant for Priority High **/
	public static final String P_HIGH = "High";
	
	/** constant for Priority Medium **/
	public static final String P_MEDIUM = "Medium";
	
	/** constant for Priority Low **/
	public static final String P_LOW = "Low";
	
	/** constant for New ticket name **/
	public static final String NEW_NAME = "New";
	
	/** constant for working ticket name **/
	public static final String WORKING_NAME = "Working";
	
	/** constant for feedback ticket name **/
	public static final String FEEDBACK_NAME = "Feedback";

	/** constant for resolved ticket name **/
	public static final String RESOLVED_NAME = "Resolved";
	
	/** constant for closed ticket name **/
	public static final String CLOSED_NAME = "Closed";
	
	/** constant for canceled ticket name **/
	public static final String CANCELED_NAME = "Canceled";

	/** counter to keep track of tickets **/
	private static int counter = 1;
	
	/** ticket id number **/
	private int ticketid;
	
	/** ticket subject **/
	private String subject;
	
	/** ticket caller **/
	private String caller;
	
	/** ticket owner **/
	private String owner;
	
	/** ticket notes **/
	private ArrayList<String> notes;
	
	/** category **/
	private Category category;
	
	/** ticket type **/
	private TicketType ticketType;
	
	/** priority **/
	private Priority priority;
	
	/** feedback code **/
	private FeedbackCode feedBackCode;
	
	/** cancellation code **/
	private CancellationCode cancellationCode;
	
	/** resolution code **/
	private ResolutionCode resolutionCode;
	
	/**
	 * enumeration for all possible category values
	 * @author maximus
	 *
	 */
	public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }

	/**
	 * enumeration for all possible priority values
	 * @author maximus
	 *
	 */
	public enum Priority { URGENT, HIGH, MEDIUM, LOW }
	
	/**
	 * enumeration for all ticket types
	 * @author maximus
	 * 
	 */
	public enum TicketType { REQUEST, INCIDENT }
	
	
	
	/**
	 * Ticket constructor that creates a ticket with detailed info
	 * @param id id ticket number
	 * @param state ticket state
	 * @param ticketType type of ticket
	 * @param subject ticket subject
	 * @param caller ticket caller 
	 * @param category ticket category
	 * @param priority ticket priority
	 * @param owner ticket owner 
	 * @param code ticker code
	 * @param notes ticket notes
	 * @throws IllegalArgumentException error
	 */
	public Ticket(int id, String state, String ticketType, String subject, String caller, String category, String priority, String owner, String code, ArrayList<String> notes) {
	
		
		
		if(id <= 0) {
			throw new IllegalArgumentException();
		}
		if(id > counter) {
			setCounter(id + 1);
		}
		if(state.equals(FEEDBACK_NAME) && !code.equals(Command.F_CALLER) && !code.equals(Command.F_CHANGE) && !code.equals(Command.F_PROVIDER)) {
			
			throw new IllegalArgumentException();
		
			
	}
	if((state.equals(RESOLVED_NAME) || state.equals(CLOSED_NAME)) && !code.equals(Command.RC_COMPLETED) && !code.equals(Command.RC_NOT_COMPLETED) && !code.equals(Command.RC_SOLVED)
			&& !code.equals(Command.RC_WORKAROUND) && !code.equals(Command.RC_NOT_SOLVED) && !code.equals(Command.RC_CALLER_CLOSED)) {
		
			throw new IllegalArgumentException();
		
	}
	if(ticketType.equals(TT_REQUEST) && (state.equals(RESOLVED_NAME) || state.equals(CLOSED_NAME)) && !code.equals(Command.RC_COMPLETED) && !code.equals(Command.RC_NOT_COMPLETED) && !code.equals(Command.RC_CALLER_CLOSED)) {
		
			
				throw new IllegalArgumentException();
			
		
	}
	if(ticketType.equals(TT_INCIDENT) && (state.equals(RESOLVED_NAME) || state.equals(CLOSED_NAME)) && !code.equals(Command.RC_SOLVED) && !code.equals(Command.RC_WORKAROUND) && !code.equals(Command.RC_NOT_SOLVED) 
			&& !code.equals(Command.RC_CALLER_CLOSED)) {
		
			
				throw new IllegalArgumentException();

			
		

	}
	if(state.equals(CANCELED_NAME) && !code.equals(Command.CC_DUPLICATE) && !code.equals(Command.CC_INAPPROPRIATE)) {
		
			throw new IllegalArgumentException();
		
	}
		
		this.ticketid = id;
		setState(state);
		setTicketType(ticketType);
		setSubject(subject);
		setCaller(caller);
		setCategory(category);
		setPriority(priority);
		setOwner(owner);
		if(state.equals(CANCELED_NAME)) {
			setCancellationCode(code);
		} else if(state.equals(FEEDBACK_NAME)) {
			setFeedbackCode(code);
		} else if(state.equals(RESOLVED_NAME) || state.equals(CLOSED_NAME)) {
			setResolutionCode(code);
		}
		if(notes == null || notes.size() < 1) {
			throw new IllegalArgumentException();
		}
		this.notes = notes;
		
		
		
		
		
		
	}
	
	/**
	 * constructor for ticket object that has limited information
	 * @param ticketType type of ticket
	 * @param subject subject of ticket
	 * @param caller caller of ticket
	 * @param category ticket category
	 * @param priority ticket priority level
	 * @param note ticket owner
	 * @throws IllegalArgumentException error
	 */
	public Ticket(TicketType ticketType, String subject, String caller, Category category, Priority priority, String note) {
		
		if(ticketType == null || priority == null || category == null) {
			throw new IllegalArgumentException();
		}
		if(subject == null || subject.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if(caller == null || caller.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if(note == null || note.isEmpty()) {
			throw new IllegalArgumentException();
		}
		ArrayList<String> notes2 = new ArrayList<String>();
		notes2.add(note);
		ticketid = counter;
		incrementCounter();
		this.state = newState;
		this.ticketType = ticketType;
		this.subject = subject;
		this.caller = caller;
		this.category = category;
		this.priority = priority;
		this.owner = "";
		this.notes = notes2;
		
	}
	
	/**
	 * this method increments the counter field
	 */
	public static void incrementCounter() {
		counter++;
	}
	
	/**
	 * this method sets the counter field
	 * @param num counter for tickets
	 * @throws IllegalArgumentException error
	 */
	public static void setCounter(int num) {
		if(num == 0) {
			throw new IllegalArgumentException("Ticket id must be a value greater than 0.");
		}
		counter = num;
	}
	
	/**
	 * getter for caller 
	 * @return caller String
	 */
	public String getCaller() {
		return caller;
	}
	
	/**
	 * getter for Cancellation code
	 * @return String of cancellation code
	 */
	public String getCancellationCode() {
		if(cancellationCode == CancellationCode.DUPLICATE) {
			return Command.CC_DUPLICATE;
		} else if (cancellationCode == CancellationCode.INAPPROPRIATE) {
			return Command.CC_INAPPROPRIATE;
		}
		return null;
	}
	
	/**
	 * getter for category 
	 * @return String of ticket category
	 */
	public String getCategory() {
		if(category == Category.DATABASE) {
			return C_DATABASE;
		} else if (category == Category.HARDWARE) {
			return C_HARDWARE;
		} else if (category == Category.SOFTWARE) {
			return C_SOFTWARE;
		} else if (category == Category.INQUIRY) {
			return C_INQUIRY;
		} else if (category == Category.NETWORK) {
			return C_NETWORK;
		} 
		
		return null;
	}
	
	/**
	 * getter for feedback code
	 * @return String of feedback
	 */
	public String getFeedbackCode() {
		if(feedBackCode == FeedbackCode.AWAITING_CALLER) {
			return Command.F_CALLER;
		} else if (feedBackCode == FeedbackCode.AWAITING_CHANGE) {
			return Command.F_CHANGE;
		} else if (feedBackCode == FeedbackCode.AWAITING_PROVIDER) {
			return Command.F_PROVIDER;
		}
		return null;
	}
	
	/**
	 * getter for notes of ticket 
	 * @return String of notes
	 */
	public String getNotes() {
		String returnNote = "";
		for(int i = 0; i < notes.size(); i++) {
			
				returnNote += "-" + notes.get(i) + "\n";
			
		}
		return returnNote;
	}
	
	/**
	 * getter for owner
	 * @return String of ticket owner
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * getter for Priority
	 * @return String of ticket priority
	 */
	public String getPriority() {
		if(priority == Priority.LOW) {
			return P_LOW;
		} else if (priority == Priority.MEDIUM) {
			return P_MEDIUM;
		} else if (priority == Priority.HIGH) {
			return P_HIGH;
		} else if (priority == Priority.URGENT) {
			return P_URGENT;
		}
		return null;
	}
	
	/**
	 * getter for resolution code
	 * @return String of ticket resolution code
	 */
	public String getResolutionCode() {
		if(resolutionCode == null) {
			return null;
		}
		if(resolutionCode.equals(ResolutionCode.CALLER_CLOSED)) {
			return Command.RC_CALLER_CLOSED;
		} else if(resolutionCode.equals(ResolutionCode.COMPLETED) ) {
			return Command.RC_COMPLETED;
		} else if(resolutionCode.equals(ResolutionCode.NOT_COMPLETED)) {
			return Command.RC_NOT_COMPLETED;
		} else if(resolutionCode.equals(ResolutionCode.NOT_SOLVED)) {
			return Command.RC_NOT_SOLVED;
		} else if(resolutionCode.equals(ResolutionCode.SOLVED)) {
			return Command.RC_SOLVED;
		} else if(resolutionCode.equals(ResolutionCode.WORKAROUND)) {
			return Command.RC_WORKAROUND;
		} 
		return null;
	}
	
	/**
	 * getter for state
	 * @return String of ticket state
	 */
	public String getState() {
		if(state == closedState) {
			return CLOSED_NAME;
		} else if(state == feedbackState) {
			return FEEDBACK_NAME;
		} else if(state == resolvedState) {
			return RESOLVED_NAME;
		} else if(state == newState) {
			return NEW_NAME;
		} else if(state == workingState) {
			return WORKING_NAME;
		} else if(state == canceledState) {
			return CANCELED_NAME;
		} 
		return null;
	}
	
	/**
	 * getter for subject 
	 * @return String of ticket subject
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * getter for ticket id
	 * @return integer of ticket id
	 */
	public int getTicketId() {
		return ticketid;
	}
	
	/**
	 * getter for ticket type
	 * @return String of ticket type
	 */
	public TicketType getTicketType() {
		return ticketType;
	}
	
	/**
	 * getter for ticket type in string
	 * @return String of ticket type
	 */
	public String getTicketTypeString() {
		if(ticketType == TicketType.INCIDENT) {
			return TT_INCIDENT;
		} else if(ticketType == TicketType.REQUEST) {
			return TT_REQUEST;
		}
		return null;
	}
	
	/**
	 * setter method for caller
	 * @param caller ticket caller
	 * @throws IllegalArgumentException error
	 */
	private void setCaller(String caller) {
		if(caller == null || caller.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.caller = caller;
	}
	
	/**
	 * setter method for cancellation code
	 * @param code ticket code
	 * @throws IllegalArgumentException error
	 */
	private void setCancellationCode(String code) {
		
		
		if(Command.CC_DUPLICATE.equals(code)) {
			this.cancellationCode = CancellationCode.DUPLICATE;
		} else if(Command.CC_INAPPROPRIATE.equals(code)){
			this.cancellationCode = CancellationCode.INAPPROPRIATE;
		} else if(code == null) {
			cancellationCode = null;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * setter method for category
	 * @param category ticket category
	 * @throws IllegalArgumentException error
	 */
	private void setCategory(String category) {
		
		if(category.equals(C_SOFTWARE)) {
			this.category = Category.SOFTWARE;
		} else if(category.equals(C_HARDWARE)) {
			this.category = Category.HARDWARE;
		} else if(category.equals(C_INQUIRY)) {
			this.category = Category.INQUIRY;
		} else if(category.equals(C_NETWORK)) {
			this.category = Category.NETWORK;
		} else if(category.equals(C_DATABASE)) {
			this.category = Category.DATABASE;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * setter method for owner
	 * @param owner ticket owner
	 * @throws IllegalArgumentException error
	 */
	private void setOwner(String owner) {
		if((owner == null || owner.length() < 1) && state != newState && state != canceledState) {
			
				throw new IllegalArgumentException();
			
		}
		this.owner = owner;
	}

	/**
	 * setter method for feedback code
	 * @param code ticket code
	 * @throws IllegalArgumentException error
	 */
	private void setFeedbackCode(String code) {
		
		if(Command.F_CALLER.equals(code)) {
			this.feedBackCode = FeedbackCode.AWAITING_CALLER;
		} else if(Command.F_CHANGE.equals(code)) {
			this.feedBackCode = FeedbackCode.AWAITING_CHANGE;
		} else if(Command.F_PROVIDER.equals(code)) {
			this.feedBackCode = FeedbackCode.AWAITING_PROVIDER;
		} else if(code == null) {
			feedBackCode = null;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * setter method for priority
	 * @param priority ticket priority
	 * @throws IllegalArgumentException error
	 */
	private void setPriority(String priority) {
		
		if(priority.equals(P_HIGH)) {
			this.priority = Priority.HIGH;
		} else if(priority.equals(P_URGENT)) {
			this.priority = Priority.URGENT;
		} else if(priority.equals(P_LOW)) {
			this.priority = Priority.LOW;
		} else if(priority.equals(P_MEDIUM)) {
			this.priority = Priority.MEDIUM;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * setter method for resolution code
	 * @param code ticket code
	 * @throws IllegalArgumentException error
	 */
	private void setResolutionCode(String code) {
		
		if(Command.RC_CALLER_CLOSED.equals(code)) {
			this.resolutionCode = ResolutionCode.CALLER_CLOSED;
		} else if(Command.RC_COMPLETED.equals(code)) {
			this.resolutionCode = ResolutionCode.COMPLETED;
		} else if(Command.RC_NOT_COMPLETED.equals(code)) {
			this.resolutionCode = ResolutionCode.NOT_COMPLETED;
		} else if(Command.RC_NOT_SOLVED.equals(code)) {
			this.resolutionCode = ResolutionCode.NOT_SOLVED;
		} else if(Command.RC_SOLVED.equals(code)) {
			this.resolutionCode = ResolutionCode.SOLVED;
		} else if(Command.RC_WORKAROUND.equals(code)) {
			this.resolutionCode = ResolutionCode.WORKAROUND;
		} else if(code == null){
			this.resolutionCode = null;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * setter method for state
	 * @param state ticket state 
	 * @throws IllegalArgumentException error
	 */
	private void setState(String state) {
		
		
		if(state.equals(NEW_NAME)) {
			this.state = newState;
		} else if(state.equals(CANCELED_NAME)) {
			this.state = canceledState;
		} else if(state.equals(WORKING_NAME)) {
			this.state = workingState;
		} else if(state.equals(RESOLVED_NAME)) {
			this.state = resolvedState;
		} else if(state.equals(CLOSED_NAME)) {
			this.state = closedState;
		} else if(state.equals(FEEDBACK_NAME)) {
			this.state = feedbackState;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * setter method for subject
	 * @param subject ticket subject
	 */
	private void setSubject(String subject) {
		if(subject == null || subject.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.subject = subject;
	}
	
	/**
	 * setter method for type
	 * @param ticketType ticket type
	 * @throws IllegalArgumentException error
	 */
	private void setTicketType(String ticketType) {
		
		if(ticketType.equals(TT_REQUEST)) {
			this.ticketType = TicketType.REQUEST;
		} else if (ticketType.equals(TT_INCIDENT)) {
			this.ticketType = TicketType.INCIDENT;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * To string method for a ticket object
	 * @return String of ticket 
	 */
	public String toString() {
		String id = String.valueOf(getTicketId());
		String blank = "";
		if(state == resolvedState || state == closedState) {
			 blank += "*" + id + "#" + getState() + "#" + getTicketTypeString() + "#" + getSubject() + "#" + getCaller() + "#"
					+ getCategory() + "#" + getPriority() + "#" + getOwner() + "#" + getResolutionCode() + "\n" + getNotes();
		} else if(state == canceledState) {
			 blank += "*" + id + "#" + getState() + "#" + getTicketTypeString() + "#" + getSubject() + "#" + getCaller() + "#"
					+ getCategory() + "#" + getPriority() + "#" + getOwner() + "#" + getCancellationCode() + "\n" + getNotes();
		} else if(state == feedbackState) {
			 blank += "*" + id + "#" + getState() + "#" + getTicketTypeString() + "#" + getSubject() + "#" + getCaller() + "#"
					+ getCategory() + "#" + getPriority() + "#" + getOwner() + "#" + getFeedbackCode() + "\n" + getNotes();
		} else {
			blank += "*" + id + "#" + getState() + "#" + getTicketTypeString() + "#" + getSubject() + "#" + getCaller() + "#"
					+ getCategory() + "#" + getPriority() + "#";
			if(getOwner() == null) {
				blank += "#";
			} else {
				blank += getOwner() + "#";
			}
			blank += "\n" + getNotes();
		}
		
		return blank;
	}
	
	/**
	 * update method to update the FSM to next state
	 * @param c Command
	 */
	public void update(Command c) {
		
		state.updateState(c);

		if(c.getNote() != null) {
			notes.add(c.getNote());
		}
		
	}
	
	/**
	 * inner class to represent the state of a ticket in the feedback state
	 * @author Max Farthing
	 *
	 */
	public class FeedbackState implements TicketState {
		
		/**
		 * getter for State name of current state
		 * @return String of state name
		 */
		public String getStateName() {
			return FEEDBACK_NAME;
		}
		
		/**
		 * method to update the state name
		 * @param c Command
		 * @throws UnsupportedOperationException for state error 
		 */
		public void updateState(Command c) {
			
			resolutionCode = c.getResolutionCode();
			
			if(c.getCommand() == CommandValue.RESOLVE && ticketType == TicketType.INCIDENT && (resolutionCode == ResolutionCode.COMPLETED
					|| resolutionCode == ResolutionCode.NOT_COMPLETED)) {
				resolutionCode = null;
				throw new UnsupportedOperationException();
			}
			if(c.getCommand() == CommandValue.RESOLVE && ticketType == TicketType.REQUEST && (resolutionCode == ResolutionCode.SOLVED
					|| resolutionCode == ResolutionCode.NOT_SOLVED || resolutionCode == ResolutionCode.WORKAROUND)) {
				resolutionCode = null;
				throw new UnsupportedOperationException();
			}
			
			if(c.getCommand() == CommandValue.REOPEN) {
				state = workingState;
				feedBackCode = null;
			} else if(c.getCommand() == CommandValue.RESOLVE) {
				state = resolvedState;
				resolutionCode = c.getResolutionCode();
				feedBackCode = null;
			} else if(c.getCommand() == CommandValue.CANCEL) {
				state = canceledState;
				cancellationCode = c.getCancellationCode();
				feedBackCode = null;
			} else {
				throw new UnsupportedOperationException();
			}
			
		}
	}
	
	/**
	 * inner class to represent the state of a ticket in the Closed state
	 * @author Max Farthing
	 *
	 */
	public class ClosedState implements TicketState {
		
		/**
		 * getter for State name of current state
		 * @return String of state name
		 */
		public String getStateName() {
			return CLOSED_NAME;
		}
		
		/**
		 * method to update the state name
		 * @param c Command
		 * @throws UnsupportedOperationException for state error
		 */
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.REOPEN) {
				state = workingState;
				resolutionCode = null;
			} else {
				throw new UnsupportedOperationException();
			}

		}
	}

	/**
	 * inner class to represent the state of a ticket in the Resolved state
	 * @author Max Farthing
	 *
	 */
	public class ResolvedState implements TicketState {
		
		/**
		 * getter for State name of current state
		 * @return String of state name
		 */
		public String getStateName() {
			return RESOLVED_NAME;
		}
		
		/**
		 * method to update the state name
		 * @param c Command
		 * @throws UnsupportedOperationException for state error
		 */
		public void updateState(Command c) {
			if(c.getCommand() == CommandValue.FEEDBACK) {
				state = feedbackState;
				feedBackCode = c.getFeedbackCode();
				resolutionCode = null;
			} else if(c.getCommand() == CommandValue.REOPEN) {
				state = workingState;
				resolutionCode = c.getResolutionCode();
			} else if(c.getCommand() == CommandValue.CONFIRM) {
				state = closedState;
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

	/**
	 * inner class to represent the state of a ticket in the New state
	 * @author Max Farthing
	 *
	 */
	public class NewState implements TicketState {
		
		/**
		 * getter for State name of current state
		 * @return String of state name
		 */
		public String getStateName() {
			return NEW_NAME;
		}
		
		/**
		 * method to update the state name
		 * @param c Command
		 * @throws UnsupportedOperationException for state error
		 */
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.PROCESS ) {
				state = workingState;
				owner = c.getOwnerId();
			} else if(c.getCommand() == CommandValue.CANCEL) {
				state = canceledState;
				cancellationCode = c.getCancellationCode();
			} else {
				throw new UnsupportedOperationException();
			}
			
		}
	}	

	/**
	 * inner class to represent the state of a ticket in the Working state
	 * @author Max Farthing
	 *
	 */
	public class WorkingState implements TicketState {
		
		/**
		 * getter for State name of current state
		 * @return String of state name
		 */
		public String getStateName() {
			return WORKING_NAME;
		}
		
		/**
		 * method to update the state name
		 * @param c Command
		 * @throws UnsupportedOperationException for state error
		 */
		public void updateState(Command c) {
			
			resolutionCode = c.getResolutionCode();
			
			if(c.getCommand() == CommandValue.RESOLVE && ticketType == TicketType.INCIDENT && (resolutionCode == ResolutionCode.COMPLETED
					|| resolutionCode == ResolutionCode.NOT_COMPLETED)) {
				resolutionCode = null;
				throw new UnsupportedOperationException();
			}
			if(c.getCommand() == CommandValue.RESOLVE && ticketType == TicketType.REQUEST && (resolutionCode == ResolutionCode.SOLVED
					|| resolutionCode == ResolutionCode.NOT_SOLVED || resolutionCode == ResolutionCode.WORKAROUND)) {
				resolutionCode = null;
				throw new UnsupportedOperationException();
			}
			
			if(c.getCommand() == CommandValue.FEEDBACK) {
				state = feedbackState;
				feedBackCode = c.getFeedbackCode();
			} else if(c.getCommand() == CommandValue.RESOLVE) {
				state = resolvedState;
				resolutionCode = c.getResolutionCode();
			} else if(c.getCommand() == CommandValue.CANCEL) {
				state = canceledState;
				cancellationCode = c.getCancellationCode();
			} else {
				throw new UnsupportedOperationException();
			}
		

		}
	}
	
	/**
	 * inner class to represent the state of a ticket in the Canceled state
	 * @author Max Farthing
	 *
	 */
	public class CanceledState implements TicketState {
		
		/**
		 * getter for State name of current state
		 * @return String of state name
		 */
		public String getStateName() {
			return CANCELED_NAME;
		}
		
		/**
		 * method to update the state name
		 * @param c Command
		 * @throws UnsupportedOperationException for state error
		 */
		public void updateState(Command c) {
			//can't return from cancelled state
			if(c.getCommand() == CommandValue.FEEDBACK || c.getCommand() == CommandValue.RESOLVE || c.getCommand() == CommandValue.PROCESS
					|| c.getCommand() == CommandValue.CANCEL || c.getCommand() == CommandValue.REOPEN || c.getCommand() == CommandValue.CONFIRM) {
				throw new UnsupportedOperationException();
			}
		}
	}
	
}
