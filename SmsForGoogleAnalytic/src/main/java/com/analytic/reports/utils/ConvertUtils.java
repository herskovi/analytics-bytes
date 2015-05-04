/**
 * 
 */
package main.java.com.analytic.reports.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * May 16, 2014
 */
public class ConvertUtils 
{
	private static Map<String, Integer> myMap = new HashMap<String, Integer>();

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 2, 2014
	 *@Description: Convert List To String
	 */

	public static String convertListToStringSeperatedByComma(List<String> strList)
	{
		//final StringBuilder sb = new StringBuilder("+");
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (final Object object : strList) {
		        if (!isFirst)
		            sb.append(',');
		        else
		            isFirst = false;
		        sb.append(object);
		    }
		    sb.append("");
		    return sb.toString();
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 15, 2014
	 *@Description: Convert Metrics From Array To String
	 */

	public static StringBuffer convertMetricsFromArrayToString(
			String[] metricsArr) {
		StringBuffer metrics= new StringBuffer();
		for (int i = 0; i < metricsArr.length; i++) 
		{
			metrics.append(metricsArr[i]);
			 if(i<metricsArr.length - 1)
			 {
				 metrics.append(",");
			 }
		}
		return metrics;
	}

	
	
	
		

}
