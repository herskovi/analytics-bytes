/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.anaytic.reports.datatypes.EmailConfirmationDT;
import main.java.com.anaytic.reports.datatypes.EmailNotificationDT;
import main.java.com.anaytic.reports.datatypes.GoalDT;
import main.java.com.anaytic.reports.datatypes.MetricDT;
import main.java.com.anaytic.reports.datatypes.ProfileDT;
import main.java.com.anaytic.reports.datatypes.UserDashboardDT;
import main.java.com.anaytic.reports.datatypes.WebpropertiesDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class MetricValueResponse extends BaseResponse 
{

	private String accountId="";
	private String webProperyId="";
	private String profileId="";
	private List<MetricDT> metricsList = new ArrayList<MetricDT>();
	
	
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
	/**
	 * @return the metricsList
	 */
	public List<MetricDT> getMetricsList() {
		return metricsList;
	}
	/**
	 * @param metricsList the metricsList to set
	 */
	public void setMetricsList(List<MetricDT> metricsList) {
		this.metricsList = metricsList;
	}
	
	
}
