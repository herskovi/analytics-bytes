package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.SMSFlowController;
import main.java.com.analytic.reports.datatypes.AccountDT;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.CustomerAnalyticInfo;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class SecureLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{

		String userIdFromLoginForm = req.getParameter("username");
		String passwordFromLoginForm = req.getParameter("j_password");
		Customer cust = CustomerDAO.getCustomerInformationByUserID(userIdFromLoginForm);
		checkUserCredential(req, resp, passwordFromLoginForm, cust);
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Aug 17, 2014
	 * @Description: Check User Credential
	 */

	public void checkUserCredential(HttpServletRequest req, HttpServletResponse resp, String passwordFromLoginForm, Customer cust) throws ServletException, IOException 
	{
		if (CustomerDAO.isUserExists(cust) && CustomerDAO.doesUserHasSameCredential(passwordFromLoginForm, cust)) 
		{
			//req.setAttribute("username", cust.getName());
			String balance = "0.0";			
			req.getSession().setAttribute("username", cust.getName());
			req.getSession().setAttribute("userid", cust.getUserId());

			
			if (cust.getBalance() !=null)
			{
				 balance = cust.getBalance();
			}
			req.getSession().setAttribute("balance", balance);
			getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_LOGIN_TO_DASHBOARD).forward(req, resp);
		}else 
		{
			req.setAttribute("login_error", "1");
			getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_LOGIN_TO_LOGIN_ERROR).forward(req,	resp);
		}
	}
	

	/**
	 * @Author: Moshe Herskovits
	 * @Date: Jun 7, 2014
	 * @Description: Prepare List of Metrics based on User Choice
	 */

	private List prepareListOfMetrics(String[] metrics) {
		List<String> metricsList = new ArrayList();
		if (metrics != null && metrics.length > 0) {
			metricsList = new ArrayList<String>(Arrays.asList(metrics));
		}
		return metricsList;
	}

	/**
	 * @throws ServletException
	 * 
	 * 
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}

}
