/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.ArrayList;
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
	private boolean isLocalMode  = false;
	private String userId ="";
	private String profileId ="";
	private String emailAddress ="";
	//private String startDate = "yesterday";
	private String startDate = "2015-06-01";
	private String endDate = "today";
	private String[] metricsArr = {"ga:sessions","ga:users","ga:goal1Completions","ga:goal2Completions","ga:goal3Completions","ga:goal4Completions","ga:goal5Completions"};
	private String[] dimensionArr = {"ga:dimension1,ga:hour,ga:minute,ga:sourceMedium,ga:campaign,ga:country,ga:pagePath"};
	private Map<String, RawDataDT> rawDataDtMap = new HashMap<String, RawDataDT>();
	private HttpServletRequest productRecommendationAnalyticsAPIRequest = null;
	private ProductRecommendationAnalyticsAPIResponse productRecommendationAnalyticsAPIResponse= null;
	private static final Logger log = Logger.getLogger(ProductRecommendationAnalyticsAPIController.class.getName());

	

	/**
	 * @param userId
	 */
	public ProductRecommendationAnalyticsAPIController(String emailAddress, String startDate, boolean localMode) 
	{
		log.info("ProductRecommendationAnalyticsAPIController Constructor userId " + userId );
		this.emailAddress = emailAddress;
		this.startDate = startDate;
		this.isLocalMode =localMode;
	}

	@Override
	public void execute() throws Exception
	{
		log.info("Start - Execute at ProductRecommendationAnalyticsAPIController " );
		try
		{
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
			Customer cust = CustomerDAO.getCustomerInformationByUserID(emailAddress);
			ProfileDT profileDT = getUserAnalyticsProfile(cust);
			setGAAccountInfo(cust);
			extractGoogleAnalyticsData(isLocalMode, profileDT.getProfileId(), startDate, endDate);

		}catch(Exception ex)
		{
			log.severe(" userId   " + userId + " profileId " + profileId + " FAILED***!!!! "+ ex.getMessage());
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
		if (cust != null)
		{
			productRecommendationAnalyticsAPIResponse.setAccountId(cust.getCustomerAnalyticList().get(0).getAccountId());
			productRecommendationAnalyticsAPIResponse.setWebProperyId(cust.getCustomerAnalyticList().get(0).getWebPropertyId());
			productRecommendationAnalyticsAPIResponse.setProfileId(cust.getCustomerAnalyticList().get(0).getProfileId());
		}
	}


	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 3, 2015
	 *@Description: Extract Google Analytics Data
	 */

	public void extractGoogleAnalyticsData(boolean isLocalMode, String profileID,String startDate, String endDate)  throws IOException, Exception
	{

		Analytics analytics = getAnalyticsService(userId, profileID);
		try 
		{
			GoogleAnalyticsDT googleAnalyticsDT = getAnalyticData(analytics);
			this.productRecommendationAnalyticsAPIResponse.getGoogleAnalyticsList().add(googleAnalyticsDT);
		} catch (Exception ex) {
			log.severe(" extractGoogleAnalyticsData was not defined for this user   " + emailAddress);
			throw ex;
		}

	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 10, 2016
	 *@Description: Get Analytics Credential
	 */

	private Analytics getAnalyticsService(String userId, String profileID) throws IOException 
	{
		log.info("getAnalyticsService Start userId " + userId + " profileId " + profileID);
		Analytics analytics = null;
		try
		{		
			analytics = CredentialUtils.loadAnalytics(userId);
		} catch (Exception ex) 
		{
			analytics = CredentialUtils.loadAnalytics(userId);
			log.severe("profileID EXCEPTION CredentialUtils.loadAnalytics(userId); - Exception!!!  " + profileID);
		}
		log.info("getAnalyticsService End userId " + userId + " profileId " + profileID);

		return analytics;
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
		List<RawDataDT> rawDataList = firstCallToGoogleAnalytics(analytics,	googleAnalyticsDT);
		List<RawDataDT> valuesToMatch = secondCallToGoogleAnalytics(analytics, rawDataList);
		aggregateAllResults(googleAnalyticsDT, valuesToMatch);
		return googleAnalyticsDT;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 8, 2016
	 *@Description: Aggregate all results into One Platform
	 */

	private void aggregateAllResults(GoogleAnalyticsDT googleAnalyticsDT, List<RawDataDT> valuesToMatch) 
	{
		for(RawDataDT rd : rawDataDtMap.values())
		{
		  valuesToMatch.add(rd);
		}
		googleAnalyticsDT.setRawDataList(valuesToMatch);
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 8, 2016
	 *@Description: First Call to Google Analytics
	 */

	private List<RawDataDT> firstCallToGoogleAnalytics(Analytics analytics, GoogleAnalyticsDT googleAnalyticsDT) throws IOException 
	{
		log.info("First Call Start ");
		log.info("First Call startDate " + startDate);
		log.info("First Call endDate " + endDate);
		List<RawDataDT> rawDataList = null;
		GaData gaData = AnalyticUtils.extractCustomReportsFromGA(analytics, profileId, metricsArr,dimensionArr, startDate, endDate);
		if (gaData != null) 
		{		
			ArrayMap<String,String> map = (ArrayMap)gaData.getTotalsForAllResults();
			for (int i = 0; i < metricsArr.length; i++) 
			{
				setAnalyticsGaData(googleAnalyticsDT, map, i);
			}	
			rawDataList = googleAnalyticsDT.getRawDataList();
			setValuesAfterFirstCall(gaData);
		}
		log.info("First Call End ");
		return rawDataList;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 8, 2016
	 *@Description: Set Values After First Call
	 */

	private void setValuesAfterFirstCall(GaData gaData) 
	{
		log.info("Start setValuesAfterFirstCall " );
		List<List<String>> gaDataRows= gaData.getRows();
		for (List<String> itemList : gaDataRows) 
		{
			//[0] - ga:dimension1, [1]- ga:hour, [2] - ga:minute, //[3]- ga:sourceMedium,[4] - ga:campaign, [5] - ga:country, [6] - ga:pagePath
			// [7] - "ga:sessions", [8] - "ga:users",[9] - "ga:goal1Completions", [10] - "ga:goal2Completions",
			//[11] - "ga:goal3Completions",[12] - "ga:goal4Completions", [13] - "ga:goal5Completions"
			String clientId = itemList.get(0);
			String hour = itemList.get(1);
			String minute = itemList.get(2);
			String key  = clientId+"@" + hour + "@" + minute;
			RawDataDT rawDataDT = getRawDataDT(key, clientId , hour, minute,itemList);
			setDimensionsValuesFirstCall(itemList, rawDataDT);
			setMetricsValues(itemList, rawDataDT);
			rawDataDtMap.put(key , rawDataDT);
			
		}
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 7, 2016
	 *@Description: Fill Raw Data List with google Analytics Results
	 */

	private void fillRawDataListWithGoogleAnalyticsResults(Analytics analytics, GoogleAnalyticsDT googleAnalyticsDT, GaData gaData) throws IOException 
	{
		
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 8, 2016
	 *@Description: Second Call for Google Analytics
	 */
	
	private List<RawDataDT> secondCallToGoogleAnalytics(Analytics analytics, List<RawDataDT> rawDataList) throws IOException 
	{
		log.info("Second Call Start ");
		log.info("Second Call startDate " + startDate);
		log.info("Second Call endDate " + endDate);
		GaData gaData;
		List<List<String>> gaDataRows;
		String[] dimensionArr = setNewDimensionForSecondCall();
		log.info("Second Call profileId " + profileId);
		log.info("Second Call metricsArr " + metricsArr);
		log.info("Second Call dimensionArr " + dimensionArr);

		gaData = AnalyticUtils.extractCustomReportsFromGA(analytics, profileId, metricsArr,dimensionArr, startDate, endDate);
		
		gaDataRows= gaData.getRows();
		setValuesAfterSecondCall(rawDataList, gaDataRows);
		//Convert to List
		List<RawDataDT> valuesToMatch=new ArrayList<RawDataDT>();
		return valuesToMatch;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 7, 2016
	 *@Description: Set New Dimension For Second Call
	 */

	private String[] setNewDimensionForSecondCall() {
		String[] dimensionArr = {"ga:dimension1,ga:hour,ga:minute,ga:browser,ga:deviceCategory,ga:landingPagePath,ga:exitPagePath"};
		return dimensionArr;
	}


	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 30, 2016
	 *@Description: Set Metrics Values from list 7 to  
	 */
	
	private void setMetricsValues(List<String> itemList, RawDataDT rawDataDT) 
	{

		rawDataDT.setSessions(itemList.get(7));
		rawDataDT.setUsers(itemList.get(8));
		rawDataDT.setGoal1Completions(itemList.get(9));
		rawDataDT.setGoal2Completions(itemList.get(10));
		rawDataDT.setGoal3Completions(itemList.get(11));
		rawDataDT.setGoal4Completions(itemList.get(12));
		rawDataDT.setGoal5Completions(itemList.get(13));
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 4, 2016
	 *@Description: Set Dimension 
	 */

	private void setDimensionsValuesFirstCall(List<String> itemList, RawDataDT rawDataDT) {
		rawDataDT.setHour(itemList.get(1));	
		rawDataDT.setMinute(itemList.get(2));				
		rawDataDT.setSourceMedium(itemList.get(3));
		rawDataDT.setCampaign(itemList.get(4));
		rawDataDT.setCountry(itemList.get(5));
		rawDataDT.setPagePath(itemList.get(6));
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 29, 2016
	 *@Description: Insert into Hashmap
	 */

	private void setValuesAfterSecondCall(List<RawDataDT> rawDataList, List<List<String>> gaDataRows) 
	{
		for (List<String> itemList : gaDataRows) 
		{
			//[0] - ga:dimension1, [1]- ga:hour, [2] - ga:minute, [3] - ga:browser, [4] - ga:deviceCategory,[5] - ga:landingPagePath, [6] - ga:exitPagePath"
			String clientId = itemList.get(0);
			String hour = itemList.get(1);
			String minute = itemList.get(2);
			String key  = clientId+"@" + hour + "@" + minute;
			RawDataDT rawDataDT = getRawDataDT(key, clientId , hour, minute,itemList);

			rawDataDT.setHour(itemList.get(1));	
			rawDataDT.setMinute(itemList.get(2));				
			rawDataDT.setBrowser(itemList.get(3));
			rawDataDT.setDeviceCategory(itemList.get(4));
			rawDataDT.setLandingPagePath(itemList.get(5));
			rawDataDT.setExitPagePath(itemList.get(6));
			setMetricsValues(itemList, rawDataDT);
			rawDataDtMap.put(key , rawDataDT);
		}
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 28, 2016
	 *@Description: Return RawDataDT from Map or create new object.
	 */

	private RawDataDT getRawDataDT(String key, String clientId, String hour, String minute, List<String> itemList) 
	{	
		RawDataDT rawDataDT = rawDataDtMap.get(key);
		if (rawDataDT == null)
		{
			rawDataDT = new RawDataDT(clientId,hour,minute);
		}		
		return rawDataDT;
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 25, 2016
	 *@Description: Set Analytics GA Header Data
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
