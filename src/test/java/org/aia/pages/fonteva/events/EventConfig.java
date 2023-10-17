package org.aia.pages.fonteva.events;

import java.util.HashMap;
import java.util.Map;

public class EventConfig {

	
//Event info Module value
	
	public static String RegistrationTimer ="55";
	public static boolean select24HoursTime =false;
	public static String startDateWithTime = "11/03/2023 10:21";
	public static String startTimePeriodType ="AM";
	
	public static String endDateWithTime   = "12/24/2032 11:22";
	public static String endTimePeriodType ="PM";
	
	public static String[] TodayDateArr;
	public static String[] startDateArr;
	public static String[] endDateArr;
	
	public static boolean startEndDateValidationValue = false;
	public static int startDateNoOfClicks;
	public static int endDateNoOfClicks;
	
	public static int getStartEndYearsDifference;
	public static int getStartEndMonthsDifference;
	
	public static int getCurrentStartYearsDifference;
	public static int getCurrentStartMonthsDifference;
	
	public static int noOfClicksForEndDateSelection;
	public static int noOfClicksForStartDateSelection;
	
	public static Map<String,Integer> monthsdays =new HashMap<>();
	static {
		monthsdays.put("01", 31);
		monthsdays.put("02", 28);
		monthsdays.put("03", 31);
		monthsdays.put("04", 30);
		monthsdays.put("05", 31);
		monthsdays.put("06", 30);
		monthsdays.put("07", 31);
		monthsdays.put("08", 31);
		monthsdays.put("09", 30);
		monthsdays.put("10",31);
		monthsdays.put("11",30);
		monthsdays.put("12",31);
	}
//Tickets Module value
	
	public static String ticketalesStartDate ="11/20/2023";
	
	
//Venues
	
	//public static String  venueNameInputField="Test123";
	
	public static String  venueNameInputField;
	
	//public static String  venueNameInputField="Test123";
	
	public static String  descriptionTextField="Create new Venue description";
	
	public static Map<String,String> address = new HashMap<>();
	static {
		address.put("street", "Main Road");
		address.put("city", "Hyderabad");
		address.put("state", "Telangana");
		address.put("country", "India");
		address.put("pincode", "530032");
	}
	
	
	public static String  venueImageURLInput="C:\\Users\\btalupuri\\AIA\\aia-fonteva-automation\\UploadFiles\\screenshoot.jpg";
	public static String getVenueRecordsCount;
	public static String getVenueRecordsCount2;
	
	
	
	
	
	

}
