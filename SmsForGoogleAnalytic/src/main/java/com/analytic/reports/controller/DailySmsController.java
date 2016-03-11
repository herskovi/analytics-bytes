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

import main.java.com.analytic.reports.alerts.controller.AlertReportController;
import main.java.com.analytic.reports.controller.response.DailySmsResponse;
import main.java.com.analytic.reports.datatypes.GoalDT;
import main.java.com.analytic.reports.interfaces.IAlertsController;
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
import main.java.com.analytic.reports.utils.CustomerHelper;
import main.java.com.analytic.reports.utils.DateUtils;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.RegistrationConsts;

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
			List<Customer> customerList = CustomerDAO.getCustomerList();
			scanAllCustomersAndSendSMS(isLocalMode, customerList);// End of While
		}catch (Exception ex) 
		{
			log.severe(" General Error execute  " + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 13, 2015
	 *@Description: Scan All Customers
	 */

	private void scanAllCustomersAndSendSMS(boolean isLocalMode, List<Customer> customerList) throws IOException, Exception 
	{
		String userId = "";
		String startDate = "2014-01-01";
		String profileID="";
		String userTimeZone="";
		String endDate = getCurrentDate();
	
		for (Customer cust : customerList) 
		{
			
			try
			{
			Calendar systemDateCal =  null;
			CustomerHelper custUtils = new CustomerHelper(cust,cust.getUniqueAccountNumber());
			
			log.severe("Start with Customer " + cust.getName() + " @@@email@@@ " + cust.getEmailAddress());
			setPrefixForMessageText(custUtils);
			GoalDT goalDT = prepareGoalDataType(custUtils);				
			int hourOfTimeToSendSmsThatWasRequestedByUser = -1;
			userTimeZone = getUserTimeZone(cust);	
			systemDateCal = setUserTimeZone(userTimeZone);
			int currentUserTimeInUserTimeZone = getCurentTimeInUserTimeZone(systemDateCal);

			//getUserIdBasedOnPKInCredentialStore
			//From Next Release all Users ID
			userId = cust.getUniqueAccountNumber();
			userId = changeUserIdInCaseCredentialDoesNotExistWithUAN(userId, cust);

				
			profileID = getProfileId(userId, cust);
			
			logInformation(userId, profileID, currentUserTimeInUserTimeZone);


			//profileTimeZone = getProfileTimeZone(userId, cust);
			ArrayList<CustomerAnalyticInfo> customerAnalyticList =   cust.getCustomerAnalyticList();
			if(customerAnalyticList != null && customerAnalyticList.size() > 0)
			{
				String timeToSendSms = cust.getCustomerAnalyticList().get(0).getTimeToSendSMS();
				hourOfTimeToSendSmsThatWasRequestedByUser = Integer.parseInt(timeToSendSms.substring(0,2));
			}
			
			
			if (currentUserTimeInUserTimeZone == hourOfTimeToSendSmsThatWasRequestedByUser)
			{
				Analytics analytic = getAnalyticsCredential(userId);
					
					try 
					{
						// Get Number of Visitors
						String[] metricsArr = getMetricsFromUserPreferences(cust);						
						systemDateCal.add(Calendar.DATE,-1);
						DateUtils dateUtils = new DateUtils(systemDateCal);
						startDate = dateUtils.getYesterdayDateString();
						//startDate = DateUtils.getYesterdayDateString(systemDateCal);//FIXME - Change to yesterday 
						String dateFormatToDisplay = dateUtils.getDateStringWithDayName();
						endDate = startDate;
						textMessage.append("On " + dateFormatToDisplay + ", ");
						boolean dataWasRecievedFromGA = getAnalyticData(startDate, endDate, profileID, analytic, metricsArr,goalDT);
						
//						IAlertsController iAlertsController = getAlertController(cust);
//						iAlertsController.execute();
//						
//						
//						textMessage.append(custUtils.setClosureIntoMessageText());
						//IAlert()
						
				


						if (!isLocalMode && dataWasRecievedFromGA) 
						{ 
							boolean isUSANumber = CustomerHelper.isUSAPhoneNumber(cust);
							IController smsFlowController = getController(cust,isUSANumber);							
							smsFlowController.execute();
							SmsHistory smsHistory = new SmsHistory(DateUtils.getCurrentDateTime(),
									userId,
									ConvertUtils.convertListToStringSeperatedByComma(cust.getTelNoForSMS()),textMessage.toString());
							SmsHistoryDAO.insertNewSMS(smsHistory);
						}else if (!dataWasRecievedFromGA)
						{
							log.severe("GA DATA THROW AN ERROR FOR THIS CUSTOMER");
							//FIXME - Insert Into New Table
						}

					} catch (Exception ex) {
						log.severe(" scanAllCustomersAndSendSMS throw an Exception    " + userId);
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
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 9, 2015
	 *@Description:
	 */
	private IAlertsController getAlertController(Customer cust) 
	{
		return new AlertReportController(cust);
	}


	private Analytics getAnalyticsCredential(String userId) throws IOException {
		Analytics analytic = null;
			try
			{		
				analytic = CredentialUtils.loadAnalytics(userId);
			} catch (Exception ex) 
			{
				// Token was not refreshed properly, call one more time fo refresh token
				analytic = CredentialUtils.loadAnalytics(userId);
			}
		return analytic;
	}


	private void logInformation(String userId, String profileID,
			int currentUserTimeInUserTimeZone) {
		log.severe("currentUserTimeInUserTimeZone XXXX!!!  " + currentUserTimeInUserTimeZone);
		log.severe("userId 2.0, 2.0 ,2.0!!!  " + userId);
		log.severe("profileID 2.5,2.5,2.5!!!  " + profileID);
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 25, 2015
	 *@Description: Set Prefix for Message Text
	 */

	private void setPrefixForMessageText(CustomerHelper custUtils) {
		textMessage.delete(0, textMessage.length());
		textMessage.append(custUtils.setIntroductionIntoMessageText());
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 22, 2015
	 *@Description: User Id should be unique Account Number and not email ID.
	 *				changeUserIdInCaseCredentialDoesNotExistWithUAN
	 */
	private String changeUserIdInCaseCredentialDoesNotExistWithUAN(String userId, Customer cust)
			throws IOException {
		boolean checkIfUserExistsInDataStore = CredentialUtils.checkIfUserExistInDataStore(userId);
		if (!checkIfUserExistsInDataStore)
		{
			userId = cust.getUserId(); 
		}
		return userId;
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 22, 2015
	 *@Description: Prepare Goal Data Type
	 */
	
	private GoalDT prepareGoalDataType(CustomerHelper custUtils) 
	{
		String profileID;
		String accountId = custUtils.getAccountId();
		String webPropertId = custUtils.getWebPropertyId();
		profileID = custUtils.getProfileId();
		String goalId = custUtils.getGoalId();
		String goalName = custUtils.getGoalName();			
		GoalDT goalDT = new GoalDT(accountId,webPropertId,profileID, goalId,  goalName);
		return goalDT;
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

	public int getCurentTimeInUserTimeZone(Calendar systemDateCal) 
	{
		int currentUserTimeInUserTimeZone = systemDateCal.get(Calendar.HOUR_OF_DAY);
		return currentUserTimeInUserTimeZone;
	}
	
	
	
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 24, 2015
	 *@Description: Set User TimeZone According to user original selection.
	 */

	public Calendar setUserTimeZone(String userTimeZone) 
	{
		TimeZone tz = getTimeZoneObjByTimeZone(userTimeZone);
		log.severe("after Time Zone: " + tz.getDisplayName());
		Calendar systemDateCal = Calendar.getInstance(tz);
		return systemDateCal;
		
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Apr 25, 2015
	 *@Description: Get Time Zone By Input. In case Time Zone is not valid reuturn default Time Zone by System.
	 */
	public TimeZone getTimeZoneObjByTimeZone(String userTimeZone) {
		TimeZone tz;
		try
		{
			tz = TimeZone.getTimeZone(userTimeZone);
		}catch (Exception ex) 
		{
			log.severe("Error in getting Time Zone" + ex.getMessage());
			tz=TimeZone.getDefault();
		}
		return tz;
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
		
	}



	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 12, 2014
	 * @Description: Get Analytic Data from Google Server. Return True if no exception occured
	 */

	public boolean getAnalyticData(String startDate, String endDate, String profileID, Analytics analytics, String[] metricsArr,GoalDT goalDT) throws IOException 
	{

		GaData gaData = AnalyticUtils.executeDataQuery(analytics, profileID, metricsArr, startDate, endDate);
		if (gaData != null) 
		{
			textMessage.append(AnalyticUtils.printGaData(gaData,metricsArr,goalDT) + "\n");
			return true;
		}else{
			return false;
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
		String userTimeZone = RegistrationConsts.DEFAULT_TIME_ZONE;
		try {
			return cust.getTimeZone();
		} catch (Exception ex) {
			log.severe(" DailySmsController getUserTimeZone: Time Zone was not defined for this user ");
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
