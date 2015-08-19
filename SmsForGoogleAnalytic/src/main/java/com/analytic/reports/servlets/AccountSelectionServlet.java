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
public class AccountSelectionServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(AccountSelectionServlet.class.getName());


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		req.setCharacterEncoding("UTF-8");//Support UTF-8

		List<String> metricsList = new ArrayList<String>(); 
		ArrayList<CustomerAnalyticInfo> customerAnalyticList = new ArrayList<CustomerAnalyticInfo>();

		String userId = req.getParameter("userId");
//		String accountDropdown = req.getParameter("accountDropdown");
//		String webPropertyDropdown = req.getParameter("webPropertyDD");
//		String profileDropdown = req.getParameter("profileDD");
		String timeToSendSMS = req.getParameter("timepicker_customminutes");
		String timeZoneToSendSMS = req.getParameter("timeZoneDropdown");
		
		
		String accountId = req.getParameter("accountIdHiddenValue");
		String webPropertyId = req.getParameter("webPropertyIdHiddenValue");
		String profileId = req.getParameter("profileIdHiddenValue");
		String profileName = req.getParameter("profileNameHiddenValue");
		log.severe("AccountSelection Store profile Name$$$$$$ " +profileName ); //FIXME-REmove after UTF-8
		System.out.println("profileName " + profileName);
		String goalId = req.getParameter("goalIdHiddenValue");
		String goalName = req.getParameter("goalNameHiddenValue");  

		
		
		validateTimeZone(timeZoneToSendSMS);
		
		String[] metrics = req.getParameterValues("metrics");
		List accountsObj = (List) req.getSession().getAttribute("accounts");

		Customer cust = CustomerDAO.getCustomerInformationByUserID(userId);
		
		metricsList = prepareListOfMetrics(metrics,goalId);
		cust.setMetrics(metricsList);
		cust.setTimeZone(timeZoneToSendSMS);
		cust.setUserSts(RegistrationConsts.USER_ENROLED_AND_COMPETE_REGISTRATION_DID_NOT_CONFIRM_EMAIL);//User approved GA but is not active till user will click on confirmation mail.

		CustomerAnalyticInfo customerAnalyticInfo = populateCustomerAnalyticInformation(accountId, webPropertyId, profileId, profileName,goalId, goalName, timeToSendSMS, accountsObj);
		
		customerAnalyticList.add(customerAnalyticInfo);
		cust.setCustomerAnalyticList(customerAnalyticList);
		CustomerDAO.updateCustomerInDB(cust);
		
		//Insert into TransactionHistoryDT
		insertIntoTransactionHistoryEntity(cust); 		
		String emailAddress = cust.getEmailAddress();
		sendConfirmationEmailToCustomer(emailAddress);
		req.getSession().setAttribute("userid", cust.getUniqueAccountNumber());
		req.getSession().setAttribute("email", cust.getEmailAddress());
		req.getSession().setAttribute("username", cust.getName());
		

		if (cust.getBalance() != null)
		{
			req.setAttribute("balance", cust.getBalance());
		}else
		{
			req.setAttribute("balance", "0.00");
		}

		//getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_REGISTRATION_TO_FIRST_TIME_DASHBOARD_PAGE).forward(req, resp);
		getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_WIZARD_ACCOUNT_SELECTION_TO_FIRST_TIME_DASHBOARD_PAGE).forward(req, resp);
		
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 4, 2014
	 *@Description:
	 */
	private void sendConfirmationEmailToCustomer(String userId) 
	{
		 try {
			new SignUpEmailNotificationController(new EmailNotificationDT(userId,UUID.randomUUID().toString())).execute();
		} catch (Exception e) 
		{
			log.severe("AccountSelectionServlet sendConfirmationEmailToCustomer failed " + e.getMessage());			
		}
		
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: InsertIntoTransactionHistory Entity
	 */

	private void insertIntoTransactionHistoryEntity(Customer cust) {
		try {
		CreateTransactionHistoryDT createTrxHistDT =  initTtansactionHistoryDT(cust);
		CreateTransactionHistoryController createTHController = (CreateTransactionHistoryController) getController(createTrxHistDT); 
		
			createTHController.execute();
		} catch (Exception e) {
			log.severe("AccountSelectionServlet insertIntoTransactionHistoryEntity failed " + e.getMessage());			
		}
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: Populate Customer Analytic Information
	 */

	private CustomerAnalyticInfo populateCustomerAnalyticInformation(String accountId, String webPropertyId, String profileId, String profileName, String goalId, String goalName, String timeToSendSMS, List accountsObj) 
	{
		CustomerAnalyticInfo customerAnalyticInfo = new CustomerAnalyticInfo();
		customerAnalyticInfo.setTimeToSendSMS(timeToSendSMS);
		customerAnalyticInfo.setAccountId(accountId);
		customerAnalyticInfo.setWebPropertyId(webPropertyId);
		customerAnalyticInfo.setProfileId(profileId);
		customerAnalyticInfo.setProfileName(profileName);
		customerAnalyticInfo.setGoalId(goalId);
		customerAnalyticInfo.setGoalName(goalName);
		
		return customerAnalyticInfo;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: init TransactionHistory DT
	 */

	private CreateTransactionHistoryDT initTtansactionHistoryDT(Customer cust) {
		return new CreateTransactionHistoryDT(cust.getEmailAddress(),DateUtils.getCurrentDateTime(), 
				ActivityConsts.BALANCE_FIRST_TIME, BalanceConsts.DEFAULT_BALANCE);
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: Validate Time Zone is not empty
	 */

	public void validateTimeZone(String timeZoneToSendSMS) {
		IValidator timeZoneValidator = new TimeZoneValidator(timeZoneToSendSMS);
		try {
			timeZoneValidator.validate();
		} catch (Exception e) 
		{
			new ServletException(e.getMessage());
			
		}
	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: Jun 7, 2014
	 * @Description: Prepare List of Metrics based on User Choice
	 */
	private List prepareListOfMetrics(String[] metrics, String goalId) 
	{
		List<String> metricsList = new ArrayList();
		if (metrics != null && metrics.length > 0)
		{
			 metricsList = new ArrayList<String>(Arrays.asList(metrics));
		}
		//In case Goal ID was given - Add another metrics
		if (goalId !=null && goalId.length() >0)
		{
			String goalXXCompletions = "ga:goal" + goalId +"Completions";
			metricsList.add(goalXXCompletions);
		}
		
		return metricsList;
	}
	
	/**
	 *@Author:       Moshe Herskovits
	 *@Date:         Aug 16, 2014
	 *@Description:  get Transaction History Controller
	 */
	private IController getController(CreateTransactionHistoryDT createtransactionHistoryDT) 
	{
	
		return new CreateTransactionHistoryController(createtransactionHistoryDT);
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
