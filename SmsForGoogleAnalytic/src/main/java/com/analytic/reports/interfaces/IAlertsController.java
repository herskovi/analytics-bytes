/**
 * 
 */
package main.java.com.analytic.reports.interfaces;

import java.io.IOException;

import main.java.com.analytic.reports.enums.AlertMetrics;


/**
 * @author admin
 * 			May 16, 2014
 */
public interface IAlertsController extends IController
{
	public void execute() throws IOException, Exception;
	public Enum<AlertMetrics> getMetricsToCheck();
	public String getCondition();
	public String getConditionValue();
	public boolean isAlertNeedToBeCompareWith();
	public String  alertCompareToValue();
	public boolean didSignificantDivergenceOccurs(Enum<AlertMetrics> metrics, String condition,String value,boolean comparision,String comparisionValue);
	public boolean shouldAlertBeSent();
	
}
