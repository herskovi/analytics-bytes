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
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		req.setAttribute("userid", "");
		req.setAttribute("username","");
		req.setAttribute("balance","0.0");
		req.getSession().invalidate();
		getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_LOGOUT_TO_LOGIN_PAGE).forward(req, resp);
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
