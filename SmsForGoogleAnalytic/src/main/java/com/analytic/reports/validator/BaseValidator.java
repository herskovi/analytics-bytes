/**
 * 
 */
package main.java.com.analytic.reports.validator;

import main.java.com.analytic.reports.interfaces.IValidator;

/**
 * @author admin
 * Jul 21, 2014
 */
public abstract class BaseValidator implements IValidator 
{
	protected boolean allValidationPassed = true; 

	public void validate() throws Exception
	{
		
	}
	
	public boolean isAllValidationPassed() {
		return allValidationPassed;
	}

	public void setAllValidationPassed(boolean allValidationPassed) {
		this.allValidationPassed = allValidationPassed;
	}


}
