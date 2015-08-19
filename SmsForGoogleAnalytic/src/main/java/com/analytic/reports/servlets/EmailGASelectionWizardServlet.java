package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import main.java.com.analytic.reports.controller.CreateTransactionHistoryController;
import main.java.com.analytic.reports.controller.SignUpEmailNotificationController;
import main.java.com.analytic.reports.datatypes.AccountDT;
import main.java.com.analytic.reports.datatypes.CreateTransactionHistoryDT;
import main.java.com.analytic.reports.datatypes.EmailNotificationDT;
import main.java.com.analytic.reports.datatypes.TransactionHistoryDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.CustomerAnalyticInfo;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.ActivityConsts;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;
import main.java.com.analytic.reports.utils.consts.RegistrationConsts;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;
import main.java.com.analytic.reports.validator.EmailAddressValidator;
import main.java.com.analytic.reports.validator.RegistrationValidator;
import main.java.com.analytic.reports.validator.TimeZoneValidator;

@SuppressWarnings("serial")
public class EmailGASelectionWizardServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(EmailGASelectionWizardServlet.class.getName());


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		req.setCharacterEncoding("UTF-8");//Support UTF-8
		validateEmailAddress(req.getParameter(RegistrationConsts.EMAIL_ADDRESS)); 
		Customer cust = addEmailToNewCustomer(req);		
		boolean isLocalMode = URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
		String url = URLUtils.buildUrl(cust.getUniqueAccountNumber(), isLocalMode); 
		resp.sendRedirect(url);
	}

	
	/**
	 *@throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Aug 19, 2015
	 *@Description:
	 */
	private void validateEmailAddress(String emailAddress)  
	{
		IValidator emailAddressValidator = new EmailAddressValidator(emailAddress);
		try {
			emailAddressValidator.validate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 17, 2014
	 * @Description: Prepare Customer Data
	 */

	private Customer addEmailToNewCustomer(HttpServletRequest req) {
		// Get All Fields from Registration Form
		String uniqueAccountNumber = req.getParameter(RegistrationConsts.UNIQUE_ACCOUNT_NUMBER);
		Customer cust = CustomerDAO.getCustomerInformationByUserID(uniqueAccountNumber);
		String emailAddress = req.getParameter(RegistrationConsts.EMAIL_ADDRESS);
		if(cust!=null)
		{
			cust.setEmailAddress(emailAddress);
			cust.setUserSts(RegistrationConsts.USER_ENROLL_HIS_MOBILE_NUMBER_AND_HIS_EMAIL_ADRESS);
			CustomerDAO.updateCustomerInDB(cust);
		}
		return cust;
	}
	
	

	/**
	 * @throws ServletException 
	 * 
	 * 
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		doGet(req, resp);
	}

}
