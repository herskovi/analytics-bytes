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
public class EmailNotificationDT implements Serializable
{
	
	private String email ="";
	private String uniqueKey ="";
	/**
	 * @param email
	 * @param uniqueKey
	 */
	public EmailNotificationDT(String email, String uniqueKey) {
		super();
		this.email = email;
		this.uniqueKey = uniqueKey;
	}
	
	/**
	 * 
	 */
	public EmailNotificationDT() 
	{
		
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
			
	


}
