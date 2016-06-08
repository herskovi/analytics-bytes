/**
 * 
 */
package main.java.com.analytic.reports.validator;


import org.apache.commons.lang3.StringUtils;

import main.java.com.analytic.reports.exceptions.AppException;
import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.CustomerHelper;
import main.java.com.analytic.reports.utils.consts.ErrorMessagesConsts;
import main.java.com.analytic.reports.utils.consts.ValidationConsts;


/**
 * @author admin
 * Aug 14, 2014
 */
public class RegistrationValidator extends BaseValidator implements IValidator 
{
	private Customer cust =null;
	private String confirmPassword ="";

	/**
	 * @param cust
	 */

	public RegistrationValidator(Customer cust, String confirmPassword) 
	{
		super();
		this.cust = cust;
		this.confirmPassword = confirmPassword;
	}

	public void validate() throws Exception 
	{
		validateMobileNumberIsNotEmpty(cust.getTelephoneNumber());
		validatePassword(cust.getPassword());
		//validateMobileNumberIsNotAlreadyExists(cust.getTelephoneNumber(),cust.getEmailAddress()); - FIXME - Remove this comment later on.

	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Feb 24, 2015
	 *@Description:
	 */
	private void validateMobileNumberIsNotAlreadyExists(String telephoneNumber, String emailAddress) throws Exception
	{
		if (CustomerHelper.isCustomerAlreadyExistsInDB(telephoneNumber,emailAddress))
		{
			throw new AppException(ErrorMessagesConsts.TELEPHONE_NUMBER_ALREADY_EXISTS);	
		};
		
	}

	/**
	 *@param telephoneNumber 
	 * @throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Dec 1, 2014
	 *@Description: Validate Mobile Number Is Not Empty
	 */
	private void validateMobileNumberIsNotEmpty(String telephoneNumber) throws Exception 
	{
		
		if (StringUtils.isEmpty(telephoneNumber))
		{
			throw new AppException(ErrorMessagesConsts.TELEPHONE_CANNOT_BE_EMPTY);	
		}		
	}

	public void validatePassword(String password) throws Exception
	{
		
		validatePasswordIsAtLeastSixCharecter(password);
		validatePasswordEqualsToConfirmPassword(password);

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
			throw new AppException(ErrorMessagesConsts.MIN_SIX_CHARACTERS);
		}

	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 31, 2014
	 *@Description: Validate Balance Is Greater Than Zero
	 */
	private void validatePasswordEqualsToConfirmPassword(String password) throws Exception
	{
		if (password != null && !password.equals(confirmPassword) )
		{
			throw new AppException(ErrorMessagesConsts.PASSWORD_AND_CONFIRM_PASSWORD_MUST_BE_IDENTICAL);
		}

	}


}
