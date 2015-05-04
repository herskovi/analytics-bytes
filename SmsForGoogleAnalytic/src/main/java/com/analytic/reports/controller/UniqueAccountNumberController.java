/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.UniqueAccountNumberResponse;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.UniqueID;

/**
 * @author admin
 * Jul 21, 2014
 */
public class UniqueAccountNumberController extends BaseController
{
	
	
	private static final Logger log = Logger.getLogger(UniqueAccountNumberController.class.getName());
	private UniqueAccountNumberResponse uniqueAccountResponse = (UniqueAccountNumberResponse) newResponse();






	@Override
	public void execute() throws Exception
	{
		// Get Customer From DB By User ID.
		 String maximumUniqueAccountNumber = UniqueID.get();
		 //Get Metrics from Customer Preference
		 uniqueAccountResponse.setUniqueAccountNumber(maximumUniqueAccountNumber);
	}



	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new UniqueAccountNumberResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.uniqueAccountResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		uniqueAccountResponse.setMessage(textMessage);	
	}



}
