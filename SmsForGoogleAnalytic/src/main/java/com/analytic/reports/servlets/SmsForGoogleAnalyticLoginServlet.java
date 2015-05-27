package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import main.java.com.analytic.reports.exceptions.AppException;
import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.PMF;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;
import main.java.com.analytic.reports.utils.consts.RegistrationConsts;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;
import main.java.com.analytic.reports.validator.RegistrationValidator;


@SuppressWarnings("serial")
public class SmsForGoogleAnalyticLoginServlet extends HttpServlet 
{
	private static final Logger log = Logger
			.getLogger(SmsForGoogleAnalyticLoginServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{

		Customer newCustomer = prepareCustomerData(req);
		String emailAddress = req.getParameter(RegistrationConsts.EMAIL_ADDRESS);//FIXME - Needs to be removed

		
		if (isTestAccount(emailAddress) || validateInput(req,resp,newCustomer, req.getParameter(RegistrationConsts.CONFIRM_PASSWORD)))
		{
			CustomerDAO.insertNewCustomer(newCustomer);
			String uniqueAccountNumber = req.getParameter(RegistrationConsts.UNIQUE_ACCOUNT_NUMBER);
			boolean isLocalMode = URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
			String url = URLUtils.buildUrl(uniqueAccountNumber, isLocalMode); //TODO - Decide which parameters needs to be send UAN or Email?
			resp.sendRedirect(url);
		}
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        May 26, 2015
	 *@Description:
	 */
	private boolean isTestAccount(String emailAddress) 
	{
		if ("test@analyticsbytes.com".equals(emailAddress))
		{
			return true;
		}else
		{
				return false;
		}
	}

	/**
	 *@throws IOException 
	 * @throws ServletException 
	 * @throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Sep 2, 2014
	 *@Description: Validate Input for New Customer
	 */
	private boolean validateInput(HttpServletRequest req, HttpServletResponse resp, Customer newCustomer, String confirmPassword) throws ServletException, IOException  
	{
		boolean allValidationPassed = true;
		try
		{
			IValidator registrationValidator = new RegistrationValidator(newCustomer, confirmPassword);
			registrationValidator.validate();	
		}catch(AppException appExc){
			req.setAttribute("isError", "1");
			req.setAttribute("errorMsg", appExc.getMessageText());
			req.setAttribute(RegistrationConsts.NAME, newCustomer.getName());
			req.setAttribute(RegistrationConsts.UNIQUE_ACCOUNT_NUMBER,newCustomer.getUniqueAccountNumber());
			req.setAttribute(RegistrationConsts.MOBILE_NUMBER, "+"+ newCustomer.getTelephoneNumber());
			req.setAttribute(RegistrationConsts.COUNTRY_CODE, newCustomer.getCountryCode());
			req.setAttribute(RegistrationConsts.PASSWORD, newCustomer.getPassword());
			req.setAttribute(RegistrationConsts.EMAIL_ADDRESS,newCustomer.getEmailAddress());			
			getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_REGISTRATION_TO_REGISTRAION_ERROR).forward(req,	resp);
			log.severe("SmsForGoogleAnalyticLoginServlet validateInput failed in AppException " +  appExc.getMessageText());
			allValidationPassed = false;
		}catch(Exception ex){
			log.severe("SmsForGoogleAnalyticLoginServlet validateInput failed in Excpetion " +  ex.getMessage());
			allValidationPassed = false;
		}	
		return allValidationPassed;
		
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
		Customer newCustomer = new Customer(uniqueAccountNumber, plan, userId, name, companyName,
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
	 * @throws ServletException 
	 * @Author: Moshe Herskovits
	 * @Date: Apr 29, 2014
	 * @Description: Invoke doGet
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}

}
