/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.CancelAccountResponse;
import main.java.com.analytic.reports.datatypes.CancelAccountDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.dao.UserStatusDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.UserStatus;
import main.java.com.analytic.reports.utils.EmailNotificationUtils;
import main.java.com.analytic.reports.utils.consts.RegistrationConsts;

/**
 * @author admin
 * Aug 31, 2014
 */
public class CancelAccountController extends BaseController
{
	private CancelAccountDT cancelAccountDT = new CancelAccountDT();
	private IResponse cancelAccountResponse = newResponse();
	private static final Logger log = Logger.getLogger(CancelAccountController.class.getName());



	public CancelAccountController(CancelAccountDT cancelAccountDT)
	{
		this.cancelAccountDT = cancelAccountDT;
	}


	@Override
	public void execute() throws Exception
	{
		Customer cust = CustomerDAO.getCustomerInformationByUserID(cancelAccountDT.getEmail());
		if (cust !=null)
		{
			cust.setUserSts(RegistrationConsts.USER_IS_CANCELED);
			CustomerDAO.updateCustomerInDB(cust);
			String customerName = cust.getName();
			String userId = cust.getEmailAddress();
			log.severe("Customer Name " + cust.getName() + " was cancelled");
			EmailNotificationUtils.sendCancellationEmailNotification(userId, customerName, null);
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
		return new CancelAccountResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.cancelAccountResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		cancelAccountResponse.setMessage(textMessage);	
	}



}
