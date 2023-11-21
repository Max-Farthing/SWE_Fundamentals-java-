package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Abstract class Activity acts a superclass for multiple other classes.
 * @author Max Farthing
 *
 */
public abstract class Activity implements Conflict {

	/**
	 * This method checks current Activity instance with the parameter
	 * and checks if there is overlap in time and day.
	 * @param possibleConflictingActivity instance of Activity object
	 * @throws ConflictException if there is overlap in time and day
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		Activity a1 = possibleConflictingActivity;
		String me1 = this.getMeetingDays();
		String me2 = possibleConflictingActivity.getMeetingDays();
		for(int i = 0; i < me1.length(); i++) {
			for(int j = 0; j < me2.length(); j++) {
				if("A".equals(me1) || "A".equals(me2)) {
					break;
				} else if(me1.charAt(i) == me2.charAt(j)) {
					if(a1.getStartTime() >= this.getStartTime() && a1.getStartTime() <= this.getEndTime()) {
						throw new ConflictException();
					}
					if(a1.getEndTime() >= this.getStartTime() && a1.getEndTime() <= this.getEndTime()){
						throw new ConflictException();
					}
					if(this.getStartTime() >= a1.getStartTime() && this.getStartTime() <= a1.getEndTime()) {
						throw new ConflictException();
					}
					if(this.getEndTime() >= a1.getStartTime() && this.getEndTime() <= a1.getEndTime()){
						throw new ConflictException();
					}
				}
			}
		}
	}

	/** Constant for upper hour **/
	private static final int UPPER_HOUR = 24;
	/** Constant for upper minute **/
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructor of activity object
	 * @param title title of activity
	 * @param meetingDays meeting days of activity
	 * @param startTime activity start time
	 * @param endTime activity end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/** used to populate the rows of the course catalog and student schedule 
	 * @return array of course catalog and schedule
	 */
	public abstract String[] getShortDisplayArray();
	
	/** used to display final schedule 
	 * @return final schedule array
	 */
	public abstract String[] getLongDisplayArray();
	
	/** boolean to check if activity is a duplicate 
	 * @param activity object
	 * @return boolean whether is duplicate or not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Returns the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is invalid
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Course's Meeting Days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's Start Time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's End Time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * this method sets meeting days, the start times and the end times of classes
	 * 
	 * @param meetingDays the days classes meet
	 * @param startTime   the time classes start
	 * @param endTime     the time classes end
	 * @throws IllegalArgumentException for any invalid meeting days or times
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		} else {
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
	
			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					mCount++;
				} else if (meetingDays.charAt(i) == 'T') {
					tCount++;
				} else if (meetingDays.charAt(i) == 'W') {
					wCount++;
				} else if (meetingDays.charAt(i) == 'H') {
					hCount++;
				} else if (meetingDays.charAt(i) == 'F') {
					fCount++;
				} else if (meetingDays.charAt(i) == 'U') {
					//placeholder for Sunday
					continue;
				} else if (meetingDays.charAt(i) == 'S') {
					continue;
					//placeholder for Saturday
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
				
			}
	
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			int startHour = 0;
			int startMin = 0;
			int endHour = 0;
			int endMin = 0;
	
			startHour = startTime / 100;
			startMin = startTime % 100;
			endHour = endTime / 100;
			endMin = endTime % 100;
	
			if (startHour < 0 || startHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			if (startMin < 0 || startMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			if (endHour < 0 || endHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			if (endMin < 0 || endMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (startHour > endHour) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (startHour == endHour && startMin > endMin) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	/**
	 * toString method for the meeting
	 * 
	 * @return string containing the meeting time
	 */
	public String getMeetingString() {
		String blank = "";
	
		int startHour = 0;
		int startMin = 0;
		int endHour = 0;
		int endMin = 0;
	
		startHour = startTime / 100;
		startMin = startTime % 100;
		endHour = endTime / 100;
		endMin = endTime % 100;
	
		blank += meetingDays;
		if ("A".equals(meetingDays)) {
			return "Arranged";
		}
		blank += " ";
		if (startHour > 12) {
			startHour = startHour - 12;
			if (startMin < 10) {
				blank += startHour + ":0" + startMin + "PM";
			} else {
				blank += startHour + ":" + startMin + "PM";
			}
		} else if (startHour == 12) {
			if (startMin < 10) {
				blank += startHour + ":0" + startMin + "PM";
			} else {
				blank += startHour + ":" + startMin + "PM";
			}
		} else {
			if (startMin < 10) {
				blank += startHour + ":0" + startMin + "AM";
			} else {
				blank += startHour + ":" + startMin + "AM";
			}
		}
	
		if (endHour > 12) {
			endHour = endHour - 12;
			if (endMin < 10) {
				blank += "-" + endHour + ":0" + endMin + "PM";
			} else {
				blank += "-" + endHour + ":" + endMin + "PM";
			}
		} else if (endHour == 12) {
			if (endMin < 10) {
				blank += "-" + endHour + ":0" + endMin + "PM";
			} else {
				blank += "-" + endHour + ":" + endMin + "PM";
			}
		} else {
			if (endMin < 10) {
				blank += "-" + endHour + ":0" + endMin + "AM";
			} else {
				blank += "-" + endHour + ":" + endMin + "AM";
			}
		}
	
		return blank;
	}

	/** overrides hash code to check hash code equality for 
	 * activity objects 
	 * @return integer of hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/** overrides equals method to check equality of activities 
	 * @return boolean if objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}