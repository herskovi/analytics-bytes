/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.analytic.reports.datatypes.EmailNotificationDT;
import main.java.com.analytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class EmailNotificationResponse extends BaseResponse 
{

	private String message = null;
	private ArrayList <EmailNotificationDT> EmailNotificationList = new ArrayList<EmailNotificationDT>();
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
	@Override
	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	public ArrayList<EmailNotificationDT> getEmailNotificationList() {
		return EmailNotificationList;
	}
	public void setEmailNotificationList(
			ArrayList<EmailNotificationDT> emailNotificationList) {
		EmailNotificationList = emailNotificationList;
	}
	
		

}
