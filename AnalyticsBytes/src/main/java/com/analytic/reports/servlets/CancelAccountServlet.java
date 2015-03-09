package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.CancelAccountController;
import main.java.com.analytic.reports.controller.SignUpEmailNotificationController;
import main.java.com.analytic.reports.controller.SupportController;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.anaytic.reports.datatypes.CancelAccountDT;
import main.java.com.anaytic.reports.datatypes.EmailNotificationDT;
import main.java.com.anaytic.reports.datatypes.SupportDT;

@SuppressWarnings("serial")
public class CancelAccountServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(CancelAccountServlet.class.getName());

	
	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{

		CancelAccountDT emailNotificationDT = populateCancelAccountDTDataTypeFromHttpReq(req);
		IController emailNotificationController = getController(emailNotificationDT); 
		
		try 
		{
			emailNotificationController.execute();
			resp.getWriter().println(emailNotificationController.getResponse().getMessage());
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

	public CancelAccountDT populateCancelAccountDTDataTypeFromHttpReq(HttpServletRequest req) 
	{
	
		String email = req.getParameter("email");
		String uniqueKey = req.getParameter("uniqueKey");
		if (uniqueKey == null || uniqueKey.trim().length() == 0 )
		{
			  uniqueKey = UUID.randomUUID().toString();
		}
		String communicationChannel = req.getParameter("communicationChannel");
		
		CancelAccountDT cancelAccountDT = new CancelAccountDT(email, uniqueKey, communicationChannel) ;
		return cancelAccountDT;
	}



	/**
	 * @Author:       Moshe Herskovits
	 * @Date:         Aug 16, 2014
	 * @Description:  get Support Controller
	 */
	private IController getController(CancelAccountDT cancelAccountDT) 
	{
		return new CancelAccountController(cancelAccountDT);
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
