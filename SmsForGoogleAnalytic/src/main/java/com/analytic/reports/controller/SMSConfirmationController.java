/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.BalanceResponse;
import main.java.com.analytic.reports.controller.response.NumberResponse;
import main.java.com.analytic.reports.controller.response.OrderConfirmationResponse;
import main.java.com.analytic.reports.controller.response.SMSConfirmationResponse;
import main.java.com.analytic.reports.datatypes.NumberDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;

/**
 * @author admin
 * Jul 21, 2014
 */
public class SMSConfirmationController extends BaseController
{
	
	private String userName = null;
	String smsReceivedToDestination=null;
	private Customer cust =null;
	private IResponse smsConfirmationResponse = newResponse();
	private static final Logger log = Logger.getLogger(SMSConfirmationController.class.getName());





	public SMSConfirmationController(String userName, String smsReceivedToDestination)
	{
		this.userName = userName;
		this.smsReceivedToDestination = smsReceivedToDestination;
	}


	@Override
	public void execute() throws Exception
	{

		// Get Customer From DB By User ID.
		 cust = CustomerDAO.getCustomerInformationByUserID(userName);
		 if (cust != null)
		 {
			 cust.setWasSMSRecieved(smsReceivedToDestination);
			 CustomerDAO.updateCustomerInDB(cust);
			 //Update the Customer Entity with 			 
		 }
		 
	}



	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new SMSConfirmationResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.smsConfirmationResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		smsConfirmationResponse.setMessage(textMessage);	
	}



}
