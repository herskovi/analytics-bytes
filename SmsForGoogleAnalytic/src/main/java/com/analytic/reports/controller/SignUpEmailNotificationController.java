/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.EmailNotificationResponse;
import main.java.com.analytic.reports.controller.response.UserDashboardResponse;
import main.java.com.analytic.reports.datatypes.EmailNotificationDT;
import main.java.com.analytic.reports.datatypes.SupportDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.dao.SupportDAO;
import main.java.com.analytic.reports.jdo.dao.UserStatusDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.Support;
import main.java.com.analytic.reports.jdo.model.UserStatus;
import main.java.com.analytic.reports.utils.EmailNotificationUtils;

/**
 * @author admin
 * Aug 31, 2014
 */
public class SignUpEmailNotificationController extends BaseController
{
	private EmailNotificationDT emailNotificationDT = new EmailNotificationDT();
	private UserStatus userStatus =null;
	private IResponse emailNotificationResponse = newResponse();
	private static final Logger log = Logger.getLogger(SignUpEmailNotificationController.class.getName());



	public SignUpEmailNotificationController(EmailNotificationDT emailNotificationDT)
	{
		this.emailNotificationDT = emailNotificationDT;
	}


	@Override
	public void execute() throws Exception
	{
		Customer cust = CustomerDAO.getCustomerInformationByUserID(emailNotificationDT.getEmail());
		if (cust !=null)
		{
			String customerName = cust.getName();
			EmailNotificationUtils.sendSigningUpEmailNotification(emailNotificationDT.getEmail(), customerName, emailNotificationDT.getUniqueKey());
			//update User Status table with new generated
			UserStatus userStatus = new UserStatus(emailNotificationDT.getEmail(),emailNotificationDT.getUniqueKey());
			UserStatusDAO.insertOrUpdateUserStatus(userStatus);

		}
	}


//
//	/**
//	 *@Author:      Moshe Herskovits
//	 *@Date:        Aug 30, 2014
//	 *@Description: Convert From Support DT to support
//	 */
//	private Support convertToSupportModel(SupportDT supportDT) 
//	{
//		support = new Support();
//		support.setEmailAddress(supportDT.getEmail());
//		support.setFirstName(supportDT.getFirstName());
//		support.setLastName(supportDT.getLastName());
//		support.setCompanyName(supportDT.getCompany());
//		support.setDescription(supportDT.getDescription());
//		return support;
//	}


	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new EmailNotificationResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.emailNotificationResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		emailNotificationResponse.setMessage(textMessage);	
	}



}
