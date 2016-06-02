package main.java.com.analytic.reports.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import main.java.com.analytic.reports.utils.CredUtils.CodeExchangeException;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialStore;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStore;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.prediction.Prediction;
import com.google.api.services.prediction.PredictionScopes;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/*
 * CredentialUtils.java provides helper methods for generating a callback URI, handling
 * an API authorization code flow, and providing an authorized Analytic API client.
 */
public class CredentialUtils {

	static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	static final String RESOURCE_LOCATION = "WEB-INF/client_secret.json";
	private static GoogleClientSecrets clientSecrets = null;
	private static final String KIND = AppEngineCredentialStore.class.getName();
	

	private static GoogleAuthorizationCodeFlow flow = null;

	/**
	 * Exception thrown when an error occurred while retrieving credentials.
	 */
	public static class GetCredentialsException extends Exception {

		protected String authorizationUrl;

		/**
		 * Construct a GetCredentialsException.
		 * 
		 * @param authorizationUrl
		 *            The authorization URL to redirect the user to.
		 */
		public GetCredentialsException(String authorizationUrl) {
			this.authorizationUrl = authorizationUrl;
		}

		/**
		 * Set the authorization URL.
		 */
		public void setAuthorizationUrl(String authorizationUrl) {
			this.authorizationUrl = authorizationUrl;
		}

		/**
		 * @return the authorizationUrl
		 */
		public String getAuthorizationUrl() {
			return authorizationUrl;
		}
	}

	/**
	 * Exception thrown when a code exchange has failed.
	 */
	public static class CodeExchangeException extends GetCredentialsException {

		/**
		 * Construct a CodeExchangeException.
		 * 
		 * @param authorizationUrl
		 *            The authorization URL to redirect the user to.
		 */
		public CodeExchangeException(String authorizationUrl) {
			super(authorizationUrl);
		}

	}

	/**
	 * Exception thrown when no refresh token has been found.
	 */
	public static class NoRefreshTokenException extends GetCredentialsException {

		/**
		 * Construct a NoRefreshTokenException.
		 * 
		 * @param authorizationUrl
		 *            The authorization URL to redirect the user to.
		 */
		public NoRefreshTokenException(String authorizationUrl) {
			super(authorizationUrl);
		}

	}

	/**
	 * Exception thrown when no user ID could be retrieved.
	 */
	private static class NoUserIdException extends Exception {
	}

	static String getRedirectUri(HttpServletRequest req) {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}

	public static GoogleClientSecrets getClientSecret() throws IOException {
		if (clientSecrets == null) {
			InputStream inputStream = new FileInputStream(new File(
					RESOURCE_LOCATION));
			Reader reader = new InputStreamReader(inputStream);

			Preconditions.checkNotNull(inputStream, "Cannot open: %s"
					+ RESOURCE_LOCATION);
			clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
		}
		return clientSecrets;
	}

	public static GoogleAuthorizationCodeFlow newFlow() throws IOException {
		
		return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
				JSON_FACTORY, getClientSecret(),
				Collections.singleton(AnalyticsScopes.ANALYTICS_READONLY))
				.setCredentialStore(

				new AppEngineCredentialStore()).setAccessType("offline")
				.build();
	}
	
	public static GoogleAuthorizationCodeFlow newFlowForStorageService() throws IOException {
		
		return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
				JSON_FACTORY, getClientSecret(),
				Collections.singleton(StorageScopes.CLOUD_PLATFORM))
				.setCredentialStore(

				new AppEngineCredentialStore()).setAccessType("offline")
				.build();
	}
	
	public static GoogleAuthorizationCodeFlow newFlowForPredictionService() throws IOException {
		
		return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
				JSON_FACTORY, getClientSecret(),
				Collections.singleton(PredictionScopes.PREDICTION))
				.setCredentialStore(
				new AppEngineCredentialStore()).setAccessType("offline")
				.build();
	}

//	public static Analytics loadAnalytics() throws IOException 
//	{
//		String userId = UserServiceFactory.getUserService().getCurrentUser()
//				.getUserId();
//		return loadAnalytics(userId);
//	}
	
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 1, 2016
	 *@Description: Load Analytics Service
	 */

	public static Analytics loadAnalytics(String userId) throws IOException 
	{
		Credential credential = newFlow().loadCredential(userId);
		return new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.build();
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 2, 2016
	 *@Description: Load Google Cloud Service Storage
	 */
	
	public static Storage loadStorage(String userId) throws IOException 
	{
		Credential credential = newFlowForStorageService().loadCredential(userId);
		return new Storage.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.build();
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 2, 2016
	 *@Description: Load Google Cloud Service Storage
	 */
	
	public static Prediction loadPrediction(String userId) throws IOException 
	{
		Credential credential = newFlowForPredictionService().loadCredential(userId);
		return new Prediction.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.build();
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 13, 2014
	 * @Description:
	 */

	public boolean load(String userId, Credential credential) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key key = KeyFactory.createKey(KIND, userId);
		try {
			Entity entity = datastore.get(key);
			credential.setAccessToken((String) entity
					.getProperty("accessToken"));
			credential.setRefreshToken((String) entity
					.getProperty("refreshToken"));
			credential.setExpirationTimeMilliseconds((Long) entity
					.getProperty("expirationTimeMillis"));
			return true;
		} catch (Exception exception) {
			return false;
		}

	}

	/**
	 * Exchange an authorization code for OAuth 2.0 credentials.
	 * 
	 * @param authorizationCode
	 *            Authorization code to exchange for OAuth 2.0 credentials.
	 * @return OAuth 2.0 credentials.
	 * @throws CodeExchangeException
	 *             An error occurred.
	 */
	public static Credential exchangeCode(String authorizationCode)
			throws CodeExchangeException 
	{
		try {
			GoogleAuthorizationCodeFlow flow = newFlow();
			GoogleTokenResponse response = flow
					.newTokenRequest(authorizationCode)
					.setRedirectUri(CredentialConsts.REDIRECT_URI).execute();
			return flow.createAndStoreCredential(response, null);
		} catch (IOException e) {
			System.err.println("An error occurred: " + e);
			throw new CodeExchangeException(null);
		}
	}

	/**
	 * @throws IOException
	 * @Author: Moshe Herskovits
	 * @Date: Jun 15, 2014
	 * @Description:
	 */
	public Credential loadCredential(String userId) throws IOException 
	{
		
		return newFlow().loadCredential(userId);

	}

	/**
	 * Builds an empty credential object.
	 * 
	 * @return An empty credential object.
	 */
	public Credential buildEmpty()
	{
		
		return new GoogleCredential.Builder()
				.setClientSecrets(this.clientSecrets)
				.setTransport(HTTP_TRANSPORT).setJsonFactory(JSON_FACTORY)
				.build();
	}
	
	/**
	 * Get credential datastore.
	 * 
	 * @return An empty credential object.
	 * @throws IOException 
	 */
	public static boolean checkIfUserExistInDataStore(String userId) throws IOException
	{
		StoredCredential storedCredential = null;
		CredentialStore credentialStore = null;
		boolean checkIfUserExistInDataStore = false;
		GoogleAuthorizationCodeFlow flow = newFlow();
		if (flow != null)
		{
			credentialStore = flow.getCredentialStore();
			if (credentialStore!= null)
			{
				 Credential credential = flow.loadCredential(userId);
				 checkIfUserExistInDataStore = credentialStore.load(userId, credential);
			}
			
		}
		return checkIfUserExistInDataStore;
	}
	
	
	
	
	 

}
