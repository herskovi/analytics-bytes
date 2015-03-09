/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.anaytic.reports.datatypes.NumberDT;
import main.java.com.anaytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class OrderConfirmationResponse extends BaseResponse 
{

	private String message = null;
	private String topUpAmount = null;
	private String balance = null;
	private String customerName = null;
	
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
	@Override
	public void setMessage(String message) 
	{
		this.message = message;
	}
	/**
	 * @return the topUpAmount
	 */
	public String getTopUpAmount() {
		return topUpAmount;
	}
	/**
	 * @param topUpAmount the topUpAmount to set
	 */
	public void setTopUpAmount(String topUpAmount) {
		this.topUpAmount = topUpAmount;
	}
	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
	
	

}
