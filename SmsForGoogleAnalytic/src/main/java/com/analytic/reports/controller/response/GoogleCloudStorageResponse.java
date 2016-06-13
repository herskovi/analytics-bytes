/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.analytic.reports.datatypes.GoogleAnalyticsDT;


/**
 * @author admin
 * Jul 23, 2014
 */
public class GoogleCloudStorageResponse extends BaseResponse 
{

	private String accountId="";
	private String webProperyId="";
	private String profileId="";
	private List<GoogleAnalyticsDT> googleAnalyticsList = new ArrayList<GoogleAnalyticsDT>();
	
	
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
	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	

	public List<GoogleAnalyticsDT> getGoogleAnalyticsList() {
		return googleAnalyticsList;
	}
	
	public void setGoogleAnalyticsList(List<GoogleAnalyticsDT> googleAnalyticsList) 
	{
		this.googleAnalyticsList = googleAnalyticsList;
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
