package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Max Farthing
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		ArrayList<Course> courses = new ArrayList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the ArrayList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the ArrayList with all the courses we read!
		return courses;
	}

	/**
     * This method processes each line from the Scanner and uses a comma delimiter
     * to create new Course objects
     * @param line from text file
     * @return each line as a Course
     * @throws IllegalArgumentException if file is invalid
     */
    private static Course readCourse(String line) {
		Scanner console = new Scanner(line);
		console.useDelimiter(",");
		
		String name = null;
		String title = null;
		String section = null;
		int credits = 0;
		String instructorId = null;
		String meetingDays = null;
		int startTime = 0;
		int endTime = 0;
		try {
			while(console.hasNext()) {
				name = console.next();
				title = console.next();
				section = console.next();
				credits = console.nextInt();
				instructorId = console.next();
				meetingDays = console.next();
				if("A".equals(meetingDays) && !console.hasNext()) {
					Course arranged = new Course(name, title, section, credits, instructorId, meetingDays, 0, 0);
					console.close();
					return arranged;
				} else if ("A".equals(meetingDays) && console.hasNext()){
					console.close();
					throw new IllegalArgumentException();
				}
				startTime = console.nextInt();
				endTime = console.nextInt();
				if(console.hasNext()) {
					console.close();
					throw new IllegalArgumentException();
				}
				console.close();
				Course valid = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
				return valid;				
						
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid");
		}
	
		console.close();
		return null;
	}

}
