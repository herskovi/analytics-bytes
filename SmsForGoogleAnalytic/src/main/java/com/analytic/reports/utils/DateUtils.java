/**
 * 
 */
package main.java.com.analytic.reports.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * @author Moshe Herskovits
 * Jul 1, 2014
 */
public class DateUtils 
{
	
	private Calendar cal = null;
	/**
	 * @param systemDateCal
	 */
	public DateUtils(Calendar systemDateCal) 
	{
		this.setCal(systemDateCal);
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 1, 2014
	 *@Description: Get Yesterdy Date
	 */
	
	public static String getYesterdayDateString(String profileTimeZoneStr, String userTimeZone) 
	{
//		//SMS should be sent at 08:00 in userTimeZone
//		int targetHour = 8; 
//		
//		//What is the timezone for given profile 
//		int difference = differenceinHoursBetweenProfileAndServerTime(profileTimeZoneStr);
//		
//		//int diff = 
//		
//		
//		
//		// Get the Offset from GMT for profile.
//		
//		//What Is Server Time now?
//		int currentHourWithinServerTimeZone = getSystemCalendar().get(Calendar.HOUR);	
		String startDate =  "2014-01-01";
		return startDate;

	}

	public static int differenceinHoursBetweenProfileAndServerTime(
			String profileTimeZoneStr) {
		int gmtOffsetForProfile = getGMTOffset(profileTimeZoneStr);
		int gmtOffsetForServerTime = getGMTOffsetForServer();
		int difference = (gmtOffsetForProfile - gmtOffsetForServerTime)/(1000*60*60); //-5; //2
		return difference;
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 7, 2014
	 *@Description:
	 */
	private static int getGMTOffsetForServer() 
	{
		Calendar systemTime = getSystemCalendar();
		return systemTime.get(Calendar.ZONE_OFFSET); 
		//TODO Remove this comments.
		//int zoneOffset = systemTime.get(Calendar.ZONE_OFFSET) / (1000*60*60); //-3	
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 7, 2014
	 *@Description: Get System Calendar
	 */

	public static Calendar getSystemCalendar() {
		Calendar systemTime = Calendar.getInstance();
		return systemTime;
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 7, 2014
	 *@Description: Get GMT Offset for speciofic profile and calculate DST as well.
	 *				
	 */
	public static int getGMTOffset(String profileTimeZoneStr) 
	{
		TimeZone profileTimeZone = getTimeZoneForProfile(profileTimeZoneStr);
		Calendar currentProfileTime = new GregorianCalendar(profileTimeZone);
		return gmtOffsetForCurrentProfile(profileTimeZone, currentProfileTime);		
	}

	public static int gmtOffsetForCurrentProfile(TimeZone profileTimeZone,
			Calendar currentProfileTime) {
		int gmtOffset = profileTimeZone.getOffset(
			    currentProfileTime.get(Calendar.ERA), 
			    currentProfileTime.get(Calendar.YEAR), 
			    currentProfileTime.get(Calendar.MONTH), 
			    currentProfileTime.get(Calendar.DAY_OF_MONTH), 
			    currentProfileTime.get(Calendar.DAY_OF_WEEK), 
			    currentProfileTime.get(Calendar.MILLISECOND));
		return gmtOffset;
	}

	public static TimeZone getTimeZoneForProfile(String profileTimeZoneStr) {
		TimeZone profileTimeZone = TimeZone.getTimeZone(profileTimeZoneStr);
		return profileTimeZone;
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 7, 2014
	 *@Description: Get Profile Tiome Zone as input and return current  Hour. 
	 */

	public static int currentHourWithinProfileTimeZone(String profileTimeZoneStr) 
	{
		TimeZone profileTimeZone = getTimeZoneForProfile(profileTimeZoneStr);
		Calendar currentProfileTime = new GregorianCalendar(profileTimeZone);
		int currentHour = currentProfileTime.get(Calendar.HOUR_OF_DAY);
		return currentHour;
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 12, 2014
	 *@Description: Get Yesterday Date String
	 */
	public String getYesterdayDateString() 
	{
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		ymdFormat.setCalendar(cal);
		return ymdFormat.format(cal.getTime());
		
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 12, 2014
	 *@Description: Get Yesterday Date String
	 */
	public static Calendar getYesterdayDateCal(Calendar systemDateCal) 
	{
		systemDateCal.add(Calendar.DATE,-1);
		return systemDateCal;
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 12, 2014
	 *@Description: Get Yesterday Date String
	 */
	public static String getYesterdayDateString(Calendar systemDateCal) 
	{
		systemDateCal.add(Calendar.DATE,-1);
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		ymdFormat.setCalendar(systemDateCal);
		return ymdFormat.format(systemDateCal.getTime());
	}
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 12, 2014
	 *@Description: Get Current Date Time
	 */
	public static String getCurrentDateTime() {
		
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	     java.util.Date date = new java.util.Date();
	     return dateFormat.format(date);		
		
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 12, 2014
	 *@Description: Get Yesterday Date String
	 */
	public String getDateStringWithDayName() 
	{
		DateFormat df = new SimpleDateFormat("EEEE, d MMM yyyy ");
		df.setCalendar(cal);
		return df.format(cal.getTime());

//		Date time =null;
//		if (cal != null)
//		{
//			timeZone = cal.getTimeZone().getDisplayName();
//			 time = cal.getTime();
//		}
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 12, 2014
	 *@Description: Get Current Date Time
	 */
	public static Date getCurrentDateObject() 
	{	
	     return new java.util.Date();		
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jan 26, 2015
	 *@Description:
	 */
	public static boolean isCurrentDateGreaterMoreThanOneMonthFromGivenDate(Calendar currentDate, Calendar creationDate) 
	{
		currentDate.add(Calendar.MONTH, -1); //substract One Month from current Date
		if (currentDate.after(creationDate))
		{
			return true;
		}
		return false;
		
	}

	/**
	 * @return the cal
	 */
	public Calendar getCal() {
		return cal;
	}

	/**
	 * @param cal the cal to set
	 */
	public void setCal(Calendar cal) {
		this.cal = cal;
	}
	
	

}
