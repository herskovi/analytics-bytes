/**
 * 
 */
package main.java.com.anaylytic.report.tests;

import java.util.logging.Logger;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import main.java.com.analytic.reports.jdo.dao.CustomerDAO;

import org.junit.Before;

/**
 * @author admin
 * Nov 29, 2014
 */
public class CleanUpTest extends TestCase
{
	private static final Logger log = Logger.getLogger(CleanUpTest.class.getName());
	
	@Before
	public static void before()
	{		
		
	}
    /**
     * @return 
     * @return the suite of tests being tested
     */
    public static  TestSuite suite()
    {
        
    	return  new TestSuite( CleanUpTest.class );
        
    }
    
    /**
     */
    @org.junit.Test
    public void cleanupAllTables()
    {
    	cleanupCustomerDB();
    	
    }
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Nov 29, 2014
	 *@Description: Clean Up all Customer DB
	 */
    @org.junit.Test
	private void cleanupCustomerDB() 
	{
		CustomerDAO.cleanUpCustomerTable();
	
		
	}
    


}
