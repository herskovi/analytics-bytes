/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.analytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class UniqueAccountNumberResponse extends BaseResponse 
{

	private String message = null;
	private String uniqueAccountNumber="";
	
	
	
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
	

	public String getUniqueAccountNumber() {
		return uniqueAccountNumber;
	}
	public void setUniqueAccountNumber(String uniqueAccountNumber) {
		this.uniqueAccountNumber = uniqueAccountNumber;
	}
	

}
