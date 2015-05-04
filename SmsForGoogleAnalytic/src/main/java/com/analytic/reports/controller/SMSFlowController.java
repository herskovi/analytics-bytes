/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.interfaces.ISMSController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.ConvertUtils;
import main.java.com.analytic.reports.utils.consts.NexmoConsts;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;


/**
 * @author admin
 * May 16, 2014
 */
public abstract class SMSFlowController implements ISMSController
{
	private static final Logger log = Logger.getLogger(SMSFlowController.class.getName());
	Customer cust =null;
	String textMessage = "";
	HttpClient client = new DefaultHttpClient();
	HttpPost post = null;
	HttpResponse response = null;


	public abstract  List<NameValuePair> populateSMSParameters();
	public abstract String getSMSGatewayURL();



	
	public SMSFlowController(Customer cust, String textMessage) {
		super();
		this.cust = cust;
		this.textMessage = textMessage;
	}

	
	
	@Override
	public void sendSMS()  
	{
		post = new HttpPost( getSMSGatewayURL());
		HttpResponse response = null;
		List<NameValuePair> nvps = populateSMSParameters();
		try 
		{
			
			UrlEncodedFormEntity urEntity =  new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
			urEntity.setContentEncoding(HTTP.UTF_8);
			post.setEntity(urEntity);
			log.severe("UTF Message" + urEntity.getContent().toString());
			response = client.execute(post);
			log.severe(" SMSFlowController sendSMS Respose Code from 7777!" + response == null ? " " : response.toString());
			setResponse(String.valueOf(response.getStatusLine().getStatusCode()));
		} catch (Exception e) 
		{
			log.severe("Failed to Send SMS " + e.getMessage());
			setResponse("SMSFlowController sendSMS failed");
		}
	}

	@Override
	public String getTextMessage() 
	{
		
		String textMessage = this.textMessage; 
		return textMessage;
	}
	
	
	@Override
	public String getFromNumber() {
		return NexmoConsts.SMS_VENDOR_PARAM_VALUE_SENDER_ID;
	}


	@Override
	public String getToNumber() 
	{
		String telNoForSMS =  ConvertUtils.convertListToStringSeperatedByComma(cust.getTelNoForSMS());
		//FIXME - REmove this code from here and move it to FE Validation.
		//        This is only for Israli mobile that were entered with out prefix of 972
		if (telNoForSMS != null && telNoForSMS.startsWith("0"))
		{
			telNoForSMS = "972"  + telNoForSMS.substring(1);
			log.severe("getToNumber in SMSFLOWCOntroller - This code should not be reachable !! ");
		}
		log.severe(" SMS Number 6666 " + telNoForSMS);	

		return telNoForSMS;
		
	}


}
