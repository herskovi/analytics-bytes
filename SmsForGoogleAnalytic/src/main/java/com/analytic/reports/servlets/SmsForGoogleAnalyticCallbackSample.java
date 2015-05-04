package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.datatypes.AccountDT;
import main.java.com.analytic.reports.datatypes.ProfileDT;
import main.java.com.analytic.reports.datatypes.TokenParamsDT;
import main.java.com.analytic.reports.datatypes.WebpropertiesDT;
import main.java.com.analytic.reports.jdo.PMF;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.HttpClientUtils;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AbstractAppEngineAuthorizationCodeCallbackServlet;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

public class SmsForGoogleAnalyticCallbackSample extends AbstractAppEngineAuthorizationCodeCallbackServlet 
{

	private static final String APPLICATION_NAME = "";
	private static final Logger log = Logger.getLogger(SmsForGoogleAnalyticCallbackSample.class.getName());

	@Override
	protected void onSuccess(HttpServletRequest req, HttpServletResponse resp,
			Credential credential) throws ServletException, IOException 
	{

		String authorizationCode = req.getParameter("code");
		String accessToken = credential.getAccessToken();
		String refreshToken = credential.getRefreshToken();
		String userId = getUserId(req);


		Customer cust = CustomerDAO.getCustomerInformationByUserID(userId);

		String userName = cust.getName();
		String refreshTokenFromDB = cust.getRefreshToken();
		boolean shouldUpdateRefreshTokenInDB = false;

		if (refreshToken == null || refreshToken.length() == 0) 
		{
			String url = excahngeAutorizationCodeWithRefreshToken(authorizationCode);
			resp.sendRedirect(url); // exchange
		}else 
		{
			// refreshTokenWasReceivedFromGoogleOAuth
			shouldUpdateRefreshTokenInDB = shouldUpdateRefreshTokenInDB(
					refreshToken, refreshTokenFromDB);
		}
		if (shouldUpdateRefreshTokenInDB)
			CustomerDAO.updateCustomerTokenInDB(cust, refreshToken, accessToken,	authorizationCode);

		Analytics analytic = initializeAnalytics(credential);
		log.info("initializeAnalytics completed successfuly ");
		Accounts accounts = analytic.management().accounts().list().execute();
		Gson gson = new Gson();


		// Future Enhancement - In case User has several accounts
		// boolean isAccountHasMoreThanOneProfile =
		// AnalyticUtils.isAccountHasMoreThanOneProfile(accounts);
		int numberOfAccounts = AnalyticUtils.getNumberOfAccounts(accounts);
		//String accountId = accounts.getItems().get(0).getId();
		//Display All Web Properties for the first Account
		//displayAllWebPropertiesForTheFirstAccount(accountId);

		List<AccountDT> accountList = new ArrayList<AccountDT>();
		for (int i = 0; i < numberOfAccounts; i++) 
		{
			String accountId = accounts.getItems().get(i).getId();
			String name = accounts.getItems().get(i).getName();
			AccountDT acc = new AccountDT(accountId, name);

//			sleep(1000);
//			Webproperties webproperties = analytic.management().webproperties().list(accountId).execute();
//			List<WebpropertiesDT> webPropertiesList = new ArrayList<WebpropertiesDT>();
//			int size = webproperties.getItems().size();
//
//			if (size == 0)
//			{
//				log.info("Account " +accountId +" without WebProperty ");
//				return;
//			}else
//			{
//				//Loop over the webProperty
//				for (int j=0; j<size; j++)
//				{
//					String webPropertyId = webproperties.getItems().get(j).getId();
//					String webPropertyName = webproperties.getItems().get(j).getName();
//					WebpropertiesDT webpropertiesDT = new WebpropertiesDT(accountId, webPropertyId,webPropertyName);
//
//					sleep(1000);
//					Profiles profiles = analytic.management().profiles().list(accountId, webPropertyId).execute();
//					int profileSize = profiles.getItems().size();
//					List<ProfileDT> profileList = new ArrayList<ProfileDT>();
//
//
//					//Loop over the Profiles
//
//					for (int k=0; k < profileSize; k++)
//					{
//						String profileId =  profiles.getItems().get(k).getId();
//						String profileName =  profiles.getItems().get(k).getName();
//						String timeZone =  profiles.getItems().get(k).getTimezone();
//						//DateTime updated = profiles.getItems().get(k).getUpdated(); 
//						//DateTime dateTime = new LocalDateTime().toDateTime(DateTimeZone.forID(timeZone));
//						ProfileDT profileDT = new ProfileDT(accountId,  webPropertyId, profileId,  profileName,timeZone);
//						profileList.add(profileDT);
//					}
//					webpropertiesDT.setProfileDTList(profileList);
//					webPropertiesList.add(webpropertiesDT);
//
//				}
//				acc.setWebPropertiesList(webPropertiesList);
//			}	
			accountList.add(acc);
		}
		req.getSession().setAttribute("userId", userId);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		DateUtils dateUtils = new DateUtils(cal);
		req.getSession().setAttribute("yesterdayDate", dateUtils.getDateStringWithDayName());
		

		req.getSession().setAttribute("userName", userName);
		req.getSession().setAttribute("accountNameList", accountList);

		String json = gson.toJson(accountList);
		req.getSession().setAttribute("accountsJson", json);
		req.getSession().setAttribute("accounts", accountList);


		String [] availableTimeZoneIDs = TimeZone.getAvailableIDs();
		narrowNumberOfAvailableTimeZoneIDs(availableTimeZoneIDs);
		String availableTimeZoneIDsJson = gson.toJson(availableTimeZoneIDs);
		req.setAttribute("availableTimeZoneIDs", availableTimeZoneIDs);
		req.setAttribute("availableTimeZoneIDsJson", availableTimeZoneIDsJson);


		//getServletContext().getRequestDispatcher("/accountSelection.jsp").forward(req, resp);
		getServletContext().getRequestDispatcher("/accountSelectionAfterRegistration.jsp").forward(req, resp);


	}

	
	



	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 28, 2015
	 *@Description:
	 */
	private void narrowNumberOfAvailableTimeZoneIDs(String[] availableTimeZoneIDs) 
	{
		// TODO Auto-generated method stub
		
	}






	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 17, 2014
	 *@Description: 
	 */
	public void sleep(long seconds) 
	{
		try 
		{
			Thread.sleep(seconds);
		} catch (InterruptedException e) 
		{
			log.severe(e.getMessage());		
		}
	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Should Update Refresh Token In Customer DB
	 */
	private boolean shouldUpdateRefreshTokenInDB(String refreshToken,
			String refreshTokenFromDB) {
		if (refreshToken != null && refreshToken.equals(refreshTokenFromDB)) {
			return false;
		}

		return true;
	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Call to accounts.google.com/o/oauth2/token with
	 *               grant_type=authorization_code
	 */
	private String excahngeAutorizationCodeWithRefreshToken(
			String authorizationCode) {

		String url2 = "https://accounts.google.com/o/oauth2/token?"
				+ "code="
				+ authorizationCode
				+ "&"
				+ "client_id="
				+ CredentialConsts.CLIENT_ID
				+ "&"
				+ "client_secret="
				+ CredentialConsts.CLIENT_SECRET
				+ "&"
				+ "redirect_uri=http%3A%2F%2Fdailyreportbysmsforga.appspot.com%2Fsmsforgoogleanalyticcallbacksample&"
				+ "grant_type=authorization_code";

		TokenParamsDT tokenParamsDT = new TokenParamsDT();
		// URl
		String url = "https://accounts.google.com/o/oauth2/token";
		tokenParamsDT.setUrl(url);
		// Code
		tokenParamsDT.setCode(authorizationCode);
		// Client_Id
		tokenParamsDT.setClientId(CredentialConsts.CLIENT_ID);
		// Client_Secrey
		tokenParamsDT.setClientSecret(CredentialConsts.CLIENT_SECRET);
		// Redirect URI
		tokenParamsDT.setRedirectUri(CredentialConsts.REDIRECT_URI);
		// Grant Type
		tokenParamsDT.setGrantType("authorization_code");
		HttpClientUtils.invokeHttpRequestPost(tokenParamsDT);

		return url2;

	}


	@Override
	protected void onError(HttpServletRequest req, HttpServletResponse resp,
			AuthorizationCodeResponseUrl errorResponse)
					throws ServletException, IOException {
		// handle error
//		String nickname = UserServiceFactory.getUserService().getCurrentUser()
//				.getNickname();
		getServletContext().getRequestDispatcher("/registerToSMSGA.jsp").forward(req, resp);
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req)
			throws ServletException, IOException {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/smsforgoogleanalyticcallbacksample");
		return url.build();
	}

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws IOException {
		return new GoogleAuthorizationCodeFlow.Builder(new UrlFetchTransport(),
				new JacksonFactory(), CredentialConsts.CLIENT_ID,
				CredentialConsts.CLIENT_SECRET,
				Collections.singleton(AnalyticsScopes.ANALYTICS_READONLY))
		.setCredentialStore(new AppEngineCredentialStore())
		.setAccessType("offline").build();

		// return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
		// JSON_FACTORY,
		// getClientCredential(),
		// Collections.singleton(BigqueryScopes.BIGQUERY)).setCredentialStore(
		// new AppEngineCredentialStore()).setAccessType("offline").build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException,
	IOException {

		//String userName = UserServiceFactory.getUserService().getCurrentUser().getUserId();
		String uniqueUserID = "";
		String googleAnalyticEmailAddress = req.getParameter("state");

		if (googleAnalyticEmailAddress != null) 
		{
			uniqueUserID = googleAnalyticEmailAddress;
		} else 
		{
			uniqueUserID = CredentialConsts.CLIENT_ID;
		}
		log.info("SmsForGoogleAnalyticCallbackSample User ID is " + uniqueUserID );
		//		} else if (userName != null) {
		//			uniqueUserID = userName;
		//
		return uniqueUserID;
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

	

	/**
	 ** 
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Get Customer Informatio nBy User ID
	 */

	public void updateCustomerWithRefreshToken(Customer cust,String refreshToken) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Customer customer = pm.getObjectById(Customer.class,
					cust.getUserId());
			customer.setRefreshToken(refreshToken);

		} finally {
			pm.close();
		}

	}

}