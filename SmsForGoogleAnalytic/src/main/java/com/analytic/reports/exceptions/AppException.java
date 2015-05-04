/**
 * 
 */
package main.java.com.analytic.reports.exceptions;

import main.java.com.analytic.reports.utils.consts.ErrorMessagesConsts;

/**
 * @author admin
 * Apr 24, 2015
 */
public class AppException extends Exception
{
	
	private String messageCode ="";
	private String messageText ="";
    
	
	/**
	 * @param messageText
	 */
	public AppException(String messageText) {
		super();
		this.messageCode = ErrorMessagesConsts.DEFAULT_ERROR_MESSAGE_CODE;
		this.messageText = messageText;
	}
	/**
	 * @param messageCode
	 * @param messageText
	 */
	public AppException(String messageCode, String messageText) {
		super();
		this.messageCode = messageCode;
		this.messageText = messageText;
	}
	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}
	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}
	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

}
