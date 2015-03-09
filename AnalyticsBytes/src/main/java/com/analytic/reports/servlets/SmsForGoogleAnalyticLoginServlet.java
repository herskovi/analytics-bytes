package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.PMF;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;
import main.java.com.analytic.reports.utils.consts.RegistrationConsts;
import main.java.com.analytic.reports.validator.RegistrationValidator;


@SuppressWarnings("serial")
public class SmsForGoogleAnalyticLoginServlet extends HttpServlet 
{
	private static final Logger log = Logger
			.getLogger(SmsForGoogleAnalyticLoginServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{

		Customer newCustomer = prepareCustomerData(req);
		validateInput(newCustomer, req.getParameter(RegistrationConsts.CONFIRM_PASSWORD));
		CustomerDAO.insertNewCustomer(newCustomer);
		String emailAddress = req.getParameter(RegistrationConsts.EMAIL_ADDRESS);
		boolean isLocalMode = URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
		String url = URLUtils.buildUrl(emailAddress, isLocalMode);
		resp.sendRedirect(url);
	}

	/**
	 *@throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Sep 2, 2014
	 *@Description: Validate Input for New Customer
	 */
	private void validateInput(Customer newCustomer, String confirmPassword)  
	{
		try
		{
			IValidator registrationValidator = new RegistrationValidator(newCustomer, confirmPassword);
			registrationValidator.validate();
			
			
		}catch(Exception ex)
		{
			log.severe("SmsForGoogleAnalyticLoginServlet validateInput failed " +  ex.getMessage());	
		}
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 17, 2014
	 * @Description: Prepare Customer Data
	 */

	private Customer prepareCustomerData(HttpServletRequest req) {
		// Get All Fields from Registration Form
		String plan = req.getParameter(RegistrationConsts.PLAN);
		String name = req.getParameter(RegistrationConsts.NAME);
		String companyName = req.getParameter(RegistrationConsts.COMPANY_NAME);
		String emailAddress = req.getParameter(RegistrationConsts.EMAIL_ADDRESS);
		String countryCode = req.getParameter(RegistrationConsts.COUNTRY_CODE);
		String mobileNumber = req.getParameter(RegistrationConsts.MOBILE_NUMBER);
		String password = req.getParameter(RegistrationConsts.PASSWORD);
		String comments = req.getParameter(RegistrationConsts.COMMENTS);
		 


		//String userId = UserServiceFactory.getUserService().getCurrentUser().getUserId(); //FIXME - Check If user ID is authenticate
		String userId= emailAddress;
		mobileNumber = changePhoneNumberFormat(countryCode, mobileNumber);

		// Create Customer Entity
		Customer newCustomer = new Customer(plan, userId, name, companyName,
				 emailAddress, countryCode,
				mobileNumber, password, comments,BalanceConsts.DEFAULT_BALANCE);
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
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Apr 29, 2014
	 * @Description: Invoke doGet
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}

}
