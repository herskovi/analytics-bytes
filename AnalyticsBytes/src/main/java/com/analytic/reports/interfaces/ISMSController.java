/**
 * 
 */
package main.java.com.analytic.reports.interfaces;

import java.util.List;

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
	public List<NameValuePair> populateSMSParameters();



}
