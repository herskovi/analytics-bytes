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
public class Support  implements IEntity
{
	@PrimaryKey
	private String emailAddress;  
	@Persistent
	private String firstName;
	@Persistent
	private String lastName;  
	@Persistent
	private String companyName; 
	@Persistent
	private String description;
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

   


}
