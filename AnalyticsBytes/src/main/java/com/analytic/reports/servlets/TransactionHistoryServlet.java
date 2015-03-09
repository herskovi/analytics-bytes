package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import main.java.com.analytic.reports.controller.RetrieveTransactionHistoryController;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.anaytic.reports.datatypes.TransactionHistoryDT;
import main.java.com.analytic.reports.controller.response.TransactionHistoryResponse;

@SuppressWarnings("serial")
public class TransactionHistoryServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(TransactionHistoryServlet.class.getName());

	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{

		String userid = getUserIdFromHttpRequest(req);
		IController transactionHistoryController = getController(userid); 
		
		try 
		{
			transactionHistoryController.execute();
			transactionHistoryController.getResponse().getMessage();
			ArrayList <TransactionHistoryDT> transactionHistoryList =  ((TransactionHistoryResponse)(transactionHistoryController.getResponse())).getTransactionHistoryList();
			Gson gson = new Gson();
			String json = gson.toJson(transactionHistoryList);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
			
			
		} catch (Exception e) 
		{
			log.severe("TransactionHistoryServlet doGet failed " +  e.getMessage());
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
	 * @Author:       Moshe Herskovits
	 * @Date:         Aug 16, 2014
	 * @Description:  get Support Controller
	 */
	private IController getController(String userId) 
	{
		return new RetrieveTransactionHistoryController(userId);
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
