/**
 * 
 */
package main.java.com.analytic.reports.controller;

import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.consts.NexmoConsts;


/**
 * @author admin
 * May 21, 2014
 */
public class USPreApprovedShortCodesNexmoSmsController extends NexmoSmsController
{
	

	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Constructor
	 */
	public USPreApprovedShortCodesNexmoSmsController(Customer cust, String textMessage) 
	{
		super(cust, textMessage);
	}



	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Get SMS Gateway URL
	 */
	@Override
	public String getSMSGatewayURL() 
	{
		return NexmoConsts.NEXMO_API_SHORT_CODES_GATEWAY_URL;
	}


}
