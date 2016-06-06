/**
 * 
 */
package main.java.com.analytic.reports.jdo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author admin
 * Apr 28, 2015
 */

@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="false")
public class SMS implements Serializable
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
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
	@Persistent(defaultFetchGroup = "true")
	private List<String> metrics = new ArrayList<String>();
	private Subscriber Subscriber;
	
	
	

	/**
	 * @param accountId
	 * @param webPropertyId
	 * @param profileId
	 * @param profileName
	 * @param goalId
	 * @param goalName
	 * @param timeToSendSMS
	 * @param timeZone
	 */
	public SMS(String accountId, String webPropertyId, String profileId,
			String profileName, String goalId, String goalName,
			String timeToSendSMS, String timeZone,List<String> metrics) {
		super();
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.profileId = profileId;
		this.profileName = profileName;
		this.goalId = goalId;
		this.goalName = goalName;
		this.timeToSendSMS = timeToSendSMS;
		this.timeZone = timeZone;
		this.metrics = metrics;
	}

	/**
	 * 
	 */
	public SMS() {
		// TODO Auto-generated constructor stub
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
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

	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public List<String> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<String> metrics) {
		this.metrics = metrics;
	}

	public Subscriber getSubscriber() {
		return Subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		Subscriber = subscriber;
	}
}