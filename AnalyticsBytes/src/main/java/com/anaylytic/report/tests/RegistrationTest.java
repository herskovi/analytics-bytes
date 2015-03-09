package main.java.com.anaylytic.report.tests;


import java.util.logging.Logger;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.com.analytic.reports.servlets.SmsForGoogleAnalyticLoginServlet;
import org.junit.Before;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;




/**
 * Unit test for Registration mode.
 */

public class RegistrationTest extends TestCase
{
	private static final Logger log = Logger.getLogger(RegistrationTest.class.getName());
		
	@Before
	public static void before()
	{		
	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RegistrationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return 
     * @return the suite of tests being tested
     */
    public static  TestSuite suite()
    {
        
    	return  new TestSuite( RegistrationTest.class );
        
    }

    /**
     */
    @org.junit.Test
    public void testPhoneNumberWithOriginalformat()
    {
    	String phoneNumber = "0524265342";
    	String countryCode = "IL";
        String phoneNumberInternational = new SmsForGoogleAnalyticLoginServlet().changePhoneNumberFormat(countryCode, phoneNumber);
        assertEquals(phoneNumberInternational, "972524265342");
     }
    
    /**
     */
    @org.junit.Test
    public void testPhoneNumberWithInternatinalformat()
    {
    	String phoneNumber = "972524265342";
    	String countryCode = "IL";
        String phoneNumberInternational = new SmsForGoogleAnalyticLoginServlet().changePhoneNumberFormat(countryCode, phoneNumber);
        assertEquals(phoneNumberInternational, "972524265342");
     }
    	
    /**
     */
    @org.junit.Test
    public void testPhoneNumberWithInternatinalformatAndPlusSign()
    {
    	String phoneNumber = "+972524265342";
    	String countryCode = "IL";
        String phoneNumberInternational = new SmsForGoogleAnalyticLoginServlet().changePhoneNumberFormat(countryCode, phoneNumber);
        assertEquals(phoneNumberInternational, "972524265342");
     }
    
 
    /**
     */
    @org.junit.Test
    public void testPhoneNumberWithInternatinalformatAndPlusSignAndZero()
    {
    	String phoneNumber = "+9720524265342";
    	String countryCode = "IL";
        String phoneNumberInternational = new SmsForGoogleAnalyticLoginServlet().changePhoneNumberFormat(countryCode, phoneNumber);
        assertEquals(phoneNumberInternational, "972524265342");
     }
 
	
    
	
}
