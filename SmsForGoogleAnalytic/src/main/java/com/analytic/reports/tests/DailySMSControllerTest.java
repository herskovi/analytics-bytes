package main.java.com.analytic.reports.tests;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.com.analytic.reports.controller.DailySmsController;
import main.java.com.analytic.reports.servlets.SmsForGoogleAnalyticLoginServlet;
import main.java.com.analytic.reports.utils.DateUtils;

import org.junit.Before;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;




/**
 * Unit test for Registration mode.
 */

public class DailySMSControllerTest extends TestCase
{
	private static final Logger log = Logger.getLogger(DailySMSControllerTest.class.getName());
		
	@Before
	public static void before()
	{		
	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DailySMSControllerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return 
     * @return the suite of tests being tested
     */
    public static  TestSuite suite()
    {
        
    	return  new TestSuite( DailySMSControllerTest.class );
        
    }

    
    /**
     */
    @org.junit.Test
    public void testStartDate()
    {
    	HttpServletRequest req = null;
    	DailySmsController dailySMSContorller = new DailySmsController(req);
    	
		//String systemDateCal = Calendar.getInstance(tz);
		
//    	String startDate = DateUtils.getYesterdayDateString(systemDateCal);
//		endDate = startDate;
//		String dateFormatToDisplay = DateUtils.getDateStringWithDayName(systemDateCal);
//		textMessage.append("On " + dateFormatToDisplay + ", ");

    	
    }
    /**
     */
    @org.junit.Test
    public void testOneDayBeforeForGiveTimeZone()
    {
    	HttpServletRequest req = null;
    	DailySmsController dailySMSContorller = new DailySmsController(req);
		Calendar systemDateCal =  Calendar.getInstance();

    	//Call to 4 methods:\
    	String userTimeZone = "America/New_York"; // Replace userTimeZone = getUserTimeZone(cust);	
    	systemDateCal = dailySMSContorller.setUserTimeZone(userTimeZone);
    	int currentUserTimeInUserTimeZone = dailySMSContorller.getCurentTimeInUserTimeZone(systemDateCal);
    	DateUtils dateUtils = new DateUtils(systemDateCal);
    	String startDate = dateUtils.getYesterdayDateString();
		String dateFormatToDisplay = dateUtils.getDateStringWithDayName();
		

    	
    	//String userTimeZone = "Asia/Jerusalem";
    
    	assertEquals(startDate, "2015-04-24");
     }
    
   
    /**
     */
    @org.junit.Test
    public void testDisplayDateInFormatOfDayNameMonthDayYear()
    {
    	Calendar systemDateCal =  Calendar.getInstance();
    	DateUtils dateUtils = new DateUtils(systemDateCal);
    	String str = dateUtils.getDateStringWithDayName();
    	assertEquals(str, "Saturday, Apr 25 2015 ");


    }

	
 
	
    
	
}
