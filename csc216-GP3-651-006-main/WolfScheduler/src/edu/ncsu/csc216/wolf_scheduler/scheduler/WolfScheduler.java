package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.function.BooleanSupplier;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;


/**
 * This program creates a "WolfScheduler" object which is as schedule containing courses
 * users can do various things with their schedules.
 * @author Max Farthing
 */
public class WolfScheduler {
	
	/** String of schedule title **/
	private String title;
	
	/** Array list of activities called schedule **/
	private ArrayList<Activity> schedule;
	
	/** Array list of Courses called catalog **/
	private ArrayList<Course> catalog;

	/**
	 * Constructor that uses filename for course records that is 
	 * stored in array list of courses. 
	 * 
	 * @param filename filename of the file import
	 * @throws IllegalArgumentException if file cannot be found
	 */
	public WolfScheduler(String filename) {
		schedule = new ArrayList<Activity>();
		
		title = "My Schedule";
		
		try {
			catalog = CourseRecordIO.readCourseRecords(filename);

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file");
		}
	}
	
	/**
	 * this method iterates through catalog of courses and returns the course
	 * if it matches the name and section or null if course does not exist
	 * 
	 * @param name name of class
	 * @param section section of class
	 * @return Course if exists
	 */
	public Course getCourseFromCatalog(String name, String section) {
		
		for(int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)){
				return catalog.get(i);
			}
		}
		
		
		return null;
	}
	
	/**
	 * allows courses to be added to the schedule if course exists and
	 * if not already enrolled in course with the same name
	 * 
	 * @param name the name of course
	 * @param section the section number of course
	 * @return boolean whether course can be added
	 * @throws IllegalArgumentException if already enrolled in course
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		for(int i = 0; i < schedule.size(); i++) {
			if(course.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
			try {
				schedule.get(i).checkConflict(course);
			} catch(ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		Course temp = getCourseFromCatalog(name, section);
		if(temp == null) {
			return false;
		}
		
		schedule.add(temp);
		return true;
	}
	
	/**
	 * allows courses to be removed from schedule
	 * @param idx id number
	 * @return boolean whether course and can be removed or not
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
			return true;
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**
	 * resets the schedule back to blank
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
		
	}

	/**
	 * getter method for course catalog, returns catalog as 2d array
	 * if catalog is empty return empty array otherwise return array 
	 * with all courses in catalog along with names section and title as
	 * columns
	 * 
	 * @return catalog as a 2d array
	 */
	public String[][] getCourseCatalog() {
		String [][] catalogArray = new String[catalog.size()][4];
        for (int i = 0; i < catalog.size(); i++) {
            Course c = catalog.get(i);
            catalogArray[i] = c.getShortDisplayArray();
        }
        return catalogArray;
	}

	/**
	 * getter method for course schedule, returns catalog as 2d array
	 * if schedule is empty return empty array otherwise return array 
	 * with all courses in schedule along with names section and title as
	 * columns
	 * 
	 * @return schedule as a 2d array
	 */
	public String[][] getScheduledActivities() {
		String [][] catalogArray = new String[schedule.size()][4];
        for (int i = 0; i < schedule.size(); i++) {
            Activity c = schedule.get(i);
            catalogArray[i] = c.getShortDisplayArray();
        }
        return catalogArray;
	}
	
	/**
	 * getter method for a FULL course schedule, returns catalog as 2d array
	 * if schedule is empty return empty array otherwise return array 
	 * with all courses in schedule along with names, section, title, credits,
	 * instructorId, and Meeting days as columns
	 * 
	 * @return full schedule as a 2d array
	 */
	public String[][] getFullScheduledActivities() {
		String [][] catalogArray = new String[schedule.size()][7];
        for (int i = 0; i < schedule.size(); i++) {
            Activity c = schedule.get(i);
            catalogArray[i] = c.getLongDisplayArray();
        }
        return catalogArray;
	}
	
	/**
	 * Setter method for setting the title name of schedule
	 * 
	 * @param string the title name of schedule as a string
	 * @throws IllegalArgumentException if title is null
	 */
	public void setScheduleTitle(String string) {
		
		
		if(string == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = string;
		
	}

	/**
	 * getter method for title name
	 * 
	 * @return title name as a string
	 */
	public String getScheduleTitle() {
		return title;
	}

	/**
	 * exports schedule to a file through the CourseRecordIO write course method
	 * 
	 * @param filename of the file where schedule will be saved
	 * @throws IllegalArgumentException if file cannot be saved
	 */
	public void exportSchedule(String filename) {
		
		try {
			ActivityRecordIO.writeActivityRecords(filename, schedule);
		} catch(IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

	/**
	 * adds event to schedule if it is not a duplicate
	 * @param eventTitle title of event
	 * @param eventMeetingDays days the event meets
	 * @param eventStartTime start time of event
	 * @param eventEndTime end time of the event 
	 * @param eventDetails the details of the event
	 * @throws IllegalArgumentException if event is already created
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		for(int i = 0; i < schedule.size(); i++) {
			if(event.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You have already created an event called " + event.getTitle());
			}
			try {
				schedule.get(i).checkConflict(event);
			} catch(ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		schedule.add(event);
	}

}
