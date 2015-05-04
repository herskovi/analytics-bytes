/**
 * 
 */
package main.java.com.analytic.reports.datatypes;

import java.io.Serializable;
import java.util.List;
//import com.google.api.services.analytics.Analytics.Management.Webproperties;
import com.google.api.services.analytics.model.Webproperties;

/**
 * @author admin
 * Jun 17, 2014
 */
public class WebpropertiesDT implements Serializable 
{
	
	private String accountId = null;
	private String webPropertyId = null;
	private String webPropertyName = null;
	private List<ProfileDT> profileDTList  = null;
	
	
	public WebpropertiesDT(String accountId, String webPropertyId, String webPropertyName ) 
	{
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.webPropertyName = webPropertyName;
	}
	
	public WebpropertiesDT(String accountId, String webPropertyId, String webPropertyName, List<ProfileDT> profileDTList ) 
	{
		this.accountId = accountId;
		this.webPropertyId = webPropertyId;
		this.webPropertyName = webPropertyName;
		this.profileDTList = profileDTList;
	}
	
	public WebpropertiesDT(String accountId) 
	{
		this.accountId = accountId;
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

	public String getWebPropertyName() {
		return webPropertyName;
	}

	public void setWebPropertyName(String webPropertyName) {
		this.webPropertyName = webPropertyName;
	}

	public List<ProfileDT> getProfileDTList() {
		return profileDTList;
	}

	public void setProfileDTList(List<ProfileDT> profileDTList) {
		this.profileDTList = profileDTList;
	}


	

}
