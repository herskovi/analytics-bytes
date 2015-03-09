package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.OrderConfirmationController;
import main.java.com.analytic.reports.controller.SMSConfirmationController;
import main.java.com.analytic.reports.controller.response.EmailConfirmationResponse;
import main.java.com.analytic.reports.controller.response.OrderConfirmationResponse;
import main.java.com.analytic.reports.controller.response.SMSConfirmationResponse;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;
import main.java.com.anaytic.reports.datatypes.NumberDT;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class SMSConfirmationServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(SMSConfirmationServlet.class.getName());

	
	
	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{
		String userId = getUserIdFromHttpRequest(req);
		String isSmsRecievedToUser = getSMSButtonConfirmationFromHttpReq(req);
		IController smsConfirmationController = getController(userId,isSmsRecievedToUser); 
		try 
		{
			smsConfirmationController.execute();
			SMSConfirmationResponse smsConfirmationResponse = (SMSConfirmationResponse)smsConfirmationController.getResponse();

//			req.getSession().setAttribute("topUpAmount", orderConfirmationResponse.getTopUpAmount());
//			req.getSession().setAttribute("topUpAmount", orderConfirmationResponse.getTopUpAmount());
//			req.getSession().setAttribute("balance", orderConfirmationResponse.getBalance());
//			getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_PAY_PAL_TO_ORDER_CONFIRMATION).forward(req, resp);

		
			//writeJsonObject();
		} catch (Exception e) 
		{
			log.severe("SMSConfirmationServlet failed " +  e.getMessage());
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
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Nov 28, 2014
	 *@Description: Populate Top Up Amount From Http Request
	 */

	public String getSMSButtonConfirmationFromHttpReq(HttpServletRequest req) 
	{
		String smsRecieved = req.getParameter("smsRecieved");
		return smsRecieved;
	}



	/**
	 *@Author:       Moshe Herskovits
	 *@Date:         Aug 16, 2014
	 *@Description:  get User Dashboard Controller
	 */
	private IController getController(String userName, String isSmsRecievedToUser) 
	{
	
		return new SMSConfirmationController(userName,isSmsRecievedToUser);
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
