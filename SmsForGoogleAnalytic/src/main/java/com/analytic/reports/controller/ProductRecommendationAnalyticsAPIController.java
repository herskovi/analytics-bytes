/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import main.java.com.analytic.reports.controller.response.DailySmsResponse;
import main.java.com.analytic.reports.controller.response.ProductRecommendationAnalyticsAPIResponse;
import main.java.com.analytic.reports.datatypes.GoogleAnalyticsDT;
import main.java.com.analytic.reports.datatypes.ProfileDT;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.SMS;
import main.java.com.analytic.reports.servlets.DailySmsServlet;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.CustomerHelper;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.consts.AnalyticConsts;

import com.google.api.client.util.ArrayMap;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.DataTable;

/**
 * @author admin
 * Jul 21, 2014
 */
public class ProductRecommendationAnalyticsAPIController extends BaseController
{
	private String userId ="";
	private String profileId ="";
	//private String startDate = "yesterday";
	private String startDate = "2015-01-01";
	String endDate = "today";
	String[] metricsArr = {"ga:metric1","ga:sessions","ga:users","ga:goal1Completions","ga:goal2Completions","ga:goal3Completions","ga:goal4Completions","ga:goal5Completions"};
	String[] dimensionArr = {"ga:dimension1,ga:hour,ga:minute,ga:sourceMedium,ga:campaign,ga:country,ga:pagePath"};
	Map<String, RawDataDT> rawDataDtMap = new HashMap<String, RawDataDT>();

	//String[] metricsArr = {"ga:metric1","ga:sessions"};
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

	/**
	 * @param userId2
	 */
	public ProductRecommendationAnalyticsAPIController(String userId) 
	{
		this.userId = userId;
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
			Customer cust = CustomerDAO.getCustomerInformationByUserID(userId);
			ProfileDT profileDT = getUserAnalyticsProfile(cust);
			setGAAccountInfo(cust);
			extractGoogleAnalyticsData(isLocalMode, userId,	profileDT.getProfileId(), startDate, endDate);

		}catch(Exception ex)
		{
			log.severe(" userId   " + userId + " profileId " + profileId + "FAILED***!!!! "+ ex.getMessage());
		}



	}
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 22, 2016
	 *@Description: Set Account Information
	 */

	private void setGAAccountInfo(Customer cust) 
	{
		productRecommendationAnalyticsAPIResponse = new ProductRecommendationAnalyticsAPIResponse();
		productRecommendationAnalyticsAPIResponse.setAccountId(cust.getCustomerAnalyticList().get(0).getAccountId());
		productRecommendationAnalyticsAPIResponse.setWebProperyId(cust.getCustomerAnalyticList().get(0).getWebPropertyId());
		productRecommendationAnalyticsAPIResponse.setProfileId(cust.getCustomerAnalyticList().get(0).getProfileId());
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
			GoogleAnalyticsDT googleAnalyticsDT = getAnalyticData(analytics);
			this.productRecommendationAnalyticsAPIResponse.getGoogleAnalyticsList().add(googleAnalyticsDT);
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

		GoogleAnalyticsDT googleAnalyticsDT = new GoogleAnalyticsDT();
		GaData gaData = AnalyticUtils.extractCustomReportsFromGA(analytics, profileId, metricsArr,dimensionArr, startDate, endDate);
		if (gaData != null) 
		{
			ArrayMap<String,String> map = (ArrayMap)gaData.getTotalsForAllResults();
			for (int i = 0; i < metricsArr.length; i++) 
			{
				setAnalyticsGaData(googleAnalyticsDT, map, i);
			}
			//RawDataDT rawDataDT = new RawDataDT(googleAnalyticsDT..get, value, value);
			List<RawDataDT> rawDataList = googleAnalyticsDT.getRawDataList(); 
			List<List<String>> gaDataRows= gaData.getRows();
			for (List<String> itemList : gaDataRows) 
			{
				//[0] - ga:dimension1, [1]- ga:hour, [2] - ga:minute, //[3]- ga:sourceMedium,[4] - ga:campaign, [5] - ga:country, [6] - ga:pagePath
				String clientId = itemList.get(0);
				RawDataDT rawDataDT = getRawDataDT(clientId , itemList);
				
				rawDataDT.setHour(itemList.get(1));	
				rawDataDT.setMinute(itemList.get(2));				
				rawDataDT.setSourceMedium(itemList.get(3));
				rawDataDT.setCampaign(itemList.get(4));
				rawDataDT.setCountry(itemList.get(5));
				rawDataDT.setPagePath(itemList.get(6));
				//rawDataDT.setMobileDeviceInfo(itemList.get(6));
				rawDataList.add(rawDataDT);
				rawDataDtMap.put(clientId, rawDataDT);
			}
		}
		return googleAnalyticsDT;

	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 28, 2016
	 *@Description: Return RawDataDT from Map or create new object.
	 */

	private RawDataDT getRawDataDT(String clientId, List<String> itemList) 
	{	
		return (rawDataDtMap.get(clientId) == null ? new RawDataDT(clientId) : rawDataDtMap.get(clientId));
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 25, 2016
	 *@Description: Set Analytics GA Data
	 */

	private void setAnalyticsGaData(GoogleAnalyticsDT googleAnalyticsDT, ArrayMap<String, String> map, int i) 
	{
		String key;
		String value;
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
		}else if(key.startsWith(AnalyticConsts.GA_METRIC_HEADER))
		{
			googleAnalyticsDT.setMetrics(value);
		}
	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: Jun 1, 2014
	 * @Description: Get Metrics From User Preferences
	 */

	private ProfileDT getUserAnalyticsProfile(Customer cust) 
	{

		ProfileDT  profileDT = null;
		if (cust != null)
		{
			userId = cust.getUniqueAccountNumber(); 
			CustomerHelper custUtils = new CustomerHelper(cust,userId);
			String accountId = custUtils.getAccountId();
			String webPropertyId = custUtils.getWebPropertyId();
			profileId = custUtils.getProfileId();
			profileDT = new ProfileDT(accountId, webPropertyId,profileId);

		}
		return profileDT;
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
		return productRecommendationAnalyticsAPIResponse;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#setResponse(java.lang.String)
	 */
	@Override
	public void setResponse(String message) {

	}


}
