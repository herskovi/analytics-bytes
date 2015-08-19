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
import main.java.com.analytic.reports.utils.consts.ActivityConsts;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;
import main.java.com.analytic.reports.utils.consts.RegistrationConsts;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;
import main.java.com.analytic.reports.validator.TimeZoneValidator;

@SuppressWarnings("serial")
public class MobileSelectionWizardServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(MobileSelectionWizardServlet.class.getName());


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		req.setCharacterEncoding("UTF-8");//Support UTF-8
		Customer cust = prepareCustomerData(req);	
		CustomerDAO.insertNewCustomer(cust);
		req.getSession().setAttribute("userid", cust.getUniqueAccountNumber());
		req.getSession().setAttribute("uniqueAccountNumber", cust.getUniqueAccountNumber());		
		getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_WIZARD_MOBILE_SELECTION_TO_WIZARD_EMAIL_SELECTION_PAGE).forward(req, resp);
	}

	
	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 17, 2014
	 * @Description: Prepare Customer Data
	 */

	private Customer prepareCustomerData(HttpServletRequest req) {
		// Get All Fields from Registration Form
		String uniqueAccountNumber = req.getParameter(RegistrationConsts.UNIQUE_ACCOUNT_NUMBER);
		String countryCode = req.getParameter(RegistrationConsts.COUNTRY_CODE);
		String mobileNumber = req.getParameter(RegistrationConsts.MOBILE_NUMBER);
		mobileNumber = changePhoneNumberFormat(countryCode, mobileNumber);
		// Create Customer Entity 
		Customer newCustomer = new Customer(uniqueAccountNumber,uniqueAccountNumber, countryCode, mobileNumber);
		newCustomer.setUserSts(RegistrationConsts.USER_ENROLL_HIS_MOBILE_NUMBER_AND_DID_NOT_COMPLETE_REGISTRATION);//User approved GA but is not active till user will click on confirmation mail.

		return newCustomer;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 2, 2014
	 *@Description: Change Phone Number format to international format.
	 */
	public String changePhoneNumberFormat(String countryCodeStr, String mobileNumber) 
	{
		PhoneNumber telephoneNumber = null;
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try 
		{			
			telephoneNumber = phoneUtil.parse(mobileNumber, countryCodeStr);
			int countryCode = telephoneNumber.getCountryCode();
			long nationalNumber = telephoneNumber.getNationalNumber();
			mobileNumber = String.valueOf(countryCode) + String.valueOf(nationalNumber);
			//mobileNumber = phoneUtil.format(telephoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
		}catch (Exception e) 
		{
			log.severe("SmsForGoogleAnalyticLoginServlet changePhoneNumberFormat Exception was thrown: " + e.toString());
		}
		return mobileNumber;
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
