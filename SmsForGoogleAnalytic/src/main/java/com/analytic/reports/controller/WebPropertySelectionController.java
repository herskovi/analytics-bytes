/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Webproperties;
import com.google.gson.Gson;

import main.java.com.analytic.reports.controller.response.WebPropertyResponse;
import main.java.com.analytic.reports.datatypes.WebpropertiesDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;

/**
 * @author admin
 * Aug 31, 2014
 */
public class WebPropertySelectionController extends BaseController
{
	private WebpropertiesDT webpropertiesDT = null;
	private String userId= "";
	private IResponse webpropertiesResponse = newResponse();
	private static final Logger log = Logger.getLogger(WebPropertySelectionController.class.getName());
	private static final String APPLICATION_NAME = "";



	public WebPropertySelectionController(WebpropertiesDT webpropertiesDT, String userId)
	{
		this.userId= userId;
		this.webpropertiesDT = webpropertiesDT;
	}


	@Override
	public void execute() throws Exception
	{		
		
		AuthorizationCodeFlow flow = initializeFlow();
	    Credential credential = flow.loadCredential(userId);
	 	Analytics analytic = initializeAnalytics(credential);
		Webproperties webproperties = analytic.management().webproperties().list(webpropertiesDT.getAccountId()).execute();
		//Gson gson = new Gson();

		List<WebpropertiesDT> webPropertiesList = new ArrayList<WebpropertiesDT>();
		int size = webproperties.getItems().size();

		if (size == 0)
		{
			log.info("Account " +webpropertiesDT.getAccountId() +" without WebProperty ");
			return;
		}else
		{
			//Loop over the webProperty
			for (int j=0; j<size; j++)
			{
				String webPropertyId = webproperties.getItems().get(j).getId();
				String webPropertyName = webproperties.getItems().get(j).getName();
				WebpropertiesDT webpropertiesSinglePropert = new WebpropertiesDT(webpropertiesDT.getAccountId(), webPropertyId,webPropertyName);
				webPropertiesList.add(webpropertiesSinglePropert);
			}
		}	
		
		((WebPropertyResponse)webpropertiesResponse).setAccountId(webpropertiesDT.getAccountId());
		((WebPropertyResponse)webpropertiesResponse).setWebPropertiesList(webPropertiesList);
			

	}

	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new WebPropertyResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.webpropertiesResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		webpropertiesResponse.setMessage(textMessage);	
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
