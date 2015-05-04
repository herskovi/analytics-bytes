/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.analytic.reports.datatypes.EmailConfirmationDT;
import main.java.com.analytic.reports.datatypes.EmailNotificationDT;
import main.java.com.analytic.reports.datatypes.GoalDT;
import main.java.com.analytic.reports.datatypes.ProfileDT;
import main.java.com.analytic.reports.datatypes.UserDashboardDT;
import main.java.com.analytic.reports.datatypes.WebpropertiesDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class GoalResponse extends BaseResponse 
{

	private String accountId="";
	private String webProperyId="";
	private String profileId="";
	private List<GoalDT> goalList = new ArrayList<GoalDT>();
	
	
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
	
	/**
	 * @return the goalList
	 */
	public List<GoalDT> getGoalList() {
		return goalList;
	}
	/**
	 * @param goalList the goalList to set
	 */
	public void setGoalList(List<GoalDT> goalList) {
		this.goalList = goalList;
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
