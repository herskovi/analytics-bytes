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
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.dao.SmsHistoryDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.CustomerAnalyticInfo;
import main.java.com.analytic.reports.jdo.model.SmsHistory;
import main.java.com.analytic.reports.servlets.DailySmsServlet;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.ConvertUtils;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.CustomerUtils;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.anaytic.reports.datatypes.GoalDT;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Goal;

/**
 * @author admin
 * Jul 21, 2014
 */
public class DailySmsController extends BaseController
{
	private HttpServletRequest req = null;
	private IResponse dailySmsControllerResponse = newResponse();
	private StringBuffer textMessage = new StringBuffer();
	private static final Logger log = Logger.getLogger(DailySmsServlet.class.getName());





	public DailySmsController(HttpServletRequest req)
	{
		this.req = req;
	}


	@Override
	public void execute() throws Exception
	{
		log.info("Start Execute at DailySmsController " );
		
		try
		{
				
			boolean isLocalMode = URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
			// Get All Customers From DB
			//TODO - Open Connection with  DB
			List<Customer> customerList = CustomerDAO.getCustomerList();
			scanAllCustomersAndSendSMS(isLocalMode, customerList);// End of While
			//TODO - Close Connection with  DB
		}catch (Exception ex) 
		{
			log.severe(" General Error execute  " + ex.getMessage());
			throw ex;
		}
	}


	private void scanAllCustomersAndSendSMS(boolean isLocalMode,
			List<Customer> customerList) throws IOException, Exception 
	{
		String refreshToken = null;
		String userId = "";
		String startDate = "2014-01-01";
		String profileID="";
		String profileName="";
		String userTimeZone="";
		String endDate = getCurrentDate();
		Calendar systemDateCal =  Calendar.getInstance();
	
		for (Customer cust : customerList) 
		{
			
			try
			{
			CustomerUtils custUtils = new CustomerUtils(cust,cust.getUserId());
			
			systemDateCal =  Calendar.getInstance();
			log.severe("Start with Customer " + cust.getName() + " @@@email@@@ " + cust.getEmailAddress());
			textMessage.delete(0, textMessage.length());
			textMessage.append(custUtils.setIntroductionIntoMessageText());
			
			String accountId = custUtils.getAccountId();
			String webPropertId = custUtils.getWebPropertyId();
			profileID = custUtils.getProfileId();
			String goalId = custUtils.getGoalId();
			String goalName = custUtils.getGoalName();			
			GoalDT goalDT = new GoalDT(accountId,webPropertId,profileID, goalId,  goalName);

			
			int hourOfTimeToSendSmsThatWasRequestedByUser = -1;
			refreshToken = cust.getRefreshToken();
			log.severe("before getUserTimeZone" );
			
			//FIXME - Change to Juda timezone
			//Define What is the hour in the  user Time Zone 
			userTimeZone = getUserTimeZone(cust);			
			int currentUserTimeInUserTimeZone = getCurentTimeInUserTimeZone(userTimeZone, systemDateCal);
			
			log.severe("currentUserTimeInUserTimeZone XXXX!!!  " + currentUserTimeInUserTimeZone);


			userId = cust.getUserId();
			profileID = getProfileId(userId, cust);
			log.severe("profileID 2.5,2.5,2.5!!!  " + profileID);

			profileName = getProfileName(userId, cust);
			//profileTimeZone = getProfileTimeZone(userId, cust);
			ArrayList<CustomerAnalyticInfo> customerAnalyticList =   cust.getCustomerAnalyticList();
			if(customerAnalyticList != null && customerAnalyticList.size() > 0)
			{
				String timeToSendSms = cust.getCustomerAnalyticList().get(0).getTimeToSendSMS();
				hourOfTimeToSendSmsThatWasRequestedByUser = Integer.parseInt(timeToSendSms.substring(0,2));
			}
			
			log.severe("currentUserTimeInUserTimeZone 333!!!  " + currentUserTimeInUserTimeZone);
			log.severe("hourOfTimeToSendSmsThatWasRequestedByUser 444!!!  " + hourOfTimeToSendSmsThatWasRequestedByUser);
			log.severe("profileID 5555!!!  " + profileID);


			if (currentUserTimeInUserTimeZone == hourOfTimeToSendSmsThatWasRequestedByUser)
			{
				Analytics analytic = null;
					try
					{		
						analytic = CredentialUtils.loadAnalytics(userId);
					} catch (Exception ex) 
					{
						// Token was not refreshed properly, call one more time for
						// refresh token
						analytic = CredentialUtils.loadAnalytics(userId);
						profileID = getProfileId(userId, cust);
						log.severe("profileID 5555EXCEPTION - Exception!!!  " + profileID);
					}
					
					try 
					{
						// Get Number of Visitors
						String[] metricsArr = getMetricsFromUserPreferences(cust);
						//startDate = DateUtils.getYesterdayDateString(profileTimeZone,userTimeZone ); 
						startDate = DateUtils.getYesterdayDateString(systemDateCal);
						endDate = startDate;
						String dateFormatToDisplay =DateUtils.getDateStringWithDayName(systemDateCal);

						textMessage.append(    profileName +  " is here. \nOn " + dateFormatToDisplay + ", ");

						getAnalyticData(startDate, endDate, profileID, analytic, metricsArr,goalDT);
						 

						if (!isLocalMode) 
						{ 
							boolean isUSANumber = CustomerUtils.isUSAPhoneNumber(cust);
							IController smsFlowController = getController(cust,isUSANumber);							
							smsFlowController.execute();
							SmsHistory smsHistory = new SmsHistory(DateUtils.getCurrentDateTime(),
									userId,
									ConvertUtils.convertListToStringSeperatedByComma(cust.getTelNoForSMS()),textMessage.toString());
							SmsHistoryDAO.insertNewSMS(smsHistory);
						}

					} catch (Exception ex) {
						log.severe(" cust.getCustomerAnalyticList().get(0) was not defined for this user   " + userId);
						throw ex;
					}
					setResponse(textMessage.toString());
			}
			}catch(Exception ex)
			{
				log.severe(" customer   " + cust.getEmailAddress() + " email " + cust.getName() + "FAILED***!!!! "+ ex.getMessage());
			}

		}
	}
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 21, 2014
	 *@Description:
	 */

