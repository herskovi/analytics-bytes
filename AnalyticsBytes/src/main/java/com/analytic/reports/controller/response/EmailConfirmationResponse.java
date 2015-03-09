/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.anaytic.reports.datatypes.EmailConfirmationDT;
import main.java.com.anaytic.reports.datatypes.EmailNotificationDT;
import main.java.com.anaytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class EmailConfirmationResponse extends BaseResponse 
{

	private String message = null;
	private boolean userActivated = false;
	private String email = "";
	private String userName = "";
	private String balance = "";

	
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
	public boolean isUserActivated() {
		return userActivated;
	}
	public void setUserActivated(boolean userActivated) {
		this.userActivated = userActivated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}			

}
