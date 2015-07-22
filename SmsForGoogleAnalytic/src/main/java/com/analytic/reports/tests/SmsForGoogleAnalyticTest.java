package main.java.com.analytic.reports.tests;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.datatypes.GoalDT;
import main.java.com.analytic.reports.servlets.DailySmsServlet;
import main.java.com.analytic.reports.servlets.SmsForGoogleAnalyticCallbackSample;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.FormatterUtils;
import main.java.com.analytic.reports.utils.consts.AnalyticConsts;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;
import main.java.com.analytic.reports.utils.consts.NexmoConsts;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.junit.Before;
import org.mockito.Mockito;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.testing.http.apache.MockHttpClient;
import com.google.api.client.util.ArrayMap;
import com.google.api.services.analytics.model.GaData;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SmsForGoogleAnalyticTest
    extends TestCase
{
	private static HttpUriRequest request;
	MockHttpClient mockHttpClient =null;
	private static final Logger log = Logger.getLogger(SmsForGoogleAnalyticTest.class.getName());
	private static final String KIND = AppEngineCredentialStore.class.getName();


	
	@Before
	public static void before()
	{
		MockHttpClient mockHttpClient = createMockHttpClient();
			
			
	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SmsForGoogleAnalyticTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        
    	return new TestSuite( SmsForGoogleAnalyticTest.class );
        
    }

    /**
     * Rigourous Test :-)
     */
    public void testSmsForGoogleAnalyticTest()
    {
    	testDoGetForSmsForGoogleAnalyticServlet(mockHttpClient);
        assertTrue( true );
    }

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 29, 2014
	 *@Description:
	 */
	private void testDoGetForSmsForGoogleAnalyticServlet(MockHttpClient mockHttpClient)
	{
		
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 29, 2014
	 *@Description: return Mock of Http Client
	 */
	private static MockHttpClient createMockHttpClient() {
		return new MockHttpClient();
	}
	
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        June 08, 2014
	 *@Description: revoke specific token from Google 
	 */
	@org.junit.Test
	 public void testRevokeTokenFromOAuth2()
    {
		String tokenToRevoke = "1/qBhFIO3t5jtW2J-z33MeZyMkgfKfdmcJoh1FzpUDzCkMEudVrK5jSpoR30zcRFq6";
    	boolean isrevokeSuccessful = revokeGoogleAnalyticServer(tokenToRevoke);
        assertTrue(isrevokeSuccessful);
    }
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        June 08, 2014
	 *@Description: revoke specific token from Google 
	 */
	@org.junit.Test
	 public void testGoalPerentage()
    {
		double sessions = 25902;
		double goalValue = 694;
		double results = (goalValue/sessions)*100;		
		double rounded = AnalyticUtils.roundGoalPercentage(results);
		System.out.println(results);
		System.out.println(rounded);
	
        assertTrue(results - rounded < 0.01);
    }
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        June 08, 2014
	 *@Description: revoke specific token from Google 
	 */
	@org.junit.Test
	 public void testGetTokenFromCredentialStoreByUserId()
    {
		String userId = "herskovi77@gmail.com";
    	String refreshToken = getRefreshToken(userId);
    	System.out.println(refreshToken);
        assertTrue(refreshToken!=null ? true :false);
    }

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 16, 2014
	 *@Description: get Refresh Token From Credential Store
	 */
	private String getRefreshToken(String userId) 
	{
		return loadRefreshTokenFromStore(userId);	
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 8, 2014
	 *@Description:
	 */
	private boolean revokeGoogleAnalyticServer(String tokenToRevoke) 
	{
		HttpClient client = new DefaultHttpClient();
	    HttpPost post = null;
	    HttpResponse response = null;

		post = new HttpPost( CredentialConsts.REVOKE_TOKEN_URL);

        List<NameValuePair> nvps = populateHttpParametersToRevoke(tokenToRevoke);
        try 
        {
					
        	post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        	response = client.execute(post);
        } catch (Exception e) 
        {
        	log.severe("failed in SmsForGoogleAnalyticTest class, revokeGoogleAnalyticServer method " + e.getMessage());
        	return false;
		}
        return true;
	

		
	}
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 8, 2014
	 *@Description: Populate Http Parameters To Revoke Token from OAuth Server
	 */
	
	public  List<NameValuePair> populateHttpParametersToRevoke(String token) 
	{
	
		List <NameValuePair> nvps = new ArrayList <NameValuePair>(1);
		nvps.add(new BasicNameValuePair(CredentialConsts.TOKEN, token));
		return nvps;
	}
	
	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 13, 2014
	 * @Description:
	 */

	public String loadRefreshTokenFromStore(String userId) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key key = KeyFactory.createKey(KIND, userId);
		try {
			Entity entity = datastore.get(key);
			String accessToken = (String) entity.getProperty("accessToken");
			String refreshToken = (String) entity.getProperty("refreshToken");
			Long expirationTimeMillis = ((Long) entity.getProperty("expirationTimeMillis"));
			return refreshToken;
		} catch (Exception exception) {
			return null;
		}

	}
	
	@org.junit.Test
	public void testDateFormatterForDisplay()
	{
		Calendar systemDateCal =  Calendar.getInstance();
		systemDateCal.add(Calendar.DATE, -1); 
		DateUtils dateUtils = new DateUtils(systemDateCal);
		String startDate = dateUtils.getDateStringWithDayName();
		assertEquals("Monday, 15 Dec 2014", startDate.trim());

	}
	
	@org.junit.Test
	public void testGoalValueIsRounded()
	{
		
		double value = 3.2222344545;		
		double x = AnalyticUtils.roundTo2Decimals(value);
		assertEquals(x, 3.22);
	}
	
	@org.junit.Test
	public void testGoalValue()
	{
		String goalValue = "18";
		double numberOfSessionsValue = 1800;	
		double goalInPercentageOfSessions = getGoalValue (goalValue, numberOfSessionsValue);
		assertEquals(goalInPercentageOfSessions, 0.01 );
	}

	private double getGoalValue(String goalValue , double numberOfSessionsValue) {
	
		double goalInPercentageOfSessions = AnalyticUtils.getGoalValueProportion(goalValue, numberOfSessionsValue);
		return goalInPercentageOfSessions;
	}
	
	@org.junit.Test
	public void testGoalValueInPercentage()
	{
		
		String goalValue = "18";
		double numberOfSessionsValue = 1800;
		double value = getGoalValue(goalValue, numberOfSessionsValue);
		value = AnalyticUtils.roundTo2Decimals(value)*100;
		assertEquals(value,1.0);
	}
	
	@org.junit.Test
	public void testPrintGaDataWithoutGoalName()
	{
		GaData gaData = prepareGaData();
		GoalDT goalDT = prepeateGoalDTData();
		String[] metricsArr = prepareMetricsArrData();
		String expectedTxt = "100 users visited your website in 130 sessions.";
		String actualTxt = "";

//		Mockito gaDataMock = new Mockito();
//		gaDataMock.mock(GaData.class);
//		gaDataMock.stub(gaData.getProfileInfo().getProfileName()).toReturn("Web Analtycs");		 
			

		try {
			actualTxt = AnalyticUtils.printGaData(gaData, metricsArr, goalDT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String goalValue = "18";
//		double numberOfSessionsValue = 1800;
//		double value = getGoalValue(goalValue, numberOfSessionsValue);
//		value = AnalyticUtils.roundTo2Decimals(value)*100;
		assertEquals(expectedTxt,actualTxt);
	}
	
	@org.junit.Test
	public void testIfSMSTextIsASCIICode()
	{
		String isSMSTextOnlyFromAsciiCode = "Hello Zvika Jerbi, On Sunday, 19 Apr 2015 , 3,019 users visited your website in 3,389 sessions. 290 (8.56%) sessions reached your goal of 'Confirmation (Sale)' to stop, email service@analyticsbytes.com";
		assertTrue(FormatterUtils.isPureAscii(isSMSTextOnlyFromAsciiCode));
	}
	
//	@org.junit.Test
//	public void testYesterdayString()
//	{
//		String isSMSTextOnlyFromAsciiCode = "Hello Zvika Jerbi, On Sunday, 19 Apr 2015 , 3,019 users visited your website in 3,389 sessions. 290 (8.56%) sessions reached your goal of 'Confirmation (Sale)' to stop, email service@analyticsbytes.com";
//		assertTrue(FormatterUtils.isPureAscii(isSMSTextOnlyFromAsciiCode));
//	}
	
	@org.junit.Test
	public void testIfSMSTextIsNotASCIICode()
	{
		String isSMSTextOnlyFromAsciiCode = "Hello משה הרשקוביץ, On Sunday, 19 Apr 2015 , 3,019 users visited your website in 3,389 sessions. 290 (8.56%) sessions reached your goal of 'Confirmation (Sale)' to stop, email service@analyticsbytes.com";
		assertFalse(FormatterUtils.isPureAscii(isSMSTextOnlyFromAsciiCode));
	}

	private GaData prepareGaData() {
		GaData gaData = new GaData();
		
		Map<String, String> map = prepateDataMap();
		gaData.setTotalsForAllResults(map);
		return gaData;
	}

	private Map<String, String> prepateDataMap() {
		Map<String,String> map = new ArrayMap<String,String>();
		map.put(AnalyticConsts.GA_USERS_HEADER, "100");
		map.put(AnalyticConsts.GA_SESSION_HEADER, "130");
		map.put("ga:goal1","8");
		return map;
	}

	private String[] prepareMetricsArrData() {
		String[] metricsArr = new String[3];
		
		metricsArr[0]="ga:users";
		metricsArr[1] = "ga:sessions";
		metricsArr[2] = "ga:goal1";
		return metricsArr;
	}

	private GoalDT prepeateGoalDTData() {
		String accountId="52160128";
		String webPropertyId="UA-52160128-1";
		String profileId="87645549";
		String goalId=null;
		String goalName = null;
		GoalDT goalDT = new GoalDT(accountId, webPropertyId, profileId,goalId,goalName);
		return goalDT;
	}
	
	
	
	
	
	
}
