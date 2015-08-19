/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.DailySmsResponse;
import main.java.com.analytic.reports.datatypes.GoalDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.dao.SmsHistoryDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.SmsHistory;
import main.java.com.analytic.reports.servlets.DailySmsServlet;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.ConvertUtils;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.CustomerHelper;
import main.java.com.analytic.reports.utils.DateUtils;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Goal;

/**
 * @author admin
 * Jul 21, 2014
 */
public class SendSmsController extends BaseController
{
	private String email = null;
	private boolean isLocalMode;
	private IResponse dailySmsControllerResponse = newResponse();
	private StringBuffer textMessage = new StringBuffer();
	private static final Logger log = Logger.getLogger(DailySmsServlet.class.getName());



	public SendSmsController(String email, boolean isLocalMode)
	{
		this.email = email;
		this.isLocalMode = isLocalMode;
	}


	@Override
	public void execute() throws Exception
	{
		log.severe("Start Execute at SendSmsController " );

		try
		{
			String userId = "";
			String startDate = "2014-01-01";
			String profileID="";
			String endDate = getCurrentDate();
			Calendar systemDateCal =  Calendar.getInstance();


			Customer cust = CustomerDAO.getCustomerInformationByUserID(email);
			log.severe("After Call to Customer DAO" );

			if (cust != null)
			{
				userId = cust.getUniqueAccountNumber(); //FIXME - Might be cust.getUniqueAccountNumber()
				CustomerHelper custUtils = new CustomerHelper(cust,userId);

				textMessage.append(custUtils.setIntroductionIntoMessageText());


				String accountId = custUtils.getAccountId();
				String webPropertId = custUtils.getWebPropertyId();
				profileID = custUtils.getProfileId();
				String goalId = custUtils.getGoalId();
				String goalName = custUtils.getGoalName();			
				GoalDT goalDT = new GoalDT(accountId,webPropertId,profileID, goalId,  goalName);


				//profileTimeZone = getProfileTimeZone(userId, cust);			
				Analytics analytic = null;
				try
				{		
					analytic = CredentialUtils.loadAnalytics(userId);
				} catch (Exception ex) 
				{
					// Token was not refreshed properly, call one more time for refresh token
					analytic = CredentialUtils.loadAnalytics(userId);
					profileID = custUtils.getProfileId();
				}
				try 
				{
					// Get Number of Visitors
					String[] metricsArr = getMetricsFromUserPreferences(cust);
					startDate = DateUtils.getYesterdayDateString(systemDateCal);
					endDate = startDate;	
					DateUtils dateUtils = new DateUtils(systemDateCal);
					String dateFormatToDisplay = dateUtils.getDateStringWithDayName();
					textMessage.append("On " + dateFormatToDisplay + ", ");
					getAnalyticData(startDate, endDate, profileID, analytic, metricsArr,goalDT);
					textMessage.append(custUtils.setClosureIntoMessageText());


					if (!isLocalMode) 
					{ 
						boolean isUSANumber = CustomerHelper.isUSAPhoneNumber(cust);
						log.severe("isUSANumber "  + isUSANumber);

						IController smsFlowController = getController(cust,isUSANumber);							
						smsFlowController.execute();
						SmsHistory smsHistory = new SmsHistory(DateUtils.getCurrentDateTime(), userId,
								ConvertUtils.convertListToStringSeperatedByComma(cust.getTelNoForSMS()),textMessage.toString());
						SmsHistoryDAO.insertNewSMS(smsHistory);

					}

				} catch (Exception ex) 
				{
					log.severe(" Exception at  SendSmsController " + userId + ex.getMessage());
					throw ex;
				}
				setResponse(textMessage.toString());


			}


		}catch (Exception ex) 
		{
			log.severe(" General Error execute  " + ex.getMessage());
			throw ex;
		}
	}


	private IController getController(Customer cust, boolean isUSANumber) 
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
	 * @Author: Moshe Herskovits
	 * @Date: Jun 12, 2014
	 * @Description: Get Analytic Data from Google Server
	 */

	public void getAnalyticData(String startDate, String endDate, String profileID, Analytics analytics, String[] metricsArr,GoalDT goalDT) throws IOException 
	{

		GaData gaData = AnalyticUtils.executeDataQuery(analytics, profileID, metricsArr, startDate, endDate, goalDT);
		if (gaData != null) 
		{
			textMessage.append(AnalyticUtils.printGaData(gaData,metricsArr,goalDT) + "\n");
		}

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
