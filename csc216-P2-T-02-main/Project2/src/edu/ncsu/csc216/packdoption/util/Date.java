package edu.ncsu.csc216.packdoption.util;

/**
 * This class creates Date objects to represent key dates throughout the program
 * including in birthdays, adoption dates etc
 * 
 * @author Max Farthing
 *
 */
public class Date implements Comparable<Date> {
	/** month for date **/
	private int month;
	/** day for date **/
	private int day;
	/** year for date **/
	private int year;

	/**
	 * constructor for Date with MMDDYYYY
	 * 
	 * @param month Date month
	 * @param day   Date day
	 * @param year  Date year
	 */
	public Date(int month, int day, int year) {
		if (!isValidDate(month, day, year)) {
			throw new IllegalArgumentException("Invalid date");
		}
		this.month = month;
		this.day = day;
		this.year = year;
	}

	/**
	 * constructor from String date
	 * 
	 * @param date Date
	 */
	public Date(String date) { // scanner or split
		if (!isValidDate(date)) {
			throw new IllegalArgumentException("Invalid date");
		}

		String[] parts = date.split("/");
		int month1 = Integer.parseInt(parts[0]);
		int day1 = Integer.parseInt(parts[1]);
		int year1 = Integer.parseInt(parts[2]);

		if (!isValidDate(month1, day1, year1)) {
			throw new IllegalArgumentException("Invalid date");
		}

		this.month = month1;
		this.day = day1;
		this.year = year1;
	}

	/**
	 * getter for month
	 * 
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * getter for day
	 * 
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * getter for year
	 * 
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * checks if date is valid for Dates created from String
	 * 
	 * @param date date
	 * @return true if date is valid
	 */
	public static boolean isValidDate(String date) { // split or scanner
		String[] parts = date.split("/");
		if (parts.length != 3) {
			return false;
		}

		int month = Integer.parseInt(parts[0]);
		int day = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		return isValidDate(month, day, year);
	}

	/**
	 * checks if date is valid for Dates with MMDDYYYY int
	 * 
	 * @param month month
	 * @param day   day
	 * @param year  year
	 * @return true if date is valid
	 */
	public static boolean isValidDate(int month, int day, int year) {
		  if (month < 1 || month > 12 || day < 1 || day > 31 || year < 2000 || year > 2050) {
		        return false;
		    }

		    if (month == 2) {
		        if (day > 29 || day == 29 && !isLeapYear(year)) {
		            return false;
		        }
		    } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
		        return false;
		    }

		    return true;

	}

	/**
	 * Compares date objects by their dates
	 * 
	 * @param o date
	 * @return date
	 */
	public int compareTo(Date o) {
		if (this.year != o.year) {
			return Integer.compare(this.year, o.year);
		} else if (this.month != o.month) {
			return Integer.compare(this.month, o.month);
		} else {
			return Integer.compare(this.day, o.day);
		}
	}

	/**
	 * Creates a string representation of Date
	 * 
	 * @return String of Date
	 */
	public String toString() {
		String s = "";

		s += getMonth() + "/" + getDay() + "/" + getYear();

		return s;
	}

	/**
	 * Provides hashcode for Date objects
	 * 
	 * @return hashcode of Date object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	/**
	 * Equals method for Date
	 * 
	 * @return boolean if dates are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		return day == other.day && month == other.month && year == other.year;
	}

	/**
	 * returns amount of days until date
	 * 
	 * @param other another Date
	 * @return int of time
	 */
	public int daysTo(Date other) {
		if (other.compareTo(this) < 0) {
			return -other.daysTo(this);
		}

		int days = 0;
		int yearDiff = other.getYear() - this.getYear();
		int monthDiff = other.getMonth() - this.getMonth();
		int dayDiff = other.getDay() - this.getDay();

		if (yearDiff > 0) {
			for (int i = 0; i < yearDiff; i++) {
				days += getDaysInYear(this.getYear() + i);
			}
		}

		if (monthDiff > 0) {
			for (int i = 0; i < monthDiff; i++) {
				days += getDaysInMonth(this.getMonth() + i, this.getYear());
			}
		}

		days += dayDiff;
		return days;
	}

	/**
	 * Private helper to get days in year
	 * 
	 * @param year year
	 * @return days in year
	 */
	private int getDaysInYear(int year) {
		if (isLeapYear(year)) {
			return 366;
		} else {
			return 365;
		}
	}

	/**
	 * Private helper to get days in month
	 * 
	 * @param month month
	 * @param year  year
	 * @return days in month
	 */
	private int getDaysInMonth(int month, int year) {
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else {
			return 31;
		}
	}

	/**
	 * Private helper to know if it is a leap year
	 * 
	 * @param year year
	 * @return treu if it is a leap year
	 */
	private static boolean isLeapYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}

	/**
	 * returns amount of years until date
	 * 
	 * @param other another Date
	 * @return int of time
	 */
	public int yearsTo(Date other) {
		// if other.year/month/day < this.year/month/day
		// return -other.yearsTo(this)
		// if other.year/month/day > this.year/month/day
		// other.year - this.year
		// other.month - this.month... if negative decrement year
		// if months same then other.day < this.day decrement year
		int sum = 0;
		if (other.getYear() == this.getYear()) {
			return sum;
		}
		if (other.getYear() < this.getYear()) { // other = 11/8/2016... this =12/8/2021
			return -other.yearsTo(this);
		}

		if (other.getYear() > this.getYear()) { // 3/2/2020 .... 4/2/2019
			sum = other.getYear() - this.getYear(); // 2020 - 2019 = 1
			if (other.getMonth() < this.getMonth()) {
				sum--; // 0
			} else if (other.getMonth() == this.getMonth() && other.getDay() < this.getDay()) {
				sum--; // 0
			}
		}

		return sum;
	}
}