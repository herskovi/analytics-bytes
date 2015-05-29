package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.CancelAccountController;
import main.java.com.analytic.reports.controller.SignUpEmailNotificationController;
import main.java.com.analytic.reports.controller.SupportController;
import main.java.com.analytic.reports.datatypes.CancelAccountDT;
import main.java.com.analytic.reports.datatypes.EmailNotificationDT;
import main.java.com.analytic.reports.datatypes.SupportDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;

@SuppressWarnings("serial")
public class UpdateAccountController extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(UpdateAccountController.class.getName());

	
	/**
	 * 
	 * 
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException 
	{

		String email = req.getParameter("email");
		String telephoneNumber = req.getParameter("telephoneNumber");

			Customer cust = CustomerDAO.getCustomerInformationByUserID(email);
			if (cust  != null)
			{
				cust.setTelephoneNumber(telephoneNumber);
				cust.getTelNoForSMS().remove(0);
				cust.getTelNoForSMS().add(telephoneNumber);
				CustomerDAO.updateCustomerInDB(cust);
			}
			
		

		

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
