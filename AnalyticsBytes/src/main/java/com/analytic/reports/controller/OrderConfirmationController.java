/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.BalanceResponse;
import main.java.com.analytic.reports.controller.response.NumberResponse;
import main.java.com.analytic.reports.controller.response.OrderConfirmationResponse;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.consts.ActivityConsts;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;
import main.java.com.anaytic.reports.datatypes.CreateTransactionHistoryDT;
import main.java.com.anaytic.reports.datatypes.NumberDT;

/**
 * @author admin
 * Jul 21, 2014
 */
public class OrderConfirmationController extends BaseController
{

	private String userName = null;
	private String topUpAmount="0";
	private Customer cust =null;
	private IResponse orderConfirmationResponse = newResponse();
	private static final Logger log = Logger.getLogger(OrderConfirmationController.class.getName());


	public OrderConfirmationController(String userName, String topUpAmount)
	{
		this.userName = userName;
		this.topUpAmount = topUpAmount;
	}


	@Override
	public void execute() throws Exception
	{

		// Get Customer From DB By User ID.
		cust = CustomerDAO.getCustomerInformationByUserID(userName);
		if (cust != null)
		{
			//Update the Customer Balance
			BalanceController balanceController =  new BalanceController(cust,topUpAmount);
			balanceController.updateBalance();
			insertIntoTransactionHistoryEntity();

			((OrderConfirmationResponse)orderConfirmationResponse).setBalance(cust.getBalance());
			((OrderConfirmationResponse)orderConfirmationResponse).setTopUpAmount(topUpAmount);
			((OrderConfirmationResponse)orderConfirmationResponse).setCustomerName(cust.getName());
		}

	}



	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jan 22, 2015
	 *@Description:
	 */
	private void insertIntoTransactionHistoryEntity() 
	{
		try
		{
			CreateTransactionHistoryDT createTrxHistDT =  initTtansactionHistoryDT();
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
	private CreateTransactionHistoryDT initTtansactionHistoryDT() 
	{
		return new CreateTransactionHistoryDT(cust.getEmailAddress(),DateUtils.getCurrentDateTime(), 
				ActivityConsts.ADD_BALANCE, topUpAmount);

	}


	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new OrderConfirmationResponse();

	}

	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.orderConfirmationResponse;		
	}



	@Override
	public void setResponse(String textMessage)  
	{
		orderConfirmationResponse.setMessage(textMessage);	
	}



}
