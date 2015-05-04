/**
 * 
 */
package main.java.com.analytic.reports.tests;

import java.io.IOException;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.junit.Test;

import com.google.api.client.testing.http.apache.MockHttpClient;
import com.google.api.client.util.Beta;

/**
 * @author admin
 * Apr 29, 2014
 */
public class MockSmsForGoogleAnalyticHttpClient extends MockHttpClient  
{
	
	private static HttpRequestExecutor requestExec = null;
	private static ClientConnectionManager conman = null;
	private static ConnectionReuseStrategy reustrat = null;
	private static ConnectionKeepAliveStrategy kastrat = null;
	private static HttpRoutePlanner rouplan =null;
	private static HttpProcessor httpProcessor = null;
	private static HttpRequestRetryHandler retryHandler = null;
	private static AuthenticationHandler proxyAuthHandler = null;
	private static AuthenticationHandler targetAuthHandler =null;
	private static UserTokenHandler stateHandler = null;
	private static final HttpParams params =null;
	private static HttpHost target = null;
	private static HttpRequest request = null;
	private static HttpContext context = null;
	private static RedirectHandler redirectHandler;
	
	@Test
	public static void createClientRequestDirector()
	{
		MockHttpClient mockHttpClient = new MockHttpClient();
		mockHttpClient.setResponseCode(200);//Response is O.K.
		mockHttpClient.setResponseCode(400);//Error in one of the parameters like redirect_url is not match.
		mockHttpClient.setResponseCode(403);//Not Authorized
		mockHttpClient.setResponseCode(403);//Not Authorized
	}
	
	MockSmsForGoogleAnalyticHttpClient()
	{
		super();	
	}
	

}
