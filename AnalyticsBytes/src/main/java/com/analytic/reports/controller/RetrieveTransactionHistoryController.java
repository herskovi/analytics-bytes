/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.TransactionHistoryResponse;
import main.java.com.analytic.reports.controller.response.UserDashboardResponse;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.SupportDAO;
import main.java.com.analytic.reports.jdo.dao.TransactionHistoryDAO;
import main.java.com.analytic.reports.jdo.model.Support;
import main.java.com.analytic.reports.jdo.model.TransactionHistory;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.anaytic.reports.datatypes.SupportDT;
import main.java.com.anaytic.reports.datatypes.TransactionHistoryDT;

/**
 * @author admin
 * Aug 31, 2014
 */
public class RetrieveTransactionHistoryController extends BaseController
{
	private String userId = "";
	private TransactionHistoryResponse transactionHistoryResponse = (TransactionHistoryResponse) newResponse();
	private static final Logger log = Logger.getLogger(RetrieveTransactionHistoryController.class.getName());



	public RetrieveTransactionHistoryController(String userId)
	{
		this.userId = userId;
	}


	@Override
	public void execute() throws Exception
	{
		List<TransactionHistory> transactionHistoryListResults = new ArrayList<TransactionHistory>();
//		if ( email == null || email.length() == 0)
//		{
//			transactionHistoryListResults = TransactionHistoryDAO.getAllTransactionHistoryList();
//		}else{
			transactionHistoryListResults = TransactionHistoryDAO.getTransactionHistorynByEmail(userId);
//		}
		mapToResponseObjects(transactionHistoryListResults);
		
	}



	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 30, 2014
	 *@Description: Convert Results From DB TransactionHistory  DT to TransactionHistoryDT
	 */
	private void mapToResponseObjects (List<TransactionHistory> transactionHistoryResults) 
	{

		  if (transactionHistoryResults!= null && !transactionHistoryResults.isEmpty()) 
		  {
			  for (TransactionHistory transactionHistory : transactionHistoryResults) 
			  {
				  	TransactionHistoryDT transactionHistoryDT = new TransactionHistoryDT();
				  	//transactionHistoryDT.setEmail(transactionHistory.getEmailAddress());
				  	transactionHistoryDT.setDescription(transactionHistory.getDescription());
				  	transactionHistoryDT.setDate(transactionHistory.getDate());
					transactionHistoryDT.setAmount(transactionHistory.getAmount());
					transactionHistoryResponse.getTransactionHistoryList().add(transactionHistoryDT);			    
				}
		  	}
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
		return this.transactionHistoryResponse;		
	}
	
	@Override
	public void setResponse(String textMessage)  
	{
		transactionHistoryResponse.setMessage(textMessage);	
	}
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

}
