/**
 * 
 */
package main.java.com.analytic.reports.datatypes;

import java.io.Serializable;

import com.google.api.services.analytics.model.Profiles;

/**
 * @author admin
 * Jun 17, 2014
 */
public class RawDataDT implements Serializable 
{
	
	private String clientUniqueId=null;
	private String hour=null;
	private String minute=null;
	private String sourceMedium=null;
	private String campaign=null;
	private String country=null;
	private String pagePath=null;
	private String mobileDeviceInfo=null;
	private String browser=null;
	private String deviceCategory=null;
	private String landingPagePath=null;
	private String exitPagePath=null;
	private String metric1=null;
	private String sessions=null;
	private String users=null;
	private String goal1Completions=null;
	private String goal2Completions=null;
	private String goal3Completions=null;
	private String goal4Completions=null;
	private String goal5Completions=null;
	
	
	public RawDataDT(String clientUniqueId) 
	{
		this.clientUniqueId = clientUniqueId;	
	}
	
	public RawDataDT(String clientUniqueId, String hour, String minute) 
	{
		this.clientUniqueId = clientUniqueId;
		this.hour = hour;
		this.minute = minute;	
	}


	public String getClientUniqueId() {
		return clientUniqueId;
	}

	public void setClientUniqueId(String clientUniqueId) {
		this.clientUniqueId = clientUniqueId;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSourceMedium() {
		return sourceMedium;
	}

	public void setSourceMedium(String sourceMedium) {
		this.sourceMedium = sourceMedium;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getMobileDeviceInfo() {
		return mobileDeviceInfo;
	}

	public void setMobileDeviceInfo(String mobileDeviceInfo) {
		this.mobileDeviceInfo = mobileDeviceInfo;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public String getLandingPagePath() {
		return landingPagePath;
	}

	public void setLandingPagePath(String landingPagePath) {
		this.landingPagePath = landingPagePath;
	}

	public String getExitPagePath() {
		return exitPagePath;
	}

	public void setExitPagePath(String exitPagePath) {
		this.exitPagePath = exitPagePath;
	}

	public String getMetric1() {
		return metric1;
	}

	public void setMetric1(String metric1) {
		this.metric1 = metric1;
	}

	public String getSessions() {
		return sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getGoal1Completions() {
		return goal1Completions;
	}

	public void setGoal1Completions(String goal1Completions) {
		this.goal1Completions = goal1Completions;
	}

	public String getGoal2Completions() {
		return goal2Completions;
	}

	public void setGoal2Completions(String goal2Completions) {
		this.goal2Completions = goal2Completions;
	}

	public String getGoal3Completions() {
		return goal3Completions;
	}

	public void setGoal3Completions(String goal3Completions) {
		this.goal3Completions = goal3Completions;
	}

	public String getGoal4Completions() {
		return goal4Completions;
	}

	public void setGoal4Completions(String goal4Completions) {
		this.goal4Completions = goal4Completions;
	}

	public String getGoal5Completions() {
		return goal5Completions;
	}

	public void setGoal5Completions(String goal5Completions) {
		this.goal5Completions = goal5Completions;
	}
	
	

}
