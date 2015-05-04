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
public class MetricDT implements Serializable 
{
	
	private String accountId=null;
	private String webPropertyId=null;
	private String profileId=null;
	private String metricName=null;
	private String metricValue=null;


	/**
	 * @param accountId
	 * @param webPropertyId
	 * @param profiles
	 */
	public MetricDT(String accountId, String webPropertyId, String profileId) 
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
	public MetricDT(String accountId, String webPropertyId, String profileId,
			String metricName, String metricValue) 
	{
		super();
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.profileId = profileId;
		this.metricName = metricName;
		this.metricValue = metricValue;
	}

	
	
	/**
	 * @param accountId
	 * @param webPropertyId
	 * @param profiles
	 */
	public MetricDT(String accountId, String webPropertyId, String profileId, String metricName) 
	{
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.profileId = profileId;
		this.metricName = metricName;
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

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public String getMetricValue() {
		return metricValue;
	}

	public void setMetricValue(String metricValue) {
		this.metricValue = metricValue;
	}
	
	
	

}