	private IController getController(Customer cust,boolean isUSANumber) 
	{
		if (!isUSANumber)
		{
			return new NexmoSmsController(cust, textMessage.toString());
		}else{
			return new USPreApprovedShortCodesNexmoSmsController(cust, textMessage.toString());

		}
	}
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 2, 2014
	 *@Description: Get Current Time In User TimeZone
	 */

	private int getCurentTimeInUserTimeZone(String userTimeZone, Calendar systemDateCal) 
	{
		TimeZone tz;
		try
		{
			tz = TimeZone.getTimeZone(userTimeZone);
		}catch (Exception ex) 
		{
			log.severe("Error in getting Time Zone" + ex.getMessage());
			tz=TimeZone.getDefault();
		}

		log.severe("after Time Zone: " + tz.getDisplayName());

		systemDateCal.setTimeZone(tz);
		int currentUserTimeInUserTimeZone = systemDateCal.get(Calendar.HOUR_OF_DAY);
		return currentUserTimeInUserTimeZone;
	}
		
		/**
		 * 
		 *@Author:      Moshe Herskovits
		 *@Date:        Sep 2, 2014
		 *@Description: Set Prefix of the SMS 
		 */
	
	private void setIntroductionIntoMessageText(Customer cust) 
	{
		textMessage.delete(0, textMessage.length());
		textMessage.append("Hello, " + cust.getName() + "\n");
		textMessage.append("Your data for ");
	}



	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 12, 2014
	 * @Description: Get Analytic Data from Google Server
	 */

	public void getAnalyticData(String startDate, String endDate, String profileID, Analytics analytics, String[] metricsArr,GoalDT goalDT) throws IOException 
	{

		GaData gaData = AnalyticUtils.executeDataQuery(analytics, profileID, metricsArr, startDate, endDate,goalDT);
		if (gaData != null) 
		{
			textMessage.append(AnalyticUtils.printGaData(gaData,metricsArr,goalDT) + "\n");
		}

	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 12, 2014
	 * @Description: Return Profile ID which was store in Customer DB by user
	 *               when he enrolled into the system
	 */

	public String getProfileId(String userId, Customer cust) 
	{
		//TODO - Change to get profile Id not from the first row
		String profileId = "";
		try {
			return cust.getCustomerAnalyticList().get(0).getProfileId();
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0) was not defined for this user " + userId);
		}
		return profileId;
	}

	/**
	 * 
	 * @Author: 		Moshe Herskovits
	 * @Date: 			Jun 12, 2014
	 * @Description: 	Return Profile ID which was store in Customer DB by user
	 *               	when he enrolled into the system
	 */

	public String getProfileName(String userId, Customer cust) 
	{
		//TODO - Change to get profile Name not from the first row
		String profileId = "";
		try {
			return cust.getCustomerAnalyticList().get(0).getProfileName();
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0) was not defined for this user " + userId);
		}
		return profileId;
	}

	/**
	 * 
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Return Profile Time Zone which was store in Customer DB derived from his google analytic profile.
	 */

	public String getProfileTimeZone(String userId, Customer cust) 
	{
		String profileTimeZone = "";
		try {
			return cust.getCustomerAnalyticList().get(0).getTimeZone();
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0) was not defined for this user "
					+ userId);

		}
		return profileTimeZone;
	}

	/**
	 * 
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 12, 2014
	 * @Description: Return User Time Zone which was store in Customer DB by user
	 *               when user enrolled into the system
	 */

	public String getUserTimeZone(Customer cust) 
	{
		String userTimeZone = "";
		try {
			return cust.getTimeZone();
		} catch (Exception ex) {
			log.severe(" DailySmsController getUserTimeZone: customer was not defined for this user ");

		}
		log.severe(" getUserTimeZone: " + userTimeZone); 
		return userTimeZone;
		
	}


	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 12, 2014
	 * @Description: Get Date of Today according to system Date
	 */

	public String getCurrentDate() 
	{
		Date myDate = new Date();
		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dmy = dmyFormat.format(myDate);
		String endDate = dmy;
		return endDate;
	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: Jun 1, 2014
	 * @Description: Get Metrics From User Preferences
	 */

	private String[] getMetricsFromUserPreferences(Customer cust) 
	{
		Object[] objectList = cust.getMetrics().toArray();
		return Arrays.copyOf(objectList, objectList.length, String[].class);
	}



	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new DailySmsResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.dailySmsControllerResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		dailySmsControllerResponse.setMessage(textMessage);	
	}



}
