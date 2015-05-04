/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.analytic.reports.datatypes.EmailConfirmationDT;
import main.java.com.analytic.reports.datatypes.EmailNotificationDT;
import main.java.com.analytic.reports.datatypes.ProfileDT;
import main.java.com.analytic.reports.datatypes.UserDashboardDT;
import main.java.com.analytic.reports.datatypes.WebpropertiesDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class ProfileResponse extends BaseResponse 
{

	private String accountId="";
	private String webProperyId="";
	private List<ProfileDT> profileList = new ArrayList<ProfileDT>();
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the webProperyId
	 */
	public String getWebProperyId() {
		return webProperyId;
	}
	/**
	 * @param webProperyId the webProperyId to set
	 */
	public void setWebProperyId(String webProperyId) {
		this.webProperyId = webProperyId;
	}
	
	/**
	 * @return the profileList
	 */
	public List<ProfileDT> getProfileList() {
		return profileList;
	}
	/**
	 * @param profileList the profileList to set
	 */
	public void setProfileList(List<ProfileDT> profileList) {
		this.profileList = profileList;
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
