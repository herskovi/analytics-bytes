/**
 * 
 */
package main.java.com.analytic.reports.interfaces;

import java.util.List;

import main.java.com.analytic.reports.utils.consts.NexmoConsts;

import org.apache.http.NameValuePair;

/**
 * @author admin
 * May 16, 2014
 */
public interface ISMSController extends IController
{
	public void sendSMS();
	public String getSMSGatewayURL();
	public String getTextMessage();
	public String getToNumber();
	public String getFromNumber();
	public List<NameValuePair> populateSMSParameters();



}
