/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This class creates the event object which is a
 * subclass of the activity class
 * @author Max Farthing
 *
 */
public class Event extends Activity {
	
	/** eventDetails are the details of an event **/
	private String eventDetails;

	/**
	 * Overriding short display array to 
	 * display some event details
	 * @return String array of short display
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] arr = new String[4];
		arr[0] = "";
		arr[1] = "";
		arr[2] = getTitle();
		arr[3] = getMeetingString();
		return arr;

	}

	/**
	 * getter method for event details
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Constructor method for the event object
	 * @param title title of event
	 * @param meetingDays the days they meet
	 * @param startTime start time of event
	 * @param endTime end time of event
	 * @param eventDetails details of the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * setter method for event details
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if event details are invalid
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		
		this.eventDetails = eventDetails;
	}

	/**
	 * Overriding the toString method to output event details
	 * @return String of event string
	 */
	@Override
	public String toString() {
		String comma = ",";
		return getTitle() + comma + getMeetingDays() + comma + getStartTime() + comma + getEndTime()
			+ comma + getEventDetails();
	}

	/**
	 * overriding long display to get event details
	 * @return String array of long display
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] arr = new String[7];
		arr[0] = "";
		arr[1] = "";
		arr[2] = getTitle();
		arr[3] = "";
		arr[4] = "";
		arr[5] = getMeetingString();
		arr[6] = getEventDetails();
		return arr;
	}

	/**
	 * Overriding the event method to differentiate between Course
	 * and Event meeting days
	 * @throws IllegalArgumentException if meeting days or times are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if("A".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);

	}

	/**
	 * Method checks if 2 events are duplicates based on titles
	 * @param o object instance of event
	 * @return boolean whether event is duplicate or not
	 */
	public boolean isDuplicate(Activity o) {
		if(o instanceof Event) {
			Event that = (Event) o;
			return this.getTitle().equals(that.getTitle());
		} else {
			return false;
		}
	}
}
