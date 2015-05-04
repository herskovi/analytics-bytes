/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.EmailConfirmationResponse;
import main.java.com.analytic.reports.datatypes.EmailConfirmationDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.dao.UserStatusDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.UserStatus;

/**
 * @author admin
 * Aug 31, 2014
 */
public class EmailConfirmationController extends BaseController
{
	private EmailConfirmationDT emailConfirmationDT = new EmailConfirmationDT();
	private UserStatus userStatus =null;
	private IResponse emailConfirmationResponse = newResponse();
	private static final Logger log = Logger.getLogger(EmailConfirmationController.class.getName());



	public EmailConfirmationController(EmailConfirmationDT emailConfirmationDT)
	{
		this.emailConfirmationDT = emailConfirmationDT;
	}


	@Override
	public void execute() throws Exception
	{		
		String uniqueKeyFromDB = UserStatusDAO.getUniquKeyInformationByEmail(emailConfirmationDT.getEmail());
		log.severe("EmailConfirmationController execute Unique Key From DB! " + uniqueKeyFromDB);
		log.severe("EmailConfirmationController execute Unique Key from Email " + emailConfirmationDT.getUniqueKey());

		if (emailConfirmationDT.getUniqueKey() != null && emailConfirmationDT.getUniqueKey().equals(uniqueKeyFromDB))
		{
			Customer customer = CustomerDAO.getCustomerInformationByUserID(emailConfirmationDT.getEmail());
			customer.setUserSts("2"); // User confirmed his mail and verified his mail.
			CustomerDAO.updateCustomerInDB(customer);
			
			((EmailConfirmationResponse)emailConfirmationResponse).setUserActivated(true);
			((EmailConfirmationResponse)emailConfirmationResponse).setUserName(customer.getName());
			if (customer.getBalance() != null)
			{
				((EmailConfirmationResponse)emailConfirmationResponse).setBalance(customer.getBalance());
			}else{
				((EmailConfirmationResponse)emailConfirmationResponse).setBalance("0");
			}

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
		return new EmailConfirmationResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.emailConfirmationResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		emailConfirmationResponse.setMessage(textMessage);	
	}



}
