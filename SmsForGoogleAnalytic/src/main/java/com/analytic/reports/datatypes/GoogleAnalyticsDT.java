/**
 * 
 */
package main.java.com.analytic.reports.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.analytics.model.Profiles;

/**
 * @author admin
 * Jun 17, 2014
 */
public class GoogleAnalyticsDT implements Serializable 
{
	
	private String accountId=null;
	private String webPropertyId=null;
	private String profileId=null;
	private String goalId=null;
	private String goalName=null;
	private String goalValue=null;
	private String users="";
	private String sessions="";
	private String metrics="";
	private List<RawDataDT> rawDataList= new ArrayList<RawDataDT>();


	/**
	 * @param accountId
	 * @param webPropertyId
	 * @param profiles
	 */
	public GoogleAnalyticsDT(String accountId, String webPropertyId, String profileId) 
	{
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.profileId = profileId;
	}
	
	/**
	 * @param accountId
	 * @param webPropertyId
	 * @param profileId
	 * @param goalId
	 * @param goalName
	 */
	public GoogleAnalyticsDT(String accountId, String webPropertyId, String profileId,
			String goalId, String goalName) 
	{
		super();
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.profileId = profileId;
		this.goalId = goalId;
		this.goalName = goalName;
	}

	
	
	/**
	 * @param accountId
	 * @param webPropertyId
	 * @param profiles
	 */
	public GoogleAnalyticsDT(String accountId, String webPropertyId, String profileId, String goalId) 
	{
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.profileId = profileId;
		this.setGoalId(goalId);
	}
	
	/**
	 * 
	 */
	public GoogleAnalyticsDT() {
		// TODO Auto-generated constructor stub
	}

	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getWebPropertyId() {
		return webPropertyId;
	}
	
	public void setWebPropertyId(String webPropertyId) {
		this.webPropertyId = webPropertyId;
	}
	
	public String getProfileId() {
		return profileId;
	}
	
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	
	/**
	 * @return the goalId
	 */
	public String getGoalId() {
		return goalId;
	}


	/**
	 * @param goalId the goalId to set
	 */
	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}


	/**
	 * @return the goalName
	 */
	public String getGoalName() {
		return goalName;
	}

	/**
	 * @param goalName the goalName to set
	 */
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	
	

	public String getGoalValue() {
		return goalValue;
	}

	public void setGoalValue(String goalValue) {
		this.goalValue = goalValue;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getSessions() {
		return sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getMetrics() {
		return metrics;
	}

	public void setMetrics(String metrics) {
		this.metrics = metrics;
	}

	/**
	 * @return the rawDataList
	 */
	public List<RawDataDT> getRawDataList() {
		return rawDataList;
	}

	/**
	 * @param rawDataList the rawDataList to set
	 */
	public void setRawDataList(List<RawDataDT> rawDataList) {
		this.rawDataList = rawDataList;
	}
	
	
	
	

}
