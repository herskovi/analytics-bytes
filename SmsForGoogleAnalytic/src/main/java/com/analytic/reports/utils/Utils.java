/*
 * Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package main.java.com.analytic.reports.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Utility class for JDO persistence, OAuth flow helpers, and others.
 *
 * @author Moshe Herskovits
 */
public class Utils 
{

  /**
   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
   * globally shared instance across your application.
   */
  private static final AppEngineDataStoreFactory DATA_STORE_FACTORY = AppEngineDataStoreFactory.getDefaultInstance();
  
  /** Global instance of the HTTP transport. */
 public  static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();

  /** Global instance of the JSON factory. */
  public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  /** Global instance of the Client Secret. */
  public static GoogleClientSecrets clientSecrets = null;
  
  public static final String clientSecretsLocation = "WEB-INF/client_secrets.json";
  public static InputStream resourceContent = null;

  /**
   * 
   *@Author:      Moshe Herskovits
   *@Date:        Apr 28, 2014
   *@Description: Get client credential from client_secret.json file 
   */
  public static GoogleClientSecrets getClientCredential() throws IOException 
  {
	  
    if (clientSecrets == null) 
    {
      
      clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(resourceContent));
       Preconditions.checkArgument(!clientSecrets.getDetails().getClientId().startsWith("Enter ")
          && !clientSecrets.getDetails().getClientSecret().startsWith("Enter "),
          "Download client_secrets.json file from https://code.google.com/apis/console/"
          + "?api=calendar into calendar-appengine-sample/src/main/resources/client_secrets.json");
    }
    return clientSecrets;
  }

  public static String getRedirectUri(HttpServletRequest req) {
    GenericUrl url = new GenericUrl(req.getRequestURL().toString());
    url.setRawPath("/oauth2callback");
    return url.build();
  }
  

  static void deleteCredentials(String userId) throws IOException {
    GoogleAuthorizationCodeFlow flow = newFlow();
    Credential credential = flow.loadCredential(userId);
    if (credential != null) {
      flow.getCredentialDataStore().delete(userId);
    }
  }


  public static GoogleAuthorizationCodeFlow newFlow() throws IOException {
    return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
        getClientCredential(), Collections.singleton(AnalyticsScopes.ANALYTICS_READONLY)).setDataStoreFactory(
        DATA_STORE_FACTORY).setAccessType("offline").build();
  }

  public static Analytics loadAnalyticClient() throws IOException {
    String userId = UserServiceFactory.getUserService().getCurrentUser().getUserId();
    Credential credential = newFlow().loadCredential(userId);
    return new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).build();
  }
  
  public static Analytics loadAnalyticClient(String userId) throws IOException {
	    Credential credential = newFlow().loadCredential(userId);
	    return new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).build();
	  }

  /**
   * Returns an {@link IOException} (but not a subclass) in order to work around restrictive GWT
   * serialization policy.
   */
  static IOException wrappedIOException(IOException e) {
    if (e.getClass() == IOException.class) {
      return e;
    }
    return new IOException(e.getMessage());
  }
  
  /**
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 25, 2014
	 *@Description: get Client Id
	 */
	public static String getCliendId() {
		return Utils.clientSecrets.getDetails().getClientId();
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 25, 2014
	 *@Description: get Client Id
	 */
	public static String getCliendSecret() {
		return Utils.clientSecrets.getDetails().getClientSecret();
	}


  private Utils() {
  }


}
