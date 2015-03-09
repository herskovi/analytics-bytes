package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.EmailConfirmationController;
import main.java.com.analytic.reports.controller.SignUpEmailNotificationController;
import main.java.com.analytic.reports.controller.SupportController;
import main.java.com.analytic.reports.controller.response.EmailConfirmationResponse;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;
import main.java.com.anaytic.reports.datatypes.EmailConfirmationDT;
import main.java.com.anaytic.reports.datatypes.EmailNotificationDT;
import main.java.com.anaytic.reports.datatypes.SupportDT;

@SuppressWarnings("serial")
public class EmailConfirmationServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(EmailConfirmationServlet.class.getName());


	/**
	 * @throws ServletException 
	 * 
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException, ServletException 
	{

		EmailConfirmationDT emailConfirmationDT = populateEmailNotificationDataTypeFromHttpRequest(req);
		IController emailConfirmationController = getController(emailConfirmationDT); 

		try 
		{
			emailConfirmationController.execute();
			EmailConfirmationResponse emailConfirmationResponse = (EmailConfirmationResponse)emailConfirmationController.getResponse();
			log.severe("EmailConfirmationServlet username " +emailConfirmationResponse.getUserName());
			req.getSession().setAttribute("username", emailConfirmationResponse.getUserName());
			log.severe("EmailConfirmationServlet email " +  emailConfirmationResponse.getEmail());
			req.getSession().setAttribute("email", emailConfirmationResponse.getEmail());
			log.severe("EmailConfirmationServlet balance " +  emailConfirmationResponse.getBalance());
			req.getSession().setAttribute("balance", emailConfirmationResponse.getBalance());
			
			if (emailConfirmationResponse.isUserActivated())
			{
				getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_LOGIN_TO_DASHBOARD).forward(req, resp);

			}else{
				getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_REGISTRATION_TO_FIRST_TIME_DASHBOARD_PAGE).forward(req, resp);
			}
		} catch (Exception e) 
		{
			log.severe("EmailConfirmationServlet doGet failed " +  e.getMessage());
		}
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 30, 2014
	 *@Description: Populate Support Data Type From Http Request
	 */

	public EmailConfirmationDT populateEmailNotificationDataTypeFromHttpRequest(HttpServletRequest req) 
	{

		String email = req.getParameter("email");
		String uniqueKey = req.getParameter("uniqueKey");
		EmailConfirmationDT emailConfirmationDT = new EmailConfirmationDT(email, uniqueKey);
		return emailConfirmationDT;
	}



	/**
	 * @Author:       Moshe Herskovits
	 * @Date:         Aug 16, 2014
	 * @Description:  get Support Controller
	 */
	private IController getController(EmailConfirmationDT emailConfirmationDT) 
	{
		return new EmailConfirmationController(emailConfirmationDT);
	}

	/**
	 * @throws ServletException 
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Metrics From User Preferences
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		doGet(req, resp);
	}

}
