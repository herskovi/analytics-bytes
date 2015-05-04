/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.analytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class SupportResponse extends BaseResponse 
{

	private String message = null;
	private ArrayList <UserDashboardDT> userDashboardList = new ArrayList<UserDashboardDT>();
	
	
	
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
	
	public ArrayList<UserDashboardDT> getUserDashboardList() {
		return userDashboardList;
	}
	public void setUserDashboardList(ArrayList<UserDashboardDT> userDashboardList) {
		this.userDashboardList = userDashboardList;
	}
	

}
