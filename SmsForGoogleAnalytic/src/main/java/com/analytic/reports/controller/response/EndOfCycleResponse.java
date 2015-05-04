/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

/**
 * @author admin
 * Jul 23, 2014
 */
public class EndOfCycleResponse extends BaseResponse 
{

	private String message = null;
	
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
	@Override
	public void setMessage(String message) 
	{
		this.message = message;
	}
	

}
