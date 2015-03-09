/**
 * 
 */
package main.java.com.analytic.reports.validator;


import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.consts.ValidationConsts;


/**
 * @author admin
 * Aug 14, 2014
 */
public class MonthlyRecurringChargeValidator extends BaseValidator implements IValidator 
{
	private Customer cust =null;

	/**
	 * @param cust
	 */

	public MonthlyRecurringChargeValidator(Customer cust) 
	{
		super();
		this.cust = cust;
	}

	public void validate() throws Exception 
	{
		boolean validateBalanceIsGreaterThanZero = validateBalanceIsGreaterThanZero(cust.getBalance());
		boolean validateCustomerIsNotWithinFirstMonth = validateCustomerIsNotWithinFirstMonth();
		
		if (validateBalanceIsGreaterThanZero && validateCustomerIsNotWithinFirstMonth)
			setAllValidationPassed(true);
		else
			setAllValidationPassed(false);
		
	}

	/**
	 *@param balance 
	 * @throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Dec 1, 2014
	 *@Description: Validate Mobile Number Is Not Empty
	 */
	private boolean validateBalanceIsGreaterThanZero(String balance) throws Exception 
	{
		if (Double.parseDouble(balance) > 0.0 )
		{
			return true;	
		}		
		return false;
	}
	
	/**
	 *@param balance 
	 * @throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Dec 1, 2014
	 *@Description: Validate Mobile Number Is Not Empty
	 */
	private boolean validateCustomerIsNotWithinFirstMonth() throws Exception 
	{
		Calendar currentDate = DateUtils.getSystemCalendar();
		Calendar creationDate = Calendar.getInstance();
		Date creationDateObj = cust.getCreationDate();
		if ( creationDateObj != null) 
		{
			creationDate.setTime(creationDateObj);	
		}
		
		if (DateUtils.isCurrentDateGreaterMoreThanOneMonthFromGivenDate(currentDate, creationDate))
		{
			return true;	
		}		
		return false;
	}


}
