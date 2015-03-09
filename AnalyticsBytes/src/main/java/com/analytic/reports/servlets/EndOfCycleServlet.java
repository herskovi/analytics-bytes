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
import main.java.com.analytic.reports.controller.EndOfCycleController;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class EndOfCycleServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(EndOfCycleServlet.class.getName());

	/**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		IController endOfCycleController = getController(req,resp); 
		try 
		{
			endOfCycleController.execute();
			writeResponseIntoJson(resp, endOfCycleController);

		} catch (Exception e) 
		{
			log.severe("DailySmsServlet failed " +  e.getMessage());
		}

	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 12, 2014
	 *@Description:
	 */

	private void writeResponseIntoJson(HttpServletResponse resp,
			IController dailySmsController) throws IOException {
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
	private IController getController(HttpServletRequest req, HttpServletResponse resp) 
	{
		return new EndOfCycleController(req);
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
