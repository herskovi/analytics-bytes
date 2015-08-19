/**
 * 
 */
package main.java.com.analytic.reports.alerts.controller;

import main.java.com.analytic.reports.enums.AlertMetrics;
import main.java.com.analytic.reports.interfaces.IAlertsController;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.consts.AlertConsts;

/**
 * @author admin
 * Aug 6, 2015
 */
public class WeeklyAlertController extends AlertBaseController  
{

	/**
	 * @param cust
	 */
	public WeeklyAlertController(Customer cust) 
	{
		super(cust);
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#getResponse()
	 */
	@Override
	public IResponse getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#setResponse(java.lang.String)
	 */
	@Override
	public void setResponse(String message)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#newResponse()
	 */
	@Override
	public IResponse newResponse() 
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IAlertsController#getMetricsToCheck()
	 */
	@Override
	public Enum<AlertMetrics> getMetricsToCheck() 
	{
		return AlertMetrics.Sessions;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IAlertsController#getCondition()
	 */
	@Override
	public String getCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IAlertsController#getConditionValue()
	 */
	@Override
	public String getConditionValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IAlertsController#isAlertNeedToBeCompareWith()
	 */
	@Override
	public boolean isAlertNeedToBeCompareWith() 
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IAlertsController#alertCompareToValue()
	 */
	@Override
	public String alertCompareToValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IAlertsController#shouldAlertBeSent()
	 */
	@Override
	public boolean shouldAlertBeSent() {
		// TODO Auto-generated method stub
		return false;
	}

	

	
}
