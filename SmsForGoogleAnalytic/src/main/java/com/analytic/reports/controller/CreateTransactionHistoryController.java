/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.TransactionHistoryResponse;
import main.java.com.analytic.reports.controller.response.UserDashboardResponse;
import main.java.com.analytic.reports.datatypes.CreateTransactionHistoryDT;
import main.java.com.analytic.reports.datatypes.SupportDT;
import main.java.com.analytic.reports.datatypes.TransactionHistoryDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.SupportDAO;
import main.java.com.analytic.reports.jdo.dao.TransactionHistoryDAO;
import main.java.com.analytic.reports.jdo.model.Support;
import main.java.com.analytic.reports.jdo.model.TransactionHistory;
import main.java.com.analytic.reports.utils.DateUtils;

/**
 * @author admin
 * Aug 31, 2014
 */
public class CreateTransactionHistoryController extends BaseController
{
	private CreateTransactionHistoryDT createTransactionHistoryDT = new CreateTransactionHistoryDT();
	private TransactionHistory transactionHistory =null;
	private IResponse historyResponse = newResponse();
	private static final Logger log = Logger.getLogger(CreateTransactionHistoryController.class.getName());
	private String mode = "";



	public CreateTransactionHistoryController(CreateTransactionHistoryDT createTransactionHistoryDT)
	{
		this.createTransactionHistoryDT = createTransactionHistoryDT;
	}


	@Override
	public void execute() throws Exception
	{
		transactionHistory = convertToTransactionHistoryModel(createTransactionHistoryDT);
		TransactionHistoryDAO.insertNewTransaction(transactionHistory);
	}



	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 30, 2014
	 *@Description: Convert From Support DT to support
	 */
	private TransactionHistory convertToTransactionHistoryModel(CreateTransactionHistoryDT createTransactionHistoryDT) 
	{
		transactionHistory = new TransactionHistory();
		transactionHistory.setEmailAddress(createTransactionHistoryDT.getUserId());
		transactionHistory.setDescription(createTransactionHistoryDT.getDescription());
		transactionHistory.setDate(createTransactionHistoryDT.getDate());
		transactionHistory.setAmount(createTransactionHistoryDT.getAmount());
		return transactionHistory;
	}


	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new TransactionHistoryResponse();		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.historyResponse;		
	}
	
	@Override
	public void setResponse(String textMessage)  
	{
		historyResponse.setMessage(textMessage);	
	}
}
