/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.GoalResponse;
import main.java.com.analytic.reports.controller.response.ProfileResponse;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;
import main.java.com.anaytic.reports.datatypes.GoalDT;
import main.java.com.anaytic.reports.datatypes.ProfileDT;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Goals;
import com.google.api.services.analytics.model.Profiles;

/**
 * @author admin
 * Aug 31, 2014
 */
public class GoalSelectionController extends BaseController
{
	private GoalDT goalDT = null;
	private String userId= "";
	private IResponse goalResponse = newResponse();
	private static final Logger log = Logger.getLogger(GoalSelectionController.class.getName());
	private static final String APPLICATION_NAME = "";



	public GoalSelectionController(GoalDT goalDT, String userId)
	{
		this.userId= userId;
		this.goalDT = goalDT;
	}


	@Override
	public void execute() throws Exception
	{		
		AuthorizationCodeFlow flow = initializeFlow();
	    Credential credential = flow.loadCredential(userId);
	 	Analytics analytic = initializeAnalytics(credential);
	 	Goals goals = getAllGoalsFromManagementAPI(analytic);
	 	handleGoalResponse(goals);	
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 15, 2014
	 *@Description: Get All Goals From Management API
	 */

	private Goals getAllGoalsFromManagementAPI(Analytics analytic)
			throws IOException {
		Goals goals  = analytic.management().goals().list(goalDT.getAccountId(), goalDT.getWebPropertyId(),goalDT.getProfileId()).execute();
		return goals;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 15, 2014
	 *@Description: Handle Goal Response
	 */


	private void handleGoalResponse(Goals goals) 
	{
		List<GoalDT> goalList = new ArrayList<GoalDT>();
		int size = goals.getItems().size();

		if (size == 0)
		{
			log.severe("Account " +goalDT.getAccountId() + "WebProperty " + goalDT.getWebPropertyId() + " ProfileId " + goalDT.getProfileId() + " without Goals ");
			return;
		}else
		{
			//Loop over the webProperty
			for (int j=0; j<size; j++)
			{
				String goalId = goals.getItems().get(j).getId();
				String goalName = goals.getItems().get(j).getName();
				GoalDT goalDTObj = new GoalDT(goalDT.getAccountId(), goalDT.getWebPropertyId(),goalDT.getProfileId(), goalId,goalName);
				goalList.add(goalDTObj);
			}
		}	
		
		((GoalResponse)goalResponse).setAccountId(goalDT.getAccountId());
		((GoalResponse)goalResponse).setWebProperyId(goalDT.getWebPropertyId());
		((GoalResponse)goalResponse).setProfileId(goalDT.getProfileId());
		((GoalResponse)goalResponse).setGoalList(goalList);
	}

	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new GoalResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.goalResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		goalResponse.setMessage(textMessage);	
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
