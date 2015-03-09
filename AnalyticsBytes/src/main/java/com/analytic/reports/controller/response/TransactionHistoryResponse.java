/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.anaytic.reports.datatypes.TransactionHistoryDT;
import main.java.com.anaytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class TransactionHistoryResponse extends BaseResponse 
{

	private String message = null;
	private ArrayList <TransactionHistoryDT> transactionHistoryList = new ArrayList<TransactionHistoryDT>();
	
	
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
	@Override
	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	public ArrayList<TransactionHistoryDT> getTransactionHistoryList() {
		return transactionHistoryList;
	}
	public void setTransactionHistoryList(
			ArrayList<TransactionHistoryDT> transactionHistoryList) {
		this.transactionHistoryList = transactionHistoryList;
	}
	

}
