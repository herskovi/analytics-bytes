package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.OrderConfirmationController;
import main.java.com.analytic.reports.controller.response.EmailConfirmationResponse;
import main.java.com.analytic.reports.controller.response.OrderConfirmationResponse;
import main.java.com.analytic.reports.datatypes.NumberDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class OrderConfirmationServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(OrderConfirmationServlet.class.getName());

	
	
	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{
		printAllParametersToLog(req);
		String userId = getUserIdFromHttpRequest(req);
		String topUpAmount = getTopUpAmountFromHttpRequest(req);
		IController orderConfirmationController = getController(userId,topUpAmount); 
		try 
		{
			orderConfirmationController.execute();
			OrderConfirmationResponse orderConfirmationResponse = (OrderConfirmationResponse)orderConfirmationController.getResponse();
			req.getSession().setAttribute("topUpAmount", orderConfirmationResponse.getTopUpAmount());
			req.getSession().setAttribute("userId", userId);
			req.getSession().setAttribute("balance", orderConfirmationResponse.getBalance());
			getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_PAY_PAL_TO_ORDER_CONFIRMATION).forward(req, resp);
			//writeJsonObject();
		} catch (Exception e) 
		{
			log.severe("OrderConfirmationServlet failed " +  e.getMessage());
		}

	}


	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jan 4, 2015
	 *@Description:
	 */
	private void printAllParametersToLog(HttpServletRequest req) 
	{
		Enumeration<String> parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) 
		{
		  String paramName = parameterNames.nextElement();
		  System.out.print("PARAMNAME!!!@@@ " + paramName );
		  String[] paramValues = req.getParameterValues(paramName);
		  for (int i = 0; i < paramValues.length; i++) 
		  {
		      String paramValue = paramValues[i];
		      System.out.println(" "  + " ParamValue:  " + paramValue);
		  }
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
		String userId = req.getParameter("custom");
		return userId;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Nov 28, 2014
	 *@Description: Populate Top Up Amount From Http Request
	 */

	public String getTopUpAmountFromHttpRequest(HttpServletRequest req) 
	{
		String userId = req.getParameter("payment_gross"); //FIXME - Should change it to 
		return userId;
	}



	/**
	 *@Author:       Moshe Herskovits
	 *@Date:         Aug 16, 2014
	 *@Description:  get User Dashboard Controller
	 */
	private IController getController(String userName, String topUpAmount) 
	{
	
		return new OrderConfirmationController(userName,topUpAmount);
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
