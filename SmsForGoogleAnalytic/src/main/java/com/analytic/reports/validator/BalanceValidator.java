/**
 * 
 */
package main.java.com.analytic.reports.validator;


import main.java.com.analytic.reports.controller.response.BalanceResponse;
import main.java.com.analytic.reports.interfaces.*;
import main.java.com.analytic.reports.jdo.model.Customer;


/**
 * @author admin
 * Aug 14, 2014
 */
public class BalanceValidator extends BaseValidator implements IValidator 
{

	private Customer cust =null;
	private String amount ="";




	/**
	 * @param cust
	 */

	public BalanceValidator(Customer cust) 
	{
		super();
		this.cust = cust;
	}

	/**
	 * @param cust
	 * @param balance
	 */
	public BalanceValidator(Customer cust, String amount) {
		super();
		this.cust = cust;
		this.amount = amount;
	}


	public void validate() throws Exception 
	{

		validateBalance();

	}



	public String getBalance() 
	{
		return cust.getBalance();
	}


	public void validateBalance() throws Exception
	{
		int balance = Integer.parseInt(getBalance()) + Integer.parseInt(amount);
		validateBalanceIsGreaterThanZero(balance);		
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: Validate Balance Is Greater Than Zero
	 */
	private void validateBalanceIsGreaterThanZero(int balance) throws Exception
	{
		if (balance < 0 )
		{
			throw new Exception("Balance cannot be negative");
		}

	}

}
