/**
 * 
 */
package main.java.com.analytic.reports.datatypes;

import java.io.Serializable;

/**
 * @author admin
 * Jun 6, 2014
 */
public class TransactionHistoryDT implements Serializable
{
	private String date ="";
	private String description ="";
	private String amount ="";
	

	/**
	 * @param email
	 * @param date
	 * @param email2
	 * @param description
	 * @param amount
	 */
	public TransactionHistoryDT(String date, String description, String amount) 
	{
		this.date = date;
		this.description = description;
		this.amount = amount;
	}
	

	
	/**
	 * 
	 */
	public TransactionHistoryDT() 
	{
		
	}



	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}



		
	


}
