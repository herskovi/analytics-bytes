package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.SupportController;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.anaytic.reports.datatypes.SupportDT;

@SuppressWarnings("serial")
public class SupportServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(SupportServlet.class.getName());

	
	
	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{

		SupportDT supportDT = populateSupportDataTypeFromHttpRequest(req);
		IController supportController = getController(supportDT); 
		
		try 
		{
			supportController.execute();
			resp.getWriter().println(supportController.getResponse().getMessage());
			//writeJsonObject();
		} catch (Exception e) 
		{
			log.severe("SupportServlet failed " +  e.getMessage());
		}

	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 30, 2014
	 *@Description: Populate Support Data Type From Http Request
	 */

	public SupportDT populateSupportDataTypeFromHttpRequest(
			HttpServletRequest req) {
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		String email = req.getParameter("email");
		String company = req.getParameter("company");
		String description = req.getParameter("description");

		SupportDT supportDT = new SupportDT(firstName, lastName, email, company, description);
		return supportDT;
	}



	/**
	 * @Author:       Moshe Herskovits
	 * @Date:         Aug 16, 2014
	 * @Description:  get Support Controller
	 */
	private IController getController(SupportDT supportDT) 
	{
		return new SupportController(supportDT);
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
