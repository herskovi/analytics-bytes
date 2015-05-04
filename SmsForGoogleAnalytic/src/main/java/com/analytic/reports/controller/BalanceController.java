/**
 * 
 */
package main.java.com.analytic.reports.controller;


import main.java.com.analytic.reports.controller.response.BalanceResponse;
import main.java.com.analytic.reports.interfaces.*;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.FormatterUtils;


/**
 * @author admin
 * Aug 14, 2014
 */
public class BalanceController implements IBalanceController 
{

	private Customer cust =null;
	private String amount ="";
	private IResponse balanceControllerResponse = newResponse();


	

	/**
	 * @param cust
	 */
	
	public BalanceController(Customer cust) 
	{
		super();
		this.cust = cust;
	}
	
	/**
	 * @param cust
	 * @param balance
	 */
	public BalanceController(Customer cust, String amount) {
		super();
		this.cust = cust;
		this.amount = amount;
	}


	public void execute() throws Exception 
	{
		
		updateBalance();
		
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#getResponse()
	 */
	@Override
	public IResponse getResponse() 
	{
		return this.balanceControllerResponse;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#setResponse(java.lang.String)
	 */
	@Override
	public void setResponse(String message) 
	{
		this.balanceControllerResponse.setMessage(message);
		
	}

	
	@Override
	public IResponse newResponse() 
	{
		 return new BalanceResponse();
	}

	@Override
	public String getBalance() 
	{
		return cust.getBalance();
	}

		
	@Override
	public void updateBalance() 
	{
		double balance = Double.parseDouble(getBalance()) + Double.parseDouble(amount);
		balance = FormatterUtils.roundTo2Decimals(balance);
		cust.setBalance(String.valueOf(balance));
		CustomerDAO.updateCustomerBalanceInDB(cust, String.valueOf(balance));
	}

}
