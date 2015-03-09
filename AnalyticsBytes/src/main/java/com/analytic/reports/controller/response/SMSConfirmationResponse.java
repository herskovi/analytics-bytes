/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.anaytic.reports.datatypes.NumberDT;
import main.java.com.anaytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class SMSConfirmationResponse extends BaseResponse 
{

	private String message = null;

	
	
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
	
	
	

}
