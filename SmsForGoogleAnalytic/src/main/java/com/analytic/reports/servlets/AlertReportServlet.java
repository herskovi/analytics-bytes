package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.alerts.controller.AlertReportController;
import main.java.com.analytic.reports.alerts.controller.WeeklyAlertController;
import main.java.com.analytic.reports.controller.DailySmsController;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.consts.CredentialConsts;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;

@SuppressWarnings({ "serial", "unused" })
public class AlertReportServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(AlertReportServlet.class.getName());

	/**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		try 
		{
			Customer cust = getCustomerInformation(req);
			IController alertReportController = getController(cust);
			alertReportController.execute();
			writeResponseIntoJson(resp, alertReportController);

		} catch (Exception e) 
		{
			log.severe("WeeklyAlertSmsServlet failed " +  e.getMessage());
		}

	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 9, 2015
	 *@Description:
	 */
	private Customer getCustomerInformation(HttpServletRequest req)  throws ServletException,IOException 
	{
			String userId = req.getParameter("userId");
			return CustomerDAO.getCustomerInformationByUserID(userId);
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 12, 2014
	 *@Description:
	 */

	private void writeResponseIntoJson(HttpServletResponse resp, IController dailySmsController) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(dailySmsController.getResponse().getMessage());
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}



	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 23, 2014
	 *@Description: get Daily SMS Controller
	 */
	private IController getController(Customer cust) 
	{
		return new WeeklyAlertController(cust);
	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: Jun 1, 2014
	 * @Description: Get Metrics From User Preferences
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		doGet(req, resp);
	}

}
