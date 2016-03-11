/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.GoalResponse;
import main.java.com.analytic.reports.controller.response.MetricValueResponse;
import main.java.com.analytic.reports.controller.response.ProfileResponse;
import main.java.com.analytic.reports.datatypes.GoalDT;
import main.java.com.analytic.reports.datatypes.MetricDT;
import main.java.com.analytic.reports.datatypes.ProfileDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.FormatterUtils;
import main.java.com.analytic.reports.utils.consts.AnalyticConsts;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ArrayMap;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Goals;
import com.google.api.services.analytics.model.Profiles;

/**
 * @author admin
 * Aug 31, 2014
 */
public class MetricsValueBeforeSubmittionController extends BaseController
{
	private ProfileDT profileDT = null;
	private String userId= "";
	String[] metricsDefaultArr = null;
	private IResponse metricValueResponse = newResponse();
	private static final Logger log = Logger.getLogger(MetricsValueBeforeSubmittionController.class.getName());
	private static final String APPLICATION_NAME = "";



	public MetricsValueBeforeSubmittionController(ProfileDT metricDT, String userId, String[] metricsDefaultArr)
	{
		this.userId= userId;
		this.profileDT = metricDT;
		this.metricsDefaultArr = metricsDefaultArr;
		
	}


	@Override
	public void execute() throws Exception
	{		
		AuthorizationCodeFlow flow = initializeFlow();
	    Credential credential = flow.loadCredential(userId);
	 	Analytics analytic = initializeAnalytics(credential);
	 	String startDate = DateUtils.getYesterdayDateString(Calendar.getInstance());
	 	String endDate = startDate;
	 	GaData gaData = getAllMetricsFromAnalyticsAPI(analytic,startDate,endDate);
	 	handleGaDataMetricsResponse(gaData);	
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 15, 2014
	 *@Description: Get All Goals From Management API
	 */

	private GaData getAllMetricsFromAnalyticsAPI(Analytics analytics, String startDate, String endDate)
			throws IOException 
	{
		GaData gaData = AnalyticUtils.executeDataQuery(analytics, profileDT.getProfileId(),metricsDefaultArr,startDate, endDate); 
		return gaData;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 15, 2014
	 *@Description: Handle Goal Response
	 */


	private void handleGaDataMetricsResponse(GaData gaData) 
	{
		ArrayMap<String,String> map = (ArrayMap)gaData.getTotalsForAllResults();
		String key = "";
		String value="";		
		List<MetricDT> metricsList = new ArrayList<MetricDT>();
		for (int i = 0; i < metricsDefaultArr.length; i++) 
		{
			key = metricsDefaultArr[i];  //i.e. ga:sessions, ga:users,etc..
			value = map.get(key);
			MetricDT metricValueDT = new MetricDT(profileDT.getAccountId(),profileDT.getWebPropertyId(),profileDT.getProfileId());
			metricValueDT.setMetricName(key);
			metricValueDT.setMetricValue(FormatterUtils.convertStringtoNumbersWithCommaSeparated(value));
			metricsList.add(metricValueDT);
		}
				
		((MetricValueResponse)metricValueResponse).setAccountId(profileDT.getAccountId());
		((MetricValueResponse)metricValueResponse).setWebProperyId(profileDT.getWebPropertyId());
		((MetricValueResponse)metricValueResponse).setProfileId(profileDT.getProfileId());
		((MetricValueResponse)metricValueResponse).setMetricsList(metricsList);
	}

	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new MetricValueResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.metricValueResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		metricValueResponse.setMessage(textMessage);	
	}
	
	protected AuthorizationCodeFlow initializeFlow() throws IOException 
	{
		return new GoogleAuthorizationCodeFlow.Builder(new UrlFetchTransport(),
				new JacksonFactory(), CredentialConsts.CLIENT_ID,
				CredentialConsts.CLIENT_SECRET,
				Collections.singleton(AnalyticsScopes.ANALYTICS_READONLY))
		.setCredentialStore(new AppEngineCredentialStore())
		.setAccessType("offline").build();

	}


	/**
	 * Performs all necessary setup steps for running requests against the API.
	 * 
	 * @param credential
	 * 
	 * @return An initialized Analytics service object.
	 * 
	 * @throws Exception
	 *             if an issue occurs with OAuth2Native authorize.
	 */
	private static Analytics initializeAnalytics(Credential credential) {

		return new Analytics.Builder(new UrlFetchTransport(),
				new JacksonFactory(), credential).setApplicationName(
						APPLICATION_NAME).build();
	}





}
