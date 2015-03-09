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
public class CancelAccountDT implements Serializable
{
	
	private String email ="";
	private String uniqueKey ="";
	private String communicationChannel = ""; //SMS/ WEB / EMAIL 
	
	/**
	 * @param email
	 * @param uniqueKey
	 */
	public CancelAccountDT(String email, String uniqueKey, String communicationChannel) {
		super();
		this.email = email;
		this.uniqueKey = uniqueKey;
		this.communicationChannel = communicationChannel;

	}
	
	/**
	 * 
	 */
	public CancelAccountDT() 
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

	public String getCommunicationChannel() {
		return communicationChannel;
	}

	public void setCommunicationChannel(String communicationChannel) {
		this.communicationChannel = communicationChannel;
	}
				
	


}
