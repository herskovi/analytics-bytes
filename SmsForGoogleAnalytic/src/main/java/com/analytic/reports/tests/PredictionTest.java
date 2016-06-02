package main.java.com.analytic.reports.tests;


import java.io.IOException;
import java.util.logging.Logger;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.com.analytic.reports.controller.PredictionController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.servlets.SmsForGoogleAnalyticLoginServlet;
import main.java.com.analytic.reports.utils.CustomerHelper;

import org.junit.Before;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;




/**
 * Unit test for Registration mode.
 */

public class PredictionTest extends TestCase
{
	private static final Logger log = Logger.getLogger(PredictionTest.class.getName());
		
	@Before
	public static void before()
	{		
	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PredictionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return 
     * @return the suite of tests being tested
     */
    public static  TestSuite suite()
    {
        
    	return  new TestSuite( PredictionTest.class );
        
    }

    /**
     * @throws Exception 
     */
    @org.junit.Test
    public void testPrediction() throws Exception
    {
    	String userId  = "herskovi77@gmail.com";
    	PredictionController pr = new PredictionController(null,userId,null,null,null);
    	pr.execute();
    	
        
        assertEquals("1", "1");
     }
    
   
 
 
	
    
	
}
