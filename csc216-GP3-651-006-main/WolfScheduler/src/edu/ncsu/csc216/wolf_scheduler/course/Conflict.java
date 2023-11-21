package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This interface is used for finding and checking conflicts in
 * the User's schedule.
 * @author Max Farthing
 */
public interface Conflict {

	/**
	 * This method is used a conflict check
	 * @param possibleConflictingActivity activity object with possible conflict
	 * @throws ConflictException Custom checked exception we have created
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}
