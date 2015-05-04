package main.java.com.analytic.reports.tests;


import java.util.UUID;
import java.util.logging.Logger;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.com.analytic.reports.controller.BalanceController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.servlets.SmsForGoogleAnalyticLoginServlet;
import main.java.com.analytic.reports.utils.EmailNotificationUtils;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;




/**
 * Unit test for BalanceController mode.
 */
@RunWith(MockitoJUnitRunner.class)
public class BalanceControllerTest extends TestCase
{
	private static final Logger log = Logger.getLogger(BalanceControllerTest.class.getName());
	private BalanceController balanceController = null;
	@Mock private Customer cust = new Customer ("1000000000",null, "herskovi77@gmail.com", "Moshe", "Amdocs", "herskovi77@gmail.com", "il", "0524265342", "123123", null, "5.00");
		
	
    
	public BalanceControllerTest( )
    {
 
    	this.balanceController = new BalanceController(cust,"7");
    	
    }

    /**
     * @return 
     * @return the suite of tests being tested
     */
    public static  TestSuite suite()
    {
    	
    	TestSuite test = new TestSuite( BalanceControllerTest.class );
    	return test;  
        
    }

    /**
     */
    @org.junit.Test
    public void testUpdateBalance()
    {
    	balanceController.updateBalance();
    	assertEquals(balanceController.getBalance(), "12");
     }
    
    /**
     * 
     *@Author:      Moshe Herskovits
     *@Date:        Jan 22, 2015
     *@Description:
     */
    @Before
    public  void initMocks() 
    {
   	    MockitoAnnotations.initMocks(this);
   	}
	
}
