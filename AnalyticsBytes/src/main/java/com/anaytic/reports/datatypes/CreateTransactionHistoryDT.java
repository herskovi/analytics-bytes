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
public class CreateTransactionHistoryDT implements Serializable
{
	private String userId="";
	private String Date ="";
	private String Description ="";
	private String Amount ="";
	
	
	/**
	 * @param email
	 * @param date
	 * @param email2
	 * @param description
	 * @param amount
	 */
	public CreateTransactionHistoryDT(String userId, String date, String description, String amount) 
	{
		this.userId = userId;
		this.Date = date;
		this.Description = description;
		this.Amount = amount;
	}
	
	

	/**
	 * 
	 */
	public CreateTransactionHistoryDT() 
	{
	
	}


	

	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getDate() {
		return Date;
	}


	public void setDate(String date) {
		this.Date = date;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		this.Description = description;
	}


	public String getAmount() {
		return Amount;
	}


	public void setAmount(String amount) {
		this.Amount = amount;
	}
	
	


		
	


}
