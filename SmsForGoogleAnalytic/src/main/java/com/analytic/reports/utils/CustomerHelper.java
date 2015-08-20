/**
 * 
 */
package main.java.com.analytic.reports.utils;

import java.util.logging.Logger;

import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.servlets.DailySmsServlet;
import main.java.com.analytic.reports.utils.consts.ValidationConsts;

/**
 * @author admin
 * Dec 6, 2014
 */
public class CustomerHelper 
{

	private static final Logger log = Logger.getLogger(CustomerHelper.class.getName());
	private Customer cust = null;
	private String userId = null;



	/**
	 * @param cust
	 * @param userId
	 */
	public CustomerHelper(Customer cust, String userId) 
	{
		this.setCust(cust);
		this.setUserId(userId);
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 2, 2014
	 *@Description: Set Prefix of the SMS 
	 */

	public String setIntroductionIntoMessageText() 
	{
		StringBuffer textMessage = new StringBuffer();
		String customerName = getCustomerName();
		textMessage.append(appendCustomerNameToMessageIfExists(customerName));
		return textMessage.toString();
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 20, 2015
	 *@Description:
	 */
	private String appendCustomerNameToMessageIfExists(String customerName) 
	{
		return ("").equals(customerName) ? "" : ("Hello" + customerName + ",\n");
	}

	public String getCustomerName() {
		String cutomerName= cust.getName()!=null ? " " + cust.getName() : "";
		return cutomerName;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 2, 2014
	 *@Description: Set Prefix of the SMS 
	 */

	public String setClosureIntoMessageText() 
	{
		StringBuffer textMessage = new StringBuffer();
		textMessage.append("to stop, email support@analyticsbytes.com");
		return textMessage.toString();

	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 6, 2014
	 *@Description:
	 */
	public  String getAccountId() 
	{
		String accountId = "";
		try {
			return cust.getCustomerAnalyticList().get(0).getAccountId();
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0).getAccountId() was not defined for this user " + userId);
		}
		return accountId;

	}


	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 6, 2014
	 *@Description:
	 */
	public  String getWebPropertyId() {

		String webPropertyId = "";
		try {
			return cust.getCustomerAnalyticList().get(0).getWebPropertyId();
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0).getWebPropertyId() was not defined for this user " + userId);
		}
		return webPropertyId;

	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 21, 2014
	 *@Description: Is Customer located at U.S.A
	 */
	public static boolean isCustomerAlreadyExistsInDB(String telephoneNumber, String emailAddress) 
	{
		return CustomerDAO.isTelephoneNumberAlreadyExistInSystem(telephoneNumber, emailAddress);
		
	}
		
		
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 21, 2014
	 *@Description: Is Customer located at U.S.A
	 */
	public static boolean isUSAPhoneNumber(Customer cust) 
	{

		boolean isUSAPhoneNumber = false;
		try {
			String countryCode = cust.getCountryCode();
			isUSAPhoneNumber = (ValidationConsts.USA_TELEPHONE_COUNTRY_CODE.equals(countryCode) ? true : false);
		} catch (Exception ex) {
			log.severe(" cust.getCountryCode was not defined for this user " );
		}
		return isUSAPhoneNumber;

	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 12, 2014
	 * @Description: Return Profile ID which was store in Customer DB by user
	 *               when he enrolled into the system
	 */

	public String getProfileId() 
	{
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

	public String getProfileName() 
	{
		String profileName = "";
		try {
			log.severe(" porfile Name UTF-8 " +cust.getCustomerAnalyticList().get(0).getProfileName() ); //FIXME - Remove this code after fixing the bug
			return cust.getCustomerAnalyticList().get(0).getProfileName();
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0) was not defined for this user " + userId);
		}
		return profileName;
	}

	/**
	 * 
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Return Profile Time Zone which was store in Customer DB derived from his google analytic profile.
	 */

	public String getProfileTimeZone() 
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

	public String getUserTimeZone() 
	{
		String userTimeZone = "";
		try {
			return cust.getTimeZone();
		} catch (Exception ex) {
			log.severe(" getUserTimeZone: customer was not defined for this user ");
		}
		return userTimeZone;
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 6, 2014
	 *@Description:
	 */
	public String getGoalId() {

		String goalId = "";
		try {
			return cust.getCustomerAnalyticList().get(0).getGoalId();
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0).getGoalId() was not defined for this user " + userId);
		}
		return goalId;

	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 6, 2014
	 *@Description:
	 */
	public String getGoalName() {

		String goalName = "";
		try {
			return cust.getCustomerAnalyticList().get(0).getGoalName();
		} catch (Exception ex) {
			log.severe(" cust.getCustomerAnalyticList().get(0).getGoalName() was not defined for this user " + userId);
		}
		return goalName;

	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 6, 2014
	 *@Description:
	 */
	public String getUserSts() {

		String usrSts = "";
		try {
			return cust.getUserSts();
		} catch (Exception ex) {
			log.severe(" cust.getUserSts() was not defined for this user " + usrSts);
		}
		return usrSts;

	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the cust
	 */
	public Customer getCust() {
		return cust;
	}

	/**
	 * @param cust the cust to set
	 */
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	

}
