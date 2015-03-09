/**
 * 
 */
package main.java.com.analytic.reports.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Logger;

import main.java.com.analytic.reports.utils.consts.AnalyticConsts;
import main.java.com.anaytic.reports.datatypes.GoalDT;

import com.google.api.client.util.ArrayMap;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.Analytics.Data;
import com.google.api.services.analytics.Analytics.Data.Ga;
import com.google.api.services.analytics.Analytics.Data.Ga.Get;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Goal;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;

/**
 * @author admin
 * May 15, 2014
 */
public class AnalyticUtils 
{
	private static final Logger log = Logger.getLogger(AnalyticUtils.class.getName());


	/**
	 * Returns the top 25 organic search keywords and traffic source by visits. The Core Reporting API
	 * is used to retrieve this data.
	 *
	 * @param analytics the analytics service object used to access the API.
	 * @param profileId the profile ID from which to retrieve data.
	 * @return the response from the API.
	 * @throws IOException tf an API error occured.
	 */
	public static GaData executeDataQuery(Analytics analytics, String profileId,String [] metricsArr,String startDate, String endDate, GoalDT goalDT) throws IOException 
	{
		StringBuffer metrics = ConvertUtils.convertMetricsFromArrayToString(metricsArr);
		GaData gaData = getGaDataByMetrics(analytics, profileId, startDate, endDate, metrics.toString());
		return gaData;
	}
	
	

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 10, 2014
	 *@Description: Call to External Google Analytic API in order to get GaData by specific metrics 
	 */

	public static GaData getGaDataByMetrics(Analytics analytics,
			String profileId, String startDate, String endDate, String metrics) 
	{
		GaData gaData =null;
		try 
		{
			//Sleep for 1 seconds before each call to GA.
			
			Data data = analytics.data();
			Ga ga = data.ga();
			Get get = ga.get("ga:" + profileId, startDate, endDate, metrics);
			//Thread.sleep(1000);
			get.setMaxResults(25);
			get.setQuotaUser("XX"+profileId);
			//Thread.sleep(1000);
			gaData = get.execute();
			
			//com.google.api.services.analytics.Analytics.Data.Ga
			
			
//			gaData =  analytics.data().ga().get("ga:" + profileId, // Table Id. ga: + profile id.
//					startDate, // Start date.
//					endDate, // End date.
//					metrics) // Metrics.
//					// .setDimensions("ga:source,ga:keyword")
//					//.setSort("-ga:visits,ga:source")
//					//.setFilters("ga:medium==organic")
//					.setMaxResults(25)
//					.execute();
		}catch(Exception ex)
		{
			log.severe("Could not execute ga().get for profile" + profileId + " and for metrics " + metrics );
			log.severe(ex.getMessage());
		}
		return gaData;
	}

	/**
	 * 
	 */


