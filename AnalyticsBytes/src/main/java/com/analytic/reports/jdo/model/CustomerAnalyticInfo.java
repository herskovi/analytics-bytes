/**
 * 
 */
package main.java.com.analytic.reports.jdo.model;

import javax.jdo.annotations.IdGeneratorStrategy; 
import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


import main.java.com.analytic.reports.interfaces.IEntity;


/**
 * @author admin
 * May 17, 2014
 */
@PersistenceCapable
public class CustomerAnalyticInfo implements IEntity 
{ 


	/**
	 * 
	 */

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;	
	@Persistent
	private String accountId;
	@Persistent
	private String webPropertyId;
	@Persistent
	private String profileId; 
	@Persistent
	private String profileName;
	@Persistent
	private String goalId;
	@Persistent
	private String goalName;
	@Persistent
	private String timeToSendSMS;
	@Persistent
	private String timeZone;	
	@Persistent
	private Customer customer;

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

	public String getGoalId() {
		return goalId;
	}

	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public String getTimeToSendSMS() {
		return timeToSendSMS;
	}

	public void setTimeToSendSMS(String timeToSendSMS) {
		this.timeToSendSMS = timeToSendSMS;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}



}
