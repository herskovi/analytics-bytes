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
public class AccountDT implements Serializable
{
	private String accountId ="";
	private String accountName = null;
	private List<WebpropertiesDT> webPropertiesList = null;
	
	
	/**
	 * @param id
	 * @param name
	 */
	public AccountDT(String accountId, String name) 
	{
		this.accountId = accountId;
		this.accountName = name;
	}
	/**
	 * @param id
	 * @param name
	 * @param List WebpropertiesDT

	 */
	public AccountDT(String accountId, String name, List<WebpropertiesDT> webpropertiesList) 
	{
		this.accountId = accountId;
		this.accountName = name;
		this.webPropertiesList = webpropertiesList;
	}
	
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the webPropertiesList
	 */
	public List<WebpropertiesDT> getWebPropertiesList() {
		return webPropertiesList;
	}
	/**
	 * @param webPropertiesList the webPropertiesList to set
	 */
	public void setWebPropertiesList(List<WebpropertiesDT> webPropertiesList) {
		this.webPropertiesList = webPropertiesList;
	}
	



}