	/**
	 * Prints the output from the Core Reporting API. The profile name is printed along with each
	 * column name and all the data in the rows.
	 *
	 * @param results data returned from the Core Reporting API.
	 * @throws IOException 
	 */
	public static String printGaData(GaData results , String [] metricsArr, GoalDT goalDT) throws IOException 
	{
		StringBuffer resp = new StringBuffer();
		log.info("printing results for profile:  " + results.getProfileInfo().getProfileName());
		ArrayMap<String,String> map = (ArrayMap)results.getTotalsForAllResults();
		String key = "";
		String value="";
		String numberOfUsers = "";
		String numberOfSessions = "";
		String goalName = goalDT.getGoalName();
		String goalValue = "";
		
//		if(goalName != null )
//		{
//			goalValue = String.valueOf(Math.round(goal.getValue()));
//		}
//		
		for (int i = 0; i < metricsArr.length; i++) 
		{
			key = metricsArr[i];  //i.e. ga:sessions, ga:users,etc..
			value = map.get(key);			
			if (AnalyticConsts.GA_USERS_HEADER.equals(key))
			{
				numberOfUsers = value;
			}else if(AnalyticConsts.GA_SESSION_HEADER.equals(key))
			{
				numberOfSessions = value;
			}else if(key.startsWith("ga:goal"))
			{
				goalValue = value;
			}
			
		}
		
		double numberOfSessionsValue = Double.parseDouble(numberOfSessions);
		double goalInPercentageOfSessions = 0.0;
		if (numberOfSessionsValue>0)
		{
			goalInPercentageOfSessions =  Double.parseDouble(goalValue) / Double.parseDouble(numberOfSessions);
		}
		
		
		double rounded = roundGoalPercentage(goalInPercentageOfSessions);
			
		resp.append(FormatterUtils.convertStringtoNumbersWithCommaSeparated(numberOfUsers));
		resp.append(" users visited your website in " + FormatterUtils.convertStringtoNumbersWithCommaSeparated(numberOfSessions) +  " sessions.\n");
		if (goalName != null)
		{
			resp.append(""  + FormatterUtils.convertStringtoNumbersWithCommaSeparated(goalValue));
			resp.append(" (" + rounded*100 +"%) sessions reached your goal of " + "'" +  goalName + "'");
			resp.append(" \n ");
		}
		return resp.toString();	

	}



	private static double roundGoalPercentage(double goalInPercentageOfSessions) {
		double rounded = 0.00;
		try
		{
			rounded = roundTo2Decimals(goalInPercentageOfSessions);
		}catch (Exception ex)
		{
			rounded = goalInPercentageOfSessions;
		}
		return rounded;
	}



	public static double roundTo2Decimals(double goalInPercentageOfSessions) 
	{
		DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(goalInPercentageOfSessions)).doubleValue();
	}

	/**
	 *@throws IOException 
	 * @Author:      Moshe Herskovits
	 *@Date:        May 17, 2014
	 *@Description: is Account Has More Than One Profile
	 */
	public static boolean isAccountHasMoreThanOneProfile(Analytics analytic, String accountId, String webpropertyId) throws IOException 
	{

		// Query profiles collection.
		Profiles profiles =
				analytic.management().profiles().list(accountId, webpropertyId).execute();
		if (profiles.getItems().size() > 1) 
		{
			return true;
		}else
		{
			return false;
		}
	}

	/**
	 *@throws IOException 
	 * @Author:      Moshe Herskovits
	 *@Date:        May 17, 2014
	 *@Description: is Account Has More Than One Profile
	 */
	public static boolean isAccountHasMoreThanOneWebProperty(Analytics analytic, String accountId ) throws IOException 
	{
		Webproperties webproperties = analytic.management().webproperties().list(accountId).execute();
		return (webproperties.getItems().size() > 1 ? true : false); 

	}


	/**
	 *	@throws  
	 *  @Author:      Moshe Herskovits
	 *  @Date:        May 17, 2014
	 *  @Description: is Account Has More Than One Profile recieve accounts as input
	 */

	public static boolean isUserHasMoreThanOneAccount(Accounts accounts) {
		if (accounts.getItems().size() > 1)
		{
			return true;
		}else{
			return false;
		}
	}
	

	/**
	 *	@throws  
	 *  @Author:      Moshe Herskovits
	 *  @Date:        May 17, 2014
	 *  @Description: is Account Has More Than One Profile recieve accounts as input
	 */

	public static int getNumberOfAccounts(Accounts accounts) {
		return accounts.getItems().size();
	}
	
	/**
	 *	@throws  
	 *  @Author:      Moshe Herskovits
	 *  @Date:        May 17, 2014
	 *  @Description: Get Default Metrics For User
	 */

	public static String[] getDefaultMetrics() 
	{
		String [] metricsArr = new String[2];
		metricsArr[0]=AnalyticConsts.GA_USERS_HEADER;
		metricsArr[1]=AnalyticConsts.GA_SESSION_HEADER;
		return metricsArr;
		
	}
	
	

}
