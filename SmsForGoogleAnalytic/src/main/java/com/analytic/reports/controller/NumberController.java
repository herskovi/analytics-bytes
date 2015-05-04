/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.NumberResponse;
import main.java.com.analytic.reports.datatypes.NumberDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;

/**
 * @author admin
 * Jul 21, 2014
 */
public class NumberController extends BaseController
{
	
	private String userName = null;
	private Customer cust =null;
	private IResponse numberControllerResponse = newResponse();
	private static final Logger log = Logger.getLogger(NumberController.class.getName());





	public NumberController(String userName)
	{
		this.userName = userName;
	}


	@Override
	public void execute() throws Exception
	{


		// Get Customer From DB By User ID.
		 cust = CustomerDAO.getCustomerInformationByUserID(userName);
		 //Get Numbers from Databse and display it. 
		 List<String> telNoForSMSList = cust.getTelNoForSMS();
		 ArrayList <NumberDT>  numberList =  ((NumberResponse)numberControllerResponse).getNumberList();
		 for ( String telNoForSMSListStr : telNoForSMSList)
		 {
			 NumberDT numberDT = new NumberDT();
			 numberDT.setTelephoneNumber(telNoForSMSListStr);
			 numberDT.setProfileName(cust.getCustomerAnalyticList().get(0).getProfileName());
			 numberDT.setGoalName(cust.getCustomerAnalyticList().get(0).getGoalName());
			 numberList.add(numberDT);
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
		return new NumberResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.numberControllerResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		numberControllerResponse.setMessage(textMessage);	
	}



}
