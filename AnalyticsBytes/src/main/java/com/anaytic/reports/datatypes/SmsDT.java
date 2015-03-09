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
public class SmsDT implements Serializable
{
	private String email="";
	private String date ="";
	private String messageText="";

	
	
	/**
	 * @param email
	 * @param date
	 * @param email2
	 * @param description
	 * @param amount
	 */
	public SmsDT(String email, String date, String messageText, String amount) 
	{
		this.email = email;
		this.date = date;
		this.messageText=messageText;
	}
	
	
	/**
	 * @param email
	 */
	public SmsDT(String email) 
	{
		this.email = email;
	}


	/**
	 * 
	 */
	public SmsDT() 
	{
	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}




		
	


}
