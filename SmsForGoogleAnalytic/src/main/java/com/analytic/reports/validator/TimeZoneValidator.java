/**
 * 
 */
package main.java.com.analytic.reports.validator;


import main.java.com.analytic.reports.interfaces.*;


/**
 * @author admin
 * Aug 14, 2014
 */
public class TimeZoneValidator extends BaseValidator implements IValidator 
{

	private String timeZone ="";

	public TimeZoneValidator(String timeZone) 
	{
		super();
		this.timeZone = timeZone;
	}

	/**
	 * 
	 */

	public void validate() throws Exception 
	{

		validateTimeZone();

	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: Validate Time Zone
	 */
	private void validateTimeZone() throws Exception
	{
		verifyTimeZoneIsNotEmpty();
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: Validate Time Zone is not empty
	 */
	private void verifyTimeZoneIsNotEmpty() throws Exception
	{
		if (timeZone == null || timeZone.length()==0)
		{
			throw new Exception("Time Zone cannot be Empty");
		}

	}

}
