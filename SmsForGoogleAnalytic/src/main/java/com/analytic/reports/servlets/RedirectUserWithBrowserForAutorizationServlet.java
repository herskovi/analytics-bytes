package main.java.com.analytic.reports.servlets;


import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.Utils;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.Analytics.Management;
import com.google.api.services.analytics.AnalyticsScopes;

@SuppressWarnings("serial")
public class RedirectUserWithBrowserForAutorizationServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(RedirectUserWithBrowserForAutorizationServlet.class.getName());
	private static final Collection<String> SCOPE = new ArrayList();
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final String P12_FILE_NAME="WEB-INF/d24862e0e5463c46d982e911d72d75bc6c46f605-privatekey.p12";
	private static final String DEVELOPER_ACCOUNT_ID = "350054109263-grin2oo1usiq4kb5suk5dlq69h2aomo6@developer.gserviceaccount.com";
	//private static final String DEVELOPER_CLIENT_ID = "350054109263-grin2oo1usiq4kb5suk5dlq69h2aomo6.apps.googleusercontent.com";
	//private static final String DEVELOPER_CLIENT_ID = "350054109263-3fj1ap88bkvm7g4io8k3ofv655lfvl9m.apps.googleusercontent.com";
	  private static final String DEVELOPER_CLIENT_ID = "350054109263-8rvc4mcjfhhs8hv1h7or1h908hquqs23.apps.googleusercontent.com";


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
			{
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		SCOPE.add(AnalyticsScopes.ANALYTICS_READONLY);
		String googleAnalyticEmailAddress = req.getParameter("googleAnalyticEmailAddress");

		try {

			//Call to Google Browser ClientRequestUrl

			String url2 = "https://accounts.google.com/o/oauth2/auth?"
				 +"scope=https://www.googleapis.com/auth/analytics.readonly&"
				 +"client_id=" +DEVELOPER_CLIENT_ID +"&"
				 +"state=%2Fprofile&"
				 +"redirect_uri=http%3A%2F%2Flocalhost%3A8888%2Fsmsforgoogleanalyticcallbacksample&"
				 +"response_type=code&"
				 +"approval_prompt=force&"
				 +"access_type=online&"
				 +"include_granted_scopes=false"; 


			String url = "https://accounts.google.com/o/oauth2/auth?"
					 +"scope=https://www.googleapis.com/auth/analytics.readonly&"
					 +"client_id=" +DEVELOPER_CLIENT_ID +"&"
					 +"state=%2Fprofile&"
					 +"redirect_uri=http%3A%2F%2Fdailyreportbysmsforga.appspot.com%2Fsmsforgoogleanalyticcallbacksample&"
					 +"response_type=code&"
					 +"approval_prompt=force&"
					 +"access_type=online&" 
					 +"include_granted_scopes=false"; 
				
			    resp.sendRedirect(url2);
			    
			
			//https://accounts.google.com/o/oauth2/auth
			//?client_id=1084945748469-eg34imk572gdhu83gj5p0an9fut6urp5.apps.googleusercontent.com
			//&redirect_uri=http%3A%2F%2Flocalhost%2Foauth2callback
			//&scope=https://www.googleapis.com/auth/youtube
			//&response_type=code
			//&access_type=offline
//
//			GoogleCredential credential = new GoogleCredential.Builder().setTransport(TRANSPORT)
//					.setJsonFactory(JSON_FACTORY)
//					.setServiceAccountId(DEVELOPER_ACCOUNT_ID)
//					.setServiceAccountScopes(SCOPE)
//					.setServiceAccountPrivateKeyFromP12File(new File(P12_FILE_NAME))
//					.build();
//
//			Analytics analytics =
//					new Analytics.Builder(TRANSPORT, JSON_FACTORY, credential)
//			.setApplicationName("Dev API Access")
//			.build();
//
//			Management.Accounts.List list = analytics.management().accounts().list();
//			com.google.api.services.analytics.model.Accounts accounts = list.execute();
//
		} catch (Exception e) {
				log.severe(" RedirectUserWithBrowserForAutorizationServlet failed due to " + e.getMessage());			
		}
//


			}
}


