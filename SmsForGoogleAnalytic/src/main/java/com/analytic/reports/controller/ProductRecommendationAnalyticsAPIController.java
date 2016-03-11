/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import main.java.com.analytic.reports.controller.response.DailySmsResponse;
import main.java.com.analytic.reports.controller.response.ProductRecommendationAnalyticsAPIResponse;
import main.java.com.analytic.reports.datatypes.GoogleAnalyticsDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.model.SMS;
import main.java.com.analytic.reports.servlets.DailySmsServlet;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.consts.AnalyticConsts;

import com.google.api.client.util.ArrayMap;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;

/**
 * @author admin
 * Jul 21, 2014
 */
public class ProductRecommendationAnalyticsAPIController extends BaseController
{
	private String userId ="";
	private String profileId ="";
	private String startDate = "";
	String endDate = DateUtils.getCurrentDateTime();
	String[] metricsArr = {"ga:session","ga:users"};
	HttpServletRequest productRecommendationAnalyticsAPIRequest = null;
	ProductRecommendationAnalyticsAPIResponse productRecommendationAnalyticsAPIResponse= null;
	private static final Logger log = Logger.getLogger(DailySmsServlet.class.getName());
	

	/**
	 * @param startDate
	 * @param profileId
	 * @param endDate
	 * @param metricsArr
	 */
	public ProductRecommendationAnalyticsAPIController(String userId, String profileId , String startDate,  String endDate, String[] metricsArr) {
		super();
		this.userId = userId;
		this.profileId = profileId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.metricsArr = metricsArr;
	}
	
	/**
	 * @param startDate
	 * @param profileId
	 * @param endDate
	 */
	public ProductRecommendationAnalyticsAPIController(String userId, String profileId, String startDate, String endDate) 
	{
		super();
		this.userId = userId;
		this.profileId = profileId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * @param startDate
	 * @param profileId
	 */
	public ProductRecommendationAnalyticsAPIController(String userId, String profileId, String startDate) {
		super();
		this.userId = userId;
		this.profileId = profileId;
		this.startDate = startDate;
		this.endDate = DateUtils.getCurrentDateTime();
	}
	
	/**
	 * @param startDate
	 * @param profileId
	 */
	public ProductRecommendationAnalyticsAPIController(String userId, String profileId) {
		super();
		this.userId = userId;
		this.profileId = profileId;
		this.startDate = startDate;
		this.endDate = DateUtils.getCurrentDateTime();	}

	
	public ProductRecommendationAnalyticsAPIController()
	{
		
	}


	/**
	 * @param req
	 */
	public ProductRecommendationAnalyticsAPIController(HttpServletRequest req) 
	{
		this.productRecommendationAnalyticsAPIRequest = req;
	}

	@Override
	public void execute() throws Exception
	{
		log.info("Start - Execute at ProductRecommendationAnalyticsAPIController " );
		try
		{
			boolean isLocalMode = true;//URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
			extractGAData(isLocalMode);
		}catch (Exception ex) 
		{
			log.severe(" General Error execute  " + ex.getMessage());
			throw ex;
		}
		log.info("End -  Execute at ProductRecommendationAnalyticsAPIController " );

	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 13, 2015
	 *@Description: Scan All Customers
	 */

	private void extractGAData(boolean isLocalMode) throws IOException, Exception 
	{
			try
			{
				//userId = cust.getUniqueAccountNumber();
				//userId = changeUserIdInCaseCredentialDoesNotExistWithUAN(userId, cust);
				extractGoogleAnalyticsData(isLocalMode, userId,	profileId, startDate, endDate);
			
			}catch(Exception ex)
			{
				log.severe(" userId   " + userId + " profileId " + profileId + "FAILED***!!!! "+ ex.getMessage());
			}

		

	}


	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 3, 2015
	 *@Description: It is time to send SMS for Subscriber
	 */

	public void extractGoogleAnalyticsData(boolean isLocalMode, String userId, String profileID,String startDate, String endDate)  throws IOException, Exception
	{
		Analytics analytics = getAnalyticsCredential(userId, profileID);
		try 
		{
			getAnalyticData(analytics);
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0) was not defined for this user   " + userId);
			throw ex;
		}
		
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 10, 2016
	 *@Description: Get Analytics Credential
	 */

	private Analytics getAnalyticsCredential(String userId, String profileID)
			throws IOException {
		Analytics analytics = null;
		try
		{		
			analytics = CredentialUtils.loadAnalytics(userId);
		} catch (Exception ex) 
		{
			// Token was not refreshed properly, call one more time for
			// refresh token
			analytics = CredentialUtils.loadAnalytics(userId);
	
			log.severe("profileID EXCEPTION CredentialUtils.loadAnalytics(userId); - Exception!!!  " + profileID);
		}
		return analytics;
	}

	

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 22, 2015
	 *@Description: Prepare Goal Data Type
	 */

	private GoogleAnalyticsDT setGoogleAnalyticsData(String users,String sessions) 
	{
		GoogleAnalyticsDT googleAnalyticsDT = new GoogleAnalyticsDT();
		
//		String accountId = custUtils.getAccountId(subscriberIndex,smsIndex);
//		String webPropertId = custUtils.getWebPropertyId(subscriberIndex,smsIndex);
//		String profileID = custUtils.getProfileId(subscriberIndex,smsIndex);
//		String goalId = custUtils.getGoalId(subscriberIndex,smsIndex);
//		String goalName = custUtils.getGoalName(subscriberIndex,smsIndex);			
//		GoalDT goalDT = new GoalDT(accountId,webPropertId,profileID, goalId,  goalName);
		return googleAnalyticsDT;
	}
	
	

	
	/**
	 * 
	 * @param analytics 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 12, 2014
	 * @Description: Get Analytic Data from Google Server
	 */

	public GoogleAnalyticsDT getAnalyticData(Analytics analytics) throws IOException 
	{
		String key = "";
		String value = "";
		GaData gaData = AnalyticUtils.executeDataQuery(analytics, profileId, metricsArr, startDate, endDate);
		GoogleAnalyticsDT googleAnalyticsDT = new GoogleAnalyticsDT();
		if (gaData != null) 
		{
			ArrayMap<String,String> map = (ArrayMap)gaData.getTotalsForAllResults();
			for (int i = 0; i < metricsArr.length; i++) 
			{
				key = metricsArr[i];  //i.e. ga:sessions, ga:users,etc..
				value = map.get(key);			
				if (AnalyticConsts.GA_USERS_HEADER.equals(key))
				{
					googleAnalyticsDT.setUsers(value);
				}else if(AnalyticConsts.GA_SESSION_HEADER.equals(key))
				{
					googleAnalyticsDT.setSessions(value);
				}else if(key.startsWith("ga:goal"))
				{
					googleAnalyticsDT.setGoalValue(value);
				}
				
			}
		}
		return googleAnalyticsDT;

	}

	
	/**
	 * @Author: Moshe Herskovits
	 * @Date: Jun 1, 2014
	 * @Description: Get Metrics From User Preferences
	 */

	private String[] getMetricsFromUserPreferences(SMS sms) 
	{
		Object[] objectList = sms.getMetrics().toArray();
		return Arrays.copyOf(objectList, objectList.length, String[].class);
	}



	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new DailySmsResponse();

	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#getResponse()
	 */
	@Override
	public IResponse getResponse() 
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#setResponse(java.lang.String)
	 */
	@Override
	public void setResponse(String message) {
		
	}


}
