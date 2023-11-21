/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Course class creates the Course Object using various Course information
 * such as title, name, section etc. Used to put into schedule
 * 
 * @author Max Farthing
 *
 */
public class Course extends Activity {

	/** Constant for minimum name length **/
	private static final int MIN_NAME_LENGTH = 5;

	/** Constant for maximum name length **/
	private static final int MAX_NAME_LENGTH = 8;

	/** Constant for minimum letter count **/
	private static final int MIN_LETTER_COUNT = 1;

	/** Constant for maximum letter count **/
	private static final int MAX_LETTER_COUNT = 4;

	/** Constant for digit count **/
	private static final int DIGIT_COUNT = 3;

	/** Constant for section length **/
	private static final int SECTION_LENGTH = 3;

	/** Constant for maximum credits **/
	private static final int MAX_CREDITS = 5;

	/** Constant for minimum credits **/
	private static final int MIN_CREDITS = 1;

	/** Course's name. */
	private String name;

	/** Course's section. */
	private String section;

	/** Course's credit hours */
	private int credits;

	/** Course's instructor */
	private String instructorId;

	/**
	 * Creates a Course object with values for all fields
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity ID
	 * @param meetingDays  meeting days for Course as a series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorID,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity ID
	 * @param meetingDays  meeting days for Course as a series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if name does not meet requirements
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		int letters = 0;
		int digits = 0;
		boolean flag = false;
		for (int i = 0; i < name.length(); i++) {
			char point = name.charAt(i);
			if (!flag) {
				if (Character.isLetter(point)) {
					letters++;
				} else if (point == ' ') {
					flag = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (flag) {
				if (Character.isDigit(point)) {
					digits++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}
		if (letters < MIN_LETTER_COUNT || letters > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (digits != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section does not meet requirements
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Returns the Course's credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits dont meet requirements for course
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Instructor's ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Instructor's ID
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructor id doesnt meet requirements
	 *                                  for course
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
 
	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * displays certain Course objects in an array
	 * @return String array of course fields
	 */
	@Override
	public String[] getShortDisplayArray() {
		String arr[] = new String[4];
		arr[0] = getName();
		arr[1] = getSection();
		arr[2] = getTitle();
		arr[3] = getMeetingString();

		return arr;
	}
 
	/**
	 * displays the various course object values in array
	 * @return String array of Course fields
	 */
	@Override
	public String[] getLongDisplayArray() {
		String arr[] = new String[7];
		String creds = String.valueOf(getCredits());
		arr[0] = getName();
		arr[1] = getSection();
		arr[2] = getTitle();
		arr[3] = creds;
		arr[4] = getInstructorId();
		arr[5] = getMeetingString();
		arr[6] = "";


		return arr;
	}

	/**
	 * Overriding the event method to differentiate between Course
	 * and Event meeting days
	 * @throws IllegalArgumentException if meeting days or times are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays.contains("S") || meetingDays.contains("U")) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * Method checks if 2 courses are duplicates based on name
	 * @param o object instance of course
	 * @return boolean whether course is duplicate or not
	 */
	public boolean isDuplicate(Activity o) {
		if(o instanceof Course) {
			Course that = (Course) o;
			return this.name.equals(that.name);
		} else {
			return false;
		}
	}

}
