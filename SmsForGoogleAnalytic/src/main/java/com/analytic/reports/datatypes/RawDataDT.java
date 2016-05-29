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
	
	

}
