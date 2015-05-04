/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import main.java.com.analytic.reports.controller.response.DailySmsResponse;
import main.java.com.analytic.reports.controller.response.UserDashboardResponse;
import main.java.com.analytic.reports.datatypes.UserDashboardDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.servlets.DailySmsServlet;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.URLUtils;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;

/**
 * @author admin
 * Jul 21, 2014
 */
public class UserDashboardController extends BaseController
{
	
	private String userName = null;
	private Customer cust =null;
	private IResponse userDashboardControllerResponse = newResponse();
	private static final Logger log = Logger.getLogger(UserDashboardController.class.getName());





	public UserDashboardController(String userName)
	{
		this.userName = userName;
	}


	@Override
	public void execute() throws Exception
	{


		// Get Customer From DB By User ID.
		 cust = CustomerDAO.getCustomerInformationByUserID(userName);
		 //Get Metrics from Customer Preference
		 List<String> metricsList = cust.getMetrics();
		 ArrayList <UserDashboardDT>  getUserDashboardList =  ((UserDashboardResponse)userDashboardControllerResponse).getUserDashboardList();
		 for ( String metricsListStr : metricsList)
		 {
			 UserDashboardDT userDashboardDT = new UserDashboardDT();
			// userDashboardDT.
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
		return new UserDashboardResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.userDashboardControllerResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		userDashboardControllerResponse.setMessage(textMessage);	
	}



}
