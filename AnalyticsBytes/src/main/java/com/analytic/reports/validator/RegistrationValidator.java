/**
 * 
 */
package main.java.com.analytic.reports.validator;


import org.apache.commons.lang3.StringUtils;

import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.model.Customer;
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
			throw new Exception("Telephone Number cannot be empty");	
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
		if (password != null && !password.equals(confirmPassword) )
		{
			throw new Exception("Password and Confirm Password must be identical");
		}

	}


}
