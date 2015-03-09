/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.anaytic.reports.datatypes.EmailConfirmationDT;
import main.java.com.anaytic.reports.datatypes.EmailNotificationDT;
import main.java.com.anaytic.reports.datatypes.UserDashboardDT;
import main.java.com.anaytic.reports.datatypes.WebpropertiesDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class WebPropertyResponse extends BaseResponse 
{

	private String accountId="";
	private List<WebpropertiesDT> webPropertiesList = new ArrayList<WebpropertiesDT>();
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public List<WebpropertiesDT> getWebPropertiesList() {
		return webPropertiesList;
	}
	public void setWebPropertiesList(List<WebpropertiesDT> webPropertiesList) {
		this.webPropertiesList = webPropertiesList;
	}

	@Override
	public String getMessage() 
	{
		return this.responseMessage;
	}
	@Override
	public void setMessage(String message) 
	{
		this.responseMessage = message;
	}
	
}
