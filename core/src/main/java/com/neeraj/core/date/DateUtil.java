package com.neeraj.core.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neeraj.core.constants.INumberConstant;

/**
 * Common date utility methods using JodaTime
 * 
 * @author Ujjwal K Gaurav
 * 
 */
public final class DateUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
	public static final String FORMAT_DDMMYYYY = "dd-MM-yyyy";
	public static final String FORMAT_DDMMYYYY_HH24MI = "dd-MM-yyyy HH:mm";
	public static final String FORMAT_DDMMYYYY_HH24MISS = "dd-MM-yyyy HH:mm:ss";
	public static final DateTimeFormatter FORMATTER_SHORT = DateTimeFormat.forPattern(FORMAT_DDMMYYYY);
	public static final DateTimeFormatter FORMATTER_LONG = DateTimeFormat.forPattern(FORMAT_DDMMYYYY_HH24MI);
	public static final DateTimeFormatter FORMATTER_LONGER = DateTimeFormat.forPattern(FORMAT_DDMMYYYY_HH24MISS);

	private DateUtil() {
		// Utility classes should not have a public or default constructor.
	}

	/**
	 * Formats input Date into String of format dd-MM-yyyy
	 * 
	 * @param date
	 * @return Date
	 */
	public static String formatDate(Date date) {
		return DateUtil.formatDate(date, DateUtil.FORMAT_DDMMYYYY_HH24MI);
	}

	/**
	 * Formats input Date into String of format dd-MM-yyyy
	 * 
	 * @param date
	 * @return Date
	 */
	public static String formatDate(Date date, String pattern) {
		String formattedDate = null;

		if (date != null) {
			DateTime tempDt = new DateTime(date.getTime());
			try {
				if (pattern == null || pattern.trim().isEmpty() || pattern.trim().equals("") || FORMAT_DDMMYYYY.equals(pattern.trim())) {
					formattedDate = tempDt.toString(DateUtil.FORMATTER_SHORT);
				} else if (FORMAT_DDMMYYYY_HH24MI.equals(pattern.trim())) {
					formattedDate = tempDt.toString(DateUtil.FORMATTER_LONG);
				} else {
					formattedDate = tempDt.toString(pattern);
				}
			} catch (Exception e) {
				LOGGER.warn("Could not format input date [" + date + "]");
				
				if (LOGGER.isTraceEnabled()) {
					LOGGER.warn(e.getLocalizedMessage(), e);
				}
			}
		}

		return formattedDate;
	}

	/**
	 * Checks its validity corresponding to the pattern of dd-mm-yyyy
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isValid(String date) {
		if (date.length() > INumberConstant.TEN || date.trim().length() < INumberConstant.TEN) {
			return false;
		}
		if (date.charAt(INumberConstant.TWO) != '-' || date.charAt(INumberConstant.FIVE) != '-') {
			return false;
		}
		if (Integer.parseInt(date.substring(INumberConstant.ZERO, INumberConstant.ONE).toString()) > INumberConstant.THIRTY_ONE
				|| Integer.parseInt(date.substring(INumberConstant.THREE, INumberConstant.FOUR).toString()) > INumberConstant.TWELVE) {
			return false;
		}
		return true;
	}

	/**
	 * Using this function since the parseDate() does not use a user defined format
	 * 
	 * @author Anant
	 * @param date
	 * @return
	 */
	@Deprecated
	public static Date convertToDate(String date) {
		Date fullDate = null;
		Date returnDate = null;

		if (date != null) {
			try {
				DateTime temp = FORMATTER_SHORT.parseDateTime(date);
				fullDate = temp.toDate();
			} catch (Exception e) {
				LOGGER.error("Could not parse input string [" + date + "] to Date." + "Error: " + e.getMessage(), e);
			}

			if (fullDate != null) {
				returnDate = new Date(fullDate.getTime());
			}
		}

		return returnDate;
	}

	/**
	 * Parses input String of format dd-MM-yyyy into Date
	 * 
	 * @param stringDate
	 * @return
	 */
	public static Date parseDate(String stringDate) {
		return DateUtil.parseDate(stringDate, DateUtil.FORMAT_DDMMYYYY_HH24MI);
	}

	/**
	 * Parses input String of format dd-MM-yyyy or format dd-MM-yyyy HH:mm into Date
	 * 
	 * @param stringDate
	 * @return
	 */
	public static Date parseDate(String stringDate, String pattern) {
		Date parsedDate = null;

		if (stringDate != null && !stringDate.trim().equals("")) {
			try {
				parsedDate = DateUtil.parseDateEx(stringDate, pattern);
			} catch (Exception e) {
				LOGGER.warn("Could not parse input date [" + stringDate + "] to pattern [" + pattern + "]");
				
				if (LOGGER.isTraceEnabled()) {
					LOGGER.warn(e.getLocalizedMessage(), e);
				}
			}
		}

		return parsedDate;
	}

	/**
	 * Parses input String of format dd-MM-yyyy or format dd-MM-yyyy HH:mm into Date
	 * 
	 * @param stringDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateEx(String stringDate, String pattern) throws ParseException {
		Date parsedDate = null;

		if (stringDate != null && !stringDate.trim().equals("")) {
			DateTime tempDt = null;

			if (pattern == null || pattern.trim().isEmpty() || pattern.trim().equals("") || FORMAT_DDMMYYYY.equals(pattern.trim())) {
				tempDt = FORMATTER_SHORT.parseDateTime(stringDate);
			} else if (FORMAT_DDMMYYYY_HH24MI.equals(pattern.trim())) {
				tempDt = FORMATTER_LONG.parseDateTime(stringDate);
			} else {
				tempDt = DateTime.parse(stringDate, DateTimeFormat.forPattern(pattern));
			}

			parsedDate = tempDt.toDate();
		}

		return parsedDate;
	}

	/**
	 * parses string into Timestamp
	 * 
	 * @author Abhishek Garg
	 * @param stringTimestamp
	 * @return
	 */
	public static Timestamp parseTimestamp(String stringTimestamp) {
		Timestamp parsedTimestamp = null;
		Date parsedDate = null;

		if (stringTimestamp != null && !stringTimestamp.trim().equals("")) {
			parsedDate = DateUtil.parseDate(stringTimestamp, FORMAT_DDMMYYYY_HH24MI);

			if (parsedDate != null) {
				parsedTimestamp = new Timestamp(parsedDate.getTime());
			}
		}

		return parsedTimestamp;
	}

	/**
	 * @author Abhishek Garg
	 * @param currentDate
	 * @param time1
	 * @param time2
	 *            This method returns new date by adding delay time to the scheduled time and taking reference from current date.
	 * @return date
	 */
	public static Timestamp addDate(Timestamp date1, Timestamp date2) {
		Long l1 = date1.getTime();
		Long l2 = date2.getTime();
		l1 = l1 + l2;
		date1.setTime(l1);

		return new Timestamp(date1.getTime());
	}

	/**
	 * @author Abhishek Garg
	 * @param date1
	 * @param date2
	 *            The method subtracts two given dates and returns new date.
	 * @return
	 */
	public static Timestamp subtractDate(Timestamp date1, Timestamp date2) {
		Long l1 = date1.getTime();
		Long l2 = date2.getTime();
		l1 = l1 - l2;
		date1.setTime(INumberConstant.ZERO);
		date1.setTime(l1);

		return new Timestamp(date1.getTime());
	}

	public static Long timeInLong(Short time) {
		int minutes, hours;
		minutes = time % INumberConstant.HUNDRED;
		hours = time / INumberConstant.HUNDRED;

		return hours * INumberConstant.HOUR_MINUTES + minutes * INumberConstant.MINUTE_SECONDS;
	}

	public static int getDayOfWeek(Date date) {
		int dayOfWeek = INumberConstant.ZERO;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		return dayOfWeek;
	}

	public static int getDaysDiff(Date date1, Date date2) {

		if (date1 == null) {
			date1 = new Date();
		}

		if (date2 == null) {
			date2 = new Date();
		}

		DateTime startDate = new DateTime(date1);
		DateTime endDate = new DateTime(date2);

		Days days = Days.daysBetween(startDate, endDate);

		return days.getDays();
	}

	public static Date getStartOfDay(Date date) {
		String dt = DateUtil.formatDate(date, DateUtil.FORMAT_DDMMYYYY);
		dt = dt + " 00:00";
		return DateUtil.parseDate(dt);
	}

	public static Date getEndOfDay(Date date) {
		String dt = DateUtil.formatDate(date, DateUtil.FORMAT_DDMMYYYY);
		dt = dt + " 23:59";
		return DateUtil.parseDate(dt);
	}

	public static Date getDate(Date today, int daysToAdd) {
		DateTime returnDate = new DateTime(today.getTime());

		returnDate = returnDate.plusDays(daysToAdd);

		return returnDate.toDate();
	}
}
