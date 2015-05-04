package main.java.com.analytic.reports.utils;

import java.util.HashMap;
import java.util.Map;

import main.java.com.analytic.reports.utils.consts.AnalyticConsts;


public class AnalyticHeader 
{
	private static AnalyticHeader instance = null;	
	private static Map<String, String> analyticHeaders = loadCache();
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 19, 2014
	 *@Description: Cache the Key Value
	 */

	public static AnalyticHeader getInstance() 
	{
		
		
			if(instance == null) 
			{ 
				instance= new AnalyticHeader();
			}
		
		return 	instance;

	}
	
	public String get(String key) 
	{
		if(analyticHeaders != null) 
		{
			return analyticHeaders.get(key);
		}
		return null;
		
	}
	

	private  static Map loadCache() 
	{
		analyticHeaders = new HashMap<String, String>();
		analyticHeaders.put(AnalyticConsts.GA_GOAL_COMPLETION_ALL_HEADER, AnalyticConsts.GA_GOAL_COMPLETION_ALL);
		analyticHeaders.put(AnalyticConsts.GA_USERS_HEADER, AnalyticConsts.GA_USERS);
		analyticHeaders.put(AnalyticConsts.GA_GOAL_VALUE_ALL_HEADER, AnalyticConsts.GA_GOAL_VALUE_ALL);
		analyticHeaders.put(AnalyticConsts.GA_BOUNCE_RATE_HEADER, AnalyticConsts.GA_BOUNCE_RATE);
		analyticHeaders.put(AnalyticConsts.GA_PERCENTAGE_NEW_SESSION_HEADER, AnalyticConsts.GA_PERCENTAGE_NEW_SESSION);
		return analyticHeaders;

	}
}