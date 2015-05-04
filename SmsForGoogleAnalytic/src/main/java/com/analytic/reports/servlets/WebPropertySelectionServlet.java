package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.Webproperties;

import main.java.com.analytic.reports.controller.CreateTransactionHistoryController;
import main.java.com.analytic.reports.controller.SignUpEmailNotificationController;
import main.java.com.analytic.reports.controller.WebPropertySelectionController;
import main.java.com.analytic.reports.controller.response.WebPropertyResponse;
import main.java.com.analytic.reports.datatypes.AccountDT;
import main.java.com.analytic.reports.datatypes.CreateTransactionHistoryDT;
import main.java.com.analytic.reports.datatypes.EmailConfirmationDT;
import main.java.com.analytic.reports.datatypes.EmailNotificationDT;
import main.java.com.analytic.reports.datatypes.TransactionHistoryDT;
import main.java.com.analytic.reports.datatypes.WebpropertiesDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.CustomerAnalyticInfo;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.JsonUtils;
import main.java.com.analytic.reports.utils.consts.ActivityConsts;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;
import main.java.com.analytic.reports.validator.TimeZoneValidator;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class WebPropertySelectionServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(WebPropertySelectionServlet.class.getName());

	private static final String APPLICATION_NAME = "";

	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		
		String userId = (String) req.getSession().getAttribute("userId");
		String accountId= req.getParameter("accountId");
		List<WebpropertiesDT> webPropertiesList = new ArrayList<WebpropertiesDT>();

		IController webPropertySelectionServletController = getController(userId, accountId);
		try {
			webPropertySelectionServletController.execute();
			webPropertiesList = ((WebPropertyResponse)webPropertySelectionServletController.getResponse()).getWebPropertiesList();
		} catch (Exception e) {
			log.severe("WebPropertySelectionServlet failed " + e.getMessage());		
		}
		  
		Gson gson = new Gson();
		String json = gson.toJson(webPropertiesList);
		
		req.getSession().setAttribute("webPropertyJson", json);
		req.getSession().setAttribute("webProperty", webPropertiesList);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);

		
		
	}

	

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 4, 2014
	 *@Description:
	 */
	private IController getController(String userId, String accountId) 
	{
		WebpropertiesDT webpropertiesDT = new WebpropertiesDT(accountId);
		WebPropertySelectionController  wpsController = new WebPropertySelectionController(webpropertiesDT,userId);
		return wpsController;
	}



	



	/**
	 * @throws ServletException 
	 * 
	 * 
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		doGet(req, resp);
	}

	
}
	




		

	