package main.java.com.analytic.reports.tests;


import java.util.UUID;
import java.util.logging.Logger;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.com.analytic.reports.servlets.SmsForGoogleAnalyticLoginServlet;
import main.java.com.analytic.reports.utils.EmailNotificationUtils;

import org.junit.Before;




/**
 * Unit test for Registration mode.
 */

public class EmailNotificationTest extends TestCase
{
	private static final Logger log = Logger.getLogger(EmailNotificationTest.class.getName());
		
	@Before
	public static void before()
	{		
	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EmailNotificationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return 
     * @return the suite of tests being tested
     */
    public static  TestSuite suite()
    {
        
    	return  new TestSuite( EmailNotificationTest.class );
        
    }

    /**
     */
    @org.junit.Test
    public void testSendEmailNotification()
    {
    	String userId = " goodmorninganalytic@yahoo.com";
    	String name = "Moshe Herskovits";
    	String	uniqueKey = UUID.randomUUID().toString();
		
	
    	int i=0;
    	try
    	{
    		EmailNotificationUtils.sendSigningUpEmailNotification(userId, name, uniqueKey);
    		i=1;
    	}catch(Exception ex)
    	{
    		i=2;
    	}
        assertEquals(i, 1);
     }
    

    	
	
	
}
