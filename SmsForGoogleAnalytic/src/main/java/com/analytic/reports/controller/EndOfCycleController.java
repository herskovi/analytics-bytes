/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import main.java.com.analytic.reports.controller.response.EndOfCycleResponse;
import main.java.com.analytic.reports.datatypes.CreateTransactionHistoryDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.servlets.DailySmsServlet;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.ActivityConsts;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;
import main.java.com.analytic.reports.utils.consts.RegistrationConsts;
import main.java.com.analytic.reports.validator.MonthlyRecurringChargeValidator;

/**
 * @author admin
 * Jul 21, 2014
 */
public class EndOfCycleController extends BaseController
{
	private HttpServletRequest req = null;
	private IResponse endOfCycleControllerResponse = newResponse();
	private static final Logger log = Logger.getLogger(DailySmsServlet.class.getName());





	public EndOfCycleController(HttpServletRequest req)
	{
		this.req = req;
	}


	@Override
	public void execute() throws Exception
	{
		log.info("Start Execute at EndOfCycleController " );
		
		try
		{
				
			boolean isLocalMode = URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
			// Get All Customers From DB
			//TODO - Open Connection with  DB
			List<Customer> customerList = CustomerDAO.getCustomerList();
			scanAllCustomersAndDeductTheirBalance(isLocalMode, customerList);// End of While
			//TODO - Close Connection with  DB
		}catch (Exception ex) 
		{
			log.severe(" General Error execute  at End Of Cycle Controller" + ex.getMessage());
			throw ex;
		}
	}


	private void scanAllCustomersAndDeductTheirBalance(boolean isLocalMode,
			List<Customer> customerList) throws IOException, Exception 
	{
		for (Customer cust : customerList) 
		{
			if (isCustomerActive(cust.getUserSts()) && shouldCustomerBeChargedThisMonth(cust))
			{
				IController deductBalanceController = getController(cust);
				deductBalanceController.execute();		
				insertIntoTransactionHistoryEntity(cust);
			}

		}
	}
	


	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jan 26, 2015
	 *@Description:
	 */
	private boolean shouldCustomerBeChargedThisMonth(Customer cust) 
	{
		IValidator monthlyRecurringChargeValidator = new MonthlyRecurringChargeValidator(cust);
		boolean shouldCustomerBeChargedThisMonth=false;
		try {
			monthlyRecurringChargeValidator.validate();
			shouldCustomerBeChargedThisMonth = monthlyRecurringChargeValidator.isAllValidationPassed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return shouldCustomerBeChargedThisMonth;
	}


	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 21, 2014
	 *@Description:
	 */

	private IController getController(Customer cust) 
	{
		
			return new DeductBalanceController(cust, BalanceConsts.DEFAULT_BALANCE_TO_DEDUCT);
			
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jan 22, 2015
	 *@Description:
	 */
	private void insertIntoTransactionHistoryEntity(Customer cust) 
	{
		try
		{
			CreateTransactionHistoryDT createTrxHistDT =  initTtansactionHistoryDT(cust);
			CreateTransactionHistoryController createTHController = new CreateTransactionHistoryController (createTrxHistDT); 
			createTHController.execute();
		} catch (Exception e) {
			log.severe("AccountSelectionServlet insertIntoTransactionHistoryEntity failed " + e.getMessage());			
		}		
	}


	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jan 22, 2015
	 *@Description:
	 */
	private CreateTransactionHistoryDT initTtansactionHistoryDT(Customer cust) 
	{
		return new CreateTransactionHistoryDT(cust.getEmailAddress(),DateUtils.getCurrentDateTime(), 
				ActivityConsts.MONTHLY_RECURRING_CHARGE, BalanceConsts.DEFAULT_BALANCE_TO_DEDUCT);

	}
	

	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new EndOfCycleResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.endOfCycleControllerResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		endOfCycleControllerResponse.setMessage(textMessage);	
	}
	
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jan 30, 2015
	 *@Description:
	 */
	private boolean isCustomerActive(String userSts) 
	{
		return ((RegistrationConsts.USER_ENROLED_AND_COMPLETE_REGISTRATION_DID_NOT_CONFIRM_EMAIL.equals(userSts) || RegistrationConsts.USER_ENROLED_AND_COMPLETE_REGISTRATION_AND_CONFIRM_HIS_EMAIL.equals(userSts)) ? true : false);
	}


}
