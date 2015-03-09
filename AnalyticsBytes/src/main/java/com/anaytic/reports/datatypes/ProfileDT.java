/**
 * 
 */
package main.java.com.anaytic.reports.datatypes;

import java.io.Serializable;

import com.google.api.services.analytics.model.Profiles;

/**
 * @author admin
 * Jun 17, 2014
 */
public class ProfileDT implements Serializable 
{
	
	private String accountId=null;
	private String webPropertyId=null;
	private String profileId=null;
	private String profileName=null;
	private String timeZone=null;


	/**
	 * @param accountId
	 * @param webPropertyId
	 * @param profiles
	 */
	public ProfileDT(String accountId, String webPropertyId, String profileId, String profileName) 
	{
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.profileId = profileId;
		this.profileName= profileName;
	}
	
	public ProfileDT(String accountId, String webPropertyId) 
	{
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
	}
	
	/**
	 * @param accountId
	 * @param webPropertyId
	 * @param profiles
	 */
	public ProfileDT(String accountId, String webPropertyId, String profileId, String profileName,String timeZone) 
	{
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.profileId = profileId;
		this.profileName= profileName;
		this.timeZone = timeZone;
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

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	

}
