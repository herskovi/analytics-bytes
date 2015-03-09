/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import main.java.com.analytic.reports.interfaces.IResponse;

/**
 * @author admin
 * Jul 23, 2014
 */
public abstract class BaseResponse implements IResponse
{
	
	protected String responseCode ="";
	protected String responseMessage ="";
	

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() 
	{
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) 
	{
		this.responseMessage = responseMessage;
	}
}
