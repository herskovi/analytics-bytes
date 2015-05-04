/**
 * 
 */
package main.java.com.analytic.reports.jdo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import main.java.com.analytic.reports.interfaces.IEntity;

/**
 * @author moshehe
 * May 10, 2014
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="false")
public class UserStatus  implements IEntity
{

	
	@PrimaryKey
	private String emailAddress;  
	@Persistent
	private String uniqueKey;
	
	/**
	 * @param emailAddress
	 * @param uniqueKey
	 */
	public UserStatus(String emailAddress, String uniqueKey) {
		super();
		this.emailAddress = emailAddress;
		this.uniqueKey = uniqueKey;
	}
	
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	
	
}
