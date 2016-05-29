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
import main.java.com.analytic.reports.controller.SendSmsController;
import main.java.com.analytic.reports.controller.TelegramRegisterDeviceController;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.EmailNotificationUtils;
import main.java.com.analytic.reports.utils.JsonUtils;
import main.java.com.analytic.reports.utils.URLUtils;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;

@SuppressWarnings("serial")
public class TelegramRegisterDeviceServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(TelegramRegisterDeviceServlet.class.getName());

	/**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		boolean isLocalMode = URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
		String telephoneNumber = req.getParameter("telephoneNumber");
		IController telegramRegisterDeviceController = getController(telephoneNumber, isLocalMode); 
		try 
		{
			telegramRegisterDeviceController.execute();
			//EmailNotificationUtils.sendSigningUpEmailNotification(email);
			JsonUtils jsonUtils = new JsonUtils();
			jsonUtils.writeMessageResponseIntoJson(resp, telegramRegisterDeviceController.getResponse());
		} catch (Exception e) 
		{
			log.severe("TelegramRegisterDeviceServlet failed " +  e.getMessage());
		}

	}



	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jul 23, 2014
	 *@Description: get Daily SMS Controller
	 */
	private IController getController(String telephoneNumber, boolean isLocalMode) 
	{
		return new TelegramRegisterDeviceController(telephoneNumber, isLocalMode);
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
