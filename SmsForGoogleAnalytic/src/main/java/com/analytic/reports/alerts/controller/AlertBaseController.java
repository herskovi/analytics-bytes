/**
 * 
 */
package main.java.com.analytic.reports.alerts.controller;

import java.io.IOException;

import main.java.com.analytic.reports.enums.AlertMetrics;
import main.java.com.analytic.reports.interfaces.IAlertsController;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;

/**
 * @author admin
 * Jul 21, 2014
 */
public abstract class AlertBaseController implements IAlertsController 
{
	Customer cust = null;
	Enum<AlertMetrics> metrics;
	String condition = "";
	String value = "";
	boolean comparision = false;
	String comparisionValue ="";
	
	/**
	 * @param cust
	 */
	public AlertBaseController(Customer cust) {
		super();
		this.cust = cust;
	}
	
	
	
	public void execute() throws IOException, Exception
	{
		metrics = getMetricsToCheck();
		condition = getCondition();
		value = getConditionValue();
		comparision = isAlertNeedToBeCompareWith();
		getAlertCompareToValue();
		didSignificantDivergenceOccurs(metrics, condition,value, comparision,comparisionValue);
		
			
	}

	@Override
	public boolean didSignificantDivergenceOccurs( Enum<AlertMetrics> metrics,String condition, String value, boolean comparision, String comparisionValue) 
	{
		//Alert me when sessions increases/decreases by more than 5% same day in the previous week/month/year
		
		
		return false;
	}



	private void getAlertCompareToValue() {
		if (comparision)
		{
			comparisionValue = alertCompareToValue();
		}
	}
	


}
