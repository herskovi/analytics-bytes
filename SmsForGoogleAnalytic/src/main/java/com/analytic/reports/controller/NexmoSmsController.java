/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.NexmoSmsResponse;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.FormatterUtils;
import main.java.com.analytic.reports.utils.consts.NexmoConsts;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


/**
 * @author admin
 * May 21, 2014
 */
public class NexmoSmsController extends SMSFlowController
{
	
	IResponse nexmoSmsResponse = newResponse();
	private static final Logger log = Logger.getLogger(NexmoSmsController.class.getName());


	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Constructor
	 */
	public NexmoSmsController(Customer cust, String textMessage) 
	{
		super(cust, textMessage);
	}

	/**
	 * @throws Exception 
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Execute flow
	 */
	@Override
	public void execute()  
	{
		sendSMS();	
		
	}


	


	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Populate Parameters before sending SMS
	 */
	public   List<NameValuePair> populateSMSParameters() {
		List <NameValuePair> nvps = new ArrayList <NameValuePair>(5);
		nvps.add(new BasicNameValuePair(NexmoConsts.SMS_VENDOR_PARAM_NAME_API_KEY, NexmoConsts.SMS_VENDOR_PARAM_VALUE_API_KEY));
		nvps.add(new BasicNameValuePair(NexmoConsts.SMS_VENDOR_PARAM_NAME_API_SECRET, NexmoConsts.SMS_VENDOR_PARAM_VALUE_API_SECRET));
		nvps.add(new BasicNameValuePair(NexmoConsts.SMS_VENDOR_PARAM_NAME_SENDER_ID, getFromNumber()));
		nvps.add(new BasicNameValuePair(NexmoConsts.SMS_VENDOR_TO_NUMBER, getToNumber()));
		nvps.add(new BasicNameValuePair(NexmoConsts.SMS_VENDOR_TEXT_MESSAGE, getTextMessage()));
		if (!(FormatterUtils.isPureAscii(getTextMessage()))) //If text is not Pure ASCII, send Type 
		{
			nvps.add(new BasicNameValuePair(NexmoConsts.SMS_VENDOR_PARAM_NAME_SENDER_TYPE, NexmoConsts.SMS_VENDOR_PARAM_VALUE_SENDER_TYPE));
		}
		log.severe("textMessage " + getTextMessage());
		return nvps;
	}

	@Override
	public String getFromNumber() {
		return NexmoConsts.SMS_VENDOR_PARAM_VALUE_SENDER_ID;
	}


	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Get SMS Gateway URL
	 */
	public String getSMSGatewayURL() 
	{
		return NexmoConsts.NEXMO_GATEWAY_URL;
	}

	
	@Override
	public void setResponse(String message) 
	{
		this.nexmoSmsResponse.setMessage(message);
	}

	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Initiate new Response Object
	 */

	@Override
	public IResponse newResponse()  
	{
		return new NexmoSmsResponse();	
	}

	
	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Get Response Object From Controller
	 */

	@Override
	public IResponse getResponse()  
	{
		return this.nexmoSmsResponse;	
	}



}
