package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.UniqueAccountNumberController;
import main.java.com.analytic.reports.controller.response.UniqueAccountNumberResponse;
import main.java.com.analytic.reports.interfaces.IController;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class UniqueAccountNumberServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(UniqueAccountNumberServlet.class.getName());

	
	
	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{
		IController uniqueAccountNumberController = getController(); 
		
		try 
		{
			uniqueAccountNumberController.execute();
			String uniqueAccountNumber =  ((UniqueAccountNumberResponse)(uniqueAccountNumberController.getResponse())).getUniqueAccountNumber();
			Gson gson = new Gson();
			String json = gson.toJson(uniqueAccountNumber);
			resp.setContentType("application/json");
			resp.getWriter().write(json);
				
			 
			
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
	private IController getController() 
	{
	
		return new UniqueAccountNumberController();
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
