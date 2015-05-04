/**
 * 
 */
package main.java.com.analytic.reports.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Logger;



/**
 * @author Moshe Herskovits
 * Jul 1, 2014
 */
public class FormatterUtils 
{
	private static final Logger log = Logger.getLogger(FormatterUtils.class.getName());

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 1, 2014
	 *@Description: Get Yesterdy Date
	 */
	public static String convertStringtoNumbersWithCommaSeparated(String value)
	{
		String retVal = value;
		try
		{
			DecimalFormat format = new DecimalFormat("#,##0");
			BigDecimal bigDec = new BigDecimal(value);
			retVal = format.format(bigDec);
			
			//value = NumberFormat.getIntegerInstance().format(value);
			
		}catch(Exception ex)
		{
			log.warning("Number format Exception for value from GA" + value);
		}
		return retVal;

	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 22, 2015
	 *@Description:
	 */

	public static double roundTo2Decimals(double goalInPercentageOfSessions) 
	{
		DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(goalInPercentageOfSessions)).doubleValue();
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 22, 2015
	 *@Description: is Pure ASCII
	 */
	
	public static boolean isPureAscii(String str)
	{
		return  str.matches("\\A\\p{ASCII}*\\z");
				//CharMatcher.ASCII.matchesAllOf(str);
	}

}
