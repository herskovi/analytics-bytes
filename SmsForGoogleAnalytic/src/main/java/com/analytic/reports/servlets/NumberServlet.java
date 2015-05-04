package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.DailySmsController;
import main.java.com.analytic.reports.controller.NumberController;
import main.java.com.analytic.reports.controller.UserDashboardController;
import main.java.com.analytic.reports.controller.response.NumberResponse;
import main.java.com.analytic.reports.controller.response.TransactionHistoryResponse;
import main.java.com.analytic.reports.datatypes.NumberDT;
import main.java.com.analytic.reports.datatypes.TransactionHistoryDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class NumberServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(NumberServlet.class.getName());

	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{
		String userId = getUserIdFromHttpRequest(req);
		IController numberController = getController(userId); 
		try 
		{
			numberController.execute();
			ArrayList <NumberDT> numberList =  ((NumberResponse)(numberController.getResponse())).getNumberList();
			Gson gson = new Gson();
			String json = gson.toJson(numberList);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
		
			//writeJsonObject();
		} catch (Exception e) 
		{
			log.severe("NumberServlet failed " +  e.getMessage());
		}

	}


	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 30, 2014
	 *@Description: Populate Support Data Type From Http Request
	 */

	public String getUserIdFromHttpRequest(HttpServletRequest req) 
	{
		String userId = req.getParameter("userid");
		return userId;
	}


	/**
	 *@Author:       Moshe Herskovits
	 *@Date:         Aug 16, 2014
	 *@Description:  get User Dashboard Controller
	 */
	private IController getController(String userName) 
	{
	
		return new NumberController(userName);
	}

	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Metrics From User Preferences
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		doGet(req, resp);
	}

}
