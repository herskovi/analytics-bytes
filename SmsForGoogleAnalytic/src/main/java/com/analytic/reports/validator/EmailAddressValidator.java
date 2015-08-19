/**
 * 
 */
package main.java.com.analytic.reports.validator;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;

import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.consts.ValidationConsts;


/**
 * @author admin
 * Aug 14, 2014
 */
public class EmailAddressValidator extends BaseValidator implements IValidator 
{
	private String emailAddress ="";

	/**
	 * @param cust
	 */

	public EmailAddressValidator(String emailAddress) 
	{
		this.emailAddress = emailAddress;
	}

	public void validate() throws Exception 
	{
		validateEmailAddressIsNotEmpty(this.emailAddress);
		validateEmailAddress(this.emailAddress);
		validateEmailAddressIsNotAlreadyExists(this.emailAddress);

	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Feb 24, 2015
	 *@Description:
	 */
	private void validateEmailAddressIsNotAlreadyExists(String emailAddress) throws Exception
	{
		
		
	}

	/**
	 *@param telephoneNumber 
	 * @throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Dec 1, 2014
	 *@Description: Validate Mobile Number Is Not Empty
	 */
	private void validateEmailAddressIsNotEmpty(String emailAddress) throws Exception 
	{
		
		if (StringUtils.isEmpty(emailAddress))
		{
			throw new Exception("Email Address cannot be empty");	
		}		
	}

	public void validateEmailAddress(String emailAddress) throws Exception
	{
		try {
	      InternetAddress emailAddr = new InternetAddress(emailAddress);
	      emailAddr.validate();
		}catch (AddressException ex) {
			throw new Exception(ex.getMessage());
	    }

	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: Validate Balance Is Greater Than Zero
	 */
	private void validatePasswordIsAtLeastSixCharecter(String password) throws Exception
	{
		if (password != null && password.length() < ValidationConsts.MINIMUM_PASSWORD_LENGTH )
		{
			throw new Exception("Min 6 Charts");
		}

	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: Validate Balance Is Greater Than Zero
	 */
	private void validatePasswordEqualsToConfirmPassword(String password) throws Exception
	{
		if (password != null && !password.equals(emailAddress) )
		{
			throw new Exception("Password and Confirm Password must be identical");
		}

	}


}
