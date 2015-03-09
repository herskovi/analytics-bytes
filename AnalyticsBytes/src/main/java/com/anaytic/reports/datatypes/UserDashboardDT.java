/**
 * 
 */
package main.java.com.anaytic.reports.datatypes;

import java.io.Serializable;
import java.util.List;

import com.google.api.services.analytics.Analytics.Management.Webproperties;
import com.google.api.services.analytics.model.Accounts;

/**
 * @author admin
 * Jun 6, 2014
 */
public class UserDashboardDT implements Serializable
{
	private String date ="";
	private String metricValue1 = null;
	private String metricValue2 = null;
	private String metricValue3  = null;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMetricValue1() {
		return metricValue1;
	}
	public void setMetricValue1(String metricValue1) {
		this.metricValue1 = metricValue1;
	}
	public String getMetricValue2() {
		return metricValue2;
	}
	public void setMetricValue2(String metricValue2) {
		this.metricValue2 = metricValue2;
	}
	public String getMetricValue3() {
		return metricValue3;
	}
	public void setMetricValue3(String metricValue3) {
		this.metricValue3 = metricValue3;
	}
	
	


}
