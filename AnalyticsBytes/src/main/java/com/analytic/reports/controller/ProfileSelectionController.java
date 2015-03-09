/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.ProfileResponse;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;
import main.java.com.anaytic.reports.datatypes.ProfileDT;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Profiles;

/**
 * @author admin
 * Aug 31, 2014
 */
public class ProfileSelectionController extends BaseController
{
	private ProfileDT profileDT = null;
	private String userId= "";
	private IResponse profileResponse = newResponse();
	private static final Logger log = Logger.getLogger(ProfileSelectionController.class.getName());
	private static final String APPLICATION_NAME = "";



	public ProfileSelectionController(ProfileDT profileDT, String userId)
	{
		this.userId= userId;
		this.profileDT = profileDT;
	}


	@Override
	public void execute() throws Exception
	{		
		
		AuthorizationCodeFlow flow = initializeFlow();
	    Credential credential = flow.loadCredential(userId);
	 	Analytics analytic = initializeAnalytics(credential);
		Profiles profiles = analytic.management().profiles().list(profileDT.getAccountId(), profileDT.getWebPropertyId()).execute();
		
		List<ProfileDT> profileList = new ArrayList<ProfileDT>();
		int size = profiles.getItems().size();

		if (size == 0)
		{
			log.severe("Account " +profileDT.getAccountId() + "WebProperty " + profileDT.getWebPropertyId() + " without Profile ");
			return;
		}else
		{
			//Loop over the webProperty
			for (int j=0; j<size; j++)
			{
				String profileId = profiles.getItems().get(j).getId();
				String profileName = profiles.getItems().get(j).getName();
				ProfileDT prfoileDT = new ProfileDT(profileDT.getAccountId(), profileDT.getWebPropertyId(),profileId,profileName);
				profileList.add(prfoileDT);
			}
		}	
		
		((ProfileResponse)profileResponse).setAccountId(profileDT.getAccountId());
		((ProfileResponse)profileResponse).setWebProperyId(profileDT.getWebPropertyId());
		((ProfileResponse)profileResponse).setProfileList(profileList);
			

	}

	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new ProfileResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.profileResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		profileResponse.setMessage(textMessage);	
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
