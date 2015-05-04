/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.analytic.reports.datatypes.CancelAccountDT;
import main.java.com.analytic.reports.datatypes.EmailNotificationDT;
import main.java.com.analytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class CancelAccountResponse extends BaseResponse 
{

	private String message = null;
	private ArrayList <CancelAccountDT> cancelAccountList = new ArrayList<CancelAccountDT>();
	
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

	public ArrayList<CancelAccountDT> getCancelAccountList() {
		return cancelAccountList;
	}
	public void setCancelAccountList(ArrayList<CancelAccountDT> cancelAccountList) {
		this.cancelAccountList = cancelAccountList;
	}
	
	
	
		

}
