/**
 * 
 */
package main.java.com.analytic.reports.jdo.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Key;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Extension;


import main.java.com.analytic.reports.interfaces.IEntity;

/**
 * @author admin
 * May 10, 2014
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="true")
 public class SmsHistory  implements IEntity
{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String encodedKey;
	@Persistent 
	private String date;
	@Persistent
    private String email;
	@Persistent
	private String toNumber;
	@Persistent
	private String messageTxt;
	
	
	/**
	 * @param date
	 * @param email
	 * @param toNumber
	 * @param messageTxt
	 */
	public SmsHistory(String date, String email, String toNumber, String messageTxt) {
		super();
		this.date = date;
		this.email = email;
		this.toNumber = toNumber;
		this.messageTxt = messageTxt;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToNumber() {
		return toNumber;
	}
	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}
	public String getMessageTxt() {
		return messageTxt;
	}
	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt;
	}
	
	
	
	
	
}
