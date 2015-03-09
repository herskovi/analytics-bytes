package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.com.analytic.reports.controller.DailySmsController;
import main.java.com.analytic.reports.controller.UserDashboardController;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;

@SuppressWarnings("serial")
public class UserDashboardServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(UserDashboardServlet.class.getName());

	
	
	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{
		String userName = req.getParameter("username");
		IController userDashboardController = getController(userName); 
		
		try 
		{
			userDashboardController.execute();
			resp.getWriter().println(userDashboardController.getResponse().getMessage());
			//writeJsonObject();
		} catch (Exception e) 
		{
			log.severe("UserDashboardServlet failed " +  e.getMessage());
		}

	}



	/**
	 *@Author:       Moshe Herskovits
	 *@Date:         Aug 16, 2014
	 *@Description:  get User Dashboard Controller
	 */
	private IController getController(String userName) 
	{
	
		return new UserDashboardController(userName);
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
