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
	private String userId ="";
	private String profileId ="";
	private String emailAddress ="";
	//private String startDate = "yesterday";
	private String startDate = "2016-06-01";
	private String endDate = "today";
	private String[] metricsArr = {"ga:metric1","ga:sessions","ga:users","ga:goal1Completions","ga:goal2Completions","ga:goal3Completions","ga:goal4Completions","ga:goal5Completions"};
	private String[] dimensionArr = {"ga:dimension1,ga:hour,ga:minute,ga:sourceMedium,ga:campaign,ga:country,ga:pagePath"};
	private Map<String, RawDataDT> rawDataDtMap = new HashMap<String, RawDataDT>();
	private HttpServletRequest productRecommendationAnalyticsAPIRequest = null;
	private ProductRecommendationAnalyticsAPIResponse productRecommendationAnalyticsAPIResponse= null;
	private static final Logger log = Logger.getLogger(ProductRecommendationAnalyticsAPIController.class.getName());


	

	

	/**
	 * @param userId
	 */
	public ProductRecommendationAnalyticsAPIController(String emailAddress) 
	{
		log.info("ProductRecommendationAnalyticsAPIController Constructor userId " + userId );
		this.emailAddress = emailAddress;
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
			Customer cust = CustomerDAO.getCustomerInformationByUserID(emailAddress);
			ProfileDT profileDT = getUserAnalyticsProfile(cust);
			setGAAccountInfo(cust);
			extractGoogleAnalyticsData(isLocalMode, emailAddress,	profileDT.getProfileId(), startDate, endDate);

		}catch(Exception ex)
		{
			log.severe(" userId   " + userId + " profileId " + profileId + " FAILED***!!!! "+ ex.getStackTrace().toString());
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
	 *@Description: It is time to send SMS for Subscriber
	 */

	public void extractGoogleAnalyticsData(boolean isLocalMode, String userId, String profileID,String startDate, String endDate)  throws IOException, Exception
	{

		Analytics analytics = getAnalyticsService(userId, profileID);
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

	private Analytics getAnalyticsService(String userId, String profileID) throws IOException 
	{
		log.info("getAnalyticsService Start userId " + userId + "profileId" + profileID);
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
		GaData gaData = AnalyticUtils.extractCustomReportsFromGA(analytics, profileId, metricsArr,dimensionArr, startDate, endDate);
		
		fillRawDataListwithGoogleAnalyticsResults(analytics, googleAnalyticsDT,
				gaData);
		return googleAnalyticsDT;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 7, 2016
	 *@Description: Fill Raw Data List with google Analytics Results
	 */

	private void fillRawDataListwithGoogleAnalyticsResults(Analytics analytics, GoogleAnalyticsDT googleAnalyticsDT, GaData gaData) throws IOException 
	{
		
		log.info("fillRawDataListwithGoogleAnalyticsResults Start userId " + userId + "gaData" + gaData);
		if (gaData != null) 
		{
			
			ArrayMap<String,String> map = (ArrayMap)gaData.getTotalsForAllResults();
				for (int i = 0; i < metricsArr.length; i++) 
				{
					setAnalyticsGaData(googleAnalyticsDT, map, i);
				}
		
				
			List<RawDataDT> rawDataList = googleAnalyticsDT.getRawDataList();
			List<List<String>> gaDataRows= gaData.getRows();
			
			setValuesFirststCall(rawDataList, gaDataRows);
			
			String[] dimensionArr = {"ga:dimension1,ga:hour,ga:minute,ga:browser,ga:deviceCategory,ga:landingPagePath,ga:exitPagePath"};
			gaData = AnalyticUtils.extractCustomReportsFromGA(analytics, profileId, metricsArr,dimensionArr, startDate, endDate);
			gaDataRows= gaData.getRows();
			setValuesSecondCall(rawDataList, gaDataRows);
			//Convert to List
			List<RawDataDT> valuesToMatch=new ArrayList<RawDataDT>();
			for(RawDataDT rd : rawDataDtMap.values()){
			  valuesToMatch.add(rd);
			}
			googleAnalyticsDT.setRawDataList(valuesToMatch);
		}
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 29, 2016
	 *@Description: Insert into Hashmap
	 */

	private void setValuesFirststCall(List<RawDataDT> rawDataList, List<List<String>> gaDataRows) 
	{
		for (List<String> itemList : gaDataRows) 
		{
			//[0] - ga:dimension1, [1]- ga:hour, [2] - ga:minute, //[3]- ga:sourceMedium,[4] - ga:campaign, [5] - ga:country, [6] - ga:pagePath
			//[7] - ga:metric1", [8] - "ga:sessions", [9] - "ga:users",[10] - "ga:goal1Completions", [11] - "ga:goal2Completions",
			//[12] - "ga:goal3Completions",[13] - "ga:goal4Completions", [14] - "ga:goal5Completions"
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
	 *@Date:        May 30, 2016
	 *@Description: Set Metrics Values from list 7 to  
	 */
	
	private void setMetricsValues(List<String> itemList, RawDataDT rawDataDT) 
	{
		rawDataDT.setMetric1(itemList.get(7));
		rawDataDT.setSessions(itemList.get(8));
		rawDataDT.setUsers(itemList.get(9));
		rawDataDT.setGoal1Completions(itemList.get(10));
		rawDataDT.setGoal2Completions(itemList.get(11));
		rawDataDT.setGoal3Completions(itemList.get(12));
		rawDataDT.setGoal4Completions(itemList.get(13));
		rawDataDT.setGoal5Completions(itemList.get(14));
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

	private void setValuesSecondCall(List<RawDataDT> rawDataList, List<List<String>> gaDataRows) 
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
